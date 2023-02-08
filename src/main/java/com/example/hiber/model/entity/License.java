package com.example.hiber.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "licenses")
@NoArgsConstructor
public class License {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "serial", length = 4)
    private String serial;

    @NotBlank
    @Column(name = "number", nullable = false, length = 6)
    private String number;

    @NotNull
    @PastOrPresent
    @Column(name = "exp_start_date", nullable = false)
    private LocalDate experienceStartDate;

    @PastOrPresent
    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid;

    @NotNull
    @Column(name = "is_foreign", nullable = false)
    private Boolean isForeign;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "previous_license_id")
    @ToString.Exclude
    private License previousLicense;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @CreationTimestamp
    @Column(name = "create_timestamp", updatable = false, nullable = false)
    private LocalDateTime createTimestamp;

    @UpdateTimestamp
    @Column(name = "update_timestamp", nullable = false)
    private LocalDateTime updateTimestamp;
}
