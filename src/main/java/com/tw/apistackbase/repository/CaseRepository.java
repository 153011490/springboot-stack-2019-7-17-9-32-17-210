package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CaseRepository extends JpaRepository<Case,String> {

    @Query(value = "select * from case order by time desc",nativeQuery = true)
    public List<Case> findAllByTime();

    public List<Case> findAllByName(String name);

}
