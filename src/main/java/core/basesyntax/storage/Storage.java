package core.basesyntax.storage;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private static Map<String, Integer> current_storage = new HashMap<>();

    public static Map<String, Integer> getCurrent_storage() {
        return current_storage;
    }
}
