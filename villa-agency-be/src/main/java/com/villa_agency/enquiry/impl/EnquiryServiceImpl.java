package com.villa_agency.enquiry.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.villa_agency.enquiry.Enquiry;
import com.villa_agency.enquiry.EnquiryRepository;
import com.villa_agency.enquiry.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	private EnquiryRepository enquiryRepo;

	public EnquiryServiceImpl(EnquiryRepository enquiryRepo) {
		super();
		this.enquiryRepo = enquiryRepo;
	}

	@Override
	public List<Enquiry> getEnquiries() {
		// TODO Auto-generated method stub
		// get list of all enquires sorted in descending order on the basis of creation
		// date
		List<Enquiry> enquiries = enquiryRepo.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));

		return enquiries;
	}

	@Override
	public void createEnquiry(Enquiry enquiry) {
		enquiryRepo.save(enquiry);

	}

}
