package bussines.usecases.reactivos;

import bussines.gateways.DomainEventRepository;
import bussines.gateways.EventBus;
import domain.command.CreateOdsCommand;
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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateOdsUseCaseReactivoTest {

    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    private  CreateOdsUseCaseReactivo useCase;

    @BeforeEach
    void  setUp(){
        useCase = new CreateOdsUseCaseReactivo(repository, bus);
    }

    @Test
    void succesFullScenario(){

        String JEFEPLANTA_ID = "test-jefeplanta-id";
        String NOMBREJEFE = "test-nombre-jefeplanta";
        String ODS_ID = "test-ods-id";
        String NOMBRE = "test-nombre";
        Date FECHADECREACION = new Date(2023,2,16);

        CreateOdsCommand command = new CreateOdsCommand(JEFEPLANTA_ID, ODS_ID, NOMBRE, FECHADECREACION);

        JefeDePlantaCreado jefeDePlantaCreado = new JefeDePlantaCreado(new Nombre(NOMBREJEFE), new OdsId(ODS_ID), new FechaDeCreacion(FECHADECREACION));
        jefeDePlantaCreado.setAggregateRootId(JEFEPLANTA_ID);

        OdsCreada odsCreada = new OdsCreada(OdsId.of(ODS_ID), new Nombre(NOMBRE), new FechaDeCreacion(FECHADECREACION));
        odsCreada.setAggregateRootId(JEFEPLANTA_ID);

        Mockito.when(repository.findById(JEFEPLANTA_ID))
                        .thenReturn(Flux.just(jefeDePlantaCreado));

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(OdsCreada.class)))
                .thenAnswer(invocationOnMock ->
                        Mono.just(invocationOnMock.getArgument(0)));

        Mockito.doAnswer(i->null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        StepVerifier.create(result)
                //.expectNextCount(1)
                .expectNextMatches(event1 ->{
                    OdsCreada odsCreada1 = (OdsCreada) event1;
                    Assertions.assertEquals(odsCreada1.getOdsId().value(), odsCreada.getOdsId().value());
                    return  event1.aggregateRootId().equals(jefeDePlantaCreado.aggregateRootId());
                })
            .verifyComplete();


    }

}