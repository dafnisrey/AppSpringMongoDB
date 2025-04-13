package com.dafnis.AppSpringMongoDB.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "theaters")
public class Theater {
    @Id
    private String id;
    private Integer theaterId;
    private Location location;
    
}
