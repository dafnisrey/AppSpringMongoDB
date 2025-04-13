package com.dafnis.AppSpringMongoDB.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "sessions")
public class Session {
    @Id
    private String id;
    private String user_id;
    private String jwt;
}
