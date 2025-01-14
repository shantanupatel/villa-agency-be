package com.villa_agency.address;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.villa_agency.response.GenericResponse;

@RestController
// @CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/addresses")
public class AddressController {

	private AddressService addressService;

	public AddressController(AddressService addressService) {
		super();
		this.addressService = addressService;
	}

	@GetMapping
	public GenericResponse<List<Address>> getAllAddresses() {
		// return addressService.getAddresses();
		List<Address> addresses = addressService.getAddresses();

		return new GenericResponse<>(HttpStatus.OK.value(), "List of addresses retrieved successfully", addresses);
	}

	@PostMapping
	public GenericResponse<String> postNewAddress(@RequestBody Address address) {
		addressService.createAddress(address);

		return new GenericResponse<>(HttpStatus.CREATED.value(), "New Address created successfully", null);
	}

	@PutMapping("/{addressId}")
	public GenericResponse<String> editExistingAddress(@PathVariable Long addressId, @RequestBody Address address) {
		addressService.updateAddressById(addressId, address);

		return new GenericResponse<>(HttpStatus.ACCEPTED.value(), "Address updated successfully", null);
	}

	@DeleteMapping("/{addressId}")
	public GenericResponse<String> deleteExistingAddress(@PathVariable Long addressId) {
		addressService.deleteAddress(addressId);

		return new GenericResponse<>(HttpStatus.ACCEPTED.value(),
				"Address deleted successfully", null);
	}
}
