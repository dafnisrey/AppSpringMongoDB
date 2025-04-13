package com.dafnis.AppSpringMongoDB.models;

import lombok.Data;

@Data
public class CommentRequest {
    private String text;
    private String name;
    private String email;
    
}
