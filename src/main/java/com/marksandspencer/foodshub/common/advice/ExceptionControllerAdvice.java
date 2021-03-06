package com.marksandspencer.foodshub.common.advice;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.marksandspencer.foodshub.common.constant.ErrorCode;
import com.marksandspencer.foodshub.common.exception.CommonServiceException;
import com.marksandspencer.foodshub.common.transfer.AppResponse;
import com.marksandspencer.foodshub.common.transfer.ErrorInfo;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @SuppressWarnings("rawtypes")
	@ExceptionHandler(value = CommonServiceException.class)
	public ResponseEntity<AppResponse> handleFoodsHubException(final CommonServiceException e) {
		log.error(e.getMessage());
		if (Objects.nonNull(e.getStatusCode()) && HttpStatus.NO_CONTENT.value() == e.getStatusCode()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(
				AppResponse.builder()
						.status(Boolean.FALSE)
						.exception(ErrorInfo.builder()
								.timestamp(LocalDateTime.now())
								.errorCode(e.getErrorCode())
								.errorMessage(e.getErrorMessage())
								.build()).build(),
								HttpStatus.BAD_REQUEST);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(value = ResourceAccessException.class)
	public ResponseEntity<AppResponse> handleResourceAccessException(final ResourceAccessException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(
				AppResponse.builder().status(Boolean.FALSE)
						.exception(ErrorInfo.builder().timestamp(LocalDateTime.now()).errorMessage(e.getMessage()).build()).build(),
				HttpStatus.BAD_GATEWAY);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<AppResponse> handleRuntimeException(final RuntimeException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(
				AppResponse.builder().status(Boolean.FALSE)
						.exception(ErrorInfo.builder().timestamp(LocalDateTime.now()).errorCode(ErrorCode.GENERAL_ERROR.getErrorCode()).errorMessage(ErrorCode.GENERAL_ERROR.getErrorMessage()).build()).build(),
				HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(value = MissingServletRequestPartException.class)
    public ResponseEntity<AppResponse> handleMissingServletRequestPartException(final MissingServletRequestPartException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(
				AppResponse.builder().status(Boolean.FALSE)
						.exception(ErrorInfo.builder().timestamp(LocalDateTime.now()).errorCode(ErrorCode.INVALID_REQUEST_DATA.getErrorCode()).errorMessage(ErrorCode.INVALID_REQUEST_DATA.getErrorMessage()).build()).build(),
				HttpStatus.BAD_REQUEST);
    }
}
