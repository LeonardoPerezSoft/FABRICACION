package domain;

import domain.generic.Entity;
import domain.values.*;

import java.util.Objects;

public class Tareas extends Entity<TareasId> {
    private final Descripcion descripcion;

    private final EstadoTarea estadoTarea;

    private final FechaDeCreacion fechaDeCreacion;

    private final TiempoTarea tiempoTarea;


    public Tareas(TareasId entityId, Descripcion descripcion, EstadoTarea estadoTarea, FechaDeCreacion fechaDeCreacion, TiempoTarea tiempoTarea) {
        super(entityId);
        this.descripcion = Objects.requireNonNull(descripcion);
        this.estadoTarea = Objects.requireNonNull(estadoTarea);
        this.fechaDeCreacion = Objects.requireNonNull(fechaDeCreacion);
        this.tiempoTarea = Objects.requireNonNull(tiempoTarea);
    }

    public void modificarNombreTarea(){}
    public void modificarTiempoTarea(){}
    public void modificarDescripcionTarea(){}
    public void modifcarEstadoTarea(){}

    public Descripcion descripcion(){return descripcion;}

    public EstadoTarea estadoTarea() {return estadoTarea;}

    public FechaDeCreacion fechaDeCreacion() {return fechaDeCreacion;}

    public TiempoTarea tiempoTarea(){return tiempoTarea;}

}
