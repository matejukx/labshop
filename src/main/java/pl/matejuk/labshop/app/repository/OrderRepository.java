package pl.matejuk.labshop.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.matejuk.labshop.memorystorage.MemoryStorage;
import pl.matejuk.labshop.repository.Repository;
import pl.matejuk.labshop.app.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Repository
public class OrderRepository implements Repository<Order, UUID> {

    @Autowired
    private MemoryStorage storage;

    @Override
    public Optional<Order> find(UUID id) {
        return storage.findOrder(id);
    }

    @Override
    public List<Order> findAll() {
        return storage.findAllOrders();
    }

    @Override
    public void create(Order entity) {
        storage.addOrder(entity);
    }

    @Override
    public void delete(Order entity) {
        storage.deleteOrder(entity.getId());
    }

    @Override
    public void update(Order entity) {
        storage.updateOrder(entity);
    }
}
