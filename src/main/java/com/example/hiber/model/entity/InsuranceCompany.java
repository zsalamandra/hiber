package com.example.hiber.model.entity;

import com.example.hiber.model.enums.InsuranceCompanyInguruDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity(name = "insurance_companies")
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceCompany {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotBlank
    @Column(name = "insurance_company_inn", nullable = false, length = 12)
    private String inn;

    @NotBlank
    @Column(name = "insurance_company_name", nullable = false, length = 160)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "inguru_company_id")
    private InsuranceCompanyInguruDto inguruCompanyId;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @CreationTimestamp
    @Column(name = "create_timestamp", updatable = false, nullable = false)
    private LocalDateTime createTimestamp;

    @UpdateTimestamp
    @Column(name = "update_timestamp", nullable = false)
    private LocalDateTime updateTimestamp;
}
