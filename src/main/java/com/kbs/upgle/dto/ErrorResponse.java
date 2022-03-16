package com.kbs.upgle.dto;

import com.kbs.upgle.constant.ErrorCode;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * @Author: kbs
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ErrorResponse {
    private final LocalDateTime time = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String message;



    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .message(errorCode.getMessage())
                        .build()
                );
    }

    public static ErrorResponse of(ErrorCode errorCode){
        return new ErrorResponse(
                errorCode.getHttpStatus().value(),
                errorCode.getHttpStatus().name(),
                errorCode.getMessage()
        );
    }
    public static ErrorResponse of(Exception e){
        return new ErrorResponse(
                ErrorCode.INTERNAL_ERROR.getCode(),
                ErrorCode.INTERNAL_ERROR.getHttpStatus().name(),
                e.getMessage()
        );
    }

    public static ErrorResponse of(ErrorCode errorCode, Exception e) {
        return new ErrorResponse(
                errorCode.getHttpStatus().value(),
                errorCode.getHttpStatus().name(),
                errorCode.getMessage(e));
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(
                errorCode.getHttpStatus().value(),
                errorCode.getHttpStatus().name(),
                errorCode.getMessage(message));
    }
}
