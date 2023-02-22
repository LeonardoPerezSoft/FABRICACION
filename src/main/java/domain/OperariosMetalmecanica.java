package domain;

import domain.generic.Entity;
import domain.values.Nombre;
import domain.values.OperariosMetalmecanicaId;
import domain.values.TareasId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OperariosMetalmecanica extends Entity<OperariosMetalmecanicaId> {

    //private final Set<TareasId> tareasIds;}

    private OperariosMetalmecanica operariosMetalmecanica;

    private Nombre nombre;


    public OperariosMetalmecanica(OperariosMetalmecanicaId entityId, Nombre nombre) {
        super(entityId);

        this.operariosMetalmecanica = operariosMetalmecanica;
        this.nombre = nombre;
    }

    public void ejecutarTarea() {
    }

   /* public Set<TareasId> tareasIds() {
        return tareasIds;
    }
    */

    public OperariosMetalmecanica operariosMetalmecanica(){return  operariosMetalmecanica;}

    public Nombre nombre() {
        return nombre;
    }
}


