package com.villa_agency.property.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.villa_agency.exception.IdNotFoundException;
import com.villa_agency.property.Property;
import com.villa_agency.property.PropertyRepository;
import com.villa_agency.property.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

	private PropertyRepository propertyRepo;

	public PropertyServiceImpl(PropertyRepository propertyRepo) {
		super();
		this.propertyRepo = propertyRepo;
	}

	@Override
	public List<Property> getAllProperties() {
		// return propertyRepo.findAll();

		List<Property> properties = propertyRepo.findAll();

		return properties.stream().toList();
	}

	@Override
	public void createProperty(Property property) {
		propertyRepo.save(property);
	}

	@Override
	public Property findPropertyById(long propertyId) {

		return propertyRepo.findById(propertyId)
		.orElseThrow(() -> new IdNotFoundException("Property with id " + propertyId + " not found"));

	}

}
