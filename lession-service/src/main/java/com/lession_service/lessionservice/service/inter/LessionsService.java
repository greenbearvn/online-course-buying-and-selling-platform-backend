package com.lession_service.lessionservice.service.inter;

import com.lession_service.lessionservice.entity.Lessions;
import com.lession_service.lessionservice.model.req.LessionsReq;
import com.lession_service.lessionservice.model.res.LessionRes;

import java.util.List;

public interface LessionsService {
    public List<Lessions> getLessionsPagination(int page, int size, String sortBy);

    public List<Lessions> getAllLessions();

    public List<LessionRes> findAllLessionByCourseId(int courseId);

    public Lessions getLessionsById(int id);

    public Lessions createLessions(LessionsReq LessionsReq);

    public Lessions updateLessions(int id,LessionsReq LessionsReq);

    public boolean deleteLessions(int id);
}
