package com.example.hiber.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "drivers")
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Policy policy;

    @NotBlank
    @Column(name = "driver_first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank
    @Column(name = "driver_last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "driver_middle_name", length = 50)
    private String middleName;

    @NotNull
    @Column(name = "driver_birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "license_id", nullable = false)
    @ToString.Exclude
    private License license;

    @CreationTimestamp
    @Column(name = "create_timestamp", updatable = false, nullable = false)
    private LocalDateTime createTimestamp;

    @UpdateTimestamp
    @Column(name = "update_timestamp", nullable = false)
    private LocalDateTime updateTimestamp;
}
