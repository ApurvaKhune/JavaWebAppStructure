package com.qount.wallremainders.utils;

public class Constants {
	// SWAGGER VARIABLES

	public static final String SWAGGER_API_SPEC_VERSION = "1.2.3";
	public static final String SWAGGER_API_HTTP = "http";
	public static final String SWAGGER_API_PACKAGE = "com.qount.invoice.controller";
	public static final String FAILURE_STATUS_STR = "Failure";
	public static final String PRECONDITION_FAILED_STR = "invalid input";
	public static final String SUCCESS_STATUS_STR = "Success";
	public static final int SUCCESS_RESPONSE_CODE = 200;
	public static final String UNEXPECTED_ERROR_STATUS_STR = "Un-expected Error";
	public static final String PARTIAL_SUCCESS = "proposal not inserted into invoices";
	public static final String INVALID_REQUEST_ERROR_STATUS = "Invalid Request";
	public static final String DATABASE_ERROR_STATUS_STR = "Database Error";
	public static final String ADMIN_ROLE = "admin";
	public static final String DUE_DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String SIMPLE_DATE_FORMAT = "MM/dd/yyyy";
	public static final String INVOICE_UI_DATE_FORMAT = SIMPLE_DATE_FORMAT;

	public static final String PROJECT_CURRENT_VERSION = "project.current.version";
	
	public static final int DATABASE_ERROR_STATUS = 422;
	public static final int INVALID_INPUT_STATUS = 412;
	public static final int EXPECTATION_FAILED = 417;
	public static final int INVALID_INPUT = 412;
	public static int DAYS_IN_MILLIS = 86400000;
}
