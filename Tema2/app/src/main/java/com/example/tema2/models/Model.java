package com.example.tema2.models;

public class Model {
    public enum ModelType{
        USER,
        ALBUM,
        POST
    }
    private ModelType modelType;
    public Model(ModelType modelType) {
        this.modelType = modelType;
    }
}
