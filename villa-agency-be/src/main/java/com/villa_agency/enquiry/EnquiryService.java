package com.villa_agency.enquiry;

import java.util.List;

public interface EnquiryService {

	List<Enquiry> getEnquiries();

	void createEnquiry(Enquiry enquiry);
}
