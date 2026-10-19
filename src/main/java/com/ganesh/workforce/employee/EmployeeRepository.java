package com.ganesh.workforce.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e where lower(e.firstName) like lower(concat('%', :keyword, '%')) " +
           "or lower(e.lastName) like lower(concat('%', :keyword, '%')) " +
           "or lower(e.email) like lower(concat('%', :keyword, '%')) " +
           "or lower(e.department) like lower(concat('%', :keyword, '%')) " +
           "or lower(e.role) like lower(concat('%', :keyword, '%'))")
    Page<Employee> search(@Param("keyword") String keyword, Pageable pageable);
}
