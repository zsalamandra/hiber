package com.example.hiber.model.entity;

import com.example.hiber.model.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "policies")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Policy {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotBlank
    @Column(name = "vehicle_brand", nullable = false, length = 30)
    private String vehicleBrand;

    @NotBlank
    @Column(name = "vehicle_model", nullable = false, length = 30)
    private String vehicleModel;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @NotNull
    @Column(name = "vehicle_year", nullable = false)
    private Short vehicleYear;

    @Column(name = "vehicle_license_plate", length = 10)
    private String vehicleLicensePlate;

    @Column(name = "vehicle_vin", length = 17)
    private String vehicleVin;

    @Column(name = "vehicle_body_num", length = 17)
    private String vehicleBodyNum;

    @Column(name = "vehicle_chassis_num", length = 17)
    private String vehicleChassisNum;

    @NotNull
    @Column(name = "vehicle_power", nullable = false)
    private Short vehiclePower;

    @Column(name = "vehicle_weight")
    private Integer vehicleWeight;

    @Column(name = "vehicle_max_weight")
    private Integer vehicleMaxWeight;

    @Column(name = "vehicle_seats")
    private Short vehicleSeats;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_doc_type", nullable = false)
    private VehicleDocumentType vehicleDocumentType;

    @Column(name = "vehicle_doc_serial", length = 4)
    private String vehicleDocumentSerial;

    @NotBlank
    @Column(name = "vehicle_doc_number", nullable = false, length = 15)
    private String vehicleDocumentNumber;

    @NotNull
    @Column(name = "vehicle_doc_date", nullable = false)
    private LocalDate vehicleDocumentDate;

    @NotNull
    @Column(name = "vehicle_doc_valid", nullable = false)
    private Boolean vehicleDocumentValid;

    @Column(name = "vehicle_dc_number", length = 15)
    private String vehicleDiagnosticCardNumber;

    @Column(name = "vehicle_dc_validity")
    private LocalDate vehicleDiagnosticCardValidity;

    @Column(name = "vehicle_dc_issue_date")
    private LocalDate vehicleDiagnosticCardIssueDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_purpose", nullable = false)
    private VehiclePurposeType vehiclePurpose;

    @NotNull
    @Column(name = "vehicle_use_trailer", nullable = false)
    private Boolean vehicleUseTrailer;

    @NotNull
    @Column(name = "multidrive", nullable = false)
    private Boolean multidrive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "policy", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Driver> drivers;

    @NotBlank
    @Column(name = "owner_first_name", nullable = false, length = 50)
    private String ownerFirstName;

    @Column(name = "owner_middle_name", length = 50)
    private String ownerMiddleName;

    @NotBlank
    @Column(name = "owner_last_name", nullable = false, length = 50)
    private String ownerLastName;

    @NotNull
    @Column(name = "owner_birth_date", nullable = false)
    private LocalDate ownerBirthDate;

    @NotNull
    @Column(name = "owner_passport_foreign", nullable = false)
    private Boolean ownerPassportForeign;

    @Column(name = "owner_passport_serial", length = 4)
    private String ownerPassportSerial;

    @NotBlank
    @Column(name = "owner_passport_number", nullable = false, length = 10)
    private String ownerPassportNumber;

    @Column(name = "owner_passport_date")
    private LocalDate ownerPassportDate;

    @NotNull
    @Type(type = "jsonb")
    @Column(name = "owner_registration_address", columnDefinition = "jsonb", nullable = false)
    private String ownerRegistrationAddress;

    @NotNull
    @Column(name = "insurer_is_owner", nullable = false)
    private Boolean insurerIsOwner;

    @Column(name = "insurer_first_name", length = 50)
    private String insurerFirstName;

    @Column(name = "insurer_middle_name", length = 50)
    private String insurerMiddleName;

    @Column(name = "insurer_last_name", length = 50)
    private String insurerLastName;

    @Column(name = "insurer_birth_date")
    private LocalDate insurerBirthDate;

    @Column(name = "insurer_passport_foreign")
    private Boolean insurerPassportForeign;

    @Column(name = "insurer_passport_serial", length = 4)
    private String insurerPassportSerial;

    @Column(name = "insurer_passport_number", length = 10)
    private String insurerPassportNumber;

    @Column(name = "insurer_passport_date")
    private LocalDate insurerPassportDate;

    @Type(type = "jsonb")
    @Column(name = "insurer_registration_address", columnDefinition = "jsonb")
    private String insurerRegistrationAddress;

    @NotBlank
    @Column(name = "insurer_phone", nullable = false, length = 18)
    private String insurerPhone;

    @NotBlank
    @Column(name = "insurer_email", nullable = false)
    private String insurerEmail;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "policy_serial", length = 3)
    private String policySerial;

    @Column(name = "policy_number", length = 10)
    private String policyNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_policy")
    private PolicyType policyType;

    @Column(name = "policy_date")
    private LocalDateTime policyDate;

    @Column(name = "policy_end_date")
    private LocalDateTime policyEndDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "use_period", nullable = false)
    private PolicyUsagePeriod usePeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_company_id")
    @ToString.Exclude
    private InsuranceCompany insuranceCompany;

    @PositiveOrZero
    @Column(name = "policy_price")
    private BigDecimal policyPrice;

    @Column(name = "policy_doc_id")
    private UUID policyDocumentId;

    @Column(name = "url_policy_doc_id")
    private Integer urlPolicyDocument;

//    @Column(name = "url_policy_doc_url")
//    private String urlPolicyDocumentUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "policy_received", columnDefinition = "varchar")
    private PolicySourceType policyReceived;

    @Column(name = "moex_promotion")
    private Boolean moexPromotion;

    @Column(name = "prev_policy_serial", length = 3)
    private String prevPolicySerial;

    @Column(name = "prev_policy_number", length = 10)
    private String prevPolicyNumber;

    @Column(name = "inguru_order_id")
    private UUID inguruOrderId;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "url_received_date")
    private LocalDateTime urlReceivedDate;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "request_complete_date")
    private LocalDateTime requestCompleteDate;

    @PositiveOrZero
    @Column(name = "comission")
    private BigDecimal comission;

    @PositiveOrZero
    @Column(name = "comission_rate")
    private BigDecimal comissionRate;

    @Column(name = "request_browser_id")
    private UUID requestBrowserId;

//    @NotNull
//    @Column(name = "is_obsolete", nullable = false)
//    private Boolean isObsolete = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "policy", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Suggestion> suggestions;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "policy")
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private List<CustomerPolicy> customerPolicies;

//    @Column(name = "promo_code")
//    private String promoCode;

//    @OneToOne(mappedBy = "policy", cascade = CascadeType.ALL)
//    private OsagoProlongation prolongation;

//    @Column(name = "timezone")
//    private String timezone;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "status_order", columnDefinition = "varchar")
//    private StatusOrderType statusOrder;

    @CreationTimestamp
    @Column(name = "create_timestamp", updatable = false, nullable = false)
    private LocalDateTime createTimestamp;

    @UpdateTimestamp
    @Column(name = "update_timestamp", nullable = false)
    private LocalDateTime updateTimestamp;
}