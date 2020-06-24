package com.cognixia.jump.jdbc.project;

public interface AddressDAO {

	public Address getAddressById(int address_id);
	
	public boolean updateAddress(Address address);
	
	public Address addAddress(Address address);
	
}
