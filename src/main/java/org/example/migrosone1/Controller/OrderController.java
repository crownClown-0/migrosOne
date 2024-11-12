package org.example.migrosone1.Controller;

import org.example.migrosone1.Service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/average/all")
    public double getAverageOrderValueForAllCashOnDeliveryOrders() {
        return orderService.calculateAverageOrderValueForAllCashOnDeliveryOrders();
    }

    @GetMapping("/average/date-range")
    public double getAverageOrderValueForCashOnDeliveryOrdersInDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return orderService.calculateAverageOrderValueForCashOnDeliveryOrdersInDateRange(startDate, endDate);
    }

    @GetMapping("/average/date-range/exclude-cancellations")
    public double getAverageOrderValueExcludingCancellationsForCashOnDeliveryOrdersInDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return orderService.calculateAverageOrderValueExcludingCancellationsForCashOnDeliveryOrdersInDateRange(startDate, endDate);
    }

    @GetMapping("/average/date-range/exclude-cancellations-discounts")
    public double getAverageOrderValueExcludingDiscountsAndCancellationsForCashOnDeliveryOrdersInDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return orderService.calculateAverageOrderValueExcludingDiscountsAndCancellationsForCashOnDeliveryOrdersInDateRange(startDate, endDate);
    }
}
