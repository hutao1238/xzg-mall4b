package com.xzg.mall.security.exception;

import org.springframework.http.HttpStatus;

/**
 */
public class UnauthorizedExceptionBase extends BaseXzgAuth2Exception {

	public UnauthorizedExceptionBase(String msg) {
		super(msg);
	}


	public UnauthorizedExceptionBase(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "unauthorized";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}

}
