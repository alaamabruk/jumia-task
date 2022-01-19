package com.jumia.task.repo;

import com.jumia.task.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query(value = "SELECT phone  FROM customer c",nativeQuery = true)
    List<String> getAllPhoneNumbers();

}
