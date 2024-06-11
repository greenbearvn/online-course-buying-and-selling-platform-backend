package com.javabackend.testedservice.repository;

import com.javabackend.testedservice.entity.Tested;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestedRepository extends JpaRepository<Tested , Integer> {

    public List<Tested> findAllByUserId(int id);
}
