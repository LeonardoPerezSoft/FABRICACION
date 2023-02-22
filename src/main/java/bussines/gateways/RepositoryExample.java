package bussines.gateways;

import domain.generic.DomainEvent;
import domain.JefePlanta;
import reactor.core.publisher.Flux;

import java.util.List;

public interface RepositoryExample {

    DomainEvent saveEventNoReactivo(DomainEvent event);
    List<DomainEvent> findByIdNoReactivo(String agregateRootId);

    void deleteById(String id);

    Flux<JefePlanta> findAll();

}
