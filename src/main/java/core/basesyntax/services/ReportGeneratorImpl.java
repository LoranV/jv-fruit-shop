package core.basesyntax.services;

import core.basesyntax.storage.Storage;

import java.util.Map;

public class ReportGeneratorImpl implements ReportGenerator {
    @Override
    public String getReport() {
        String header = "fruit,quantity\n";
        StringBuilder sb = new StringBuilder();
        sb.append(header);
        for (Map.Entry<String, Integer> entry : Storage.current_storage.entrySet()) {
            sb.append(entry.getKey())
                    .append(",")
                    .append(entry.getValue())
                    .append("\n");
        }
        return sb.toString();
    }
}
