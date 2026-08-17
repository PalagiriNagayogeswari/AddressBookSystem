package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.model.Address;
import com.bridgelabz.addressbook.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts/{contactId}/address")
public class AddressController {

    @Autowired
    private AddressService addressService;



    // UC-07 : Add Address
    // POST /contacts/{id}/address

    @PostMapping
    public Address save(
            @PathVariable("contactId") int contactId,
            @RequestBody Address address) {

        return addressService.save(
                contactId,
                address
        );
    }



    // UC-08 : Get Address
    // GET /contacts/{id}/address


    @GetMapping
    public List<Address> getAddresses(
            @PathVariable("contactId") int contactId) {

        return addressService.getByContactId(
                contactId
        );
    }



    // UC-09 : Update Address
    // PUT /contacts/{id}/address

    @PutMapping
    public Address update(
            @PathVariable("contactId") int contactId,
            @RequestBody Address address) {

        return addressService.update(
                contactId,
                address
        );
    }



    // UC-10 : Delete Address
    // DELETE /contacts/{id}/address


    @DeleteMapping("/{addressId}")
    public String delete(
            @PathVariable("contactId") int contactId,
            @PathVariable("addressId") int addressId) {

        boolean deleted =
                addressService.delete(
                        contactId,
                        addressId
                );

        if (deleted) {
            return "Address deleted successfully";
        }

        return "Address not found";
    }
}