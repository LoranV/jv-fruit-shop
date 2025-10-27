package core.basesyntax.services;

import core.basesyntax.storage.Storage;
import java.util.Map;

public class ReportGeneratorImpl implements ReportGenerator {
    @Override
    public String getReport() {
        String header = "fruit,quantity\n";
        Map<String, Integer> storage = Storage.getCurrent_storage();
        StringBuilder sb = new StringBuilder();
        sb.append(header);
        for (Map.Entry<String, Integer> entry : storage.entrySet()) {
            sb.append(entry.getKey())
                    .append(",")
                    .append(entry.getValue())
                    .append("\n");
        }
        return sb.toString();
    }
}
