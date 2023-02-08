package com.example.hiber.repository;

import com.example.hiber.model.entity.Policy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<Policy, UUID> {

    @Query("select p "
            + "from policies p "
            + " left join fetch p.drivers d "
            + " left join fetch d.license "
            + " left join fetch p.suggestions "
            + "where p.orderId in :orderIds")
    List<Policy> findAllByOrderId(@Param("orderIds") List<Integer> orderIds);

    @Query("select p "
            + "from policies p "
            + "where p.id = :id")
    Optional<Policy> findById(@Param("id") UUID id);

    @Query("select p "
            + "from policies p "
            + "where p.orderId = :orderId")
    Optional<Policy> findByOrderId(@Param("orderId") Integer orderId);

    default void insert(Policy order) {
        save(order);
    }
}
