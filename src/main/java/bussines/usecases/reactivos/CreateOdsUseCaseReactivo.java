package bussines.usecases.reactivos;

import bussines.gateways.DomainEventRepository;
import bussines.gateways.EventBus;
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

public class CreateOdsUseCaseReactivo extends UseCaseForCommand<CreateOdsCommand> {

    private final DomainEventRepository repository;

    private final EventBus bus;

    public CreateOdsUseCaseReactivo(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    //flattenAsObservable


    @Override
    public Flux<DomainEvent> apply(Mono<CreateOdsCommand> createOdsCommandMono) {
        return createOdsCommandMono.flatMapMany(command ->
                repository.findById(command.getJefePlantaId()).collectList()
                        .flatMapIterable(events -> {
                            JefePlanta jefePlanta = JefePlanta.from(JefePlantaId.of(command.getJefePlantaId()), events);
                            jefePlanta.CrearOds(OdsId.of(command.getOdsId()),
                                    new Nombre(command.getNombre()), new FechaDeCreacion(command.getFechaDeCreacion()));
                            return jefePlanta.getUncommittedChanges();
                        }).map(event -> {
                            bus.publish(event);
                            return event;
                        }).flatMap(event -> repository.saveEvent(event))
        );


    }
}
