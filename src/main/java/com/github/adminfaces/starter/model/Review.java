package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Review implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int reviewId;

	@Column(name = "AgencyId")
	private Long  AgencyId;

	@Column(name = "createdUserId")
	private int createdUserId;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Column(name = "createdUserName")
	private String createdUserName;

	@Column(name = "likeAgency")
	private String likeMovie;

	@Column(name = "comments")
	private String comments;

	@Column(name = "score")
	private Integer score;
	@Temporal (TemporalType.DATE)
	@Column(name = "createTimestamp")
	private Date createTimestamp;
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public Long getAgencyId() {
		return AgencyId;
	}
	public void setAgencyId(Long agencyId) {
		AgencyId =  agencyId;
	}
	public int getCreatedUserId() {
		return createdUserId;
	}
	public void setCreatedUserId(int createdUserId) {
		this.createdUserId = createdUserId;
	}
	public String getCreatedUserName() {
		return createdUserName;
	}
	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}
	public String getLikeMovie() {
		return likeMovie;
	}
	public void setLikeMovie(String likeMovie) {
		this.likeMovie = likeMovie;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Date getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
