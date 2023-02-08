package com.example.hiber.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "temporary_credential")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"superRole", "role"})
public class CredentialTemporaryEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uuid;

    @Column(name = "id")
    private Long id;

    @Column(name = "source_system_id")
    private String sourceSystemId;

    @Column(name = "info_system_name")
    private String infoSystemName;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "person_id")
    private String personId;

    @Column(name = "relation_type")
    private String relationType;

    @Column(name = "is_member_locked")
    private Boolean isMemberLocked;

    @ManyToOne
    @JoinColumn(columnDefinition = "super_role_id", name = "super_role_id")
    private SuperRoleEntity superRole;

    @ManyToOne
    @JoinColumn(columnDefinition = "role_id", name = "role_id")
    private RoleEntity role;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "is_account_locked")
    private Boolean isAccountLocked;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @UpdateTimestamp
    @Column(name = "modify_date_time", insertable = false)
    private LocalDateTime modifyDateTime;

    @Column(name = "is_sent")
    private Boolean isSent = false;

}
