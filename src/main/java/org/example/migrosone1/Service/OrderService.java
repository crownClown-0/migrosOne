package org.example.migrosone1.Service;

import org.example.migrosone1.Entity.Order;
import org.example.migrosone1.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public double calculateAverageOrderValueForAllCashOnDeliveryOrders() {
        List<Order> orders = orderRepository.findAllCashOnDeliveryOrders();
        return calculateAverageOrderValue(orders);
    }

    public double calculateAverageOrderValueForCashOnDeliveryOrdersInDateRange(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderRepository.findCashOnDeliveryOrdersByDateRange(startDate, endDate);
        return calculateAverageOrderValue(orders);
    }

    public double calculateAverageOrderValueExcludingCancellationsForCashOnDeliveryOrdersInDateRange(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderRepository.findCashOnDeliveryOrdersWithoutCancellationsByDateRange(startDate, endDate);
        return calculateAverageOrderValue(orders);
    }

    public double calculateAverageOrderValueExcludingDiscountsAndCancellationsForCashOnDeliveryOrdersInDateRange(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderRepository.findCashOnDeliveryOrdersWithoutDiscountsAndCancellationsByDateRange(startDate, endDate);
        return calculateAverageOrderValue(orders);
    }

    private double calculateAverageOrderValue(List<Order> orders) {
        double totalOrderValue = 0.0;
        int totalItems = 0;

        for (Order order : orders) {
            double orderTotal = order.getOrderProducts().stream()
                    .mapToDouble(item -> item.getProductPrice() * item.getQuantity())
                    .sum();
            totalOrderValue += orderTotal;
            totalItems += order.getOrderProducts().size();
        }

        return totalItems > 0 ? totalOrderValue / totalItems : 0.0;
    }
}
