package bussines.usecases;


import domain.generic.Command;
import domain.generic.DomainEvent;

import java.util.List;

public interface UseCaseForEventNoReactive<R extends DomainEvent> {
    List<DomainEvent> apply(R event);
}
