package com.ohajda.tsettask.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@IdClass(ServiceVersionId.class)
@Table(name = "service_version")
public class ServiceVersion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "name", length = 100)
    private String name;

    @Id
    @Min(1)
    private Integer version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonIgnore
    public ServiceVersionId getId() {
        return new ServiceVersionId(name, version);
    }

    public ServiceVersion() {
    }

    public ServiceVersion(String name, @Min(1) Integer version) {
        this.name = name;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ServiceVersion that = (ServiceVersion) o;

        return new EqualsBuilder()
            .append(getName(), that.getName())
            .append(getVersion(), that.getVersion())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(getName())
            .append(getVersion())
            .toHashCode();
    }

    @Override
    public String toString() {
        return "ServiceVersion{" +
            "name='" + name + '\'' +
            ", version=" + version +
            '}';
    }
}
