package org.example.migrosone1.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_products")
public class OrderProduct {
    @Id
    private Long OrderProductId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private double productPrice;
    private int quantity;
}
