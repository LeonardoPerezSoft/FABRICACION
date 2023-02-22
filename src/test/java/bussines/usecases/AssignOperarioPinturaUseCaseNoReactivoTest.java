package bussines.usecases;

import bussines.gateways.RepositoryExample;
import domain.command.AssignOperarioPinturaCommand;
import domain.events.JefeDePlantaCreado;
import domain.events.OdsAgregada;
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

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssignOperarioPinturaUseCaseNoReactivoTest {

    @Mock
    private RepositoryExample repository;

    private AssignOperarioPinturaUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp(){
        //Arrange
        useCase = new AssignOperarioPinturaUseCaseNoReactivo(repository);

    }
    @Test
    void successFullScenarios(){
        //Arrange

        String JEFEPLANTA_ID = "test-jefeplanta-id";
        String NOMBREJEFE = "test-nombre-jefeplanta";
        String ODS_ID = "test-ods-id";
        String OPERARIOPINTURA_ID = "test-operarioPintura-id";
        String NOMBRE = "test-nombre";
        Date FECHADECREACION = new Date(2023,2,16);

        AssignOperarioPinturaCommand command = new AssignOperarioPinturaCommand(JEFEPLANTA_ID, ODS_ID, OPERARIOPINTURA_ID, NOMBRE);

        JefeDePlantaCreado jefeDePlantaCreado =  new JefeDePlantaCreado(new Nombre(NOMBREJEFE), new OdsId(ODS_ID), new FechaDeCreacion(FECHADECREACION));
        jefeDePlantaCreado.setAggregateRootId(JEFEPLANTA_ID);

        OdsCreada odsCreada = new OdsCreada(OdsId.of(ODS_ID), new Nombre(NOMBRE), new FechaDeCreacion(FECHADECREACION));
        odsCreada.setAggregateRootId(JEFEPLANTA_ID);

        OperarioPinturaAsignado operarioPinturaAsignado = new OperarioPinturaAsignado(OdsId.of(ODS_ID), OperariosPinturaId.of(OPERARIOPINTURA_ID),
                new Nombre(NOMBRE));
        operarioPinturaAsignado.setAggregateRootId(JEFEPLANTA_ID);

        /*OperarioPinturaAsignado operarioPinturaAsignado = new OperarioPinturaAsignado(new OperariosPinturaId(OPERARIOPINTURA_ID), new  Nombre(NOMBRE));
        operarioPinturaAsignado.setAggregateRootId(JEFEPLANTA_ID);
        */


        Mockito.when(repository.findByIdNoReactivo(JEFEPLANTA_ID))
                .thenReturn(List.of(jefeDePlantaCreado, odsCreada));

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(OperarioPinturaAsignado.class)))
                .thenAnswer(interceptor -> {
                    return interceptor.getArgument(0);
                });

        //Act

              List<DomainEvent> result = useCase.apply(command);

              //Assert
        Assertions.assertEquals(command.getJefeplantaId(), result.get(0).aggregateRootId());
        //Assertions.assertEquals("id-test-fail", result.get(0).aggregateRootId());
        Assertions.assertInstanceOf(OperarioPinturaAsignado.class, result.get(0));

    }



}