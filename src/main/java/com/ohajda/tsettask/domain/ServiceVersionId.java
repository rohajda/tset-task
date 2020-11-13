package com.ohajda.tsettask.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class ServiceVersionId implements Serializable {
    private String name;

    private Integer version;

    public ServiceVersionId() {
    }

    public ServiceVersionId(String name, Integer version) {
        this.name = name;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ServiceVersionId that = (ServiceVersionId) o;

        return new EqualsBuilder()
            .append(name, that.name)
            .append(version, that.version)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(name)
            .append(version)
            .toHashCode();
    }
}
