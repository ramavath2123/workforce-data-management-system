package com.ganesh.workforce.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository repository;
    public EmployeeService(EmployeeRepository repository) { this.repository = repository; }

    public Employee create(EmployeeRequest r) {
        if (repository.findAll().stream().anyMatch(e -> e.getEmail().equalsIgnoreCase(r.email().trim()))) {
            throw new DuplicateEmailException(r.email());
        }
        return repository.save(new Employee(r.firstName().trim(), r.lastName().trim(),
            r.email().trim().toLowerCase(), r.department().trim(), r.role().trim()));
    }

    @Transactional(readOnly = true)
    public Employee getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }
    @Transactional(readOnly = true)
    public Page<Employee> list(Pageable pageable) { return repository.findAll(pageable); }
    @Transactional(readOnly = true)
    public Page<Employee> search(String keyword, Pageable pageable) {
        return keyword == null || keyword.isBlank() ? repository.findAll(pageable) : repository.search(keyword.trim(), pageable);
    }
    public Employee update(Long id, EmployeeRequest r) {
        Employee e = getById(id);
        boolean duplicate = repository.findAll().stream().anyMatch(other ->
            !other.getId().equals(id) && other.getEmail().equalsIgnoreCase(r.email().trim()));
        if (duplicate) throw new DuplicateEmailException(r.email());
        e.setFirstName(r.firstName().trim()); e.setLastName(r.lastName().trim());
        e.setEmail(r.email().trim().toLowerCase()); e.setDepartment(r.department().trim()); e.setRole(r.role().trim());
        return repository.save(e);
    }
    public void delete(Long id) { repository.delete(getById(id)); }
}
