package com.villa_agency.enquiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {

	// test effort to create a new custom method for fetching enquiries based on
	// created date in descending order (latest enquiry first)
	// public List<Enquiry> findByOrderByCreatedDateDesc();
}
