package core.basesyntax.services.operations;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.storage.Storage;

public class BalanceOperation implements OperationHandler {
    @Override
    public void getOperation(FruitTransaction transaction) {
        if (transaction.getQuantity() >= 0) {
            if (Storage.current_storage.containsKey(transaction.getFruit())) {
                Storage.current_storage.replace(transaction.getFruit(), transaction.getQuantity());
            } else {
                Storage.current_storage.put(transaction.getFruit(), transaction.getQuantity());
            }
        } else {
            throw new RuntimeException("Incorrect input data for balance. Quantity should be greater or equal 0");
        }
    }
}
