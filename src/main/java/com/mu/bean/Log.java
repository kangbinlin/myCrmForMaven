package com.mu.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class Log {
    private Long id;

    private Employee opuser;

    private Date optime;

    private String opip;

    private String function;

    private String params;

}