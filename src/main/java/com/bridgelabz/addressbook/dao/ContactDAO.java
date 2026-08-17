package com.bridgelabz.addressbook.dao;

import com.bridgelabz.addressbook.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactDAO {

    @Autowired
    private DataSource dataSource;



    // UC-01 : Add Contact
    // POST /contacts


    public Contact save(Contact contact) {

        String sql = """
                INSERT INTO contacts
                (id, name, phone, email)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, contact.getId());
            statement.setString(2, contact.getName());
            statement.setString(3, contact.getPhone());
            statement.setString(4, contact.getEmail());

            statement.executeUpdate();

            return contact;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error while saving contact", e
            );
        }
    }



    // UC-02 : Get Contact
    // GET /contacts/{id}


    public Contact getById(int id) {

        String sql = """
                SELECT id, name, phone, email
                FROM contacts
                WHERE id = ?
                """;

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                Contact contact = new Contact();

                contact.setId(
                        resultSet.getInt("id")
                );

                contact.setName(
                        resultSet.getString("name")
                );

                contact.setPhone(
                        resultSet.getString("phone")
                );

                contact.setEmail(
                        resultSet.getString("email")
                );

                return contact;
            }

            return null;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error while fetching contact", e
            );
        }
    }



    // UC-03 : Get All Contacts
    // GET /contacts


    public List<Contact> getAll() {

        String sql = """
                SELECT id, name, phone, email
                FROM contacts
                ORDER BY id
                """;

        List<Contact> contacts = new ArrayList<>();

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Contact contact = new Contact();

                contact.setId(
                        resultSet.getInt("id")
                );

                contact.setName(
                        resultSet.getString("name")
                );

                contact.setPhone(
                        resultSet.getString("phone")
                );

                contact.setEmail(
                        resultSet.getString("email")
                );

                contacts.add(contact);
            }

            return contacts;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error while fetching contacts", e
            );
        }
    }



    // UC-04 : Update Contact
    // PUT /contacts/{id}


    public Contact update(int id, Contact contact) {

        String sql = """
                UPDATE contacts
                SET name = ?,
                    phone = ?,
                    email = ?
                WHERE id = ?
                """;

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, contact.getName());
            statement.setString(2, contact.getPhone());
            statement.setString(3, contact.getEmail());
            statement.setInt(4, id);

            int rowsUpdated =
                    statement.executeUpdate();

            if (rowsUpdated == 0) {
                return null;
            }

            contact.setId(id);

            return contact;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error while updating contact", e
            );
        }
    }



    // UC-05 : Delete Contact
    // DELETE /contacts/{id}


    public boolean delete(int id) {

        String sql =
                "DELETE FROM contacts WHERE id = ?";

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            int rowsDeleted =
                    statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error while deleting contact", e
            );
        }
    }



    // UC-06 : Search Contact
    // GET /contacts/search?name=hari


    public List<Contact> searchByName(String name) {

        String sql = """
                SELECT id, name, phone, email
                FROM contacts
                WHERE LOWER(name) LIKE LOWER(?)
                ORDER BY id
                """;

        List<Contact> contacts = new ArrayList<>();

        try (
                Connection connection =
                        dataSource.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    "%" + name + "%"
            );

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Contact contact = new Contact();

                contact.setId(
                        resultSet.getInt("id")
                );

                contact.setName(
                        resultSet.getString("name")
                );

                contact.setPhone(
                        resultSet.getString("phone")
                );

                contact.setEmail(
                        resultSet.getString("email")
                );

                contacts.add(contact);
            }

            return contacts;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error while searching contacts", e
            );
        }
    }
}