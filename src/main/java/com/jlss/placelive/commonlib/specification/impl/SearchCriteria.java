package com.simplyminds.common.specification.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * A Query criteria class that helps on recognizing the user query in a way that can be
 * Easily transformed into an db query language , programmatically
 * bassically has three insstace variable
 * each one are used as key , operation , value
 * the Words represents their meaning no need for more specifications on them
 **/

@Setter
@Getter
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;


    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
