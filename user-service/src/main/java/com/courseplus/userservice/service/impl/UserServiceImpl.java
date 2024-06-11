package com.courseplus.userservice.service.impl;

import com.courseplus.userservice.entity.User;
import com.courseplus.userservice.models.obj.Role;
import com.courseplus.userservice.models.req.LoginReq;
import com.courseplus.userservice.models.req.UserReq;
import com.courseplus.userservice.models.res.Profile;
import com.courseplus.userservice.models.res.ProfileUserRes;
import com.courseplus.userservice.models.res.UserRes;
import com.courseplus.userservice.repository.UserRepository;
import com.courseplus.userservice.repository.inter.RedisRepo;
import com.courseplus.userservice.rest.inter.ProfileRestService;
import com.courseplus.userservice.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private  final RedisRepo redisRepo;
    private final ProfileRestService profileRestService;

    private final String KEY = "session_user";

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserRes findUserById(int id) {

        Optional<User> user = Optional.of(userRepository.findById(id).orElseThrow());
        UserRes userRes =  new UserRes();
        userRes.setUserName(user.get().getUserName());
        userRes.setEmail(user.get().getEmail());
        userRes.setRole(user.get().getRole());

        return userRes;


    }

    @Override
    public Mono<ProfileUserRes> profileUserDetail(int id) {

        Optional<User> user = Optional.of(userRepository.findById(id).orElseThrow());
        UserRes userRes =  new UserRes();
        userRes.setUserName(user.get().getUserName());
        userRes.setEmail(user.get().getEmail());
        userRes.setRole(user.get().getRole());

        Mono<UserRes> courseMono = Mono.just(userRes);
        Mono<Profile> profileMono = profileRestService.getDetailProfileOfUser(id);
        return Mono.zip(courseMono, profileMono)
                .map(tuple -> {
                    ProfileUserRes profileUserRes = new ProfileUserRes();
                    profileUserRes.setUserRes(tuple.getT1());
                    profileUserRes.setProfile(tuple.getT2());

                    return profileUserRes;
                });
    }

    @Override
    public User createUser(UserReq userReq) {
        User newUser = new User();
        newUser.setEmail(userReq.getEmail());
        newUser.setUserName(userReq.getUserName());
        newUser.setRole(Role.USER);
        newUser.setPassword( passwordEncoder.encode(userReq.getPassword()));

        return userRepository.save(newUser);
    }

    @Override
    public UserRes Login(LoginReq loginReq) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(loginReq.getEmail()));
        if(user.isPresent()){
            UserRes userRes = UserRes.builder()
                    .userId(user.get().getUserId())
                    .email(user.get().getEmail())
                    .userName(user.get().getUserName())
                    .role(user.get().getRole())
                    .build();

            redisRepo.addValueToMap(KEY, "user_current",userRes);



            return userRes;
        }
        return  new UserRes();
    }

    @Override
    public boolean logOut() {
        redisRepo.removeValueFromMap(KEY,"user_currennt");
        return redisRepo.removeValueFromMap(KEY,"user_current");
        
    }

    @Override
    public Map getCurrentUser() {
        return redisRepo.getMapData(KEY);
    }

    @Override
    public List<UserRes> getAll() {
        List<UserRes> userRes = userRepository.findAll().stream()
                .map(userEntity -> UserRes.userResBuilder(userEntity))
                .collect(Collectors.toList());

        return userRes ;
    }

    @Override
    public boolean update(int id,User userReq) {

        User user = userRepository.findByEmail(userReq.getEmail());
        user.setUserId(id);
        user.setUserName(userReq.getUserName());
        user.setEmail(userReq.getEmail());
        user.setRole(userReq.getRole());

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean delete(int id) {

        try{
            userRepository.deleteById(id);
            return true;
        }
        catch (Exception e){

        }
        return false;
    }
}
