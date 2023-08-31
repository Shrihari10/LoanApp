package com.wellsfargo.loanapp.exception;

public class ErrorResponse {

		private String message;
		private int statusCode;
		
		public ErrorResponse(String message){
			super();
			this.message = message;
		}
		
		public ErrorResponse(int statusCode, String message){
			this.statusCode = statusCode;
			this.message = message;
		}
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public int getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}
		
		
		
}
