package com.villa_agency.enquiry;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.villa_agency.response.GenericResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
// @RequestMapping("/enquiries")
public class EnquiryController {

	private EnquiryService enquiryService;

	public EnquiryController(EnquiryService enquiryService) {
		super();
		this.enquiryService = enquiryService;
	}

	@GetMapping(path = "/admin/enquiries")
	public GenericResponse<List<Enquiry>> getEnquiries() {
		List<Enquiry> enquiries = enquiryService.getEnquiries();

		return new GenericResponse<>(HttpStatus.OK.value(), "List of enquiries retrieved successfully", enquiries);
	}

	// @CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(consumes = "application/json", path = "/enquiries")
	public ResponseEntity<String> createEnquiry(@RequestBody Enquiry enquiry) {
		enquiryService.createEnquiry(enquiry);

		return new ResponseEntity<>("Your enquiry has been submitted successfully", HttpStatus.CREATED);
	}
}
