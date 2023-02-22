package bussines.usecases;

import bussines.gateways.RepositoryExample;
import domain.JefePlanta;
import domain.events.JefeDePlantaCreado;
import domain.generic.DomainEvent;
import domain.values.FechaDeCreacion;
import domain.values.JefePlantaId;
import domain.values.Nombre;
import domain.values.OdsId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CreateOdsEventUseCaseNoReactivo implements UseCaseForEventNoReactive<JefeDePlantaCreado> {

    private RepositoryExample repository;

    public CreateOdsEventUseCaseNoReactivo(RepositoryExample repository) {
        this.repository = repository;
    }


    @Override
    public List<DomainEvent> apply(JefeDePlantaCreado event) {
       List<DomainEvent> events= repository.findByIdNoReactivo(event.aggregateRootId());
        JefePlanta jefePlanta = JefePlanta.from(JefePlantaId.of(event.aggregateRootId()), List.of(event));
        jefePlanta.CrearOds(event.getOdsId(),
                event.getNombre(),
                event.getFechaDeCreacion());
        return jefePlanta.getUncommittedChanges().stream().map(event1 -> {
            return repository.saveEventNoReactivo(event1);
        }).collect(Collectors.toList());

    }
}

