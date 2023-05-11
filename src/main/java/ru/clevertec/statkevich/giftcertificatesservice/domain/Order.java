package ru.clevertec.statkevich.giftcertificatesservice.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "timestamp_of_a_purchase", updatable = false)
    private LocalDateTime purchaseDateTime;


    @Column(name = "price", updatable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "certificate_id")
    private GiftCertificate certificate;

    @PrePersist
    void initProperties() {
        this.purchaseDateTime = LocalDateTime.now();
        this.price = certificate.getPrice();
    }
}
