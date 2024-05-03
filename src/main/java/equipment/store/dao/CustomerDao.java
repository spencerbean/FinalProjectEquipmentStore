package equipment.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import equipment.store.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
