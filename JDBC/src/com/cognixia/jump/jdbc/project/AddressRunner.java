package com.cognixia.jump.jdbc.project;

public class Main {

	public static void main(String[] args) {
		
		AddressDAOImp addressDao = new AddressDAOImp();
		
//		System.out.println("Get Address by id");
//		Address dept = addressDao.getAddressById(10009);
//		if(dept != null) {
//			System.out.println(dept);
//		}else {
//			System.out.println("Error: could not retrieve address by id");
//		}
		
	
		// adding an Address manually 
//		Address newAddress = new Address(0, "123 New Road", "Pearland", "TX", "77047");
//		Address added = addressDao.addAddress(newAddress);
//		if(added != null) {
//			System.out.println("Address was added");
//		}else {
//			System.out.println("Error: Unable to add address");
//		}
		
		System.out.println("Updating an address");
		Address toUpdate = addressDao.getAddressById(10009);
		toUpdate.setStreet("456 Updated Lane");
		toUpdate.setCity("Houston");
		toUpdate.setZip("77001");
		
		boolean updated = addressDao.updateAddress(toUpdate);
		if(updated) {
			System.out.println("Update was successful");
		}
		
	}

}
