package domain;

import domain.generic.Entity;
import domain.values.*;

import java.util.*;

public class Ods extends Entity<OdsId> {

    //private final Map<JefePlantaId, Set<JefePlanta>> jefesDePlanta;
    //private final List<JefePlanta> jefesDePlanta;

    private List<OperariosPintura> operariosPintura;

    private List<OperariosMetalmecanica> operariosMetalmecanica;
    private List<OperariosEnsambleE> operariosEnsambleE;
    private Nombre nombre;
    private FechaDeCreacion fechaDeCreacion;


    public Ods(OdsId odsId, Nombre nombre, FechaDeCreacion fechaDeCreacion) {
        super(odsId);
         // this.jefesDePlanta = new ArrayList<>();
        //this.jefesDePlanta = jefesDePlanta;
        this.nombre =  Objects.requireNonNull(nombre);
        this.fechaDeCreacion = Objects.requireNonNull(fechaDeCreacion);
        this.operariosPintura= new ArrayList<>();
        this.operariosEnsambleE = new ArrayList<>();
        this.operariosMetalmecanica= new ArrayList<>();
    }


    public void asignarOperariosPintura(OperariosPintura operariosPintura){
        this.operariosPintura.add(operariosPintura);
    }


    public void asignarOperariosEnsambleE(OperariosEnsambleE operariosEnsambleE){
        this.operariosEnsambleE.add(operariosEnsambleE);
    }
    public void asignarOperariosMetalmecanica(OperariosMetalmecanica operariosMetalmecanica){
        this.operariosMetalmecanica.add(operariosMetalmecanica);
    }
    public void asignarTareasOperarios(){}



    public Nombre nombre(){return nombre;}
    public FechaDeCreacion fechaDeCreacion(){return fechaDeCreacion;}



}
