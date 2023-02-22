package domain.events;

import domain.generic.DomainEvent;
import domain.values.Nombre;
import domain.values.OdsId;
import domain.values.OperariosEnsambleEId;

import java.util.ArrayList;
import java.util.List;

public class OperarioEnsambleEAsignado extends DomainEvent {

    private final OdsId odsId;
    private final OperariosEnsambleEId operariosEnsambleEId;

    private final Nombre nombre;

    public OperarioEnsambleEAsignado(OdsId odsId, OperariosEnsambleEId operariosEnsambleEId, Nombre nombre) {
        super("fabricacion.operarioEnsambleEAsignado");
        this.odsId = odsId;
        this.operariosEnsambleEId = operariosEnsambleEId;
        this.nombre = nombre;
    }

    public OperariosEnsambleEId getOperariosEnsambleEId() {
        return operariosEnsambleEId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public OdsId getOdsId() {
        return odsId;
    }
}
