package com.jlss.placelive.commonlib.specification.impl;


import com.jlss.placelive.commonlib.dto.SpecificationResponseDto;
import com.simplyminds.common.specification.impl.SearchCriteria;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.logging.Logger;

public class GenericSpecification<T> implements Specification<T> {
    private static final Logger logger = Logger.getLogger(GenericSpecification.class.getName());


    private final SearchCriteria searchCriteria;
    // we will set it here so that no need to send explicitly the source entity .
    private Class<?> targetEntity;

    public GenericSpecification(com.simplyminds.common.specification.impl.SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        // First check if this is a join field
         return handleSimplePredicate(root, builder);

    }

    private Predicate handleSimplePredicate(Root<T> root, CriteriaBuilder builder) {
        return createPredicateForField(root.get(searchCriteria.getKey()), builder);
    }
    private Predicate createPredicateForField(Path<?> path, CriteriaBuilder builder) {
        if (path.getJavaType() == String.class) {
            if (searchCriteria.getOperation().equals(":")) {
                return builder.like(path.as(String.class), "%" + searchCriteria.getValue() + "%");
            } else {
                return builder.equal(path, searchCriteria.getValue());
            }
        } else {
            return builder.equal(path, searchCriteria.getValue());
        }
    }


}