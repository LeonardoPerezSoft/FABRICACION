package domain.values;

import domain.generic.ValueObject;

import java.util.Date;
import java.util.Objects;

public class TiempoTarea implements ValueObject<Integer> {

    private final Integer value;

    public TiempoTarea(Integer value) {
        this.value = Objects.requireNonNull(value);
        if(value <= 0){
            throw new IllegalArgumentException("El tiempo de la tarea debe ser un tiempo positivo");
        }

    }


    @Override
    public Integer value() {
        return value;
    }
}
