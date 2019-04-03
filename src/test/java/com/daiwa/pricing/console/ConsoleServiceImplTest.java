package com.daiwa.pricing.console;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ConsoleServiceImplTest {

    private ConsoleService consoleService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUp() throws Exception {
        consoleService = new ConsoleServiceImpl();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void successOutputStartWithOKFollowingReturnValue() {
        String value = "ABC";
        consoleService.successOutput(value);
        assertEquals("OK ABC\n", outContent.toString());
    }

    @Test
    public void failureOutputStartWithErrFollowingErrorMessage() {
        String errorMessage = "No history exists";
        consoleService.failureOutput(errorMessage);
        assertEquals("ERR No history exists\n", errContent.toString());
    }
}