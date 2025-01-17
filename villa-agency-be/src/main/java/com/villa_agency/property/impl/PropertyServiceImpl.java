package com.villa_agency.property.impl;

import java.util.List;

import org.springframework.stereotype.Service;

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
		return propertyRepo.findAll();
	}

}
