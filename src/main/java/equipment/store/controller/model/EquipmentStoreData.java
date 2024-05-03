package equipment.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import equipment.store.entity.Customer;
import equipment.store.entity.Employee;
import equipment.store.entity.EquipmentStore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EquipmentStoreData {
	private Long equipmentStoreId;
	private String equipmentStoreName;
	private String equipmentStoreAddress;
	private String equipmentStoreCity;
	private String equipmentStoreState;
	private String equipmentStoreZip;
	private String equipmentStorePhone;
	private Set<EquipmentStoreCustomer> customers = new HashSet<>();
	private Set<EquipmentStoreEmployee> employees = new HashSet<>();
	
	//Constructor initializing EquipmentStoreData object from EquipmentStore entity
	public EquipmentStoreData(EquipmentStore equipmentStore) {
		equipmentStoreId = equipmentStore.getEquipmentStoreId();
		equipmentStoreName = equipmentStore.getEquipmentStoreName();
		equipmentStoreAddress = equipmentStore.getEquipmentStoreAddress();
		equipmentStoreCity = equipmentStore.getEquipmentStoreCity();
		equipmentStoreState = equipmentStore.getEquipmentStoreState();
		equipmentStoreZip = equipmentStore.getEquipmentStoreZip();
		equipmentStorePhone = equipmentStore.getEquipmentStorePhone();
		
		for (Customer customer : equipmentStore.getCustomers()) {
			customers.add(new EquipmentStoreCustomer(customer));
		}
		for (Employee employee : equipmentStore.getEmployees()) {
			employees.add(new EquipmentStoreEmployee(employee));
		}
		
	}
	
	// DTO for representing customer information associated with an equipment store
		@Data
		@NoArgsConstructor
		public static class EquipmentStoreCustomer {
			private Long customerId;
			private String customerFirstName;
			private String customerLastName;
			private String customerEmail;

			public EquipmentStoreCustomer(Customer customer) {
				customerId = customer.getCustomerId();
				customerFirstName = customer.getCustomerFirstName();
				customerLastName = customer.getCustomerLastName();
				customerEmail = customer.getCustomerEmail();
			}
		}

		// DTO for representing employee information associated with an equipment store
		@Data
		@NoArgsConstructor
		public static class EquipmentStoreEmployee {
			private Long employeeId;
			private String employeeFirstName;
			private String employeeLastName;
			private String employeePhone;
			private String employeeJobTitle;

			public EquipmentStoreEmployee(Employee employee) {
				employeeId = employee.getEmployeeId();
				employeeFirstName = employee.getEmployeeFirstName();
				employeeLastName = employee.getEmployeeLastName();
				employeePhone = employee.getEmployeePhone();
				employeeJobTitle = employee.getEmployeeJobTitle();
			}
		}
	
	
	
	
}
