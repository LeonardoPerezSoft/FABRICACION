package bussines.usecases;

import bussines.gateways.RepositoryExample;
import domain.JefePlanta;
import domain.command.AssignOperarioEnsambleECommand;
import domain.generic.DomainEvent;
import domain.values.JefePlantaId;
import domain.values.Nombre;
import domain.values.OdsId;
import domain.values.OperariosEnsambleEId;

import java.util.List;
import java.util.stream.Collectors;

public class AssignOperarioEnsambleEUseCaseNoReactivo implements  UseCaseForCommandNoReactive<AssignOperarioEnsambleECommand>{

    private RepositoryExample repository;

    public AssignOperarioEnsambleEUseCaseNoReactivo(RepositoryExample repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AssignOperarioEnsambleECommand command) {
       List<DomainEvent> events = repository.findByIdNoReactivo(command.getJefeplantaId());
        JefePlanta jefePlanta = JefePlanta.from(JefePlantaId.of(command.getJefeplantaId()),events);

        jefePlanta.asignarOperariosEnsambleE(OdsId.of(command.getOdsId()),
                OperariosEnsambleEId.of(command.getOperariosEnsambleEId()),
                new Nombre(command.getNombre()));

        return  jefePlanta.getUncommittedChanges().stream().map(event ->{
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}
