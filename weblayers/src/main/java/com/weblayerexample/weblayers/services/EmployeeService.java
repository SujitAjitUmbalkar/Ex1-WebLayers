package com.weblayerexample.weblayers.services;

import com.weblayerexample.weblayers.Exceptions.NoResourceFoundExceptions;
import com.weblayerexample.weblayers.dto.EmployeeDto;
import com.weblayerexample.weblayers.entities.EmployeeEntity;
import com.weblayerexample.weblayers.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService
{
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public void checkIfEmployeeExists(Long id)
    {
        boolean  exists = employeeRepository.existsById(id);
        if(!exists) {    throw new NoResourceFoundExceptions("Employee not found with id "+ id);  }
    }

    // GET BY ID
    public Optional<EmployeeDto> getEmployeeById(Long id)
    {
        return employeeRepository.findById(id)
                .map(employee ->
                        modelMapper.map(employee, EmployeeDto.class));
    }

    // GET ALL
    public List<EmployeeDto> getAllEmployees(Integer age, String sortBy)
    {
        List<EmployeeEntity> employeeEntities =
                employeeRepository.findAll();

        return employeeEntities.stream()
                .map(employee ->
                        modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    // CREATE
    public EmployeeDto createNewEmployee(EmployeeDto inputEmployee)
    {
        EmployeeEntity employeeEntity =
                modelMapper.map(inputEmployee, EmployeeEntity.class);

        EmployeeEntity savedEmployee =
                employeeRepository.save(employeeEntity);

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    // PUT (FULL UPDATE)
    public EmployeeDto updateEmployee(EmployeeDto inputEmployee,Long id)
    {
        checkIfEmployeeExists(id);

        EmployeeEntity existingEmployee = employeeRepository.findById(id).get();

//        WHY WE  USED GET HERE
//`findById()` returns an `Optional<EmployeeEntity>`. In **GET**,
// we return the `Optional` safely using `map()` because the employee may or may not exist.
//In **PUT/PATCH**, we first verify that the employee exists (`checkIfEmployeeExists(id)`),
// so we are sure the `Optional` contains a value and can
// safely extract the actual `EmployeeEntity` using `get()` (though `orElseThrow()` is preferred in production code).

        inputEmployee.setId(existingEmployee.getId());

        modelMapper.map(inputEmployee, existingEmployee);

        EmployeeEntity updatedEmployee = employeeRepository.save(existingEmployee);

        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    // DELETE
    public void deleteEmployeeById(Long id)
    {
            checkIfEmployeeExists(id);
            employeeRepository.deleteById(id);
    }

    // PATCH (PARTIAL UPDATE)
    public EmployeeDto patchEmployeeById(Long employeeId,Map<String, Object> updates)
    {
        checkIfEmployeeExists(employeeId);

        EmployeeEntity employee = employeeRepository.findById(employeeId).get();

        updates.forEach((fieldName, value) ->
        {
            Field field =
                    ReflectionUtils.findField(
                            EmployeeEntity.class,
                            fieldName
                    );

            if(field != null)
            {
                field.setAccessible(true);
                ReflectionUtils.setField(
                        field,
                        employee,
                        value
                );
            }
        });

        EmployeeEntity savedEmployee =
                employeeRepository.save(employee);

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }
}