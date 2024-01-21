package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.employee;
import com.example.demo.service.employeeServices;
import com.example.exception.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/api")
public class controlling_emp {
	@GetMapping("/hello")
	public String sayHello() {
		return "hello";
	}

	@Autowired
	private employeeServices es;

	@PostMapping("/saveemp")
	public ResponseEntity<employee> saveemp(@Validated @RequestBody employee e) {
		try {
			employee emp = es.saveemployee(e);
			return ResponseEntity.status(HttpStatus.CREATED).body(emp);
		} catch (Exception E) {
			System.out.println(E);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/getemp")
	public ResponseEntity<List<employee>> getemp() {
		try {
			List<employee> empl = es.listAll();
			return ResponseEntity.ok(empl);
		} catch (Exception E) {
			System.out.println(E);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@GetMapping("/get_emp_by_id/{id}")
	public ResponseEntity<employee> getempbyid(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
		employee epl = es.getEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("employee not found "));
		return ResponseEntity.ok().body(epl);

	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<employee>  updateEmployee(@PathVariable(value = "id")int id ,@Validated @RequestBody employee e)
	throws ResourceNotFoundException
	{
		employee epl= es.getEmployeeById(id).orElseThrow(()->new ResourceNotFoundException("employee not found "));
		epl.setName(e.getName());
		epl.setTech(e.getTech());
		final employee latestEmp=es.saveemployee(epl);
		return ResponseEntity.ok().body(latestEmp);
		
	}
	@DeleteMapping("/deleteemployee/{id}")
	public ResponseEntity<Map<String, Boolean>>  updateEmployee(@PathVariable(value = "id")int id )
	throws ResourceNotFoundException
	{
		
		es.getEmployeeById(id).orElseThrow(()->new ResourceNotFoundException("employee not found "));
		es.delete(id);
		Map< String, Boolean> response=new HashMap<>();
		response.put("hurrah deleted!!", true);
		return ResponseEntity.ok().body(response);
		
	}
	
	

}
