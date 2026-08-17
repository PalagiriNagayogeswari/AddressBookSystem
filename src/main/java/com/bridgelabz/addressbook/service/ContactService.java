package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dao.AddressDAO;
import com.bridgelabz.addressbook.dao.ContactDAO;
import com.bridgelabz.addressbook.model.Address;
import com.bridgelabz.addressbook.model.Contact;
import com.bridgelabz.addressbook.model.ContactDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactDAO contactDAO;

    @Autowired
    private AddressDAO addressDAO;


    public Contact save(Contact contact) {
        return contactDAO.save(contact);
    }


    public Contact getById(int id) {
        return contactDAO.getById(id);
    }


    public List<Contact> getAll() {
        return contactDAO.getAll();
    }


    public Contact update(int id, Contact contact) {
        return contactDAO.update(id, contact);
    }


    public boolean delete(int id) {
        return contactDAO.delete(id);
    }


    public List<Contact> search(String name) {
        return contactDAO.searchByName(name);
    }

    public ContactDetails getContactDetails(int contactId) {

        Contact contact =
                contactDAO.getById(contactId);

        if (contact == null) {
            return null;
        }

        List<Address> addresses =
                addressDAO.getByContactId(contactId);

        return new ContactDetails(
                contact,
                addresses
        );
    }
}