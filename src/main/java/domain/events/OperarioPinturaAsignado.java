package domain.events;

import domain.OperariosPintura;
import domain.generic.DomainEvent;
import domain.values.JefePlantaId;
import domain.values.Nombre;
import domain.values.OdsId;
import domain.values.OperariosPinturaId;

import java.util.ArrayList;
import java.util.List;

public class OperarioPinturaAsignado extends DomainEvent {

   // private final JefePlantaId jefePlantaId; ///////
    private final OdsId odsId;
    private final OperariosPinturaId operariosPinturaId;

    private final Nombre nombre;


    public OperarioPinturaAsignado(OdsId odsId, OperariosPinturaId operariosPinturaId, Nombre nombre) {
        super("fabricacion.operarioPinturaAsignado");

        this.odsId = odsId;
        this.operariosPinturaId = operariosPinturaId;
        this.nombre = nombre;

    }



    public OperariosPinturaId getOperariosPinturaId() {
        return operariosPinturaId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public OdsId getOdsId() {
        return odsId;
    }
}
