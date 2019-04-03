package com.daiwa.pricing.model;

import com.daiwa.pricing.enum_keywords.CommandKeyWords;

import java.util.Map;
import java.util.TreeMap;

public class History {

    private TreeMap<Integer, Observation> treeMap;

    public History() {
        treeMap = new TreeMap<>();
    }

    public void addObservation(Integer timestamp, Observation observation) {
        treeMap.put(timestamp, observation);
    }

    public Observation getPriorObservation(Integer timestamp) {

        Map.Entry<Integer, Observation> entry = treeMap.floorEntry(timestamp);
        if (entry == null) {
            throw new RuntimeException("No observation exists for timestamp " + timestamp.toString());
        }
        return entry.getValue();
    }

    public Observation getLastObservation() {
        Map.Entry<Integer, Observation> entry = treeMap.lastEntry();
        if (entry == null) {
            throw new RuntimeException("No observation exists");
        }
        return entry.getValue();
    }

    public void deleteObservationFromTimestamp(Integer timestamp) {

        Integer nextTimestamp = treeMap.ceilingKey(timestamp);
        if (nextTimestamp != null) {
            treeMap.remove(nextTimestamp);
            deleteObservationFromTimestamp(nextTimestamp);
        }
    }

    public boolean containObservationFromTimestamp(Integer timestamp) {
        return treeMap.ceilingKey(timestamp) != null;
    }

    public Observation getPriorObservationAfterOperation(Integer timestamp, CommandKeyWords command) {

        Map.Entry<Integer, Observation> entry = treeMap.floorEntry(timestamp);
        if (entry == null) {
            throw new RuntimeException(command.toString() + " observations successfully! But no observation is available prior the timestamp " + timestamp.toString());
        }
        return entry.getValue();
    }
}
