package bussines.usecases;


import domain.generic.DomainEvent;
import domain.generic.Command;

import java.util.List;

public interface UseCaseForCommandNoReactive<R extends Command> {
    List<DomainEvent> apply(R command);
}
