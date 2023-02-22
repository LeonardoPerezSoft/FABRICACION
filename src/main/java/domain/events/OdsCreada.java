package domain.events;

import domain.generic.DomainEvent;
import domain.values.*;

import java.util.Set;

public class OdsCreada extends DomainEvent {
    private OdsId odsId;
    private Nombre nombre;

    private FechaDeCreacion fechaDeCreacion;

   // private final Set<OperariosPinturaId> operariosPinturaIds;
   // private final Set<OperariosMetalmecanicaId> operariosMetalmecanicaIds;
    // private final Set<OperariosEnsambleEId> operariosEnsambleEIds;



    public OdsCreada(OdsId odsId, Nombre nombre, FechaDeCreacion fechaDeCreacion) {
        super("fabricacion.odscreada");
        this.odsId = odsId;
        this.nombre = nombre;
        this.fechaDeCreacion = fechaDeCreacion;

    }


    public OdsId getOdsId() {
        return odsId;
            }

    public FechaDeCreacion getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public Nombre getNombre() {
        return nombre;

    }
}
