package com.courseplus.userservice.service.impl;

import com.courseplus.userservice.entity.User;
import com.courseplus.userservice.models.obj.Role;
import com.courseplus.userservice.models.req.LoginReq;
import com.courseplus.userservice.models.req.UserReq;
import com.courseplus.userservice.models.res.UserRes;
import com.courseplus.userservice.repository.UserRepository;
import com.courseplus.userservice.repository.inter.RedisRepo;
import com.courseplus.userservice.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private  final RedisRepo redisRepo;

    private final String KEY = "session_user";

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
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
    public boolean Login(LoginReq loginReq) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(loginReq.getEmail()));
        if(user.isPresent()){
            UserRes userRes = UserRes.builder()
                    .userId(user.get().getUserId())
                    .email(user.get().getEmail())
                    .userName(user.get().getUserName())
                    .role(user.get().getRole())
                    .build();

            redisRepo.addValueToMap(KEY, "user_current",userRes);
            return true;
        }
        return false;
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
}
