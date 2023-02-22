package domain.values;

import domain.generic.ValueObject;

import java.util.Objects;

public class EstadoTarea implements ValueObject<EstadoTarea.Props> {

    private final Boolean pendiente;
    private final Boolean enProceso;

    private final Boolean terminada;


    public EstadoTarea(Boolean pendiente, Boolean enProceso, Boolean terminada) {
        this.pendiente = Objects.requireNonNull(pendiente);
        this.enProceso = Objects.requireNonNull(enProceso);
        this.terminada = Objects.requireNonNull(terminada);
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public Boolean pendiente() {
                return pendiente;
            }

            @Override
            public Boolean enProceso() {
                return enProceso;
            }

            @Override
            public Boolean terminada() {
                return terminada;
            }
        };
    }
    public interface Props{
        Boolean pendiente();
        Boolean enProceso();
        Boolean terminada();

    }
}
