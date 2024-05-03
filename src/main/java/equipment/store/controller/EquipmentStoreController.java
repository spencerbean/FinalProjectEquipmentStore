package equipment.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import equipment.store.controller.model.EquipmentStoreData;
import equipment.store.service.EquipmentStoreService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/equipment_store")
@Slf4j
public class EquipmentStoreController {
	@Autowired
	private EquipmentStoreService equipmentStoreService;
	
	
	// Method for creating new petStore
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public EquipmentStoreData insertEquipmentStore(@RequestBody EquipmentStoreData equipmentStoreData) {
		log.info("Creating equipment store {}", equipmentStoreData);
		return equipmentStoreService.saveEquipmentStore(equipmentStoreData);
	}
}
