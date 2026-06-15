package com.weblayerexample.weblayers.controllers;

import com.weblayerexample.weblayers.dto.EmployeeDto;
import com.weblayerexample.weblayers.entities.EmployeeEntity;
import com.weblayerexample.weblayers.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
class EmployeeController
{
    private final EmployeeRepository employeeRepository;

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id)
    {
         return  employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false, name = "InputAge") Integer age,
                                                @RequestParam(required = false) String sortBy)
    {
        return employeeRepository.findAll();        // parameters are accepted but ignored for some time
    }

    @PostMapping
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity inputEmployee)
    {
//        InputEmployee.setId(100L);
        return  employeeRepository.save(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeById()
    {
        return "Return from PutMapping ";
    }

    @DeleteMapping
    public String deleteEmployeeById()
    {
        return "Return from DeleteMapping";
    }

    @PatchMapping
    public String patchEmployeeById()
    {
        return "Return from PatchMapping";
    }

}
