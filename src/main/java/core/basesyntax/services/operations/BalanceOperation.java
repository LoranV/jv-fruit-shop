package core.basesyntax.services.operations;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.storage.Storage;
import java.util.Map;

public class BalanceOperation implements OperationHandler {
    private Map<String, Integer> storage = Storage.getCurrent_storage();

    @Override
    public void getOperation(FruitTransaction transaction) {
        if (transaction.getQuantity() >= 0) {
            if (storage.containsKey(transaction.getFruit())) {
                storage.replace(transaction.getFruit(), transaction.getQuantity());
            } else {
                storage.put(transaction.getFruit(), transaction.getQuantity());
            }
        } else {
            throw new RuntimeException(
                    "Incorrect input data for balance. Quantity should be greater or equal 0");
        }
    }
}
