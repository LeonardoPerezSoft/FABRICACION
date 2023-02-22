package bussines.usecases.reactivos;

import bussines.gateways.DomainEventRepository;
import bussines.gateways.EventBus;
import bussines.generic.UseCaseForCommand;
import domain.JefePlanta;
import domain.command.AssignOperarioPinturaCommand;
import domain.generic.DomainEvent;
import domain.values.JefePlantaId;
import domain.values.Nombre;
import domain.values.OdsId;
import domain.values.OperariosPinturaId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AssignOperarioPinturaUseCaseReactivo extends UseCaseForCommand<AssignOperarioPinturaCommand> {

    private final DomainEventRepository repository;
    private final EventBus bus;

    public AssignOperarioPinturaUseCaseReactivo(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AssignOperarioPinturaCommand> assignOperarioPinturaCommandMono) {
        return assignOperarioPinturaCommandMono.flatMapMany(command ->
                repository.findById(command.getJefeplantaId()).collectList()
                .flatMapIterable(events -> {
                    JefePlanta jefePlanta = JefePlanta.from(JefePlantaId.of(command.getJefeplantaId()), events);
                    jefePlanta.asignarOperariosPintura(OdsId.of(command.getOdsId()),
                            OperariosPinturaId.of(command.getOperariosPinturaId()),
                            new Nombre(command.getNombre()));
                    return jefePlanta.getUncommittedChanges();

                }).map(event-> {
                    bus.publish(event);
                    return event;
                        }).flatMap(event -> repository.saveEvent(event)));
    }
}
