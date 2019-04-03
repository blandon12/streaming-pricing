package com.daiwa.pricing.console;

import com.daiwa.pricing.enum_keywords.ResultKeyWords;
import org.springframework.stereotype.Service;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    @Override
    public void successOutput(String value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ResultKeyWords.OK.toString())
            .append(" ").append(value);

        System.out.println(stringBuilder.toString());
    }

    @Override
    public void failureOutput(String errorMessage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ResultKeyWords.ERR.toString())
            .append(" ").append(errorMessage);

        System.err.println(stringBuilder.toString());
    }

    @Override
    public void displayCommandTips() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nInput the following commands:\n")
            .append("(1) CREATE <id> <timestamp> <data>\n")
            .append("(2) UPDATE <id> <timestamp> <data>\n")
            .append("(3) DELETE <id> [timestamp]\n")
            .append("(4) GET <id> <timestamp>\n")
            .append("(5) LATEST <id>\n")
            .append("(6) QUIT\n")
            .append("=================\n");

        System.out.println(stringBuilder.toString());
    }
}
