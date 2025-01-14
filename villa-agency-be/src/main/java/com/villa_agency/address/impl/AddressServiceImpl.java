package com.villa_agency.address.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.villa_agency.address.Address;
import com.villa_agency.address.AddressRepository;
import com.villa_agency.address.AddressService;
import com.villa_agency.exception.IdNotFoundException;
import com.villa_agency.property.Property;
import com.villa_agency.property.PropertyRepository;

@Service
public class AddressServiceImpl implements AddressService {

	private AddressRepository addressRepo;
	private PropertyRepository propertyRepo;

	public AddressServiceImpl(AddressRepository addressRepo, PropertyRepository propertyRepo) {
		super();
		this.addressRepo = addressRepo;
		this.propertyRepo = propertyRepo;
	}

	@Override
	public List<Address> getAddresses() {
		return addressRepo.findAll();
	}

	@Override
	public void createAddress(Address address) {
		// TODO Auto-generated method stub
		addressRepo.save(address);
	}

	@Override
	public void updateAddressById(Long id, Address address) {
		Optional<Address> selectedAddress = addressRepo.findById(id);

		if (selectedAddress.isEmpty()) {
			throw new IdNotFoundException("Address with id " + id + "not found.");
		}

		Address addressDetails = selectedAddress.get();

		addressDetails.setStreet(address.getStreet());
		addressDetails.setCity(address.getCity());
		addressDetails.setState(address.getState());
		addressDetails.setZip(address.getZip());
		addressDetails.setCountry(address.getCountry());

		addressRepo.save(addressDetails);

	}

	@Override
	public void deleteAddress(Long id) {
		Optional<Property> foundProperty = propertyRepo.findByAddress_Id(id);

		if (foundProperty.isPresent()) {
			throw new DataIntegrityViolationException("Property already uses this address");
		}


		// if (foundProperty.equals(id)) {
		// System.out.println(foundProperty);
		// }

		addressRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Address with id " + id + " not found."));

		addressRepo.deleteById(id);

	}

}
