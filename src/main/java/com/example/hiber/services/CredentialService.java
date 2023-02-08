package com.example.hiber.services;

import com.example.hiber.model.entity.CredentialEntity;
import com.example.hiber.model.entity.CredentialEntityInfo;
import com.example.hiber.repository.CredentialEntityRepository;
import com.example.hiber.repository.CredentialTemporaryEntityRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialService {

	private final CredentialEntityRepository repository;
	private final CredentialTemporaryEntityRepository temporaryEntityRepository;

	public List<CredentialEntityInfo> getCredentials(Integer page, Integer size) {

//		PageRequest pageParam = PageRequest.of(page, size);

		return repository.find(page, size, OffsetDateTime.now().minusMonths(5), OffsetDateTime.now());
	}

	public boolean saveCredential(CredentialEntity credential) {

		try {
			repository.save(credential);
			return true;
		} catch (RuntimeException ex) {
			return false;
		}
	}

}
