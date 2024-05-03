package equipment.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import equipment.store.controller.model.EquipmentStoreData;
import equipment.store.controller.model.EquipmentStoreData.EquipmentStoreCustomer;
import equipment.store.controller.model.EquipmentStoreData.EquipmentStoreEmployee;
import equipment.store.dao.CustomerDao;
import equipment.store.dao.EmployeeDao;
import equipment.store.dao.EquipmentStoreDao;
import equipment.store.entity.Customer;
import equipment.store.entity.Employee;
import equipment.store.entity.EquipmentStore;

@Service
public class EquipmentStoreService {
	
	@Autowired
	private EquipmentStoreDao equipmentStoreDao;
	
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private CustomerDao customerDao;
	
	
	// This method saves or updates an equipment store based on EquipmentStoreData
		@Transactional(readOnly = false)
		public EquipmentStoreData saveEquipmentStore(EquipmentStoreData equipmentStoreData) {
			Long equipmentStoreId = equipmentStoreData.getEquipmentStoreId();
			EquipmentStore equipmentStore = findOrCreateEquipmentStore(equipmentStoreId);

			copyEquipmentStoreFields(equipmentStore, equipmentStoreData);
			return new EquipmentStoreData(equipmentStoreDao.save(equipmentStore));
		}
		
		// moves info from equipment store object to the equipment store entity
		private void copyEquipmentStoreFields(EquipmentStore equipmentStore, EquipmentStoreData equipmentStoreData) {
			equipmentStore.setEquipmentStoreName(equipmentStoreData.getEquipmentStoreName());
			equipmentStore.setEquipmentStoreAddress(equipmentStoreData.getEquipmentStoreAddress());
			equipmentStore.setEquipmentStoreCity(equipmentStoreData.getEquipmentStoreCity());
			equipmentStore.setEquipmentStoreState(equipmentStoreData.getEquipmentStoreState());
			equipmentStore.setEquipmentStoreZip(equipmentStoreData.getEquipmentStoreZip());
			equipmentStore.setEquipmentStorePhone(equipmentStoreData.getEquipmentStorePhone());
		}
		
		// finds an equipment store by ID or makes a new one
		private EquipmentStore findOrCreateEquipmentStore(Long equipmentStoreId) {
			EquipmentStore equipmentStore;

			if (Objects.isNull(equipmentStoreId)) {
				equipmentStore = new EquipmentStore();
			} else {
				equipmentStore = findEquipmentStoreById(equipmentStoreId);
			}

			return equipmentStore;
		}
		
		// retrieves an equipment store by ID
		private EquipmentStore findEquipmentStoreById(Long equipmentStoreId) {
			return equipmentStoreDao.findById(equipmentStoreId).orElseThrow(
					() -> new NoSuchElementException("Equipment store with Id: '" + equipmentStoreId + "' does not exist."));
		}
		
		// Saves new employee associated with equipment_store
		@Transactional(readOnly = false)
		public EquipmentStoreEmployee saveEmployee(Long equipmentStoreId, EquipmentStoreEmployee equipmentStoreEmployee) {
			EquipmentStore equipmentStore = findEquipmentStoreById(equipmentStoreId);
			Long employeeId = equipmentStoreEmployee.getEmployeeId();
			Employee employee = findOrCreateEmployee(equipmentStoreId, employeeId);

			copyEmployeeFields(employee, equipmentStoreEmployee);
			employee.setEquipmentStore(equipmentStore);
			equipmentStore.getEmployees().add(employee);

			return new EquipmentStoreEmployee(employeeDao.save(employee));
		}
		
		// copies data for employee object
		private void copyEmployeeFields(Employee employee, EquipmentStoreEmployee employeeStoreEmployee) {
			employee.setEmployeeFirstName(employeeStoreEmployee.getEmployeeFirstName());
			employee.setEmployeeLastName(employeeStoreEmployee.getEmployeeLastName());
			employee.setEmployeePhone(employeeStoreEmployee.getEmployeePhone());
			employee.setEmployeeJobTitle(employeeStoreEmployee.getEmployeeJobTitle());
		}

