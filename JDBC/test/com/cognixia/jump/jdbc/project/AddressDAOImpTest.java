package com.cognixia.jump.jdbc.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressDAOImpTest {

	private Connection conn;
	private AddressDAOImp dao;

	@BeforeEach
	void setUp() throws SQLException {
		conn = DriverManager.getConnection("jdbc:h2:mem:address;MODE=MySQL;DB_CLOSE_DELAY=-1");
		try (Statement st = conn.createStatement()) {
			st.execute("drop table if exists address");
			st.execute("create table address (address_id int primary key, street varchar(100), "
					+ "city varchar(50), state varchar(2), zip_code varchar(10))");
		}
		dao = new AddressDAOImp(conn);
	}

	@AfterEach
	void tearDown() throws SQLException {
		conn.close();
	}

	@Test
	void addAddressWithExplicitIdReturnsTheAddress() {
		Address added = dao.addAddress(new Address(7, "123 Main St", "Dayton", "OH", "45402"));

		assertNotNull(added);
		assertEquals(7, added.getId());
	}

	@Test
	void addedAddressCanBeReadBack() {
		dao.addAddress(new Address(7, "123 Main St", "Dayton", "OH", "45402"));

		Address found = dao.getAddressById(7);

		assertNotNull(found);
		assertEquals("123 Main St", found.getStreet());
		assertEquals("Dayton", found.getCity());
		assertEquals("OH", found.getState());
		assertEquals("45402", found.getZip());
	}

	@Test
	void unknownIdReturnsNull() {
		assertNull(dao.getAddressById(999));
	}

	@Test
	void updateAddressChangesTheRow() {
		dao.addAddress(new Address(7, "123 Main St", "Dayton", "OH", "45402"));

		assertTrue(dao.updateAddress(new Address(7, "9 Oak Ave", "Columbus", "OH", "43004")));
		assertEquals("Columbus", dao.getAddressById(7).getCity());
	}

	@Test
	void updateOfMissingAddressReturnsFalse() {
		assertFalse(dao.updateAddress(new Address(42, "x", "y", "OH", "1")));
	}
}
