package bussines.usecases.reactivos;

import bussines.gateways.DomainEventRepository;
import bussines.gateways.EventBus;
import bussines.generic.UseCaseForCommand;
import domain.JefePlanta;
import domain.command.AssignOperarioEnsambleECommand;
import domain.generic.DomainEvent;
import domain.values.JefePlantaId;
import domain.values.Nombre;
import domain.values.OdsId;
import domain.values.OperariosEnsambleEId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AssignOperarioEnsambleEUseCaseReactivo extends UseCaseForCommand<AssignOperarioEnsambleECommand> {

    private final DomainEventRepository repository;
    private final EventBus bus;

    public AssignOperarioEnsambleEUseCaseReactivo(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AssignOperarioEnsambleECommand> assignOperarioEnsambleECommandMono) {
        return assignOperarioEnsambleECommandMono.flatMapMany(command ->
                repository.findById(command.getJefeplantaId()).collectList()
                        .flatMapIterable(events -> {
                            JefePlanta jefePlanta = JefePlanta.from(JefePlantaId.of(command.getJefeplantaId()), events);
                            jefePlanta.asignarOperariosEnsambleE(OdsId.of(command.getOdsId()),
                                    OperariosEnsambleEId.of(command.getOperariosEnsambleEId()),
                            new Nombre(command.getNombre()));
                            return jefePlanta.getUncommittedChanges();
                        }).map(event -> {
                            bus.publish(event);
                            return event;
                        }).flatMap(event -> repository.saveEvent(event)));
    }
}
