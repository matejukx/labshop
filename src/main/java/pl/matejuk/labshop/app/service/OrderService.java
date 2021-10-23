package pl.matejuk.labshop.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.matejuk.labshop.app.entity.Order;
import pl.matejuk.labshop.app.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Optional<Order> find(UUID id) {
        return repository.find(id);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public void create(Order entity) {
        repository.create(entity);
    }

    public void delete(Order entity) {
        repository.delete(entity);
    }

    public void update(Order entity) {
        repository.update(entity);
    }
}
