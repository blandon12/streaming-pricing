package com.daiwa.pricing.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HistoriesTest {

    private Histories histories;

    @Before
    public void setUp() throws Exception {
        histories = new Histories();
    }

    @Test
    public void addHistoryPutEntryToHistories() {
        histories.addHistory(100, new History());
        assertTrue(histories.containHistoryIdentifier(100));
    }

    @Test
    public void getHistoryReturnHistoryForIdentifierGiven() {
        History h1 = new History();
        History h2 = new History();
        histories.addHistory(100, h1);
        histories.addHistory(102, h2);

        assertEquals(h1, histories.getHistory(100));
    }

    @Test
    public void deleteHistoryRemoveEntryFromHistories() {
        histories.addHistory(100, new History());
        histories.deleteHistory(100);
        assertNull(histories.getHistory(100));
    }

    @Test
    public void containHistoryIdentifierReturnFalseIfNoIdentifierExists() {
        assertFalse(histories.containHistoryIdentifier(100));
    }
}