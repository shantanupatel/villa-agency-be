package com.villa_agency.address.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.villa_agency.address.Address;
import com.villa_agency.address.AddressRepository;
import com.villa_agency.address.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	private AddressRepository addressRepo;

	public AddressServiceImpl(AddressRepository addressRepo) {
		super();
		this.addressRepo = addressRepo;
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

}
