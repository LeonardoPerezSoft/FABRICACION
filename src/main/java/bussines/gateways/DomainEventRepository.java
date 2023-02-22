package bussines.gateways;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import domain.generic.DomainEvent;

public interface DomainEventRepository {
    Flux<DomainEvent> findById(String aggregateId);
    Mono<DomainEvent> saveEvent(DomainEvent event);
}
