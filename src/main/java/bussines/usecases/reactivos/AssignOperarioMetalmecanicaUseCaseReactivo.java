package bussines.usecases.reactivos;

import bussines.gateways.DomainEventRepository;
import bussines.gateways.EventBus;
import bussines.generic.UseCaseForCommand;
import domain.JefePlanta;
import domain.command.AssignOperarioMetalmecanicaCommand;
import domain.generic.DomainEvent;
import domain.values.JefePlantaId;
import domain.values.Nombre;
import domain.values.OdsId;
import domain.values.OperariosMetalmecanicaId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AssignOperarioMetalmecanicaUseCaseReactivo extends UseCaseForCommand<AssignOperarioMetalmecanicaCommand> {

    private final DomainEventRepository repository;
    private final EventBus bus;

    public AssignOperarioMetalmecanicaUseCaseReactivo(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AssignOperarioMetalmecanicaCommand> assignOperarioMetalmecanicaCommandMono) {
        return assignOperarioMetalmecanicaCommandMono.flatMapMany(command ->
                repository.findById(command.getJefeplantaId()).collectList()
                        .flatMapIterable(events -> {
                            JefePlanta jefePlanta = JefePlanta.from(JefePlantaId.of(command.getJefeplantaId()), events);
                            jefePlanta.asignarOperariosMetalmecanica(OdsId.of(command.getOdsId()),
                                    OperariosMetalmecanicaId.of(command.getOperariosMetalmecanicaId()),
                                    new Nombre(command.getNombre()));
                            return  jefePlanta.getUncommittedChanges();
                        }).map(event->{
                            bus.publish(event);
                            return event;
                        }).flatMap(event -> repository.saveEvent(event)));
    }
}
