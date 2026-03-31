package com.restaurant.handler;

import com.restaurant.exception.BaseException;
import com.restaurant.exception.ErrorMessage;
import com.restaurant.exception.MessageType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ApiError<String>> globalHandler(BaseException exception , WebRequest request) {
        return ResponseEntity.badRequest().body(createApiError(exception.getMessage(), request));
    }

    public <T> ApiError<T> createApiError(T message , WebRequest request) {

        ApiError<T> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.BAD_REQUEST.toString());

        Exception<T> exception = new Exception<>();
        exception.setHostName(getHostName());
        exception.setPath(request.getDescription(false));
        exception.setMessage(message);

        apiError.setException(exception);
        return apiError;
    }

    public String getHostName() {

        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostName();
        }catch (java.lang.Exception exception){
            exception.printStackTrace();
        }

        throw new BaseException(new ErrorMessage(MessageType.LOCAL_HOST_NOT_FOUND , "Local host not found"));
    }

}
