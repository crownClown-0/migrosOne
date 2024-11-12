package org.example.migrosone1.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long orderId;

    private Boolean cashOnDelivery;
    private LocalDate orderDate;
    private Boolean cancelled;
    private Boolean discountUsed;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> OrderProducts;

}