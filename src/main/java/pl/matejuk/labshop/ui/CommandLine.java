package pl.matejuk.labshop.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.matejuk.labshop.app.service.ClientService;
import pl.matejuk.labshop.app.service.OrderService;

import java.util.Arrays;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {

    private final CommandLineHandler handler;

    @Autowired
    public CommandLine(CommandLineHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run(String... args) throws Exception {
        var isRunning = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input 'help' to list available commands!");
        while(isRunning){
            System.out.println("What do you want to do?");
            var input = scanner.nextLine();
            isRunning = handler.handle(input);
        }
    }
}
