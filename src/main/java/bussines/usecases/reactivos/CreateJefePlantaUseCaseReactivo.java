package bussines.usecases.reactivos;

import bussines.gateways.DomainEventRepository;
import bussines.generic.UseCaseForCommand;
import domain.JefePlanta;
import domain.command.CreateJefePlantaCommand;
import domain.generic.DomainEvent;
import domain.values.FechaDeCreacion;
import domain.values.JefePlantaId;
import domain.values.Nombre;
import domain.values.OdsId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CreateJefePlantaUseCaseReactivo extends UseCaseForCommand<CreateJefePlantaCommand> {

    private final DomainEventRepository repository;

    public CreateJefePlantaUseCaseReactivo(DomainEventRepository repository) {
        this.repository = repository;

    }

    @Override
    public Flux<DomainEvent> apply(Mono<CreateJefePlantaCommand> createJefePlantaCommandMono) {
        return createJefePlantaCommandMono.flatMapIterable(command ->{
            JefePlanta jefePlanta = new JefePlanta(JefePlantaId.of(command.getJefePlantaId()),
                    new Nombre(command.getNombre()),
             OdsId.of(command.getOdsId()),
                    new FechaDeCreacion(command.getFechaDeCreacion()));
            return jefePlanta.getUncommittedChanges();
        }).flatMap(event ->
                repository.saveEvent(event));
    }
}
