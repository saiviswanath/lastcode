package com.example.demodatajpa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ErrorResp {
	private String errorCode;
	private String errorMsg;
}

