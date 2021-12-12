package com.bullish.class_enrollment.response;

public class IdResponse extends UpdateResponse {

	Long id = -1L;
	
	/**
	 * @param id
	 */
	public IdResponse(Long id) {
		super(true, null);
		this.id = id;
	}
	
	/**
	 * @param message
	 */
	public IdResponse(String message) {
		super(false, message);
	}
	
	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}
}
