package com.weblayerexample.weblayers.controllers;

import com.weblayerexample.weblayers.dto.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/employees")
class EmployeeController
{
    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") Long id)
    {
        return new EmployeeDto("Sujit" , id , "Sujit@gmail.com", 23 , LocalDate.of(2024 , 1 , 2 ), true);
    }

    @GetMapping
    public String getAllEmployees(@RequestParam(required = false, name = "InputAge") Integer age,
                                  @RequestParam(required = false) String sortBy)
    {
        return "hii , Age is " + age + " "+ sortBy ;
    }

    @PostMapping
    public EmployeeDto addEmployee(@RequestBody EmployeeDto InputEmployee)
    {
        InputEmployee.setId(100L);
        return  InputEmployee;
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
