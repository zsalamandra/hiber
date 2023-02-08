package com.example.hiber.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "source_system_id")
    private String sourceSystemId;

    @Column(name = "info_system_name")
    private String infoSystemName;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<SuperRoleEntity> superRoles = new HashSet<>();

//    @OneToMany(mappedBy = "role")
//    private List<CredentialEntity> credentials;

    @OneToMany(mappedBy = "role")
    private List<CredentialTemporaryEntity> temporaryCredentials;
}
