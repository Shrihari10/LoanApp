package com.wellsfargo.loanapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseGenerator {

    public static <T> ResponseEntity<T> generateResponse(HttpStatus status, String message, T body) {
        return (ResponseEntity<T>) ResponseEntity.status(status)
                             .body(new ResponseWrapper<>(message, body));
    }

    private static class ResponseWrapper<T> {
        private String message;
        private T data;

        public ResponseWrapper(String message, T data) {
            this.message = message;
            this.data = data;
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

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

        // Getters and setters
    }
}