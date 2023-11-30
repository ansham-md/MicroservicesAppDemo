package com.msapp.department.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department
{
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	public Long departmentId;
	
	@Column(name="Name")
	public String departmentName;
	
	//@Column(name="DepartmentAddress")
	//public String departmentAddress;
	
	@Column(name="DepartmentCode")
	public String departmentCode;

	
	
	
	
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

}
