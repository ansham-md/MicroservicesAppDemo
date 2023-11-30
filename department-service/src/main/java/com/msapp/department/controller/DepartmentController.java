package com.msapp.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType; 


/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

import com.msapp.department.entity.Department;
import com.msapp.department.service.DepartmentService;

@RestController

//@RequestMapping(consumes = "application/json", produces = "application/json")

//consumes = MediaType.APPLICATION_JSON_VALUE,

@RequestMapping(path = "/departments",
produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController

{
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department department)
	{
		//logger.info("inside saveDepartment method in CONTROLLER");
		return departmentService.saveDepartment(department);
		
		//System.out.println("hi inside"+departmentService.getDepartments());
		//System.out.println(department.departmentName);
		
		//return departmentService.getDepartments();
	}
	
	@GetMapping("/")
	public List<Department> findAllDepartments()
	{
		return departmentService.getDepartments();
	}
	@GetMapping("/{id}")
	public Department findDepartmentById(@PathVariable("id") Long departmentId)
	{
		return departmentService.findDepartmentById(departmentId);
	}
	
	@GetMapping("/name/{name}")
	public Department findDepartmentByName(@PathVariable("name") String departmentName)
	{
		return departmentService.findDepartmentByName(departmentName);
	}
	public List<Department> findAllDepartment()
	{
		return departmentService.findAllDepartments();
	}
	
	@PutMapping("/")
	public Department updateDepartment(@RequestBody Department department)
	{
		
		return departmentService.saveDepartment(department);
	}
	
	@DeleteMapping("/{id}")
	public String deleteDepartment(@PathVariable("id") Long departmentId)
	{
		
		return departmentService.deleteDepartment(departmentId);
	}
	
	/*@GetMapping("/11")
	public int out()
	{
		System.out.println("ypure here");
		return 11;
	}*/
	
	
	
	
	//
	/*
	public Department fetchDepartments(Department department)
	{
		
		return department;
		
	}*/
}
