package com.villa_agency.property;

import java.util.List;

public interface PropertyService {

	List<Property> getAllProperties();

	void createProperty(Property property);

	Property findPropertyById(long propertyId);
}
