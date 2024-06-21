package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.AddressEntity;
import com.eventify.backend.repositories.AddressRepository;
import com.eventify.backend.services.servicesInter.AddressServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressServiceInter {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressEntity> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public AddressEntity getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public AddressEntity createAddress(AddressEntity address) {
        return addressRepository.save(address);
    }

    @Override
    public AddressEntity updateAddress(Long id, AddressEntity addressDetails) {
        AddressEntity address = addressRepository.findById(id).orElse(null);
        if (address != null) {
            address.setLocation(addressDetails.getLocation());
            address.setStreet(addressDetails.getStreet());
            address.setCity(addressDetails.getCity());
            address.setZipCode(addressDetails.getZipCode());
            address.setState(addressDetails.getState());
            address.setCountry(addressDetails.getCountry());
            return addressRepository.save(address);
        }
        return null;
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
