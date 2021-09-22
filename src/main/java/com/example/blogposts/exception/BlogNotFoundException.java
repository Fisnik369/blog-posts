package com.example.blogposts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public BlogNotFoundException(String message){
        super(message);
    }
}
