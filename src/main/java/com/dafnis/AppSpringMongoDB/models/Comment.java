package com.dafnis.AppSpringMongoDB.models;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;

    @Field("movie_id")
    private ObjectId movieId;
    private String text;
    private Date date;
    private String name;
    private String email;
}
