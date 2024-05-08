package com.booleanuk.api.cinema.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.booleanuk.api.cinema.models.Response;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static <T> ResponseEntity<Response<?>> buildResponse(T data) {
        if (data == null) {
            return buildErrorResponse("Not Found", HttpStatus.NOT_FOUND);
        } else {
            return buildSuccessResponse(data);
        }
    }

    public static <T> ResponseEntity<Response<?>> buildSuccessResponse(T data) {
        Response<T> response = new Response<>("success", data);
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<Response<?>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", message);

        Response<Map<String, String>> response = new Response<>("error", errorDetails);
        return ResponseEntity.status(status).body(response);
    }
}

