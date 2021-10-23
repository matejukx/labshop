package pl.matejuk.labshop.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.matejuk.labshop.memorystorage.MemoryStorage;
import pl.matejuk.labshop.repository.Repository;
import pl.matejuk.labshop.app.entity.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Repository
public class ClientRepository implements Repository<Client, UUID> {

    @Autowired
    private MemoryStorage storage;

    @Override
    public Optional<Client> find(UUID id) {
        return storage.findClient(id);
    }

    @Override
    public List<Client> findAll() {
        return storage.findAllClients();
    }

    @Override
    public void create(Client entity) {
        storage.addClient(entity);
    }

    @Override
    public void delete(Client entity) {
        storage.deleteClient(entity.getId());
    }

    @Override
    public void update(Client entity) {
        storage.updateClient(entity);
    }
}
