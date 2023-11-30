package com.msapp.users.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msapp.users.ValueObjects.Department;
import com.msapp.users.ValueObjects.ResponseTemplateVO;
import com.msapp.users.entity.Users;
import com.msapp.users.service.UserService; 

@RestController
@RequestMapping(path = "/users",
consumes = MediaType.APPLICATION_JSON_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)

public class UserController
{
	@Autowired
	public UserService userService;
	
	@PostMapping("/")
	public Users saveUser(@RequestBody Users user)
	{
		return userService.saveUser(user);
	}
	
	@GetMapping("/")
	public List<Users> findAllUsers()
	{
		
		return userService.findAllUsers();
	}
	@GetMapping("/rate/{id}")
	public String giveRatings(@PathVariable("id") Long userId,@RequestParam("rating") int stars)
	{
		return userService.giveRatings(userId,stars);
	}
	@GetMapping("/departments")
	public List<ResponseTemplateVO> findAllUsersWithDepartments()
	{
		return userService.findAllUsersWithDepartments();
	}
	
	//return userService.findAllUsersWithDepartments();
	@GetMapping("/{id}")
	public List<ResponseTemplateVO> getUserWithDepartment(@PathVariable("id") Long userId)
	{
		return userService.getUserWithDepartment(userId);
	}
	@GetMapping("/gender")
	public List<Users> getUserWithGender(@RequestParam("gender") String gender)
	{
		return userService.getUserWithGender(gender);
	}
	@GetMapping("/age")
	public List<Users> getUserWithAgeGreaterThan(@RequestParam("age") long age)
	{
		return userService.getUserWithAgeGreaterThan(age);
	}
	@GetMapping("/department/Id")
	public List<ResponseTemplateVO> getUserWorkingInADepartment(@RequestParam("departmentId") long departmentId)
	{
		return userService.getUserWorkingInADepartment(departmentId);
	}
	@GetMapping("/department/Name")
	public List<ResponseTemplateVO> getUserWorkingInADepartmentName(@RequestParam("departmentName") String departmentName)
	{
		return userService.getUserWorkingInADepartmentName(departmentName);
	}
	@GetMapping("/gender/sorted")
	public List<Users> getUserWithGenderSorted(@RequestParam("gender") String gender)
	{
		return userService.getUserWithGenderSorted(gender);
	}
	
	@PutMapping("/")
	public Users updateUser(@RequestBody Users user)
	{
		return userService.saveUser(user);
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable("id") Long userId)
	{
		return userService.deleteUser(userId);
	}
	
}
