package com.ohajda.tsettask.repository;

import com.ohajda.tsettask.domain.ServiceVersion;
import com.ohajda.tsettask.domain.ServiceVersionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceVersionRepository extends JpaRepository<ServiceVersion, ServiceVersionId> {


    List<ServiceVersion> findAllByVersion(Integer version);

    @Query(value = "select max(version) from ServiceVersion")
    Integer maxVersion(String name);

    @Query("select new ServiceVersion(name,max(version)) from ServiceVersion " +
        "group by name")
    List<ServiceVersion> findLatestServices();
}
