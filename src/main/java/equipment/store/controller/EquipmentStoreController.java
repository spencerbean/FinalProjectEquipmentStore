package equipment.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import equipment.store.controller.model.EquipmentStoreData;
import equipment.store.controller.model.EquipmentStoreData.EquipmentStoreCustomer;
import equipment.store.controller.model.EquipmentStoreData.EquipmentStoreEmployee;
import equipment.store.service.EquipmentStoreService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/equipment_store")
@Slf4j
public class EquipmentStoreController {
	@Autowired
	private EquipmentStoreService equipmentStoreService;
	
	
	// Method for creating new equipmentStore
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public EquipmentStoreData insertEquipmentStore(@RequestBody EquipmentStoreData equipmentStoreData) {
		log.info("Creating equipment store {}", equipmentStoreData);
		return equipmentStoreService.saveEquipmentStore(equipmentStoreData);
	}
	
	// Method for updating equipmentStore information using the equipmentStoreId to identify it
		@PutMapping("/{equipmentStoreId}")
		public EquipmentStoreData updateEquipmentStore(@PathVariable Long equipmentStoreId, @RequestBody EquipmentStoreData equipmentStoreData) {
			equipmentStoreData.setEquipmentStoreId(equipmentStoreId);
			log.info("Updating store with Id={}", equipmentStoreId);
			return equipmentStoreService.saveEquipmentStore(equipmentStoreData);
		}
		
		// Method for adding new employee
		@PostMapping("/{equipmentStoreId}/employee")
		@ResponseStatus(code = HttpStatus.CREATED)
		public EquipmentStoreEmployee insertEmployeeInEquipmentStore(@PathVariable Long equipmentStoreId,
				@RequestBody EquipmentStoreEmployee employee) {
			log.info("Adding employee: '{}' to store '{}'", employee, equipmentStoreId);
			return equipmentStoreService.saveEmployee(equipmentStoreId, employee);
		}
		
		// Method for adding new customer
		@PostMapping("/{equipmentStoreId}/customer")
		@ResponseStatus(code = HttpStatus.CREATED)
		public EquipmentStoreCustomer insertCustomerInEquipmentStore(@PathVariable Long equipmentStoreId,
				@RequestBody EquipmentStoreCustomer customer) {
			log.info("Adding customer: '{}' to store '{}'", customer, equipmentStoreId);
			return equipmentStoreService.saveCustomer(equipmentStoreId, customer);
		}
		
		// Method for retrieving list of equipment store w/o customer or employee info
		@GetMapping
		public List<EquipmentStoreData> getAllEquipmentStores() {
			log.info("Retrieving all equipment store information without customer and employee data.");
			return equipmentStoreService.retrieveAllEquipmentStores();
		}
		
		// Method for pulling all info about one equipment store
		@GetMapping("/{equipmentStoreId}")
		public EquipmentStoreData getEquipmentStoreById(@PathVariable Long equipmentStoreId) {
			log.info("Retrieving information for equipment store with Id: '{}'", equipmentStoreId);
			return equipmentStoreService.retrieveEquipmentStoreById(equipmentStoreId);
		}
		
		// Method for deleting a equipment store, the employees associated with it, and also the equipment_store/customer table data linking the two
		@DeleteMapping("/{equipmentStoreId}")
		public Map<String, String> deleteEquipmentStoreById(@PathVariable Long equipmentStoreId) {
			log.info("Deleting equipment store with Id: '{}'", equipmentStoreId);
			equipmentStoreService.deleteEquipmentStoreById(equipmentStoreId);

			return Map.of("message", "Deletion of equipment store with Id: '" + equipmentStoreId + "' was successful.");
		}
}
