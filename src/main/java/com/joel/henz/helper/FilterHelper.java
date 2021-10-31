package com.joel.henz.helper;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.joel.henz.model.Department;
import com.joel.henz.model.Employee;
import com.joel.henz.model.Regulation;

public final class FilterHelper {
	
	private FilterHelper() {
		//prevent instantiation
	}
	
	public static Predicate<Regulation> filterRegulationByDepartmentId(int departmentId){
		return regulation -> regulation.getDepartment().getId() == departmentId;
	}
	
	public static Predicate<Department> filterDepartmentById(int departmentId){
		return department -> department.getId() == departmentId;
	}
	
	public static Predicate<Employee> filterEmployeeByDepartmentId(int departmentId){
		return employee -> employee.getDepartment().getId() == departmentId;
	}
	
	public static <T> List<T> filterByPredicate(List<T> list, Predicate<T> predicate){
		return list.stream().filter(predicate).collect(Collectors.<T>toList());
	}
}
