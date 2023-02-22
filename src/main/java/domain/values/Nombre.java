package domain.values;

import domain.generic.ValueObject;

import java.util.Date;
import java.util.Objects;

public class Nombre implements ValueObject<String> {
    private final String value;

    public Nombre(String value) {
        this.value= Objects.requireNonNull(value);
        if(this.value.isEmpty()){
            throw new IllegalArgumentException("El Nombre no es Válido");
        }
    }


    @Override
    public String value() {
        return value;
    }
}
