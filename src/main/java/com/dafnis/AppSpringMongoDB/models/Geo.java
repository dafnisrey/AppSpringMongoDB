package com.dafnis.AppSpringMongoDB.models;

import java.util.List;
import lombok.Data;

@Data
public class Geo {
    private String type;
    private List<Double> coordinates;
}
