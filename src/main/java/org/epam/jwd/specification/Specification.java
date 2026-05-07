package org.epam.jwd.specification;

public interface Specification<Plain> {
    boolean isSatisfiedBy(Plain plain);
}
