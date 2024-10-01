package com.eventify.backend.controllers;

import com.eventify.backend.entities.VendorEntity;
import com.eventify.backend.services.servicesInter.VendorServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorServiceInter vendorService;

    @PostMapping
    public VendorEntity createVendor(@RequestBody VendorEntity vendor) {
        return vendorService.createVendor(vendor);
    }

    @GetMapping("/{id}")
    public VendorEntity getVendorById(@PathVariable("id") Long vendorId) {
        return vendorService.getVendorById(vendorId);
    }

    @GetMapping
    public List<VendorEntity> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @PutMapping("/{id}")
    public VendorEntity updateVendor(@PathVariable("id") Long vendorId, @RequestBody VendorEntity vendorDetails) {
        return vendorService.updateVendor(vendorId, vendorDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteVendor(@PathVariable("id") Long vendorId) {
        vendorService.deleteVendor(vendorId);
    }
}
