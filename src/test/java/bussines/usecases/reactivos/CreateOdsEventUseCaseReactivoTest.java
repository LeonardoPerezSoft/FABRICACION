package bussines.usecases.reactivos;

import bussines.gateways.DomainEventRepository;
import bussines.gateways.EventBus;
import bussines.usecases.reactivos.CreateOdsEventUseCaseReactivo;
import domain.events.JefeDePlantaCreado;
import domain.events.OdsCreada;
import domain.generic.DomainEvent;
import domain.values.FechaDeCreacion;
import domain.values.Nombre;
import domain.values.OdsId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
class CreateOdsEventUseCaseReactivoTest {
    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    private CreateOdsEventUseCaseReactivo useCase;

    @BeforeEach
    void setUp(){
        useCase = new CreateOdsEventUseCaseReactivo(repository, bus); }

    @Test
    void successFullScenario(){
        final  String JEFEPLANTA_ID = "test-jefeplanta-id";
        Date FECHADECREACION = new Date(2023,2,16);
        String NOMBREJEFE = "test-nombre-jefeplanta";
        String ODS_ID = "test-ods-id";
        String NOMBRE = "test-nombre";


        JefeDePlantaCreado event =new JefeDePlantaCreado(new Nombre(NOMBREJEFE), new OdsId(ODS_ID), new FechaDeCreacion(FECHADECREACION));
        event.setAggregateRootId(JEFEPLANTA_ID);

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(OdsCreada.class)))
                .thenAnswer(invocationOnMock -> Mono.just(invocationOnMock.getArgument(0))
                );

        Mockito.doAnswer(i->null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));

        Flux<DomainEvent> result = useCase.apply(Mono.just(event));

        StepVerifier.create(result)
                .expectNextMatches(event1 -> {
                    OdsCreada odsCreada = (OdsCreada) event1;
                    Assertions.assertEquals(odsCreada.getOdsId().value(), event.getOdsId());
                    return event1.aggregateRootId().equals(event.aggregateRootId());})
                .verifyComplete();

        
    }


}