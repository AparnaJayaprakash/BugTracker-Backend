package com.jwtauth.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
	private String message;
    private List<String> errors;

}
