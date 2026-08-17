package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dao.AddressDAO;
import com.bridgelabz.addressbook.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressDAO addressDAO;


    // UC-07
    public Address save(
            int contactId,
            Address address) {

        return addressDAO.save(
                contactId,
                address
        );
    }


    // UC-08
    public List<Address> getByContactId(
            int contactId) {

        return addressDAO.getByContactId(
                contactId
        );
    }


    // UC-09
    public Address update(
            int contactId,
            Address address) {

        return addressDAO.update(
                contactId,
                address
        );
    }


    // UC-10
    public boolean delete(
            int contactId,
            int addressId) {

        return addressDAO.delete(
                contactId,
                addressId
        );
    }
}