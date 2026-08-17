package com.bridgelabz.addressbook.dao;

import com.bridgelabz.addressbook.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressDAO {

    @Autowired
    private DataSource dataSource;



    // UC-07 : Add Address
    // POST /contacts/{id}/address


    public Address save(int contactId, Address address) {

        String sql = """
                INSERT INTO addresses
                (id, contact_id, street, city, state, pincode)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, address.getId());
            statement.setInt(2, contactId);
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getCity());
            statement.setString(5, address.getState());
            statement.setString(6, address.getPincode());

            statement.executeUpdate();

            address.setContactId(contactId);

            return address;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error while saving address", e
            );
        }
    }



    // UC-08 : Get Address
    // GET /contacts/{id}/address


    public List<Address> getByContactId(int contactId) {

        String sql = """
                SELECT id, contact_id, street, city, state, pincode
                FROM addresses
                WHERE contact_id = ?
                ORDER BY id
                """;

        List<Address> addresses = new ArrayList<>();

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, contactId);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Address address = mapAddress(resultSet);

                addresses.add(address);
            }

            return addresses;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error while fetching addresses", e
            );
        }
    }



    // UC-09 : Update Address
    // PUT /contacts/{id}/address


    public Address update(
            int contactId,
            Address address) {

        String sql = """
                UPDATE addresses
                SET street = ?,
                    city = ?,
                    state = ?,
                    pincode = ?
                WHERE id = ?
                AND contact_id = ?
                """;

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, address.getStreet());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getState());
            statement.setString(4, address.getPincode());

            statement.setInt(5, address.getId());
            statement.setInt(6, contactId);

            int rowsUpdated =
                    statement.executeUpdate();

            if (rowsUpdated == 0) {
                return null;
            }

            address.setContactId(contactId);

            return address;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error while updating address", e
            );
        }
    }



    // UC-10 : Delete Address
    // DELETE /contacts/{id}/address


    public boolean delete(
            int contactId,
            int addressId) {

        String sql = """
                DELETE FROM addresses
                WHERE id = ?
                AND contact_id = ?
                """;

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, addressId);
            statement.setInt(2, contactId);

            int rowsDeleted =
                    statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error while deleting address", e
            );
        }
    }



    // Helper method


    private Address mapAddress(ResultSet resultSet)
            throws SQLException {

        Address address = new Address();

        address.setId(
                resultSet.getInt("id")
        );

        address.setContactId(
                resultSet.getInt("contact_id")
        );

        address.setStreet(
                resultSet.getString("street")
        );

        address.setCity(
                resultSet.getString("city")
        );

        address.setState(
                resultSet.getString("state")
        );

        address.setPincode(
                resultSet.getString("pincode")
        );

        return address;
    }
}