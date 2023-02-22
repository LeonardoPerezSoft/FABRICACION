package domain.events;

import domain.OperariosMetalmecanica;
import domain.generic.DomainEvent;
import domain.values.Nombre;
import domain.values.OdsId;
import domain.values.OperariosMetalmecanicaId;
import domain.values.OperariosPinturaId;

import java.util.ArrayList;
import java.util.List;

public class OperarioMetalmecanicaAsignado extends DomainEvent {


    private final OdsId odsId;
    private final OperariosMetalmecanicaId operariosMetalmecanicaId;
    private final Nombre nombre;

    public OperarioMetalmecanicaAsignado(OdsId odsId, OperariosMetalmecanicaId operariosMetalmecanicaId, Nombre nombre) {
        super("fabricacion.operarioMetalmecanicaAsignado");
        this.odsId = odsId;
        this.operariosMetalmecanicaId = operariosMetalmecanicaId;
        this.nombre = nombre;

    }

    public OperariosMetalmecanicaId getOperariosMetalmecanicaId() {

        return operariosMetalmecanicaId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public OdsId getOdsId() {
        return odsId;
    }
}
