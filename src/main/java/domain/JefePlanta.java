package domain;

import domain.events.*;
import domain.generic.AggregateRoot;
import domain.generic.DomainEvent;
import domain.values.*;

import java.util.List;
import java.util.Objects;

public class JefePlanta extends AggregateRoot<JefePlantaId> {

    protected List<Ods> ods;
    protected Nombre nombre;

    public JefePlanta(JefePlantaId entityId, Nombre nombre, OdsId odsId, FechaDeCreacion fechaDeCreacion) {
        super(entityId);
        subscribe(new JefePlantaEventChange(this));
        appendChange(new JefeDePlantaCreado(nombre, odsId, fechaDeCreacion)).apply();
    }

    private JefePlanta(JefePlantaId id){
        super(id);
        subscribe(new JefePlantaEventChange(this));
    }


    public static JefePlanta from(JefePlantaId jefePlantaId, List<DomainEvent> events){
        JefePlanta jefePlanta = new JefePlanta(jefePlantaId);
        //Hago la instanciacion de ese nuevo agregado con su Id
        events.forEach(event -> jefePlanta.applyEvent(event));
        //Se aplican los cambios del evento, es decir a mi agregado
       // events.forEach(jefePlanta::applyEvent);
        return jefePlanta;
    }


    public void CrearOds(OdsId odsId, Nombre nombre, FechaDeCreacion fechaDeCreacion){
        Objects.requireNonNull(odsId);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(fechaDeCreacion);
        appendChange(new OdsCreada(odsId, nombre, fechaDeCreacion)).apply();}

    public void asignarOperariosPintura(OdsId odsId, OperariosPinturaId operariosPinturaId, Nombre nombre){
        Objects.requireNonNull(odsId);
        Objects.requireNonNull(operariosPinturaId);
        Objects.requireNonNull(nombre);
        appendChange(new OperarioPinturaAsignado(odsId, operariosPinturaId, nombre)).apply();
    }

    public void asignarOperariosMetalmecanica(OdsId odsId, OperariosMetalmecanicaId operariosMetalmecanicaId, Nombre nombre){
        Objects.requireNonNull(odsId);
        Objects.requireNonNull(operariosMetalmecanicaId);
        Objects.requireNonNull(nombre);
        appendChange(new OperarioMetalmecanicaAsignado(odsId, operariosMetalmecanicaId, nombre)).apply();
    }

    public void asignarOperariosEnsambleE(OdsId odsId, OperariosEnsambleEId operariosEnsambleEId, Nombre nombre){
        Objects.requireNonNull(odsId);
        Objects.requireNonNull(operariosEnsambleEId);
        Objects.requireNonNull(nombre);
        appendChange(new OperarioEnsambleEAsignado(odsId, operariosEnsambleEId, nombre)).apply();
    }


    public void asignarOds(OdsId odsId, Nombre nombre){
        appendChange(new OdsAgregada(odsId, nombre)).apply();
    }

    public void asignarFechaDeOds(OdsId odsId, FechaDeCreacion fechaDeCreacion){
        appendChange(new FechaCreada(odsId, fechaDeCreacion)).apply();
    }

    public List<Ods> getOdss() {
        return ods;
    }

    public Nombre nombre(){return nombre;}

}
