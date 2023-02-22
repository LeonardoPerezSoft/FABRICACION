package domain.command;

import domain.generic.Command;

import java.util.PrimitiveIterator;

public class AssignOperarioPinturaCommand extends Command {

    private String jefeplantaId;

    private String odsId;
    private String operariosPinturaId;
    private String Nombre;

    public AssignOperarioPinturaCommand() {
    }

    public AssignOperarioPinturaCommand(String jefeplantaId, String odsId, String operariosPinturaId, String nombre) {
        this.jefeplantaId = jefeplantaId;
        this.odsId = odsId;
        this.operariosPinturaId = operariosPinturaId;
        Nombre = nombre;
    }

    public String getJefeplantaId() {
        return jefeplantaId;
    }

    public void setJefeplantaId(String jefeplantaId) {
        this.jefeplantaId = jefeplantaId;
    }

    public String getOperariosPinturaId() {
        return operariosPinturaId;
    }

    public void setOperariosPinturaId(String operariosPinturaId) {
        this.operariosPinturaId = operariosPinturaId;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getOdsId() {
        return odsId;
    }

    public void setOdsId(String odsId) {
        this.odsId = odsId;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
