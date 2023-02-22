package domain.generic;

import java.util.List;

public abstract class AggregateRoot<I extends Identity> extends Entity<I> {

    private final ChangeEventSubscriber changeEventSubscriber;

    public AggregateRoot(I id) {
        super(id);
        changeEventSubscriber = new ChangeEventSubscriber();
    }

    protected ChangeEventSubscriber.ChangeApply appendChange(DomainEvent event) {
        var nameClass = identity().getClass().getSimpleName();
        var aggregate = nameClass.replaceAll("(Identity|Id|ID)", "").toLowerCase();
        event.setAggregateName(aggregate);
        event.setAggregateRootId(identity().value());
        return changeEventSubscriber.appendChange(event);
        //Marca el evento con el nombre y ID del agregado


    }

    public List<DomainEvent> getUncommittedChanges() {
        return List.copyOf(changeEventSubscriber.changes());
        //Retorno los cambios que se tienen en la lista de changeEventSuscriber
    }

    protected final void subscribe(EventChange eventChange) {
        changeEventSubscriber.subscribe(eventChange);
        //Suscribo los cambios que se realizaron para mi agregado en ChangeEventeSuscriber
    }

    public void markChangesAsCommitted() {
        changeEventSubscriber.changes().clear();
    }

    protected void applyEvent(DomainEvent domainEvent) {
        changeEventSubscriber.applyEvent(domainEvent);
        //Como aplique esos cambios y ya estaban suscritos en ChangeEventSubscriber
        //Los aplico en mi domainEvent
    }

}
