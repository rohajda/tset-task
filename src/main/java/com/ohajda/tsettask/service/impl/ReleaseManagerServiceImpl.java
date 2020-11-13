package com.ohajda.tsettask.service.impl;

import com.ohajda.tsettask.domain.Release;
import com.ohajda.tsettask.domain.ServiceVersion;
import com.ohajda.tsettask.repository.ReleaseRepository;
import com.ohajda.tsettask.repository.ServiceVersionRepository;
import com.ohajda.tsettask.service.ReleaseManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing releases.
 */
@Service
@Transactional
public class ReleaseManagerServiceImpl implements ReleaseManagerService {
    private final Logger log = LoggerFactory.getLogger(ReleaseManagerServiceImpl.class);

    private final ServiceVersionRepository serviceVersionRepository;

    private final ReleaseRepository releaseRepository;

    public ReleaseManagerServiceImpl(ServiceVersionRepository serviceVersionRepository,
                                     ReleaseRepository releaseRepository) {
        this.serviceVersionRepository = serviceVersionRepository;
        this.releaseRepository = releaseRepository;
    }

    @Override
    public Integer deploy(ServiceVersion serviceVersion) {
        log.debug("Deploy service {}", serviceVersion);
        Integer currentRelaseVersion = releaseRepository.maxId();
        currentRelaseVersion = currentRelaseVersion == null ? 0 : currentRelaseVersion;

        Optional<ServiceVersion> optionalServiceVersion = serviceVersionRepository.findById(serviceVersion.getId());
        if (!optionalServiceVersion.isPresent()) {
            // we have new version

            serviceVersionRepository.save(serviceVersion);

            Release release = new Release();
            release.setId(++currentRelaseVersion);
            release.setServices(serviceVersionRepository.findLatestServices());

            releaseRepository.save(release);
        }

        // return current release version
        return currentRelaseVersion;
    }

    @Override
    public List<ServiceVersion> services(Integer releaseVersion) {
        log.debug("Return services for version {}", releaseVersion);

        List<ServiceVersion> serviceVersionList = new ArrayList<>();

        Optional<Release> releaseOptional = releaseRepository.findById(releaseVersion);
        if (releaseOptional.isPresent()) {
            serviceVersionList = releaseOptional.get().getServices();
        }

        return serviceVersionList;
    }
}
