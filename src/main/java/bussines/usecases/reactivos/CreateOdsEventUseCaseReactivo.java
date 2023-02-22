package bussines.usecases.reactivos;

import bussines.gateways.DomainEventRepository;
import bussines.gateways.EventBus;
import domain.JefePlanta;
import domain.events.JefeDePlantaCreado;
import domain.generic.DomainEvent;
import domain.values.FechaDeCreacion;
import domain.values.JefePlantaId;
import domain.values.Nombre;
import domain.values.OdsId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@Component
public class CreateOdsEventUseCaseReactivo implements Function<Mono<JefeDePlantaCreado>, Flux<DomainEvent>> {

    private DomainEventRepository repository;
    private EventBus bus;

    public CreateOdsEventUseCaseReactivo(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<JefeDePlantaCreado> jefeDePlantaCreadoMono) {
        return jefeDePlantaCreadoMono.flatMapIterable(event -> {
            JefePlanta jefePlanta = JefePlanta.from(JefePlantaId.of(event.aggregateRootId()), List.of(event));
            jefePlanta.CrearOds(event.getOdsId(),
                    event.getNombre(),
                    event.getFechaDeCreacion());
            return jefePlanta.getUncommittedChanges();

        }).flatMap(domainEvent -> repository.saveEvent(domainEvent))
                .map(domainEvent -> {
                    bus.publish(domainEvent);
                    return domainEvent;
                });
    }
}
