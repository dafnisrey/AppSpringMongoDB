package com.dafnis.AppSpringMongoDB.models;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "movies")
public class Movie {
    @Id
    private String id;
    private String title;
    private String plot;
    private String fullplot;
    private List<String> genres;
    private List<String> cast;
    private Date released;
    private List<String> directors;
    private Awards awards;

    @Data
    public static class Awards{
        private Integer wins;
        private Integer nominations;
        private String text;
    }
    
}
