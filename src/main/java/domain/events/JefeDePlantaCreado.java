package domain.events;

import domain.generic.DomainEvent;
import domain.values.FechaDeCreacion;
import domain.values.Nombre;
import domain.values.OdsId;

public class JefeDePlantaCreado extends DomainEvent {

    //private final JefePlantaId jefeDePlantaId;
        private Nombre  nombre;
    private OdsId odsId;

    private FechaDeCreacion fechaDeCreacion;

    public JefeDePlantaCreado() {
        super("fabricacion.JefeDePlantaCreado");
    }

    public JefeDePlantaCreado(Nombre nombre,
                              OdsId odsId,
                              FechaDeCreacion fechaDeCreacion ) {
        super("fabricacion.JefeDePlantaCreado");
        //this.jefeDePlantaId = jefeDePlantaId;

        this.nombre = nombre;
        this.odsId = odsId;
        this.fechaDeCreacion = fechaDeCreacion;




    }

   /* public JefePlantaId getJefeDePlanta() {
        return jefeDePlanta;
    }
    */


    public Nombre getNombre() {
        return nombre;
    }

    public OdsId getOdsId() {
        return odsId;
    }

    public FechaDeCreacion getFechaDeCreacion() {
        return fechaDeCreacion;
    }
}
