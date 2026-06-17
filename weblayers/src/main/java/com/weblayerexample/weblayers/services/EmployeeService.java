package com.weblayerexample.weblayers.services;

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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService 
{
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeDto getEmployeeById(Long id)
    {
        EmployeeEntity employeeEntity =  employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDto.class);
    }

    public List<EmployeeDto> getAllEmployees(Integer age, String sortBy)
    {
        List<EmployeeEntity> employeeEntities =  employeeRepository.findAll();
        return
                employeeEntities
                        .stream()
                        .map(employeeEntity -> modelMapper.map(employeeEntity , EmployeeDto.class))
                        .collect(Collectors.toList());
    }

    public EmployeeDto createNewEmployee(EmployeeDto inputEmployee)
    {
        EmployeeEntity employeeEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);   // accept and convert to Entity
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);    // save entity
        return modelMapper.map(savedEmployeeEntity, EmployeeDto.class);     // return DTO
    }

    public EmployeeDto updateEmployee(EmployeeDto inputEmployee, Long id)
    {
        EmployeeEntity existingemployeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // IMPORTANT: preserve ID
        inputEmployee.setId(existingemployeeEntity.getId());

        // map DTO fields into existing entity
        // don't use .class because it will create new object
        modelMapper.map(inputEmployee, existingemployeeEntity);

        EmployeeEntity updatedEntity = employeeRepository.save(existingemployeeEntity);

        return modelMapper.map(updatedEntity, EmployeeDto.class);
    }

    public Boolean deleteEmployeeById(Long id)
    {
        boolean exists = employeeRepository.existsById(id);

        if(exists)
        {
            employeeRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public EmployeeDto patchEmployeeById(Long employeeId,
                                         Map<String, Object> update)
    {
        EmployeeEntity employeeExistingEntity =
                employeeRepository.findById(employeeId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found with id " + employeeId));

        update.forEach((field, value) -> {

            System.out.println("Field = " + field);
            System.out.println("Value = " + value);

            Field fieldToBeUpdated =
                    ReflectionUtils.findField(EmployeeEntity.class, field);

            System.out.println("Found field = " + fieldToBeUpdated);

            if(fieldToBeUpdated != null) {
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(
                        fieldToBeUpdated,
                        employeeExistingEntity,
                        value
                );
            }
        });

        EmployeeEntity savedEmployee =
                employeeRepository.save(employeeExistingEntity);

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }
}

