package domain.values;

import domain.generic.ValueObject;


import java.time.LocalDate;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class FechaDeCreacion implements ValueObject<Date> {

    private final Date value;


    public FechaDeCreacion(Date value) {

        this.value = value;
      /*   if (this.value.equals(LocalDate.now()))
         {
             throw new IllegalArgumentException("La fecha de creación debe ser hoy");
         }
*/

    }


    @Override
    public Date value() {
        return value;
    }
}
