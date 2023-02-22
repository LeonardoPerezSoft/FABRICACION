package bussines.usecases.reactivos;

import bussines.gateways.DomainEventRepository;
import bussines.gateways.EventBus;
import domain.command.CreateJefePlantaCommand;
import domain.events.JefeDePlantaCreado;
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
class CreateJefePantaUseCaseReactivoTest {

    @Mock
    private DomainEventRepository repository;
    private CreateJefePlantaUseCaseReactivo useCase;

    @BeforeEach
    void setUp(){
        useCase = new CreateJefePlantaUseCaseReactivo(repository);
    }

    @Test
    void successFullScenario(){

        String JEFEPLANTA_ID = "test-jefeplanta-id";
        String NOMBRE = "test-nombre";
        String NOMBREJEFE = "test-nombre-jefeplanta";
        String ODS_ID = "test-ods-id";
        Date FECHADECREACION = new Date(2023,2,16);

        CreateJefePlantaCommand command = new CreateJefePlantaCommand(NOMBRE,
                JEFEPLANTA_ID);

        JefeDePlantaCreado event =  new JefeDePlantaCreado(new Nombre(NOMBREJEFE), new OdsId(ODS_ID), new FechaDeCreacion(FECHADECREACION));
        event.setAggregateRootId(JEFEPLANTA_ID);

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(JefeDePlantaCreado.class)))
                .thenAnswer(invocationOnMock ->
                    Mono.just(invocationOnMock.getArgument(0))
                );



        Flux<DomainEvent> result = useCase.apply(Mono.just(command));


        StepVerifier.create(result)
                //.expectNextCount(1)
                .expectNextMatches(event1 -> {
                    JefeDePlantaCreado jefeDePlantaCreado = (JefeDePlantaCreado) event1;
                    Assertions.assertEquals(event.getNombre().value(), event.getNombre().value());
                    return event1.aggregateRootId().equals(event.aggregateRootId()); })

                .verifyComplete();


    }



}