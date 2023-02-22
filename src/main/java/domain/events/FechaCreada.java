package domain.events;

import domain.generic.DomainEvent;
import domain.values.FechaDeCreacion;
import domain.values.OdsId;

public class FechaCreada extends DomainEvent {

    private final OdsId odsId;
    private final FechaDeCreacion fechaDeCreacion;

    public FechaCreada(OdsId odsId, FechaDeCreacion fechaDeCreacion) {
        super("fabricacion.fechacreada");
        this.odsId = odsId;
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public OdsId getOdsId() {
        return odsId;
    }

    public FechaDeCreacion getFechaDeCreacion() {
        return fechaDeCreacion;
    }
}
