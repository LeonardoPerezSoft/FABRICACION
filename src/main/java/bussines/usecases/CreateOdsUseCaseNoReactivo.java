package bussines.usecases;

import bussines.gateways.DomainEventRepository;
import bussines.gateways.EventBus;
import bussines.gateways.RepositoryExample;
import bussines.generic.UseCaseForCommand;
import domain.JefePlanta;
import domain.command.CreateOdsCommand;
import domain.generic.DomainEvent;
import domain.values.FechaDeCreacion;
import domain.values.JefePlantaId;
import domain.values.Nombre;
import domain.values.OdsId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

public class CreateOdsUseCaseNoReactivo implements UseCaseForCommandNoReactive<CreateOdsCommand> {

    private final RepositoryExample repository;


    public CreateOdsUseCaseNoReactivo(RepositoryExample repository) {
        this.repository = repository;
    }


    @Override
    public List<DomainEvent> apply(CreateOdsCommand command) {
        List<DomainEvent> events = repository.findByIdNoReactivo(command.getJefePlantaId());
               JefePlanta jefePlanta = JefePlanta.from(JefePlantaId.of(command.getJefePlantaId()), events);
                jefePlanta.CrearOds(OdsId.of(command.getOdsId()),
                new Nombre(command.getNombre()),
                new FechaDeCreacion(command.getFechaDeCreacion()));
        return jefePlanta.getUncommittedChanges().stream().map(event -> {
                   return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}
