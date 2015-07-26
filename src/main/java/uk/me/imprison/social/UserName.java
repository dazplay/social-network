package uk.me.imprison.social;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class UserName {
    private String name;

    private UserName(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return name;
    }

    @Override public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public static UserName fromString(String bob) {
        return new UserName(bob);
    }
}
