package com.example.hiber.repository;

import com.example.hiber.model.entity.CredentialTemporaryEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialTemporaryEntityRepository extends JpaRepository<CredentialTemporaryEntity, UUID> {

}
