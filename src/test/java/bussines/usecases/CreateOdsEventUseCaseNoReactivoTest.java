package bussines.usecases;

import bussines.gateways.RepositoryExample;
import domain.command.CreateOdsCommand;
import domain.events.JefeDePlantaCreado;
import domain.events.OdsCreada;
import domain.generic.DomainEvent;
import domain.values.FechaDeCreacion;
import domain.values.JefePlantaId;
import domain.values.Nombre;
import domain.values.OdsId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class CreateOdsEventUseCaseNoReactivoTest {

    @Mock
    private RepositoryExample repository;
    private CreateOdsEventUseCaseNoReactivo useCase;

    @BeforeEach
    void setUp(){
        useCase = new CreateOdsEventUseCaseNoReactivo(repository);
    }

    @Test
    void successFullScenarios(){

        final  String JEFEPLANTA_ID = "test-jefeplanta-id";
        Date FECHADECREACION = new Date(2023,2,16);
        String NOMBREJEFE = "test-nombre-jefeplanta";
        String ODS_ID = "test-ods-id";
        String NOMBRE = "test-nombre";

        JefeDePlantaCreado event = new JefeDePlantaCreado(new Nombre(NOMBREJEFE), new OdsId(ODS_ID), new FechaDeCreacion(FECHADECREACION));
        event.setAggregateRootId(JEFEPLANTA_ID);

        /*
        JefeDePlantaCreado jefeDePlantaCreado = new JefeDePlantaCreado(new Nombre(NOMBREJEFE), new OdsId(ODS_ID), new FechaDeCreacion(FECHADECREACION));
        jefeDePlantaCreado.setAggregateRootId(JEFEPLANTA_ID);

         */


  /*
        OdsCreada odsCreada = new OdsCreada(OdsId.of(ODS_ID), new Nombre(NOMBRE), new FechaDeCreacion(FECHADECREACION));
        odsCreada.setAggregateRootId(JEFEPLANTA_ID);

        Mockito.when(repository.findByIdNoReactivo(JEFEPLANTA_ID))
                        .thenReturn(List.of(JEFEPLANTA_ID));

   */

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(OdsCreada.class)))
                .thenAnswer( interceptor ->{
                    return  interceptor.getArgument(0);
                });

        List<DomainEvent> result = useCase.apply(event);

        Assertions.assertEquals(event.aggregateRootId(), result.get(0).aggregateRootId());
        Assertions.assertInstanceOf(OdsCreada.class, result.get(0));

    }


}