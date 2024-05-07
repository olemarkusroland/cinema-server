package com.booleanuk.api.cinema.services;

import org.springframework.http.ResponseEntity;
import com.booleanuk.api.cinema.models.Response;

public class ResponseUtil {
    public static <T> ResponseEntity<Response<T>> buildResponseEntity(T data) {
        Response<T> response = new Response<>();
        if (data == null) {
            response.set("Fail", null);
            return ResponseEntity.status(404).body(response);
        } else {
            response.set("success", data);
            return ResponseEntity.ok(response);
        }
    }
}

