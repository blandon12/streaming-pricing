package com.daiwa.pricing.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HistoryTest {

    private History history;

    @Before
    public void setUp() throws Exception {
        history = new History();
    }

    @Test
    public void addObservationWillPutNewEntryToTreeMap() {
        history.addObservation(100, new Observation("abc"));
        assertEquals("abc", history.getLastObservation().getData());
    }

    @Test
    public void getPriorObservationWillReturnGreatestObservationPriorTimestamp() {
        history.addObservation(100, new Observation("aaa"));
        history.addObservation(101, new Observation("bbb"));
        history.addObservation(102, new Observation("ccc"));

        assertEquals("bbb", history.getPriorObservation(101).getData());
    }

    @Test (expected = RuntimeException.class)
    public void getPriorObservationThrowExceptionIfNoAvailableObservation() {
        history.getPriorObservation(101);
    }

    @Test (expected = RuntimeException.class)
    public void getlatestObservationThrowExceptionIfNoAvailableObservation() {
        history.getLastObservation();
    }

    @Test (expected = RuntimeException.class)
    public void getPriorObservationAfterOperationThrowExceptionIfNoAvailableObservation() {
        history.getPriorObservation(101);
    }

    @Test
    public void getLatestObservationReturnLastEntryInHistory() {
        history.addObservation(100, new Observation("aaa"));
        history.addObservation(101, new Observation("bbb"));
        history.addObservation(102, new Observation("ccc"));

        assertEquals("ccc", history.getLastObservation().getData());
    }

    @Test
    public void deleteObservationFromTimestampRemoveEntryFromHistory() {
        history.addObservation(100, new Observation("aaa"));
        history.addObservation(101, new Observation("bbb"));

        history.deleteObservationFromTimestamp(101);
        assertEquals("aaa", history.getLastObservation().getData());
    }

    @Test
    public void containObservationFromTimestampReturnTrueIfCeilingKeyExist() {
        history.addObservation(100, new Observation("aaa"));
        history.addObservation(101, new Observation("bbb"));

        assertTrue(history.containObservationFromTimestamp(100));
    }
}