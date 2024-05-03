package equipment.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import equipment.store.entity.EquipmentStore;

public interface EquipmentStoreDao extends JpaRepository<EquipmentStore, Long> {

}
