package bussines.usecases.reactivos;

import bussines.gateways.DomainEventRepository;
import bussines.gateways.EventBus;
import domain.OperariosEnsambleE;
import domain.command.AssignOperarioEnsambleECommand;
import domain.events.JefeDePlantaCreado;
import domain.events.OdsCreada;
import domain.events.OperarioEnsambleEAsignado;
import domain.events.OperarioPinturaAsignado;
import domain.generic.DomainEvent;
import domain.values.FechaDeCreacion;
import domain.values.Nombre;
import domain.values.OdsId;
import domain.values.OperariosEnsambleEId;
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
class AssignOperarioEnsambleEUseCaseReactivoTest {

    @Mock
    private DomainEventRepository repository;
    @Mock
    private EventBus bus;

    private AssignOperarioEnsambleEUseCaseReactivo useCase;


    @BeforeEach
    void setUp(){
        useCase = new AssignOperarioEnsambleEUseCaseReactivo(repository, bus);
    }
    @Test
    void successFullScenario(){

        String JEFEPLANTA_ID = "test-jefeplanta-id";
        String NOMBREJEFE = "test-nombre-jefeplanta";
        String ODS_ID = "test-ods-id";
        String OPERARIOENSAMBLEE_ID = "test-operario-ensamble-id";
        String NOMBRE = "test-nombre";
        Date FECHADECREACION = new Date(2023,2,16);


        AssignOperarioEnsambleECommand command = new AssignOperarioEnsambleECommand(JEFEPLANTA_ID, ODS_ID, OPERARIOENSAMBLEE_ID, NOMBRE);

        JefeDePlantaCreado jefeDePlantaCreado =  new JefeDePlantaCreado(new Nombre(NOMBREJEFE), new OdsId(ODS_ID), new FechaDeCreacion(FECHADECREACION));
        jefeDePlantaCreado.setAggregateRootId(JEFEPLANTA_ID);

        OdsCreada odsCreada = new OdsCreada(OdsId.of(ODS_ID), new Nombre(NOMBRE), new FechaDeCreacion(FECHADECREACION));
        odsCreada.setAggregateRootId(JEFEPLANTA_ID);

        OperarioEnsambleEAsignado operarioEnsambleEAsignado = new OperarioEnsambleEAsignado(OdsId.of(ODS_ID), OperariosEnsambleEId.of(OPERARIOENSAMBLEE_ID),
                new Nombre(NOMBRE));
        operarioEnsambleEAsignado.setAggregateRootId(JEFEPLANTA_ID);

        Mockito.when(repository.findById(JEFEPLANTA_ID))
                .thenReturn(Flux.just(jefeDePlantaCreado, odsCreada));

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(OperarioEnsambleEAsignado.class)))
                .thenAnswer(invocationOnMock ->
                        Mono.just(invocationOnMock.getArgument(0)));

        Mockito.doAnswer(i->null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        StepVerifier.create(result)
                //.expectNextCount(1)
                .expectNextMatches(event1 -> {
                    OperarioEnsambleEAsignado operarioEnsambleEAsignado1 = (OperarioEnsambleEAsignado) event1;
                    Assertions.assertEquals(operarioEnsambleEAsignado1.getOperariosEnsambleEId().value(), operarioEnsambleEAsignado.getOperariosEnsambleEId().value());
                    return event1.aggregateRootId().equals(jefeDePlantaCreado.aggregateRootId());
                })
                .verifyComplete();



    }

}