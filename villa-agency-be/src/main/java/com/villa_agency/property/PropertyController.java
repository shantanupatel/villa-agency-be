package com.villa_agency.property;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class PropertyController {

	private PropertyService propertyService;

	public PropertyController(PropertyService propertyService) {
		super();
		this.propertyService = propertyService;
	}

	@GetMapping
	public ResponseEntity<List<Property>> getAllProperties() {
		List<Property> properties = propertyService.getAllProperties();

		return new ResponseEntity<>(properties, HttpStatus.OK);
	}
}
