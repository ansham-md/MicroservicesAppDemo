package com.msapp.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.msapp.users.entity.Users;




@Repository
public interface UserRepository extends JpaRepository<Users, Long>
{

	List<Users> findByUserId(Long userId);
	List<Users> findByDepartmentId(Long departmentId);
	List<Users> findByUserIdGreaterThan(Long userId);
	List<Users> findByGender(String gender);
	List<Users> findByAgeGreaterThan(Long age);
	
	@Query("from Users where gender=?1 order by name")
	List<Users> findByGenderSorted(String gender);
	
	//List<Users> findByAgeLesserThan(Long age);
	

}
