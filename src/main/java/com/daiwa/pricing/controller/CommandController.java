package com.daiwa.pricing.controller;

import com.daiwa.pricing.enum_keywords.CommandKeyWords;
import com.daiwa.pricing.console.ConsoleService;
import com.daiwa.pricing.model.Histories;
import com.daiwa.pricing.service.CommandService;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class CommandController {

    private Histories histories;

    private CommandService commandService;

    private ConsoleService consoleService;

    private Scanner scanner = new Scanner(System.in);

    public CommandController(CommandService commandService, ConsoleService consoleService) {
        this.commandService = commandService;
        this.consoleService = consoleService;
        this.histories = new Histories();
    }

    public void process() {

        consoleService.displayCommandTips();

        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.equals(CommandKeyWords.QUIT.toString())) {
                break;
            }

            String[] commandArr = line.split("\\s+");
            try {
                runCommand(commandArr);
            } catch (RuntimeException e) {
                consoleService.failureOutput(e.getMessage());
            }
        }
    }

    public void runCommand(String[] commandArr) {

        String command = commandArr[0];
        Integer id = commandArr.length > 1 ? Integer.valueOf(commandArr[1]) : null;
        Integer timestamp = commandArr.length > 2 ? Integer.valueOf(commandArr[2]) : null;

        if (command.equals(CommandKeyWords.CREATE.toString())) {

            if (id == null || timestamp == null || commandArr[3] == null) {
                throw new RuntimeException("Invalid parameters");
            }
            consoleService.successOutput(commandService.createHistory(id, timestamp, commandArr[3], histories));

        } else if (command.equals(CommandKeyWords.UPDATE.toString())) {

            if (id == null || timestamp == null || commandArr[3] == null) {
                throw new RuntimeException("Invalid parameters");
            }
            consoleService.successOutput(commandService.updateObservation(id, timestamp, commandArr[3], histories));

        } else if (command.equals(CommandKeyWords.GET.toString())) {

            if (id == null || timestamp == null) {
                throw new RuntimeException("Invalid parameters");
            }
            consoleService.successOutput(commandService.getObservation(id, timestamp, histories));

        } else if (command.equals(CommandKeyWords.LATEST.toString())) {

            if (id == null) {
                throw new RuntimeException("Invalid parameters");
            }
            consoleService.successOutput(commandService.latestObservation(id, histories));

        } else if (command.equals(CommandKeyWords.DELETE.toString())) {

            if (id == null) {
                throw new RuntimeException("Invalid parameters");
            }

            if (timestamp != null) {
                consoleService.successOutput(commandService.deleteObservation(id, timestamp, histories));
            } else {
                consoleService.successOutput(commandService.deleteHistory(id, histories));
            }

        } else {
            throw new RuntimeException("Invalid parameters");
        }

    }
}
