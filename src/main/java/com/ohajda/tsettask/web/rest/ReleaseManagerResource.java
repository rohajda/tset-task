package com.ohajda.tsettask.web.rest;

import com.ohajda.tsettask.domain.ServiceVersion;
import com.ohajda.tsettask.service.ReleaseManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing releases.
 */
@RestController
@RequestMapping("/api")
public class ReleaseManagerResource {
    private final Logger log = LoggerFactory.getLogger(ReleaseManagerResource.class);

    private final ReleaseManagerService releaseManagerService;

    public ReleaseManagerResource(ReleaseManagerService releaseManagerService) {
        this.releaseManagerService = releaseManagerService;
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServiceVersion>> getServices(@RequestParam("systemVersion") Integer systemVersion) {
        log.debug("REST request to get services for release version {}", systemVersion);
        return ResponseEntity.ok().body(releaseManagerService.services(systemVersion));
    }

    @PostMapping("/deploy")
    public ResponseEntity<Integer> deployService(@Valid @RequestBody ServiceVersion serviceVersion) {
        log.debug("REST request to deploy service {}", serviceVersion);

        Integer result = releaseManagerService.deploy(serviceVersion);

        return ResponseEntity.ok().body(result);
    }

}
