package com.villa_agency.socialplatform;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.villa_agency.sociallink.SocialLink;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@SuppressWarnings("deprecation")
@Entity
@SQLDelete(sql = "UPDATE social_platform SET deleted = true, deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
public class SocialPlatform {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String platformName;

	private String baseUrl;

	@CreationTimestamp(source = SourceType.DB)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;

	@UpdateTimestamp(source = SourceType.DB)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedDate;

	private boolean deleted = false;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime deletedAt;

	// One-to-One mapped by Profile
	@OneToOne(mappedBy = "socialPlatform", cascade = CascadeType.ALL)
	@JsonBackReference // prevents recursive serialization
	private SocialLink socialLink;

	public SocialPlatform() {
		super();
	}

	public SocialPlatform(Long id, String platformName, String baseUrl, LocalDateTime createdDate,
			LocalDateTime updatedDate, boolean deleted,
			LocalDateTime deletedAt) {
		super();
		this.id = id;
		this.platformName = platformName;
		this.baseUrl = baseUrl;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.deleted = deleted;
		this.deletedAt = deletedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public SocialLink getSocialLink() {
		return socialLink;
	}

	public void setSocialLink(SocialLink socialLink) {
		this.socialLink = socialLink;
	}

	@Override
	public String toString() {
		return "SocialPlatform [id=" + id + ", platformName=" + platformName + ", baseUrl=" + baseUrl + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", deleted=" + deleted + ", deletedAt=" + deletedAt
				+ ", socialLink=" + socialLink + "]";
	}

}
