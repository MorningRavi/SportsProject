package Registration.SportsClub.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="registration_details")
public class RegistrationMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="registration_id")
	private int registration_id;
	
	@Column(name="applicant_name")
	private String applicant_name;
	
	@Column(name="email_id")
	private String email_id;
	
	@Column(name="mobile_no")
	private String mobile_no;
	
	@Column(name="gender")
	private char gender;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="image_path")
	private String image_path;
	
	@Column(name="club_id")
	private int club_id;
	
	@Column(name="sports_id")
	private int sports_id;
	
	@Column(name="fees")
	private double fees;
	
	@Column(name="deleted_flag",columnDefinition = "varchar(50) default 'N'")
	private String deleted_flag;

	@Transient
	private int age;
	
	@Transient
	private String club_name;
	
	@Transient
	private String sports_name;

	public int getRegistration_id() {
		return registration_id;
	}

	public void setRegistration_id(int registration_id) {
		this.registration_id = registration_id;
	}

	public String getApplicant_name() {
		return applicant_name;
	}

	public void setApplicant_name(String applicant_name) {
		this.applicant_name = applicant_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public int getClub_id() {
		return club_id;
	}

	public void setClub_id(int club_id) {
		this.club_id = club_id;
	}

	public int getSports_id() {
		return sports_id;
	}

	public void setSports_id(int sports_id) {
		this.sports_id = sports_id;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getClub_name() {
		return club_name;
	}

	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}

	public String getSports_name() {
		return sports_name;
	}

	public void setSports_name(String sports_name) {
		this.sports_name = sports_name;
	}
	
	public String getDeleted_flag() {
		return deleted_flag;
	}

	public void setDeleted_flag(String deleted_flag) {
		this.deleted_flag = deleted_flag;
	}
}
