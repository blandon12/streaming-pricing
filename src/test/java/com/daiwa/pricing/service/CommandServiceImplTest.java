package com.daiwa.pricing.service;

import com.daiwa.pricing.model.Histories;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandServiceImplTest {
    
    private CommandService commandService;

    @Before
    public void setUp() throws Exception {
        commandService = new CommandServiceImpl();
    }

    @Test
    public void createHistoryAddElementToHistories() {

        Histories histories = new Histories();
        commandService.createHistory(1, 100, "abc", histories);

        assertTrue(histories.containHistoryIdentifier(1));
    }

    @Test (expected = RuntimeException.class)
    public void createHistoryThrowExceptionIfHistoryExists() {
        Histories histories = new Histories();
        commandService.createHistory(1, 100, "abc", histories);
        commandService.createHistory(1, 101, "bbc", histories);
    }

    @Test
    public void updateObservationAddElementToHistory() {
        Histories histories = new Histories();
        commandService.createHistory(1, 100, "abc", histories);
        commandService.updateObservation(1, 101, "bbc", histories);

        assertEquals("bbc", histories.getHistory(1).getLastObservation().getData());
    }

    @Test
    public void getObservationReturnObservationPriorTimestamp() {
        Histories histories = new Histories();
        commandService.createHistory(1, 100, "abc", histories);

        assertEquals("abc", commandService.getObservation(1, 101, histories));
    }

    @Test
    public void latestObservationReturnLastObservationInHistory() {
        Histories histories = new Histories();
        commandService.createHistory(1, 100, "abc", histories);
        commandService.updateObservation(1, 101, "bbc", histories);

        assertEquals("bbc", commandService.latestObservation(1, histories));

    }

    @Test
    public void deleteHistoryRemoveElementFromHistories() {
        Histories histories = new Histories();
        commandService.createHistory(1, 100, "abc", histories);
        commandService.deleteHistory(1, histories);

        assertFalse(histories.containHistoryIdentifier(1));
    }

    @Test
    public void deleteObservationRemoveObservationAfterTimestamp() {
        Histories histories = new Histories();
        commandService.createHistory(1, 100, "abc", histories);
        commandService.updateObservation(1, 101, "bbc", histories);
        commandService.deleteObservation(1, 101, histories);

        assertEquals("abc", commandService.latestObservation(1, histories));
    }
}