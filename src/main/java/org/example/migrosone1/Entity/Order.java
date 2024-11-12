package org.example.migrosone1.Entity;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "orders")
public class Orders {
    @Id
    private Long orderId;
    private String paymentMethod;
    // Diğer alanlar...

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
    // Getter ve Setter'lar
}