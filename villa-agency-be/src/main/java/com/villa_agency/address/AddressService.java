package com.villa_agency.address;

import java.util.List;

public interface AddressService {

	List<Address> getAddresses();

	void createAddress(Address address);

	void updateAddressById(Long id, Address address);

	void deleteAddress(Long id);
}
