package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.VendorEntity;
import com.eventify.backend.repositories.VendorRepository;
import com.eventify.backend.services.servicesInter.VendorServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorServiceInter {

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public VendorEntity createVendor(VendorEntity vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public VendorEntity updateVendor(Long vendorId, VendorEntity vendorDetails) {
        Optional<VendorEntity> vendorOpt = vendorRepository.findById(vendorId);
        if (vendorOpt.isPresent()) {
            VendorEntity vendor = vendorOpt.get();
            vendor.setVendorName(vendorDetails.getVendorName());
            vendor.setCompanyName(vendorDetails.getCompanyName());
            vendor.setEmail(vendorDetails.getEmail());
            vendor.setPhone(vendorDetails.getPhone());
            vendor.setServiceCategory(vendorDetails.getServiceCategory());
            vendor.setServiceDescription(vendorDetails.getServiceDescription());
            return vendorRepository.save(vendor);
        }
        return null; // Or throw a custom NotFoundException
    }

    @Override
    public VendorEntity getVendorById(Long vendorId) {
        return vendorRepository.findById(vendorId).orElse(null);
    }

    @Override
    public List<VendorEntity> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public void deleteVendor(Long vendorId) {
        vendorRepository.deleteById(vendorId);
    }
}
