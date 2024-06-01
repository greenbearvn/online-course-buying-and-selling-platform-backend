package com.lession_service.lessionservice.service.impl;

import com.lession_service.lessionservice.entity.Lessions;
import com.lession_service.lessionservice.model.obj.Video;
import com.lession_service.lessionservice.model.req.LessionsReq;
import com.lession_service.lessionservice.model.res.LessionRes;
import com.lession_service.lessionservice.model.res.VideoRes;
import com.lession_service.lessionservice.repository.LessionsRepository;
import com.lession_service.lessionservice.rest.impl.HttpServiceImpl;
import com.lession_service.lessionservice.rest.inter.HttpService;
import com.lession_service.lessionservice.service.inter.LessionsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.file.WatchEvent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LessionsServiceImpl implements LessionsService {

    private final LessionsRepository lessionsRepository;

    private final HttpService httpService;

    @Override
    public List<Lessions> getLessionsPagination(int page, int size, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size,sort);
        List<Lessions> items =  lessionsRepository.findAll(pageable).getContent();
        return items;
    }


    // get list lession by course id
    public List<LessionRes> findAllLessionByCourseId(int courseId) {
        List<Lessions> lessions = lessionsRepository.findAllLessionByCourseId(courseId);
        List<LessionRes> lessionRes = lessions.stream()
                .map(lession ->{
                    List<VideoRes> videoRes = httpService.getVideosOfLession(lession.getLessionId()).collectList().block();
                    return LessionRes.fromLessionRes(lession, videoRes);
                } ).collect(Collectors.toList());
        return lessionRes;
    }

    @Override
    public List<Lessions> getAllLessions() {
        return lessionsRepository.findAll();
    }

    @Override
    public Lessions getLessionsById(int id) {
        Lessions item = lessionsRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        return item;
    }

    @Override
    public Lessions createLessions(LessionsReq LessionsReq) {
        Lessions newItem = Lessions.builder().lessionName(LessionsReq.getLessionName()).lessionDuration(LessionsReq.getLessionDuration()).courseId(LessionsReq.getCourseId()).build();
        return lessionsRepository.save(newItem);

    }

    @Override
    @Transactional
    public Lessions updateLessions(int id, LessionsReq lessionsReq) {
        Lessions existingLessions = lessionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lessions not found with id: " + id));

        // Update the attributes of the existingLessions object
        existingLessions.setLessionName(lessionsReq.getLessionName());
        existingLessions.setLessionDuration(lessionsReq.getLessionDuration());
        existingLessions.setCourseId(lessionsReq.getCourseId());

        // Save the updated entity
        return lessionsRepository.save(existingLessions);
    }


    @Override
    public boolean deleteLessions(int id) {
        Optional<Lessions> item = lessionsRepository.findById(id);
        if(item.isPresent()){
            lessionsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
