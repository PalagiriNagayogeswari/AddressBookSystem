package com.bridgelabz.addressbook.model;

import java.util.List;

public class ContactDetails {

    private Contact contact;
    private List<Address> addresses;

    public ContactDetails() {
    }

    public ContactDetails(
            Contact contact,
            List<Address> addresses) {

        this.contact = contact;
        this.addresses = addresses;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}