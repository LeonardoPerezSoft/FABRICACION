package domain.command;

import domain.generic.Command;

import java.util.Date;

public class CreateJefePlantaCommand extends Command {
    private String nombre;
    private String jefePlantaId;

    private String odsId;

    private String nombreOds;

    private Date fechaDeCreacion;


    public CreateJefePlantaCommand() {
    }

    public CreateJefePlantaCommand(String nombre, String jefePlantaId) {
        this.nombre = nombre;
        this.jefePlantaId = jefePlantaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getJefePlantaId() {
        return jefePlantaId;
    }

    public void setJefePlantaId(String jefePlantaId) {
        this.jefePlantaId = jefePlantaId;
    }

    public String getOdsId() {
        return odsId;
    }

    public String getNombreOds() {
        return nombreOds;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }
}
