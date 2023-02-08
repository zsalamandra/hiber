package com.example.hiber.repository;

import com.example.hiber.model.entity.CredentialEntity;
import com.example.hiber.model.entity.CredentialEntityInfo;
import java.time.OffsetDateTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CredentialEntityRepository extends JpaRepository<CredentialEntity, Long> {

	@Query(value =
		  "select "
		+ "'PUSH' as ntype, "
		+ "c.id, "
		+ "c.person_id as personId, "
		+ "c.start_date as StartDate "
		+ "from credential c "
	    + "where start_date between :from and :to "
		+ "union all "
		+ "select "
        + "'SMS' as ntype, "
		+ "tc.id, "
		+ "tc.person_id as personId, "
		+ "tc.start_date as StartDate "
		+ "from temporary_credential tc "
	    + "where start_date between :from and :to "
		+ "order by id desc limit :size offset :page"
		, nativeQuery = true)
	List<CredentialEntityInfo> find(Integer page, Integer size, OffsetDateTime from, OffsetDateTime to);
}
