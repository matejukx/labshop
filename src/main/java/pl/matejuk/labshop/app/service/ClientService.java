package pl.matejuk.labshop.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.matejuk.labshop.app.entity.Client;
import pl.matejuk.labshop.app.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Optional<Client> find(UUID id) {
        return repository.find(id);
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public void create(Client entity) {
        repository.create(entity);
    }

    public void delete(Client entity) {
        repository.delete(entity);
    }

    public void update(Client entity) {
        repository.update(entity);
    }
}
