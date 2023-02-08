package com.example.hiber.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CredentialRoleDto {

    private Long id;
    private String personId;
    private Long superRoleId;
    private String superRoleName;
    private String relationType;
    private LocalDate dueDate;
    private Boolean isDeleted;
}
