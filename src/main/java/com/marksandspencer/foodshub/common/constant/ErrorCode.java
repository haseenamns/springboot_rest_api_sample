package com.marksandspencer.foodshub.common.constant;

/**
 * The enum Error code.
 */
public enum ErrorCode {

	/**
	 * The General error.
	 */
	GENERAL_ERROR("SPQ-01", "Something went wrong, please try again"),
	/**
	 * The No data.
	 */
	NO_DATA("SPQ-02", "No Data Available", 204),
	/**
	 * The Invalid jwt token.
	 */
	INVALID_JWT_TOKEN("SPQ-03", "Invalid JWT Token"),
	/**
	 * Request part is missing
	 */
	INVALID_REQUEST_DATA("SPQ-04", "Invalid request data");


	private String code;
	private String errorMessage;
	private Integer statusCode;

	ErrorCode(final String code, final String errorMessage, final Integer statusCode) {
		this.code = code;
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
	}

	ErrorCode(final String code, final String errorMessage) {
		this.code = code;
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return code;
	}

	/**
	 * Gets error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Gets status code.
	 *
	 * @return the status code
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

}
