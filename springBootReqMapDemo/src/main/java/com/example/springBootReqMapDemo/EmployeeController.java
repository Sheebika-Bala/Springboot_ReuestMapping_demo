package com.example.springBootReqMapDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee details) {
        Employee emp = employeeRepository.findById(id).orElseThrow();
        emp.setName(details.getName());
        emp.setEmail(details.getEmail());
        emp.setDepartment(details.getDepartment());
        return employeeRepository.save(emp);
    }

    @PatchMapping("/{id}")
    public Employee patchEmployee(@PathVariable Long id, @RequestBody Employee details) {
        Employee emp = employeeRepository.findById(id).orElseThrow();
        if (details.getName() != null) emp.setName(details.getName());
        if (details.getEmail() != null) emp.setEmail(details.getEmail());
        if (details.getDepartment() != null) emp.setDepartment(details.getDepartment());
        return employeeRepository.save(emp);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "Deleted employee with ID: " + id;
    }
}

