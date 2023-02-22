package domain;

import domain.generic.Entity;
import domain.values.Nombre;
import domain.values.OperariosPinturaId;

public class OperariosPintura extends Entity<OperariosPinturaId> {
    //private final List<TareasId> tareasIds;
    private OperariosPintura operariosPintura;

    private Nombre nombre;


    public OperariosPintura(OperariosPinturaId entityId, Nombre nombre) {
        super(entityId);
        this.operariosPintura = operariosPintura;
        this.nombre = nombre;
    }

    public void ejecutarTarea() {
    }

    /*public Set<TareasId> tareasIds() {
        return (Set<TareasId>) tareasIds;
    }*/

  public OperariosPintura operariosPintura(){return operariosPintura;}

    public Nombre nombre(){return nombre;}
}
