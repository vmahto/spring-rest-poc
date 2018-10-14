package com.vm.rest.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {
//extends ResponseEntityExceptionHandler
	@ExceptionHandler(value = AppException.class)
	public ResponseEntity<ErrMessage> handleAppException(AppException e, WebRequest request){
	
		
		System.out.println("handle my server errors");
		ErrMessage errMessage = new ErrMessage();
		errMessage.setErrcode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errMessage.setMsg(e.getMessage());
		/*return handleExceptionInternal(e, errMessage, 
		          new HttpHeaders(), HttpStatus.OK, request);*/
		return new ResponseEntity<ErrMessage>(errMessage, HttpStatus.OK);
	}
}
