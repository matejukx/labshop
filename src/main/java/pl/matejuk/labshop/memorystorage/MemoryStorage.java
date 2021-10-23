package pl.matejuk.labshop.memorystorage;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.matejuk.labshop.app.entity.Client;
import pl.matejuk.labshop.app.entity.Order;

import java.util.*;

@Log
@Component
public class MemoryStorage {
    /**
     * Set of all clients.
     */
    private Set<Client> clients = new HashSet<>();

    /**
     * Set of all orders.
     */
    private Set<Order> orders  = new HashSet<>();

    public synchronized List<Client> findAllClients(){
        return new ArrayList<>(clients);
    }

    public synchronized Optional<Client> findClient(UUID id){
        return clients.stream().filter(client -> client.getId().equals(id)).findFirst();
    }

    public synchronized void addClient(Client client){
        findClient(client.getId()).ifPresentOrElse(
            result -> {
                throw new IllegalArgumentException(
                        String.format("The client id \"%s\" is not unique", result.getId()));
            },
                () -> clients.add(client)
        );
    }

    public synchronized void deleteClient(UUID id){
        findClient(id).ifPresentOrElse(
                result -> clients.remove(result),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The client with id \"%s\" does not exist", id)
                    );
                }
        );
    }

    public synchronized void updateClient(Client client){
        findClient(client.getId()).ifPresentOrElse(
                result -> {
                    clients.remove(result);
                    clients.add(client);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The client with id \"%s\" does not exist", client.getId())
                    );
                }
        );
    }


    public synchronized List<Order> findAllOrders(){
        return new ArrayList<>(orders);
    }

    public synchronized Optional<Order> findOrder(UUID id){
        return orders.stream().filter(client -> client.getId().equals(id) ).findFirst();
    }

    public synchronized void addOrder(Order order){
        findOrder(order.getId()).ifPresentOrElse(
                result -> {
                    throw new IllegalArgumentException(
                            String.format("The order id \"%s\" is not unique", result.getId()));
                },
                () -> orders.add(order)
        );
    }

    public synchronized void deleteOrder(UUID id){
        findOrder(id).ifPresentOrElse(
                result -> orders.remove(result),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The order with id \"%s\" does not exist", id)
                    );
                }
        );
    }

    public synchronized void updateOrder(Order order){
        findOrder(order.getId()).ifPresentOrElse(
                result -> {
                    orders.remove(result);
                    orders.add(order);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The order with id \"%s\" does not exist", order.getId())
                    );
                }
        );
    }
}
