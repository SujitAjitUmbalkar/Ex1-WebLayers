package com.weblayerexample.weblayers.services;

import com.weblayerexample.weblayers.dto.EmployeeDto;
import com.weblayerexample.weblayers.entities.EmployeeEntity;
import com.weblayerexample.weblayers.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return null;
    }

    public void deleteEmployeeById(Long id)
    {
    }

}

