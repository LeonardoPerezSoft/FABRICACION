package domain.command;

import domain.generic.Command;

public class AssignOperarioEnsambleECommand extends Command {

    private String jefeplantaId;
    private String odsId;
    private String operariosEnsambleEId;
    private String Nombre;

        public AssignOperarioEnsambleECommand() {
    }

    public AssignOperarioEnsambleECommand(String jefeplantaId, String odsId, String operariosEnsambleEId, String nombre) {
        this.jefeplantaId = jefeplantaId;
        this.odsId = odsId;
        this.operariosEnsambleEId = operariosEnsambleEId;
        Nombre = nombre;
    }



    public String getJefeplantaId() {
        return jefeplantaId;
    }

    public void setJefeplantaId(String jefeplantaId) {
        this.jefeplantaId = jefeplantaId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getOdsId() {
        return odsId;
    }

    public void setOdsId(String odsId) {
        this.odsId = odsId;
    }


    public String getOperariosEnsambleEId() {
        return operariosEnsambleEId;
    }

    public void setOperariosEnsambleEId(String operariosEnsambleEId) {
        this.operariosEnsambleEId = operariosEnsambleEId;
    }
}
