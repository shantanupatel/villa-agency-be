package com.villa_agency.sociallink;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.villa_agency.socialplatform.SocialPlatform;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;

@Entity
public class SocialLink {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String profileUrl;

	private String displayName;

	@CreationTimestamp(source = SourceType.DB)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;

	@UpdateTimestamp(source = SourceType.DB)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedDate;

	// Foreign key column to parent entity
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "social_platform_id", referencedColumnName = "id")
	@JsonIgnore // avoids circular nesting, we’ll show platformName instead
	private SocialPlatform socialPlatform;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String platformName;

	@PostLoad
	private void populateDerivedFields() {
		this.platformName = socialPlatform != null ? socialPlatform.getPlatformName() : null;
	}

	public SocialLink() {
		super();
	}

	// public SocialLink(Long id, String name, String link, LocalDateTime
	// createdDate, LocalDateTime updatedDate) {
	// super();
	// this.id = id;
	// this.name = name;
	// this.link = link;
	// this.createdDate = createdDate;
	// this.updatedDate = updatedDate;
	// }

	public SocialLink(Long id, String profileUrl, String displayName, LocalDateTime createdDate,
			LocalDateTime updatedDate, SocialPlatform socialPlatform) {
		super();
		this.id = id;
		this.profileUrl = profileUrl;
		this.displayName = displayName;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.socialPlatform = socialPlatform;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// public String getName() {
	// return name;
	// }
	//
	// public void setName(String name) {
	// this.name = name;
	// }

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public SocialPlatform getSocialPlatform() {
		return socialPlatform;
	}

	public void setSocialPlatform(SocialPlatform socialPlatform) {
		this.socialPlatform = socialPlatform;
	}

	@Override
	public String toString() {
		return "SocialLink [id=" + id + ", profileUrl=" + profileUrl + ", displayName=" + displayName + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", socialPlatform=" + socialPlatform + "]";
	}

}
