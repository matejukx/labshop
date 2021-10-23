package pl.matejuk.labshop.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.matejuk.labshop.app.entity.Client;
import pl.matejuk.labshop.app.repository.ClientRepository;
import pl.matejuk.labshop.app.repository.IClientRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final IClientRepository repository;

    @Autowired
    public ClientService(IClientRepository repository) {
        this.repository = repository;
    }

    public Optional<Client> find(UUID id) {
        return repository.findById(id);
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Client create(Client entity) {
       return repository.save(entity);
    }

    @Transactional
    public void delete(Client entity) {
        repository.delete(entity);
    }

    @Transactional
    public void update(Client entity) {
        repository.save(entity);
    }
}
