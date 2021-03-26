package com.mu.util;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class AjaxResult {
private String msg;
private boolean success;

public AjaxResult() {
	super();
	// TODO Auto-generated constructor stub
}


public AjaxResult(String msg) {
	super();
	this.msg = msg;
}


public AjaxResult(String msg, boolean success) {
	super();
	this.msg = msg;
	this.success = success;
}

}
