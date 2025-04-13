package com.dafnis.AppSpringMongoDB.models;

import lombok.Data;

@Data
public class Location {

    private Address address;
    private Geo geo;
    
}
