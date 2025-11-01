package core.basesyntax.services.impl;

import core.basesyntax.services.ReportGenerator;
import java.util.Map;

public class ReportGeneratorImpl implements ReportGenerator {
    @Override
    public String getReport(Map<String, Integer> transactions) {
        final String String_Separator = ",";
        final String Header = "fruit,quantity";
        StringBuilder sb = new StringBuilder();
        sb.append(Header);
        sb.append(System.lineSeparator());
        for (Map.Entry<String, Integer> entry : transactions.entrySet()) {
            sb.append(entry.getKey())
                    .append(String_Separator)
                    .append(entry.getValue())
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
