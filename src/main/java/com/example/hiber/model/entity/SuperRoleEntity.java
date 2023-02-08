package com.example.hiber.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "super_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"roles", "credentials", "temporaryCredentials"})
@ToString(exclude = {"roles", "credentials", "temporaryCredentials"})
public class SuperRoleEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "source_system_id")
    private String sourceSystemId;

    @Column(name = "info_system_name")
    private String infoSystemName;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "role_relations",
            joinColumns = { @JoinColumn(name = "super_role_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<RoleEntity> roles = new HashSet<>();

//    @OneToMany(mappedBy = "superRole")
//    private List<CredentialEntity> credentials;

    @OneToMany(mappedBy = "superRole")
    private List<CredentialTemporaryEntity> temporaryCredentials;

    public void addRole(RoleEntity role) {
        this.roles.add(role);
        role.getSuperRoles().add(this);
    }

}
