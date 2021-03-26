package com.mu.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Department {
    private Long id;

    private String sn;

    private String name;

    private Employee manager;

    private Department parent;

    private Boolean state;
}