		// finds or creates new employee
		private Employee findOrCreateEmployee(Long employeeStoreId, Long employeeId) {
			Employee employee;

			if (Objects.isNull(employeeId)) {
				employee = new Employee();
			} else {
				employee = findEmployeeById(employeeStoreId, employeeId);
			}

			return employee;
		}
		
		// retrieves employee by ID and checks to make sure info matches with equipment store ID
		private Employee findEmployeeById(Long equipmentStoreId, Long employeeId) {
			Employee employee = employeeDao.findById(employeeId).orElseThrow(
					() -> new NoSuchElementException("Employee with Id: '" + employeeId + "' does not exist."));

			if (employee.getEquipmentStore().getEquipmentStoreId() == equipmentStoreId) {
				return employee;
			} else {
				throw new IllegalArgumentException(
						"Employee with Id: '" + employeeId + "' already exists at store with Id: '" + equipmentStoreId + "'.");
			}
		}
		
		// Saves new customer associated with specific equipment store
		@Transactional(readOnly = false)
		public EquipmentStoreCustomer saveCustomer(Long equipmentStoreId, EquipmentStoreCustomer equipmentStoreCustomer) {
			EquipmentStore equipmentStore = findEquipmentStoreById(equipmentStoreId);
			Long customerId = equipmentStoreCustomer.getCustomerId();
			Customer customer = findOrCreateCustomer(equipmentStoreId, customerId);

			copyCustomerFields(customer, equipmentStoreCustomer);
			customer.getEquipmentStores().add(equipmentStore);
			equipmentStore.getCustomers().add(customer);

			return new EquipmentStoreCustomer(customerDao.save(customer));
		}
		
		// copies fields to Customer object
		private void copyCustomerFields(Customer customer, EquipmentStoreCustomer equipmentStoreCustomer) {
			customer.setCustomerFirstName(equipmentStoreCustomer.getCustomerFirstName());
			customer.setCustomerLastName(equipmentStoreCustomer.getCustomerLastName());
			customer.setCustomerEmail(equipmentStoreCustomer.getCustomerEmail());
		}
		
		// finds or creates a new customer
		private Customer findOrCreateCustomer(Long equipmentStoreId, Long customerId) {
			Customer customer;

			if (Objects.isNull(customerId)) {
				customer = new Customer();
			} else {
				customer = findCustomerById(equipmentStoreId, customerId);
			}

			return customer;
		}
		
		// retrieves customer ID and info from database, or throw exception if there isn't one
		private Customer findCustomerById(Long equipmentStoreId, Long customerId) {
			Customer customer = customerDao.findById(customerId).orElseThrow(
					() -> new NoSuchElementException("Customer with Id: '" + customerId + "' does not exist."));
			boolean foundStore = false;

			for (EquipmentStore equipmentStore : customer.getEquipmentStores()) {
				if (equipmentStore.getEquipmentStoreId() == equipmentStoreId) {
					foundStore = true;
				}
			}

			if (foundStore) {
				return customer;
			} else {
				throw new IllegalArgumentException(
						"Equipment store with Id: '" + equipmentStoreId + "' already has customer with Id: '" + customerId + "'.");
			}
		}
		
		// generates list of read only equipment stores w/o customer and employee data
		@Transactional(readOnly = true)
		public List<EquipmentStoreData> retrieveAllEquipmentStores() {
			List<EquipmentStore> equipmentStores = equipmentStoreDao.findAll();
			List<EquipmentStoreData> result = new LinkedList<>();

			for (EquipmentStore equipmentStore : equipmentStores) {
				EquipmentStoreData equipmentStoreData = new EquipmentStoreData(equipmentStore);

				equipmentStoreData.getCustomers().clear();
				equipmentStoreData.getEmployees().clear();

				result.add(equipmentStoreData);
			}

			return result;
		}
		
		// retrieves equipment store by id and returns all information about it
		@Transactional(readOnly = true)
		public EquipmentStoreData retrieveEquipmentStoreById(Long equipmentStoreId) {
			return new EquipmentStoreData(findEquipmentStoreById(equipmentStoreId));
		}

		// deletes equipment store 
		public void deleteEquipmentStoreById(Long equipmentStoreId) {
			EquipmentStore equipmentStore = findEquipmentStoreById(equipmentStoreId);
			equipmentStoreDao.delete(equipmentStore);
		}

}
