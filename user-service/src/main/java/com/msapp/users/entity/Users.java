package com.msapp.users.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users
{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="UserId")
	public long userId;
	
	/*@Column(name="FirstName")
	public String firstName;
	
	@Column(name="LastName")
	public String lastName;
	
	@Column(name="Email")
	public String email;
	*/
	
	@Column(name="Name")
	public String name;
	
	@Column(name="Age")
	public long age;
	
	@Column(name="Gender")
	public String gender;
	
	@Column(name="DepartmentId")
	public long departmentId;
	
	
	@Column(name="stars")
	public double stars;
	
	@Column(name="TotalReviews")
	public int totalReviews;
	
	@Column(name="status")
	public String status;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public double getStars() {
		return stars;
	}

	public void setStars(double stars) {
		this.stars = stars;
	}

	public int getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(int totalReviews) {
		this.totalReviews = totalReviews;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

	

