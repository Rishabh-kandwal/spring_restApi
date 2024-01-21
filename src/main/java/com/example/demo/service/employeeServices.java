package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.employee;
import com.example.demo.repository.Employee_Repository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class employeeServices 
{
	@Autowired
	private Employee_Repository pr;
	
	public employee saveemployee(employee e) 
	{
		return pr.save(e);
	}
	public Optional<employee> getEmployeeById(int id) 
	{
		return pr.findById(id);
	}
	public List<employee> listAll()
	{
		return pr.findAll();		
	}
	public employee update(employee e) 
	{
		return pr.save(e);
	}
	public void delete(int id) 
	{
		
		pr.deleteById(id);
	}
}
