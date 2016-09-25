package com.sample.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sample.springmvc.domain.Employee;


@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer,Employee>implements EmployeeDao {

	@Override
	public Employee findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveEmployee(Employee employee) {
       persist(employee);
	} 

	@Override
	public void deleteEmployeeBySsn(String ssn) {
        Query query = getEntityManager().createNativeQuery("delete from Employee where ssn = :ssn");
        query.setParameter("ssn", ssn);
        query.executeUpdate();
         
	}

	@Override
	public List<Employee> findAllEmployees() {
		List<Employee>  employees =getEntityManager().createQuery("SELECT e from Employee e").getResultList();
		return employees;
	}

	@Override
	public Employee findEmployeeBySsn(String ssn) {
		return (Employee)getEntityManager().createQuery("SELECT e from Employee where e.ssn = :ssn").
		                    setParameter("ssn", ssn).getSingleResult();
		
		
/*		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root from = criteriaQuery.from(Employee.class);

		List criteriaList = new ArrayList();// this list will have all the where
											// clause predicates

		Predicate predicate1 = criteriaBuilder.equal(from.get("ssn"), ssn);
		criteriaList.add(predicate1);*/
	}

}
