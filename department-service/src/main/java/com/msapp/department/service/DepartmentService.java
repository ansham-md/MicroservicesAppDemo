package com.msapp.department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msapp.department.entity.Department;
import com.msapp.department.repo.DepartmentRepository;

@Service
public class DepartmentService
{
	@Autowired
	private DepartmentRepository departmentRepository;

	//Department(1,"anshan","anshan","anshan")
	public Department saveDepartment(Department department) 
	{
		return departmentRepository.save(department);
	}
	public Department findDepartmentById(Long departmentId)
	{
		departmentRepository.findByDepartmentId(departmentId);
		System.out.println();
		return departmentRepository.findByDepartmentId(departmentId);
	}
	
	
	public List<Department> getDepartments()
	{
		return departmentRepository.findAll();
	}

	
	public String deleteDepartment(Long departmentId)
	{
		if(departmentRepository.findByDepartmentId(departmentId)!=null)
		{
			//departmentRepository.getReferenceById(departmentId).departmentId=(long) 10;
			//System.out.println(departmentRepository.getReferenceById(departmentId).departmentAddress);
			departmentRepository.delete(departmentRepository.getReferenceById(departmentId));
			return "SUCCESS";
		}
		else
			return "FAILED , department does not exist!";
	}

	
	public List<Department> findAllDepartments()
	{	
		return departmentRepository.findAll();
	}
	public Department findDepartmentByName(String departmentName)
	{
		
		return departmentRepository.findByDepartmentName(departmentName);
	}
	
	
}
