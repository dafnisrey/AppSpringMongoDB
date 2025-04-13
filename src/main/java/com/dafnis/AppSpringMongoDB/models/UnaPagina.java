package com.dafnis.AppSpringMongoDB.models;

import java.util.List;
import lombok.Data;

@Data
public class UnaPagina<T> {

    private List<T> content;
    private int numpagina;
    private int tamaño;
    private int elementostotales;

    public UnaPagina(List<T> content, int numpagina, int tamaño, int elementostotales){
        this.content = content;
        this.numpagina = numpagina;
        this.tamaño = tamaño;
        this.elementostotales = elementostotales;
    }
    
}
