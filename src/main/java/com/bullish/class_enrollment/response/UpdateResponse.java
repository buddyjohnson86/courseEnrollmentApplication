package com.bullish.class_enrollment.response;

public class UpdateResponse {

	private boolean isSuccess;
	
	private String erorrMessage;

	/**
	 * @param isSuccess
	 */
	public UpdateResponse(boolean isSuccess) {
		this(isSuccess, null);
	}
	
	/**
	 * @param isSuccess
	 * @param errorMessage
	 */
	public UpdateResponse(boolean isSuccess, String errorMessage) {
		this.setSuccess(isSuccess);
		this.setErorrMessage(errorMessage);
	}

	/**
	 * @return
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return
	 */
	public String getErorrMessage() {
		return erorrMessage;
	}

	/**
	 * @param erorrMessage
	 */
	public void setErorrMessage(String erorrMessage) {
		this.erorrMessage = erorrMessage;
	}
	
	
}
