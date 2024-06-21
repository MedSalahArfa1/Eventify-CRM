package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.AddressEntity;

import java.util.List;

public interface AddressServiceInter {
    List<AddressEntity> getAllAddresses();

    AddressEntity getAddressById(Long id);

    AddressEntity createAddress(AddressEntity address);

    AddressEntity updateAddress(Long id, AddressEntity addressDetails);

    void deleteAddress(Long id);
}
