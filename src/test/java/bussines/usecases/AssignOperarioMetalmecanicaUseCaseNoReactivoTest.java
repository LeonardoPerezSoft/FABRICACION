package bussines.usecases;

import bussines.gateways.RepositoryExample;
import domain.OperariosMetalmecanica;
import domain.command.AssignOperarioEnsambleECommand;
import domain.command.AssignOperarioMetalmecanicaCommand;
import domain.events.*;
import domain.generic.DomainEvent;
import domain.values.FechaDeCreacion;
import domain.values.Nombre;
import domain.values.OdsId;
import domain.values.OperariosMetalmecanicaId;
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
class AssignOperarioMetalmecanicaUseCaseNoReactivoTest {

    @Mock
    private RepositoryExample repository;

    private AssignOperarioMetalmecanicaUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp(){
        useCase = new AssignOperarioMetalmecanicaUseCaseNoReactivo(repository);

    }
    @Test
    void successFullScenarios(){

        String JEFEPLANTA_ID = "test-jefeplanta-id";
        String NOMBREJEFE = "test-nombre-jefeplanta";
        String ODS_ID = "test-ods-id";
        String OPERARIOMETALMECANICA_ID = "test-operarioPintura-id";
        String NOMBRE = "test-nombre";
        Date FECHADECREACION = new Date(2023,2,17);

        AssignOperarioMetalmecanicaCommand command = new AssignOperarioMetalmecanicaCommand(JEFEPLANTA_ID, ODS_ID, OPERARIOMETALMECANICA_ID, NOMBRE);

        JefeDePlantaCreado jefeDePlantaCreado = new JefeDePlantaCreado(new Nombre(NOMBREJEFE), new OdsId(ODS_ID), new FechaDeCreacion(FECHADECREACION));
        jefeDePlantaCreado.setAggregateRootId(JEFEPLANTA_ID);

        OdsCreada odsCreada = new OdsCreada(OdsId.of(ODS_ID), new Nombre(NOMBRE), new FechaDeCreacion(FECHADECREACION));
        odsCreada.setAggregateRootId(JEFEPLANTA_ID);

        OperarioMetalmecanicaAsignado operarioMetalmecanicaAsignado= new OperarioMetalmecanicaAsignado(OdsId.of(ODS_ID),
                new OperariosMetalmecanicaId(OPERARIOMETALMECANICA_ID), new Nombre(NOMBRE));
        operarioMetalmecanicaAsignado.setAggregateRootId(JEFEPLANTA_ID);

        Mockito.when(repository.findByIdNoReactivo(JEFEPLANTA_ID))
                .thenReturn(List.of(jefeDePlantaCreado, odsCreada));

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(OperarioMetalmecanicaAsignado.class)))
                .thenAnswer(interceptor -> {
                    return interceptor.getArgument(0);
                });

        List<DomainEvent> result = useCase.apply(command);


        Assertions.assertEquals(command.getJefeplantaId(), result.get(0).aggregateRootId());
        //Assertions.assertEquals("id-test-fail", result.get(0).aggregateRootId());
        Assertions.assertInstanceOf(OperarioMetalmecanicaAsignado.class, result.get(0));
    }

}