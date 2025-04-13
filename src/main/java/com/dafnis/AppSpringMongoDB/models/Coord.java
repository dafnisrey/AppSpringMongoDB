package com.dafnis.AppSpringMongoDB.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class Coord {
    
    public double longitud;
    public double latitud;

    public Coord(){}
    @JsonCreator
    public Coord(@JsonProperty("longitud") Double longitud, @JsonProperty("latitud") Double latitud){
        this.longitud = longitud;
        this.latitud = latitud;

    }
}
