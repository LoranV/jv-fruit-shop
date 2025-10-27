package core.basesyntax.services.impl;

import core.basesyntax.services.ReportGenerator;
import java.util.Map;

public class ReportGeneratorImpl implements ReportGenerator {
    @Override
    public String getReport(Map<String, Integer> transactions) {
        String header = "fruit,quantity\n";
        StringBuilder sb = new StringBuilder();
        sb.append(header);
        for (Map.Entry<String, Integer> entry : transactions.entrySet()) {
            sb.append(entry.getKey())
                    .append(",")
                    .append(entry.getValue())
                    .append("\n");
        }
        return sb.toString();
    }
}
