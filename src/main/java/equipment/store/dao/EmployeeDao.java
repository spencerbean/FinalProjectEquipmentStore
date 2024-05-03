package equipment.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import equipment.store.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
