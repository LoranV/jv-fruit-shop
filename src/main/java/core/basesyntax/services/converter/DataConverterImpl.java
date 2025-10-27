package core.basesyntax.services.converter;

import core.basesyntax.model.FruitTransaction;
import java.util.ArrayList;
import java.util.List;

public class DataConverterImpl implements DataConverter {
    @Override
    public List<FruitTransaction> convertToTransaction(List<String> list) {
        List<FruitTransaction> fruitTransactionList = new ArrayList<>();
        FruitTransaction.Operation operation;
        String fruit;
        int amount;
        String[] values;
        try {
            for (String string : list) {
                values = string.split(",");
                operation = (convertToOperation(values[0]));
                fruit = values[1];
                amount = Integer.parseInt(values[2]);
                fruitTransactionList.add(new FruitTransaction(operation, fruit, amount));
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid data", e);
        }
        return fruitTransactionList;
    }

    @Override
    public FruitTransaction.Operation convertToOperation(String code) {
        return switch (code) {
            case "b" -> FruitTransaction.Operation.BALANCE;
            case "s" -> FruitTransaction.Operation.SUPPLY;
            case "p" -> FruitTransaction.Operation.PURCHASE;
            case "r" -> FruitTransaction.Operation.RETURN;
            default -> throw new RuntimeException("Unknown operation " + code);
        };
    }
}
