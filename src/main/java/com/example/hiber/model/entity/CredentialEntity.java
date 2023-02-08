package com.example.hiber.model.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credential")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialEntity {

    @Id
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

//    @ManyToOne
//    @JoinColumn(columnDefinition = "super_role_id", name = "super_role_id")
//    private SuperRoleEntity superRole;
//
//    @ManyToOne
//    @JoinColumn(columnDefinition = "role_id", name = "role_id")
//    private RoleEntity role;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "is_account_locked")
    private Boolean isAccountLocked;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

}
