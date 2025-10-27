package core.basesyntax;

import core.basesyntax.dao.Reader;
import core.basesyntax.dao.ReaderImpl;
import core.basesyntax.dao.Writer;
import core.basesyntax.dao.WriterImpl;
import core.basesyntax.dao.converter.DataConverter;
import core.basesyntax.dao.converter.DataConverterImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.services.OperationStrategy;
import core.basesyntax.services.OperationStrategyImpl;
import core.basesyntax.services.ReportGenerator;
import core.basesyntax.services.ReportGeneratorImpl;
import core.basesyntax.services.ShopService;
import core.basesyntax.services.ShopServiceImpl;
import core.basesyntax.services.operations.BalanceOperation;
import core.basesyntax.services.operations.OperationHandler;
import core.basesyntax.services.operations.PurchaseOperation;
import core.basesyntax.services.operations.ReturnOperation;
import core.basesyntax.services.operations.SupplyOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Feel free to remove this class and create your own.
 */
public class HelloWorld {
    public static void main(String[] args) {
        Reader reader = new ReaderImpl(
                "src/main/java/core/basesyntax/resources/reportToRead.csv");
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
        List<FruitTransaction> listoftransaction = converter.convertToTransaction(list);

        // 4. Process the incoming transactions with applicable OperationHandler implementations
        ShopService shopService = new ShopServiceImpl(operationStrategy);
        shopService.process(listoftransaction);
        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        String resultingReport = reportGenerator.getReport();

        // 6. Write the received report into the destination file
        Writer fileWriter = new WriterImpl(
                "src/main/java/core/basesyntax/resources/finalReport.csv");
        fileWriter.write(resultingReport);

    }

}
