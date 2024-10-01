package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.VendorEntity;
import java.util.List;

public interface VendorServiceInter {
    VendorEntity createVendor(VendorEntity vendor);
    VendorEntity updateVendor(Long vendorId, VendorEntity vendor);
    VendorEntity getVendorById(Long vendorId);
    List<VendorEntity> getAllVendors();
    void deleteVendor(Long vendorId);
}
