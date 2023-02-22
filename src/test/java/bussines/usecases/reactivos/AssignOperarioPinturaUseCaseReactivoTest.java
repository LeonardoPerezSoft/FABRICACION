package bussines.usecases.reactivos;

import bussines.gateways.DomainEventRepository;
import bussines.gateways.EventBus;
import domain.command.AssignOperarioPinturaCommand;
import domain.events.JefeDePlantaCreado;
import domain.events.OdsCreada;
import domain.events.OperarioPinturaAsignado;
import domain.generic.DomainEvent;
import domain.values.FechaDeCreacion;
import domain.values.Nombre;
import domain.values.OdsId;
import domain.values.OperariosPinturaId;
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

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AssignOperarioPinturaUseCaseReactivoTest {

    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    private AssignOperarioPinturaUseCaseReactivo useCase;


    @BeforeEach
    void setUp(){
        useCase = new AssignOperarioPinturaUseCaseReactivo(repository, bus);
    }

    @Test
    void successFullScenario(){

        String JEFEPLANTA_ID = "test-jefeplanta-id";
        String NOMBREJEFE = "test-nombre-jefeplanta";
        String ODS_ID = "test-ods-id";
        String OPERARIOPINTURA_ID = "test-operarioPintura-id";
        String NOMBRE = "test-nombre";
        Date FECHADECREACION = new Date(2023,2,16);

        AssignOperarioPinturaCommand command = new AssignOperarioPinturaCommand(JEFEPLANTA_ID, ODS_ID, OPERARIOPINTURA_ID, NOMBRE);

        JefeDePlantaCreado jefeDePlantaCreado = new JefeDePlantaCreado(new Nombre(NOMBREJEFE), new OdsId(ODS_ID), new FechaDeCreacion(FECHADECREACION));
        jefeDePlantaCreado.setAggregateRootId(JEFEPLANTA_ID);



        OdsCreada odsCreada = new OdsCreada(OdsId.of(ODS_ID), new Nombre(NOMBRE), new FechaDeCreacion(FECHADECREACION));
        odsCreada.setAggregateRootId(JEFEPLANTA_ID);


        OperarioPinturaAsignado operarioPinturaAsignado = new OperarioPinturaAsignado(OdsId.of(ODS_ID), OperariosPinturaId.of(OPERARIOPINTURA_ID),
                new Nombre(NOMBRE));
        operarioPinturaAsignado.setAggregateRootId(JEFEPLANTA_ID);

        Mockito.when(repository.findById(JEFEPLANTA_ID))
                .thenReturn(Flux.just(jefeDePlantaCreado, odsCreada));

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(OperarioPinturaAsignado.class)))
                .thenAnswer(invocationOnMock ->
                        Mono.just(invocationOnMock.getArgument(0)));


        Mockito.doAnswer(i->null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        StepVerifier.create(result)
                //.expectNextCount(1)
                .expectNextMatches(event1 -> {
                    OperarioPinturaAsignado operarioPinturaAsignado1 = (OperarioPinturaAsignado) event1;
                    Assertions.assertEquals(operarioPinturaAsignado1.getOperariosPinturaId().value(), operarioPinturaAsignado.getOperariosPinturaId().value());
                    return event1.aggregateRootId().equals(jefeDePlantaCreado.aggregateRootId());
                })
                .verifyComplete();
    }

}