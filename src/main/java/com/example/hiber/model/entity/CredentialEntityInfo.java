package com.example.hiber.model.entity;

import com.example.hiber.model.enums.NotificationType;
import java.time.LocalDate;

public interface CredentialEntityInfo {

	Long getId();

	NotificationType getNtype();

	String getInfoSystemName();

	Long getOrganizationId();

	String getPersonId();

	LocalDate getStartDate();
}
