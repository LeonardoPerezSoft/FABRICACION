package domain.generic;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;


public class ChangeEventSubscriber {
    private final List<DomainEvent> changes = new LinkedList<>();
    private final Map<String, AtomicLong> versions = new ConcurrentHashMap<>();
    private final Set<Consumer<? super DomainEvent>> observables = new HashSet<>();


    public List<DomainEvent> changes() {
        return changes;
        //Aqui retornamos esos cambios esperados
    }


    public final ChangeApply appendChange(DomainEvent event) {
        changes.add(event);
        //Añade ese nuevo evento, nueva Odscreada
        return () ->
                applyEvent(event);
        //Retorno y aplica eso cambios de esa OdsCreada y se los adjunta a DomaintEvent
    }


    public final void subscribe(EventChange eventChange) {
        this.observables.addAll(eventChange.behaviors);
        //Aqui tomo esos cambios y se los muestro o pongo a unba lista cosumidores u observables para los que se tenga el mismo comportamiento
    }


    public final void applyEvent(DomainEvent domainEvent) {
        observables.forEach(consumer -> {
            try {
                consumer.accept(domainEvent);
                //*El consumer acepta los datos que estan en el DomainEvent
                //**El consumidor es decir el agragedo, acepta ese nuevo evento "Ods creada"
                var map = versions.get(domainEvent.type);
                long version = nextVersion(domainEvent, map);
                domainEvent.setVersionType(version);
                //*Aqui se pasa el evento del agregado por el domain event como primera version
                //**Aqui se pasa el evento por el domain event como segunda version, porque primero recibió al Agregado

            } catch (ClassCastException ignored) {
            }
        });
    }

    private long nextVersion(DomainEvent domainEvent, AtomicLong map) {
        if (map == null) {
            versions.put(domainEvent.type, new AtomicLong(domainEvent.versionType()));
            return domainEvent.versionType();
        }
        return versions.get(domainEvent.type).incrementAndGet();
    }



    @FunctionalInterface
    public interface ChangeApply {
        void apply();
    }

}