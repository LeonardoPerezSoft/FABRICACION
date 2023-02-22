package bussines.usecases;

import bussines.gateways.RepositoryExample;
import domain.command.CreateOdsCommand;
import domain.events.JefeDePlantaCreado;
import domain.events.OdsAgregada;
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

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateOdsUseCaseNoReactivoTest {

    @Mock
    private RepositoryExample repository;

    private CreateOdsUseCaseNoReactivo useCase;

    @BeforeEach
    void  setUp(){
        useCase = new CreateOdsUseCaseNoReactivo(repository);

    }

    @Test
    void successFullScenarios() {

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

        Mockito.when(repository.findByIdNoReactivo(JEFEPLANTA_ID))
                .thenReturn(List.of(jefeDePlantaCreado));


        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(OdsCreada.class)))
                .thenAnswer(interceptor ->{
                    return interceptor.getArgument(0);
                });


        List<DomainEvent> result = useCase.apply(command);



        Assertions.assertEquals(command.getJefePlantaId(),result.get(0).aggregateRootId());
        Assertions.assertInstanceOf(OdsCreada.class, result.get(0));

        //Assertions.assertEquals("test-to-fail", result.get(0).aggregateRootId());


    }


}

