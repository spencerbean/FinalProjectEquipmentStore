package equipment.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import equipment.store.controller.model.EquipmentStoreData;
import equipment.store.dao.EquipmentStoreDao;
import equipment.store.entity.EquipmentStore;

@Service
public class EquipmentStoreService {
	
	@Autowired
	private EquipmentStoreDao equipmentStoreDao;
	
	
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
}
