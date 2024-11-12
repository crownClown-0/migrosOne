package org.example.migrosone1.Repository;

import org.example.migrosone1.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //Tüm kapıda ödeme seçili siparişler
    @Query("SELECT o FROM Order o WHERE o.cashOnDelivery = true")
    List<Order> findAllCashOnDeliveryOrders();

    //Belirtilen tarih aralığındaki tüm kapıda ödeme seçili siparişler
    @Query("SELECT o FROM Order o WHERE o.cashOnDelivery = true AND o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findCashOnDeliveryOrdersByDateRange(LocalDate startDate, LocalDate endDate);

    //İptal edilen siparişleri hariç tutar ve belirtilen tarih aralığındaki kapıda ödeme seçili siparişler
    @Query("SELECT o FROM Order o WHERE o.cashOnDelivery = true AND o.cancelled = false AND o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findCashOnDeliveryOrdersWithoutCancellationsByDateRange(LocalDate startDate, LocalDate endDate);

    //İptal edilen ve indirimli siparişleri hariç tutar ve belirtilen tarih aralığındaki kapıda ödeme seçili siparişler
    @Query("SELECT o FROM Order o WHERE o.cashOnDelivery = true AND o.cancelled = false AND o.discountUsed = false AND o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findCashOnDeliveryOrdersWithoutDiscountsAndCancellationsByDateRange(LocalDate startDate, LocalDate endDate);
}
