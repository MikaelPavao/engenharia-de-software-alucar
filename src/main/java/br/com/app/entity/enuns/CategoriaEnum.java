package br.com.app.entity.enuns;

public enum CategoriaEnum {

    HACTH("Hacth"),
    PICKUP("Pick up"),
    SEDA("Seda"),
    SUV("Suv");

    private String modelo;
    CategoriaEnum(String modelo) {
        this.modelo = modelo;
    }
}
