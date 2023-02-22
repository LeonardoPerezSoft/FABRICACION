package domain.command;

import domain.generic.Command;

public class AssignOperarioMetalmecanicaCommand extends Command {

    private String jefeplantaId;
    private String odsId;
    private String operariosMetalmecanicaId;
    private String Nombre;

    public AssignOperarioMetalmecanicaCommand() {
    }

    public AssignOperarioMetalmecanicaCommand(String jefeplantaId, String odsId, String operariosMetalmecanicaId, String nombre) {
        this.jefeplantaId = jefeplantaId;
        this.odsId = odsId;
        this.operariosMetalmecanicaId = operariosMetalmecanicaId;
        Nombre = nombre;
    }

    public String getJefeplantaId() {
        return jefeplantaId;
    }

    public void setJefeplantaId(String jefeplantaId) {
        this.jefeplantaId = jefeplantaId;
    }

    public String getOdsId() {
        return odsId;
    }

    public void setOdsId(String odsId) {
        this.odsId = odsId;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getOperariosMetalmecanicaId() {
        return operariosMetalmecanicaId;
    }

    public void setOperariosMetalmecanicaId(String operariosMetalmecanicaId) {
        this.operariosMetalmecanicaId = operariosMetalmecanicaId;
    }
}
