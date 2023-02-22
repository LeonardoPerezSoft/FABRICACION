package domain.command;

import domain.generic.Command;
import domain.values.FechaDeCreacion;
import domain.values.Nombre;

import java.util.Date;

public class CreateOdsCommand extends Command {

    private String jefePlantaId;

    private String odsId;
    private String nombre;

    private Date fechaDeCreacion;

    public CreateOdsCommand() {
    }

    public CreateOdsCommand(String jefePlantaId,String odsId, String nombre, Date fechaDeCreacion){
        this.jefePlantaId= jefePlantaId;
        this.odsId = odsId;
        this.nombre = nombre;
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getJefePlantaId() {
        return jefePlantaId;
    }

    public void setJefePlantaId(String jefePlantaId) {
        this.jefePlantaId = jefePlantaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getOdsId() {
        return odsId;
    }

    public void setOdsId(String odsId) {
        this.odsId = odsId;
    }
}

