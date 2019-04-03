package com.daiwa.pricing.service;

import com.daiwa.pricing.enum_keywords.CommandKeyWords;
import com.daiwa.pricing.model.Histories;
import com.daiwa.pricing.model.History;
import com.daiwa.pricing.model.Observation;
import org.springframework.stereotype.Service;

@Service
public class CommandServiceImpl implements CommandService {

    @Override
    public String createHistory(Integer identifier, Integer timestamp, String data, Histories histories) {
        if (histories.containHistoryIdentifier(identifier)) {
            throw new RuntimeException("The history exists for identifier " + identifier);
        } else {
            History history = new History();
            history.addObservation(timestamp, new Observation(data));
            histories.addHistory(identifier, history);

            return data;
        }
    }

    @Override
    public String updateObservation(Integer identifier, Integer timestamp, String data, Histories histories) {

        if (!histories.containHistoryIdentifier(identifier)) {
            throw new RuntimeException("No history exists for identifier " + identifier);
        } else {
            History history = histories.getHistory(identifier);
            history.addObservation(timestamp, new Observation(data));
            Observation observation = history.getPriorObservationAfterOperation(timestamp, CommandKeyWords.UPDATE);
            return observation.getData();
        }
    }

    @Override
    public String getObservation(Integer identifier, Integer timestamp, Histories histories) {

        if (!histories.containHistoryIdentifier(identifier)) {
            throw new RuntimeException("No history exists for identifier " + identifier.toString());
        } else {
            History history = histories.getHistory(identifier);

            return history.getPriorObservation(timestamp).getData();
        }
    }

    @Override
    public String latestObservation(Integer identifier, Histories histories) {

        if (!histories.containHistoryIdentifier(identifier)) {
            throw new RuntimeException("No history exists for identifier " + identifier.toString());
        } else {
            History history = histories.getHistory(identifier);

            return history.getLastObservation().getData();
        }

    }

    @Override
    public String deleteHistory(Integer identifier, Histories histories) {

        if (!histories.containHistoryIdentifier(identifier)) {
            throw new RuntimeException("No history exists for identifier " + identifier.toString());
        } else {
            History history = histories.getHistory(identifier);
            Observation lastObservation = history.getLastObservation();
            histories.deleteHistory(identifier);

            return lastObservation.getData();
        }
    }

    @Override
    public String deleteObservation(Integer identifier, Integer timestamp, Histories histories) {
        if (!histories.containHistoryIdentifier(identifier)) {
            throw new RuntimeException("No history exists for identifier " + identifier.toString());
        } else {
            History history = histories.getHistory(identifier);

            if (!history.containObservationFromTimestamp(timestamp)) {
                throw new RuntimeException("No available observation exists");
            }
            history.deleteObservationFromTimestamp(timestamp);

            return history.getPriorObservationAfterOperation(timestamp, CommandKeyWords.DELETE).getData();
        }
    }
}
