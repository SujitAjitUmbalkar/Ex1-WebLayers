package com.weblayerexample.weblayers.repositories;

import com.weblayerexample.weblayers.entities.EmployeeEntity;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>
{


}
