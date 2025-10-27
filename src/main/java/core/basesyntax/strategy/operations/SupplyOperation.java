package core.basesyntax.strategy.operations;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import java.util.Map;

public class SupplyOperation implements OperationHandler {
    private Map<String, Integer> storage = Storage.getCurrentStorage();

    @Override
    public void apply(FruitTransaction transaction) {
        if (transaction.getQuantity() > 0) {
            if (storage.containsKey(transaction.getFruit())) {
                int currentQuantity = storage.get(transaction.getFruit());
                storage.replace(transaction.getFruit(),
                        (currentQuantity + transaction.getQuantity()));
            } else {
                storage.put(transaction.getFruit(), transaction.getQuantity());
            }
        } else {
            throw new RuntimeException(
                    "Incorrect input data for supply. Quantity should be greater than 0");
        }
    }
}
