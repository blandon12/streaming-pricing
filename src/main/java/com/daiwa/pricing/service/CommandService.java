package com.daiwa.pricing.service;

import com.daiwa.pricing.model.Histories;

public interface CommandService {

    String createHistory(Integer identifier, Integer timestamp, String data, Histories histories);

    String updateObservation(Integer identifier, Integer timestamp, String data, Histories histories);

    String getObservation(Integer identifier, Integer timestamp, Histories histories);

    String latestObservation(Integer identifier, Histories histories);

    String deleteHistory(Integer identifier, Histories histories);

    String deleteObservation(Integer identifier, Integer timestamp, Histories histories);
}
