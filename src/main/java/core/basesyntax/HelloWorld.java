package core.basesyntax;

import core.basesyntax.dao.DBWriter;
import core.basesyntax.dao.DBWriterImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.dao.DBReader;
import core.basesyntax.dao.DBReaderImpl;
import core.basesyntax.dao.converter.DataConverter;
import core.basesyntax.dao.converter.DataConverterImpl;
import core.basesyntax.services.*;
import core.basesyntax.services.operations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Feel free to remove this class and create your own.
 */
public class HelloWorld {
    // HINT: In the `public static void main(String[] args)` it is better to create instances of your classes, 
    // and call their methods, but do not write any business logic in the `main` method!

    public static void main(String[] args) {
        DBReader reader = new DBReaderImpl("src/main/java/core/basesyntax/resources/reportToRead.csv");
        List<String> list = reader.read();

        DataConverter converter = new DataConverterImpl();
        List<FruitTransaction> listoftransaction = converter.convertToTransaction(list);

        // 3. Create and feel the map with all OperationHandler implementations
        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperation());
        operationHandlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperation());
        operationHandlers.put(FruitTransaction.Operation.RETURN, new ReturnOperation());
        operationHandlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperation());
        OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlers);

        // 4. Process the incoming transactions with applicable OperationHandler implementations
        ShopService shopService = new ShopServiceImpl(operationStrategy);
        shopService.process(listoftransaction);
        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        String resultingReport = reportGenerator.getReport();

        // 6. Write the received report into the destination file
        DBWriter fileWriter = new DBWriterImpl( "src/main/java/core/basesyntax/resources/finalReport.csv");
        fileWriter.write(resultingReport);

    }

}
