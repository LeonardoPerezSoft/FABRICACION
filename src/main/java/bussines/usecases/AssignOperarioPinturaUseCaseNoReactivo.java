package bussines.usecases;

import bussines.gateways.RepositoryExample;
import domain.JefePlanta;
import domain.command.AssignOperarioPinturaCommand;
import domain.generic.DomainEvent;
import domain.values.*;

import java.util.List;
import java.util.stream.Collectors;

public class AssignOperarioPinturaUseCaseNoReactivo implements UseCaseForCommandNoReactive<AssignOperarioPinturaCommand>{
    private RepositoryExample repository;

    public AssignOperarioPinturaUseCaseNoReactivo(RepositoryExample repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AssignOperarioPinturaCommand command) {
        List<DomainEvent> events = repository.findByIdNoReactivo(command.getJefeplantaId());
        JefePlanta jefePlanta = JefePlanta.from(JefePlantaId.of(command.getJefeplantaId()), events);
               jefePlanta.asignarOperariosPintura(OdsId.of(command.getOdsId()),
                OperariosPinturaId.of(command.getOperariosPinturaId()),
                     new Nombre(command.getNombre()));
        return jefePlanta.getUncommittedChanges().stream().map(event->{
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}
