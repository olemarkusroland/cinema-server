package com.booleanuk.api.cinema.models;

import lombok.Getter;

@Getter
public class Response<T> {
    protected String status;
    protected T data;

    protected Response() {}

    public Response(String status, T data) {
        this.status = status;
        this.data   = data;
    }

    public void set(String status, T data) {
        this.status = status;
        this.data   = data;
    }
}