package bussines.usecases;

import bussines.gateways.RepositoryExample;
import domain.JefePlanta;
import domain.command.CreateJefePlantaCommand;
import domain.generic.Command;
import domain.generic.DomainEvent;
import bussines.usecases.UseCaseForCommandNoReactive;
import domain.values.FechaDeCreacion;
import domain.values.JefePlantaId;
import domain.values.Nombre;
import domain.values.OdsId;

import java.util.List;
import java.util.stream.Collectors;

public class CreateJefePlantaUseCaseNoReactivo implements UseCaseForCommandNoReactive<CreateJefePlantaCommand> {
    private RepositoryExample repository;

    public CreateJefePlantaUseCaseNoReactivo(RepositoryExample repository){this.repository = repository;}




    @Override
    public List<DomainEvent> apply(CreateJefePlantaCommand command) {
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
