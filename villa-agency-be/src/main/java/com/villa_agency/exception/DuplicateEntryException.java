package com.villa_agency.exception;

@SuppressWarnings("serial")
public class DuplicateEntryException extends RuntimeException {

	public DuplicateEntryException(String message) {
		super(message);
	}
}
