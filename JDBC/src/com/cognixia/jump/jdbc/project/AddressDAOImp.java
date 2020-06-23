package com.cognixia.jump.jdbc.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jump.jdbc.connection.ConnectionManagerProperties;

public class AddressDAOImp implements AddressDAO{

	private Connection conn = ConnectionManagerProperties.getConnection();
	
	@Override
	public Address getAddressById(int id) {
		Address address = null;
		// write query with spaces for parameters and pass to a PS
		try(PreparedStatement pstmt = conn.prepareStatement("select * from address where address_id = ?");) {
			
			// set the parameters
			pstmt.setInt(1, id);
			// run the query w/ the PS
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int addressId = rs.getInt(1);
				String street = rs.getString(2);
				String city = rs.getString(3);
				String state = rs.getString(4);
				String zip = rs.getString(5);
				
				address = new Address(addressId, street, city, state, zip);
			}
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return address;
	}

	@Override
	public boolean updateAddress(Address address) {
		//update department set dept_name = ?, dept_phone = ? where dept_id = ?
		try (PreparedStatement pstmt = conn
				.prepareStatement("update address set street = ?, city = ?, state = ?, zip_code = ? where address_id = ?");) {

			pstmt.setString(1, address.getStreet());
			pstmt.setString(2, address.getCity());
			pstmt.setString(3, address.getState());
			pstmt.setString(4, address.getZip());
			pstmt.setInt(5,  address.getId());
			
			int update = pstmt.executeUpdate();

			if (update > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Address addAddress(Address address) {
		
		try(PreparedStatement pstmt = conn.prepareStatement("insert into address values(?,?,?,?,?)");) {
			
			pstmt.setInt(1, address.getId());
			pstmt.setString(2, address.getStreet());
			pstmt.setString(3, address.getCity());
			pstmt.setString(4, address.getState());
			pstmt.setString(5, address.getZip());
			
			int insert = pstmt.executeUpdate();
			if(insert > 0) {
				return address;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO: how to handle this method now that returning an Address instead of boolean? Is null OK?
		return null;
	}

}
