package com.weblayerexample.weblayers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto
{
    private String name;
    private Long id;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isactive;

//    public EmployeeDto()
//    {
//    }
//
//    public EmployeeDto(String name, Long id, String email, Integer age, LocalDate dateOfJoining, Boolean isactive)
//    {
//        this.name = name;
//        this.id = id;
//        this.email = email;
//        this.age = age;
//        this.dateOfJoining = dateOfJoining;
//        this.isactive = isactive;
//    }
//
//    public String getName()
//    {
//        return name;
//    }
//
//    public void setName(String name)
//    {
//        this.name = name;
//    }
//
//    public Long getId()
//    {
//        return id;
//    }
//
//    public void setId(Long id)
//    {
//        this.id = id;
//    }
//
//    public String getEmail()
//    {
//        return email;
//    }
//
//    public void setEmail(String email)
//    {
//        this.email = email;
//    }
//
//    public Integer getAge()
//    {
//        return age;
//    }
//
//    public void setAge(Integer age)
//    {
//        this.age = age;
//    }
//
//    public LocalDate getDateOfJoining()
//    {
//        return dateOfJoining;
//    }
//
//    public void setDateOfJoining(LocalDate dateOfJoining)
//    {
//        this.dateOfJoining = dateOfJoining;
//    }
//
//    public Boolean getIsactive()
//    {
//        return isactive;
//    }
//
//    public void setIsactive(Boolean isactive)
//    {
//        this.isactive = isactive;
//    }
}

