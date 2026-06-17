package com.weblayerexample.weblayers.controllers;

import com.weblayerexample.weblayers.dto.EmployeeDto;
import com.weblayerexample.weblayers.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController
{
    private final EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId)
    {
        return employeeService.getEmployeeById(employeeId)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new NoSuchElementException("employee not found "));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(
            @RequestParam(required = false, name = "InputAge") Integer age,
            @RequestParam(required = false) String sortBy)
    {
        return ResponseEntity.ok(employeeService.getAllEmployees(age, sortBy));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@Valid @RequestBody EmployeeDto inputEmployee)
    {
        EmployeeDto savedEmployee = employeeService.createNewEmployee(inputEmployee);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@Valid @RequestBody EmployeeDto inputEmployee, @PathVariable Long id)
    {
        return ResponseEntity.ok(employeeService.updateEmployee(inputEmployee, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> patchEmployeeById(@PathVariable Long id, @Valid @RequestBody Map<String, Object> update)
    {
        return ResponseEntity.ok(employeeService.patchEmployeeById(id, update));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id)
    {
        employeeService.deleteEmployeeById(id);

        return ResponseEntity.noContent().build(); // 204
    }
}