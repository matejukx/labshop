package pl.matejuk.labshop.app.dto;

import lombok.*;
import pl.matejuk.labshop.app.entity.Client;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateClientRequest {
    private String name;
    private String surname;

    public static BiFunction<Client, UpdateClientRequest, Client> dtoToEntityMapper(){
        return (client, updateClientRequest) ->{
            client.setName(updateClientRequest.getName());
            client.setSurname(updateClientRequest.getSurname());
            return client;
        };
    }
}
