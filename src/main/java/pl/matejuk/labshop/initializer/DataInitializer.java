package pl.matejuk.labshop.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.matejuk.labshop.app.entity.Client;
import pl.matejuk.labshop.app.entity.Order;
import pl.matejuk.labshop.app.service.ClientService;
import pl.matejuk.labshop.app.service.OrderService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class DataInitializer {

    private final ClientService clientService;
    private final OrderService orderService;

    @Autowired
    public DataInitializer(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @PostConstruct
    void init(){
        Client client1 = Client.builder()
                .name("Adam")
                .surname("Sandler")
                .build();
        client1 = this.clientService.create(client1);

        Client client2 = Client.builder()
                .name("Max")
                .surname("Kolonko")
                .build();
        client2 = this.clientService.create(client2);

        Client client3 = Client.builder()
                .name("Chuck")
                .surname("Norris")
                .build();
        client3 = this.clientService.create(client3);

        Order order1 = Order.builder()
                .price(120)
                .date(LocalDate.of(2021,5,5))
                .client(client1)
                .description("Paint restoration")
                .build();
        this.orderService.create(order1);

        Order order2 = Order.builder()
                .price(121300)
                .date(LocalDate.of(2021,3,15))
                .client(client1)
                .description("Interior cleaning")
                .build();
        this.orderService.create(order2);

        Order order3 = Order.builder()
                .price(10000)
                .date(LocalDate.of(2021,5,13))
                .client(client1)
                .description("Wheels detailing")
                .build();
        this.orderService.create(order3);

        Order order4 = Order.builder()
                .price(1000)
                .date(LocalDate.of(2021,4,11))
                .client(client1)
                .description("Interior cleaning")
                .build();
        this.orderService.create(order4);

        Order order5 = Order.builder()
                .price(1000)
                .date(LocalDate.of(2021,3,2))
                .client(client2)
                .description("Paint restoration")
                .build();
        this.orderService.create(order5);

        Order order6 = Order.builder()
                .price(1000)
                .date(LocalDate.of(2021,2,5))
                .client(client3)
                .description("Wheels detailing")
                .build();
        this.orderService.create(order6);

        Order order7 = Order.builder()
                .price(1000)
                .date(LocalDate.of(2021,1,23))
                .client(client3)
                .description("Interior cleaning")
                .build();
        this.orderService.create(order7);
    }

}
