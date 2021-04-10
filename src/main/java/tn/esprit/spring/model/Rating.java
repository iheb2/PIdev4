package tn.esprit.pidev.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rating  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Rating(Long long1, int i, int j, int k, double d, int l, Date date) {
		// TODO Auto-generated constructor stub
	}
	public Rating(Long long1, Long agencyId, int likes, int dislike, Double rating, Integer totalRatings,
			Date createTimestamp) {
		super();
		this.id = long1;
		this.AgencyId = agencyId;
		this.likes = likes;
		this.dislike = dislike;
		this.rating = rating;
		this.totalRatings = totalRatings;
		this.createTimestamp = createTimestamp;
	}
	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@Column(name = "ratingId")
	@GeneratedValue( strategy = GenerationType.IDENTITY )

	private Long id;
	private Long AgencyId;

	@Column(name = "likes")
	private int likes;

	@Column(name = "dislike")
	private int dislike;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "totalRatings")
	private Integer totalRatings;
	
	@Temporal (TemporalType.DATE)
	@Column(name = "createTimestamp")
	private Date createTimestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getTotalRatings() {
		return totalRatings;
	}

	public void setTotalRatings(Integer totalRatings) {
		this.totalRatings = totalRatings;
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

	public Long getAgencyId() {
		return AgencyId;
	}

	public void setAgencyId(Long agencyId) {
		AgencyId = agencyId;
	}
	public Object compareTo(Rating rating2) {
		// TODO Auto-generated method stub
		return null;
	}


}
