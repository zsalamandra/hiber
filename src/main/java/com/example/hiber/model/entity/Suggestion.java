package com.example.hiber.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "suggestions")
@NoArgsConstructor
public class Suggestion {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "policy_id", insertable = false, updatable = false)
    private UUID orderId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", nullable = false)
    @ToString.Exclude
    private Policy policy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_company_id", nullable = false, updatable = false)
    @ToString.Exclude
    private InsuranceCompany insuranceCompany;

    @Column(name = "e_id")
    private Integer eId;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "prolongation_begin_date")
    private LocalDate prolongationBeginDate;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "error_message")
    private String errorMessage;
}
