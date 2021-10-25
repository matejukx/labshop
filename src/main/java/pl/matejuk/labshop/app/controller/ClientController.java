package pl.matejuk.labshop.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.matejuk.labshop.app.dto.*;
import pl.matejuk.labshop.app.entity.Client;
import pl.matejuk.labshop.app.service.ClientService;
import pl.matejuk.labshop.app.service.OrderService;

import java.util.UUID;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    private final ClientService clientService;
    private final OrderService orderService;

    @Autowired
    public ClientController(ClientService clientService, OrderService orderService){
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<GetClientsResponse> getClients(){
        return ResponseEntity.ok(GetClientsResponse.entityToDtoMapper().apply(clientService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetClientResponse> getClient(@PathVariable("id") UUID id){
        var clientEntity = this.clientService.find(id);
        return clientEntity.map(value -> ResponseEntity.ok(GetClientResponse.entityToDtoMapper().apply(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("{id}/orders")
    public ResponseEntity<GetOrdersResponse> getClientOrders(@PathVariable("id") UUID id){
        var clientEntity = this.clientService.find(id);
        if (clientEntity.isPresent()){
            var orders = this.orderService.findAllByClient(clientEntity.get());
            return ResponseEntity.ok(GetOrdersResponse.entityToDtoMapper().apply(orders));
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody CreateClientRequest request, UriComponentsBuilder builder){
        Client client = CreateClientRequest.dtoToEntityMapper().apply(request);
        client = this.clientService.create(client);
        return ResponseEntity.created(builder.pathSegment("api", "clients", "{id}").buildAndExpand(client.getId()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateClient(@RequestBody UpdateClientRequest request, @PathVariable("id") UUID id){
        var client = this.clientService.find(id);
        if (client.isPresent()){
            UpdateClientRequest.dtoToEntityMapper().apply(client.get(), request);
            this.clientService.update(client.get());
            return ResponseEntity.accepted().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") UUID id){
        var client = this.clientService.find(id);
        if (client.isPresent()){
            this.clientService.delete(client.get());
            return ResponseEntity.accepted().build();
        }
        else return ResponseEntity.notFound().build();
    }
}
