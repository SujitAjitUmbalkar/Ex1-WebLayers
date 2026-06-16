package com.weblayerexample.weblayers.controllers;

import com.weblayerexample.weblayers.dto.EmployeeDto;
import com.weblayerexample.weblayers.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
class EmployeeController
{
    private final EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") Long id)
    {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees(@RequestParam(required = false, name = "InputAge") Integer age,
                                                @RequestParam(required = false) String sortBy)
    {
        return employeeService.getAllEmployees(age , sortBy);
    }

    @PostMapping
    public EmployeeDto createNewEmployee(@RequestBody EmployeeDto inputEmployee)
    {
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping
    public EmployeeDto updateEmployeeById(@RequestBody EmployeeDto inputEmployee, @PathVariable Long id)
    {
        return employeeService.updateEmployee(inputEmployee , id);
    }

    @DeleteMapping
    public void deleteEmployeeById(@PathVariable Long id)
    {
        employeeService.deleteEmployeeById(id);
    }

    @PatchMapping
    public String patchEmployeeById()
    {
        return "Return from PatchMapping";
    }

}
