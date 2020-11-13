package com.ohajda.tsettask.repository;

import com.ohajda.tsettask.domain.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Integer> {


    @Query(value = "select max(id) from Release")
    Integer maxId();

}
