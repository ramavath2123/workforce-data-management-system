package com.ganesh.workforce.employee;

import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service = service; }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@Valid @RequestBody EmployeeRequest request) { return service.create(request); }

    @GetMapping("/{id}") public Employee getById(@PathVariable Long id) { return service.getById(id); }

    @GetMapping
    public Page<Employee> list(@RequestParam(defaultValue="0") int page,
                               @RequestParam(defaultValue="10") int size,
                               @RequestParam(defaultValue="id") String sortBy,
                               @RequestParam(defaultValue="asc") String direction) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 100),
            Sort.by("desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));
        return service.list(pageable);
    }

    @GetMapping("/search")
    public Page<Employee> search(@RequestParam(defaultValue="") String keyword,
                                 @RequestParam(defaultValue="0") int page,
                                 @RequestParam(defaultValue="10") int size) {
        return service.search(keyword, PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 100),
            Sort.by("lastName").ascending()));
    }

    @PutMapping("/{id}") public Employee update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request) {
        return service.update(id, request);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
