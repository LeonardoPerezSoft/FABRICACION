package domain.values;

import domain.generic.ValueObject;

import java.util.Objects;

public class Descripcion implements ValueObject<String> {

    private final String value;

    public Descripcion(String value) {
        this.value= Objects.requireNonNull(value);
        if(this.value.isEmpty()){
            throw new IllegalArgumentException("Se debe hacer la descripción");
        }
    }


    @Override
    public String value() {
        return value;
    }
}
