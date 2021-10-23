package pl.matejuk.labshop.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.matejuk.labshop.app.entity.Client;
import pl.matejuk.labshop.app.entity.Order;
import pl.matejuk.labshop.app.service.ClientService;
import pl.matejuk.labshop.app.service.OrderService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineHandler {

    private final ClientService clientService;
    private final OrderService orderService;
    private final Scanner scanner;

    @Autowired
    public CommandLineHandler(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);
    }

    boolean handle(String input){
        switch (input){
            case "help":
                return displayCommands();
            case "categories":
                return listCategories();
            case "list orders":
                return listAllElements("Orders");
            case "list clients":
                return listAllElements("Clients");
            case "add order":
                return addOrder();
            case "delete order":
                return deleteOrder();
            case "add client":
                return addClient();
            case "delete client":
                return deleteClient();
            case "exit":
                return exit();
            default:
                System.out.println("No such command is handled! type 'help' to list all commands!");
                return true;
        }
    }
    boolean displayCommands(){
        System.out.println("Available commands:");
        System.out.println("categories - list all categories");
        System.out.println("list 'category name' - list all elements in a category");
        System.out.println("add 'category name' - add a new element to a category");
        System.out.println("delete 'category name' - delete a element in a category");
        System.out.println("exit - exit the application");
        return true;
    }
    boolean listCategories(){
        System.out.println("Available categories: \n Orders | Clients ");
        return true;
    }
    boolean listAllElements(String category){
        if (category.equals("orders") || category.equals("Orders")){
            orderService.findAll().forEach(System.out::println);
            }
        else if(category.equals("clients") || category.equals("Clients")) {
            clientService.findAll().forEach(System.out::println);
        }
        else{
            System.out.println("No such category!");
        }
        return true;
    }

    boolean addClient(){
        var newId = UUID.randomUUID();
        System.out.println("Please enter client name:");
        var name = scanner.nextLine();
        System.out.println("Please enter client surname:");
        var surname = scanner.nextLine();

        Client client = Client.builder()
                .id(newId)
                .name(name)
                .surname(surname)
                .build();
        this.clientService.create(client);
        System.out.println("Created a new Client:");
        System.out.println(client);

        return true;
    }

    boolean addOrder(){
        System.out.println("Please enter the price");
        var price =Integer.parseInt(scanner.nextLine());

        System.out.println("Please enter client id:");
        var clientId = UUID.fromString(scanner.nextLine());

        var client = this.clientService.find(clientId);

        client.ifPresentOrElse(
                clientResult -> {
                    Order order = Order.builder()
                            .id(UUID.randomUUID())
                            .price(price)
                            .date(LocalDate.now())
                            .client(clientResult)
                            .build();
                    this.orderService.create(order);
                    System.out.println("Created new Order:");
                    System.out.println(order);
                },
                () -> System.out.println("No such client exists!")
        );
        return true;
    }

    boolean deleteOrder(){
        System.out.println("Please enter ID of the order you want to delete");
        var id = UUID.fromString(scanner.nextLine());
        var result = this.orderService.find(id);
        result.ifPresentOrElse(
                order -> {
                    System.out.println("Deleting Order");
                    System.out.println(order);
                    this.orderService.delete(order);
                },
                () -> System.out.println("No such Order exists!")
        );
        return true;
    }

    boolean deleteClient(){
        System.out.println("Please enter the ID of Client you want to delete");
        System.out.println("Be advised that this also deletes all of the clients orders!");

        var id = UUID.fromString(scanner.nextLine());
        var result = this.clientService.find(id);
        result.ifPresentOrElse(
                client -> {
                    this.orderService.findAll().stream()
                            .filter(order -> order.getClient()
                                    .getId().equals(client.getId()))
                            .forEach(this.orderService::delete);
                    System.out.println("Deleting Client");
                    System.out.println(client);
                    this.clientService.delete(client);
                },
                () -> System.out.println("No such Client exists!")
        );
        return true;
    }

    boolean exit(){
        return false;
    }
}
