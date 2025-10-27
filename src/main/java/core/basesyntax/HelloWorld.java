package core.basesyntax;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.services.Reader;
import core.basesyntax.services.ReportGenerator;
import core.basesyntax.services.ShopService;
import core.basesyntax.services.Writer;
import core.basesyntax.services.converter.DataConverter;
import core.basesyntax.services.converter.DataConverterImpl;
import core.basesyntax.services.impl.ReaderImpl;
import core.basesyntax.services.impl.ReportGeneratorImpl;
import core.basesyntax.services.impl.ShopServiceImpl;
import core.basesyntax.services.impl.WriterImpl;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import core.basesyntax.strategy.operations.BalanceOperation;
import core.basesyntax.strategy.operations.OperationHandler;
import core.basesyntax.strategy.operations.PurchaseOperation;
import core.basesyntax.strategy.operations.ReturnOperation;
import core.basesyntax.strategy.operations.SupplyOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Feel free to remove this class and create your own.
 */
public class HelloWorld {
    public static void main(String[] args) {
        Reader reader = new ReaderImpl("src/main/resources/reportToRead.csv");
        List<String> list = reader.read();

        // 3. Create and feel the map with all OperationHandler implementations
        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperation());
        operationHandlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperation());
        operationHandlers.put(FruitTransaction.Operation.RETURN, new ReturnOperation());
        operationHandlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperation());
        OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlers);

        //
        DataConverter converter = new DataConverterImpl();
        List<FruitTransaction> transactions = converter.convertToTransaction(list);

        // 4. Process the incoming transactions with applicable OperationHandler implementations
        ShopService shopService = new ShopServiceImpl(operationStrategy);
        shopService.process(transactions);
        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        String resultingReport = reportGenerator.getReport();

        // 6. Write the received report into the destination file
        Writer fileWriter = new WriterImpl("src/main/resources/finalReport.csv");
        fileWriter.write(resultingReport);

    }

}
