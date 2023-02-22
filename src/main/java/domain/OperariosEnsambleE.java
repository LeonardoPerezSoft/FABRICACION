package domain;

import domain.generic.Entity;
import domain.values.Nombre;
import domain.values.OperariosEnsambleEId;
import domain.values.OperariosPinturaId;
import domain.values.TareasId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OperariosEnsambleE extends Entity<OperariosEnsambleEId> {
   // private final Set<TareasId> tareasIds;
    private OperariosEnsambleE operariosEnsambleE;

    private Nombre nombre;


    public OperariosEnsambleE(OperariosEnsambleEId entityId, Nombre nombre) {
        super(entityId);

        this.operariosEnsambleE = operariosEnsambleE;
        this.nombre = nombre;
    }

    public void ejecutarTarea() {
    }

    /*
    public Set<TareasId> tareasIds() {
        return tareasIds;
    }
    */

    public OperariosEnsambleE operariosEnsambleE(){return  operariosEnsambleE;}
    public Nombre nombre(){return nombre;}
}
