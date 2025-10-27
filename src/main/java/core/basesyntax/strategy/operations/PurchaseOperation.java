package core.basesyntax.strategy.operations;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import java.util.Map;

public class PurchaseOperation implements OperationHandler {
    private Map<String, Integer> storage = Storage.getCurrentStorage();

    public void apply(FruitTransaction transaction) {
        if (transaction.getQuantity() > 0) {
            if (storage.containsKey(transaction.getFruit())) {
                int currentQuantity = storage.get(transaction.getFruit());
                storage.replace(transaction.getFruit(),
                        (currentQuantity - transaction.getQuantity()));
            } else {
                throw new RuntimeException("Incorrect fruit. Fruit is not available");
            }
        } else {
            throw new RuntimeException(
                    "Incorrect input data for purchase. Quantity should be greater than 0");
        }
    }
}
