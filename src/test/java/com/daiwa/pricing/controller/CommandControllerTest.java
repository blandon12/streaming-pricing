package com.daiwa.pricing.controller;

import com.daiwa.pricing.console.ConsoleService;
import com.daiwa.pricing.service.CommandService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandControllerTest {

    private CommandController commandController;

    @Mock
    private ConsoleService consoleService;

    @Mock
    private CommandService commandService;

    @Before
    public void setUp() throws Exception {
        commandController = new CommandController(commandService, consoleService);
    }

    @Test
    public void runCommandWillCallCreateHistoryForCreateCommand() {

        String[] commandArr = {"CREATE", "1", "100", "ABC"};
        commandController.runCommand(commandArr);
        verify(commandService, times(1))
            .createHistory(eq(1), eq(100), eq("ABC"), any());
    }

    @Test (expected = RuntimeException.class)
    public void runCommandThrowExceptionIfMissingArguments() {
        String[] commandArr = {"CREATE", "1", "100"};
        commandController.runCommand(commandArr);
    }

    @Test
    public void runCommandWillCallUpdateObservationForUpdateCommand() {
        String[] commandArr = {"UPDATE", "1", "100", "ABC"};
        commandController.runCommand(commandArr);
        verify(commandService, times(1))
            .updateObservation(eq(1), eq(100), eq("ABC"), any());
    }

    @Test
    public void runCommandWillCallGetObservationForGetCommand() {
        String[] commandArr = {"GET", "1", "100"};
        commandController.runCommand(commandArr);
        verify(commandService, times(1))
            .getObservation(eq(1), eq(100), any());
    }

    @Test
    public void runCommandWillCallLatestObservationForLatestCommand() {
        String[] commandArr = {"LATEST", "1"};
        commandController.runCommand(commandArr);
        verify(commandService, times(1))
            .latestObservation(eq(1), any());
    }

    @Test
    public void runCommandWillCallDeleteObservationForDeleteCommandWithTimestamp() {
        String[] commandArr = {"DELETE", "1", "100"};
        commandController.runCommand(commandArr);
        verify(commandService, times(1))
            .deleteObservation(eq(1), eq(100), any());
    }

    @Test (expected = RuntimeException.class)
    public void runCommandThrowExceptionForInvalidCommandKeyWord() {
        String[] commandArr = {"INVALID", "1", "100"};
        commandController.runCommand(commandArr);
    }
}