package com.ohajda.tsettask.service;

import com.ohajda.tsettask.domain.ServiceVersion;

import java.util.List;

public interface ReleaseManagerService {

    /**
     * Checks if serviceVersionNumber changed, if yes increases SystemVersionNumber and takes care
     * that the new SystemVersionNumber is linked to all services deployed at the time
     *
     * @param serviceVersion
     * @return
     */
    Integer deploy(ServiceVersion serviceVersion);


    /**
     * returns a list of Services and their corresponding service version numbers deployed with the given
     * SystemVersionNumber
     *
     * @param releaseVersion
     * @return
     */
    List<ServiceVersion> services(Integer releaseVersion);

}
