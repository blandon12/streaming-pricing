package com.daiwa.pricing.console;

public interface ConsoleService {

    void successOutput(String value);

    void failureOutput(String errorMessage);

    void displayCommandTips();
}
