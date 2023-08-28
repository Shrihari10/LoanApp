package com.wellsfargo.loanapp.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseGenerator {

    public static <T> ResponseEntity<T> generateResponse(HttpStatus status, String message, T body) {
        return (ResponseEntity<T>) ResponseEntity.status(status)
                             .body(new ResponseWrapper<>(message, body));
    }

    private static class ResponseWrapper<T> {
        private String message;
        private T body;
        
        public ResponseWrapper(String message, T body) {
			super();
			this.message = message;
			this.body = body;
		}
        
        public ResponseWrapper() {
			super();
		}
        
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public T getBody() {
			return body;
		}
		public void setBody(T body) {
			this.body = body;
		}
    }
}