package com.eventify.backend.controllers;

import com.eventify.backend.entities.AddressEntity;
import com.eventify.backend.services.servicesInter.AddressServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressServiceInter addressService;

    @GetMapping
    public List<AddressEntity> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressEntity> getAddressById(@PathVariable Long id) {
        AddressEntity address = addressService.getAddressById(id);
        return address != null ? ResponseEntity.ok(address) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public AddressEntity createAddress(@RequestBody AddressEntity address) {
        return addressService.createAddress(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressEntity> updateAddress(@PathVariable Long id, @RequestBody AddressEntity addressDetails) {
        AddressEntity updatedAddress = addressService.updateAddress(id, addressDetails);
        return updatedAddress != null ? ResponseEntity.ok(updatedAddress) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
