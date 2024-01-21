package com.example.demo.repository;

//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import com.example.demo.model.employee;

public interface Employee_Repository extends JpaRepository< employee,Integer>
{

}
