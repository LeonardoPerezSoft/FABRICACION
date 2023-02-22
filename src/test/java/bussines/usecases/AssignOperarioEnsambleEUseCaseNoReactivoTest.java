package bussines.usecases;

import bussines.gateways.RepositoryExample;
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

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssignOperarioEnsambleEUseCaseNoReactivoTest {

    @Mock
    private RepositoryExample repository;

    private  AssignOperarioEnsambleEUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp(){
        useCase = new AssignOperarioEnsambleEUseCaseNoReactivo(repository);
    }

    @Test
    void  successFullScenarios(){

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

        Mockito.when(repository.findByIdNoReactivo(JEFEPLANTA_ID))
                .thenReturn(List.of(jefeDePlantaCreado, odsCreada));

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(OperarioEnsambleEAsignado.class)))
                .thenAnswer(interceptor -> {
                    return interceptor.getArgument(0);
                });

        List<DomainEvent> result = useCase.apply(command);


        Assertions.assertEquals(command.getJefeplantaId(), result.get(0).aggregateRootId());
        //Assertions.assertEquals("id-test-fail", result.get(0).aggregateRootId());
        Assertions.assertInstanceOf(OperarioEnsambleEAsignado.class, result.get(0));
    }

}