package com.msapp.users.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.msapp.users.ValueObjects.Department;
import com.msapp.users.ValueObjects.ResponseTemplateVO;
import com.msapp.users.entity.Users;
import com.msapp.users.exception.BusinessException;
import com.msapp.users.repository.UserRepository;


@Service
public class UserService 
{
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public RestTemplate restTemplate;

	public Users saveUser(Users user)
	{
		user.setStars(0);
		user.setTotalReviews(0);
		if(checker(user).equals("ok"))
			return userRepository.save(user);
		else
			return user;
	}
	public String checker(Users user)
	{
		//System.out.println("inhere");
		int n=(int)user.getName().charAt(0);
		String g=user.getGender();
		if(!((n>=97&&n<=122)||(n>=65&&n<=90)))
			 throw new BusinessException("not a valid name");
		
		if(user.getAge()>65)
			 throw new BusinessException("not a valid Age");
		
		if(!(g.equals("M")||g.equals("F")||g.equals("N")))
			throw new BusinessException("not a Valid gender, its either (M),(F) or (N)");
		
		return "ok";
	}

	public List<Users> findAllUsers()
	{
		//System.out.println(userRepository.findByGender("F").toArray());
		return userRepository.findAll();
		//return userRepository.findByGender("F");
	}
	
	public List<ResponseTemplateVO> findAllUsersWithDepartments()
	{
		return findTheUsersWithDepartments(userRepository.findAll());
	}
	
	public String deleteUser(Long userId)
	{
		if(userRepository.getReferenceById(userId).getStatus().compareTo("ACITIVE")!=0)
		{
			userRepository.getReferenceById(userId).setStatus("BLOCKED");
			userRepository.save(userRepository.getReferenceById(userId));
			return "SUCCESS";
		}
		else
			return "FAILED , user does not exist!";
	}
	
	public List<ResponseTemplateVO> getUserWithDepartment(Long userId)
	{
		
		//userRepository.findbyId(1l);
		return findTheUsersWithDepartments(userRepository.findByUserId(userId));
	}
	

	public List<ResponseTemplateVO> findTheUsersWithDepartments(List<Users> userList)
	{
		List<ResponseTemplateVO> ids = Lists.newArrayList();
		for(Users user: userList)
		{
			ResponseTemplateVO vo=new ResponseTemplateVO();
			Department department=restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(), Department.class);
			vo.setUser(user);
			vo.setDepartment(department);
			ids.add(vo);
		}
		return ids;
	}
	
	
	public String displayError(Exception e)
	{
		return "Sorry!, Department sservice is down";
	}

	public List<Users> getUserWithGender(String gender) 
	{
		return userRepository.findByGender(gender);
	}

	public List<Users> getUserWithAgeGreaterThan(long age) 
	{
		
		return userRepository.findByAgeGreaterThan(age);
	}

	public List<ResponseTemplateVO> getUserWorkingInADepartment(long departmentId)
	{
		//System.out.println(departmentId);
		return findTheUsersWithDepartments(userRepository.findByDepartmentId(departmentId));
	}

	public List<ResponseTemplateVO> getUserWorkingInADepartmentName(String departmentName)
	{
		Department department=restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/name/"+departmentName, Department.class);
		return getUserWorkingInADepartment(department.getDepartmentId());
	}

	public List<Users> getUserWithGenderSorted(String gender) {
		
		return userRepository.findByGenderSorted(gender);
	}
	
	
	
	public String giveRatings(Long userId,int currentRating) 
	{
		while(userRepository.getReferenceById(userId)!=null)
		{
			Users user=userRepository.getReferenceById(userId);
			double oldRating=user.getStars();
			double temp;
			//temp=(((oldRating*(user.getTotalReviews()))+currentRating)\(user.getTotalReviews()+1));
			temp=((oldRating*user.getTotalReviews())+currentRating)/(user.getTotalReviews()+1);
			
			
			user.setStars(temp);
			user.setTotalReviews(user.getTotalReviews()+1);
			userRepository.save(user);
			return "SUCCESS";
			
		}
		return "USER NOT FOUND";
	}

	



	/*
	public List<ResponseTemplateVO> findAllUsersWithDepartments()
	{
		List<ResponseTemplateVO> ids = Lists.newArrayList();
		for(Users dummy: userRepository.findAll())
		{
			getUserWithDepartment(dummy.getUserId());
			ids.add(getUserWithDepartment(dummy.getUserId())); 
		}
		return ids;
	}
	
	
	public List<ResponseTemplateVO> test()
	{
		return findTheUsersWithDepartments(userRepository.findAll());
	}
	
	public ResponseTemplateVO getUserWithDepartment(Long userId)
	{
		ResponseTemplateVO vo=new ResponseTemplateVO();
		Users user=userRepository.findByUserId(userId);
		String temp="http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId();
		Department department=restTemplate.getForObject(temp, Department.class);
		
		vo.setUser(user);
		vo.setDepartment(department);
		return vo;
	}
	
	
	public List<ResponseTemplateVO> getUserWithDepartment(Long userId)
	{
		
		Users user=userRepository.findByUserId(userId);
		return findTheUsersWithDepartments((List<Users>) user);
	}
	*/
}	

