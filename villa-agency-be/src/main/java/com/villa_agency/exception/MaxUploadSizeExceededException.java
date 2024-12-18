package com.villa_agency.exception;

@SuppressWarnings("serial")
public class MaxUploadSizeExceededException extends org.springframework.web.multipart.MaxUploadSizeExceededException {

	public MaxUploadSizeExceededException(long maxUploadSize) {
		super(maxUploadSize);
	}

	public MaxUploadSizeExceededException(long maxUploadSize, Throwable ex) {
		super(maxUploadSize, ex);
	}

}
