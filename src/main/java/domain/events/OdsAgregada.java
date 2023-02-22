package domain.events;

import domain.generic.DomainEvent;
import domain.values.Nombre;
import domain.values.OdsId;

public class OdsAgregada extends DomainEvent {
    private  final OdsId identity;

    private final Nombre nombre;

    public OdsAgregada(OdsId identity, Nombre nombre) {
        super("fabricacion.odsagregada");
        this.identity = identity;
        this.nombre = nombre;
    }

    public OdsId getIdentity() {
        return identity;
    }

    public Nombre getNombre() {
        return nombre;
    }
}
