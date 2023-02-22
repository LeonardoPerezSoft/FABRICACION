package bussines.usecases;

import bussines.gateways.RepositoryExample;
import domain.command.CreateJefePlantaCommand;
import domain.events.JefeDePlantaCreado;
import domain.events.OdsCreada;
import domain.generic.DomainEvent;
import domain.values.FechaDeCreacion;
import domain.values.Nombre;
import domain.values.OdsId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
class CreateJefePlantaUseCaseNoReactivoTest {

    @Mock
    private RepositoryExample repository;

    private  CreateJefePlantaUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp(){
        //Arrange
        useCase = new CreateJefePlantaUseCaseNoReactivo(repository);

    }
    @Test
    void successFullScenario(){
        //Arrange
        String JEFEPLANTA_ID = "test-jefeplanta-id";
        String NOMBRE = "test-nombre";
        String NOMBREJEFE = "test-nombre-jefeplanta";
        String ODS_ID = "test-ods-id";
        Date FECHADECREACION = new Date(2023,2,16);

        CreateJefePlantaCommand command = new CreateJefePlantaCommand(NOMBRE,
                JEFEPLANTA_ID);

        JefeDePlantaCreado event = new JefeDePlantaCreado(new Nombre(NOMBREJEFE), new OdsId(ODS_ID), new FechaDeCreacion(FECHADECREACION));
        event.setAggregateRootId(JEFEPLANTA_ID);


        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(JefeDePlantaCreado.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        //Act
        List<DomainEvent>result = useCase.apply(command);


        //
        Assertions.assertEquals(event.aggregateRootId(), result.get(0).aggregateRootId());
      // Assertions.assertEquals("test-to-fail", result.get(0).aggregateRootId());
    }


}


