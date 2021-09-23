package pl.trollcraft.prison.service.localeService;

import java.util.HashMap;
import java.util.Map;

public class LocaleService {

    private final Map<String, String> localeMap;

    public LocaleService() {
        this.localeMap = new HashMap<>();
    }

    public void add(String key, String text) {
        this.localeMap.put(key, text);
    }

    public String get(String key) {
        String text = localeMap.get(key);
        return text != null ? text : "";
    }
}
