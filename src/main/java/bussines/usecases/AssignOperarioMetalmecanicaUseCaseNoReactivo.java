package bussines.usecases;

import bussines.gateways.RepositoryExample;
import domain.JefePlanta;
import domain.command.AssignOperarioMetalmecanicaCommand;
import domain.generic.DomainEvent;
import domain.values.JefePlantaId;
import domain.values.Nombre;
import domain.values.OdsId;
import domain.values.OperariosMetalmecanicaId;

import java.util.List;
import java.util.stream.Collectors;

public class AssignOperarioMetalmecanicaUseCaseNoReactivo implements UseCaseForCommandNoReactive<AssignOperarioMetalmecanicaCommand>{
private RepositoryExample repository;

    public AssignOperarioMetalmecanicaUseCaseNoReactivo(RepositoryExample repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AssignOperarioMetalmecanicaCommand command) {
        List<DomainEvent> events = repository.findByIdNoReactivo(command.getJefeplantaId());
        JefePlanta jefePlanta = JefePlanta.from(JefePlantaId.of(command.getJefeplantaId()),events);
        jefePlanta.asignarOperariosMetalmecanica(OdsId.of(command.getOdsId()),
        OperariosMetalmecanicaId.of(command.getOperariosMetalmecanicaId()),
             new Nombre(command.getNombre()));
        return jefePlanta.getUncommittedChanges().stream().map(event -> {
        return repository.saveEventNoReactivo(event);
    }).collect(Collectors.toList());
}
}

