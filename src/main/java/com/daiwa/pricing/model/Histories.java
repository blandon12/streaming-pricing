package com.daiwa.pricing.model;

import java.util.HashMap;
import java.util.Map;

public class Histories {

    private Map<Integer, History> historyMap;

    public Histories() {
        historyMap = new HashMap<>();
    }

    public void addHistory(Integer identifier, History history) {
        historyMap.put(identifier, history);
    }

    public boolean containHistoryIdentifier(Integer identifier) {
        return historyMap.containsKey(identifier);
    }

    public History getHistory(Integer identifier) {
        return historyMap.get(identifier);
    }

    public void deleteHistory(Integer identifier) {
        historyMap.remove(identifier);
    }
}
