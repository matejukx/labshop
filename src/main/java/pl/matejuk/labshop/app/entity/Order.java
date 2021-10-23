package pl.matejuk.labshop.app.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Order {
    private UUID id;
    private Client client;
    private int price;
    private LocalDate date;
}
