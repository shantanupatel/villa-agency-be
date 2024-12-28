package com.villa_agency.property;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.villa_agency.response.GenericResponse;

@RestController
// @RequestMapping("/properties")
public class PropertyController {

	private PropertyService propertyService;

	public PropertyController(PropertyService propertyService) {
		super();
		this.propertyService = propertyService;
	}

	@GetMapping(path = "/all/properties")
	public GenericResponse<List<Property>> getAllProperties() {
		List<Property> properties = propertyService.getAllProperties();

		// return new ResponseEntity<>(properties, HttpStatus.OK);
		return new GenericResponse<>(HttpStatus.OK.value(), "List of properties retrieved successfully", properties);
	}

	@PostMapping(path = "/admin/properties")
	public GenericResponse<String> createNewProperty(@RequestBody Property property) {
		propertyService.createProperty(property);

		return new GenericResponse<>(HttpStatus.CREATED.value(), "New property created successfully", null);
	}
}
