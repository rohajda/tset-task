package com.ohajda.tsettask.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "release")
public class Release implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;


    @ManyToMany
    @JoinTable(name = "release_service_version",
        joinColumns = @JoinColumn(name = "release_id", referencedColumnName = "id"),
        inverseJoinColumns = {@JoinColumn(name = "service_name", referencedColumnName = "name"),
            @JoinColumn(name = "service_version", referencedColumnName = "version")}
    )
    private List<ServiceVersion> services = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ServiceVersion> getServices() {
        return services;
    }

    public void setServices(List<ServiceVersion> services) {
        this.services = services;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Release release = (Release) o;

        return new EqualsBuilder()
            .append(getId(), release.getId())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(getId())
            .toHashCode();
    }

    @Override
    public String toString() {
        return "Release{" +
            "id=" + id +
            ", services=" + services +
            '}';
    }
}
