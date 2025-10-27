package core.basesyntax.services;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.services.operations.OperationHandler;

public interface OperationStrategy {
    OperationHandler get(FruitTransaction.Operation operation);
}
