package domain;

import domain.events.*;
import domain.generic.DomainEvent;
import domain.generic.EventChange;
import domain.values.JefePlantaId;
import domain.values.OperariosEnsambleEId;
import domain.values.OperariosMetalmecanicaId;
import domain.values.OperariosPinturaId;

import java.util.ArrayList;
import java.util.List;

public class JefePlantaEventChange extends EventChange {

    public JefePlantaEventChange(JefePlanta jefePlanta) {
        apply((JefeDePlantaCreado event) -> {

            jefePlanta.ods = new ArrayList<>();
            //Esos cambios que es el jefe de planta creado lo envio a lista
            //Esto es con el fin que debo tener mi jefe de planta creado para poder Crear la ODS
            jefePlanta.nombre = event.getNombre();

            //Solicito el nombre del evento o agregado para este caso

        });
        apply((OdsCreada event) -> {
            Ods ods = new Ods(event.getOdsId(), event.getNombre(), event.getFechaDeCreacion());
            jefePlanta.ods.add(ods);
            //Asocio la nueva ODScreada Al JefeDePlanta
        });

        apply((OperarioPinturaAsignado event) -> {
            OperariosPintura operariosPintura = new OperariosPintura(event.getOperariosPinturaId(), event.getNombre());
           Ods ods = jefePlanta.ods.stream()
                    .filter(ods1 -> ods1.identity().value()
                            .equals((event.getOdsId()).value())).findFirst().orElse(null);
           ods.asignarOperariosPintura(operariosPintura);
        });

        apply((OperarioMetalmecanicaAsignado event) -> {
            OperariosMetalmecanica operariosMetalmecanica = new OperariosMetalmecanica(event.getOperariosMetalmecanicaId(), event.getNombre());
            Ods ods = jefePlanta.ods.stream()
                    .filter(ods1 -> ods1.identity().value()
                            .equals((event.getOdsId()).value())).findFirst().orElse(null);

            ods.asignarOperariosMetalmecanica(operariosMetalmecanica);
        });

        apply((OperarioEnsambleEAsignado event) -> {
            OperariosEnsambleE operariosEnsambleE = new OperariosEnsambleE(event.getOperariosEnsambleEId(), event.getNombre());
            Ods ods = jefePlanta.ods.stream()
                    .filter(ods1 -> ods1.identity().value()
                            .equals((event.getOdsId()).value())).findFirst().orElse(null);
            ods.asignarOperariosEnsambleE(operariosEnsambleE);
        });




    }
}
