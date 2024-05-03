package equipment.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


//this class defines the structure of the "equipmentStore" table in the database
@Entity
@Data
public class EquipmentStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long equipmentStoreId;
	private String equipmentStoreName;
	private String equipmentStoreAddress;
	private String equipmentStoreCity;
	private String equipmentStoreState;
	private String equipmentStoreZip;
	private String equipmentStorePhone;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "equipment_store_customer", joinColumns = @JoinColumn(name = "equipment_store_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private Set<Customer> customers = new HashSet<>();

	@OneToMany(mappedBy = "equipmentStore", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Employee> employees = new HashSet<>();
}