package co.uk.app.commerce.users.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Users implements Serializable {

	private static final long serialVersionUID = 1738906516140815390L;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private Set<Address> address = new HashSet<>();

	@OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
	private UserReg userreg = null;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "users_id")
	private Long userId;

	@Column(name = "username")
	@NotNull
	private String username;

	@NotNull
	private String registertype;

	private String profiletype;

	@Column(name = "language_id")
	private Integer languageId;

	@Column(name = "registration", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date registration;

	@Column(name = "lastsession", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date lastsession;

	@Column(name = "registrationupdate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date registrationupdate;

	@JsonIgnore
	private String field1;

	@JsonIgnore
	private String field2;

	@JsonIgnore
	private String field3;

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRegistertype() {
		return registertype;
	}

	public void setRegistertype(String registertype) {
		this.registertype = registertype;
	}

	public String getProfiletype() {
		return profiletype;
	}

	public void setProfiletype(String profiletype) {
		this.profiletype = profiletype;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	public Date getLastsession() {
		return lastsession;
	}

	public void setLastsession(Date lastsession) {
		this.lastsession = lastsession;
	}

	public Date getRegistrationupdate() {
		return registrationupdate;
	}

	public void setRegistrationupdate(Date registrationupdate) {
		this.registrationupdate = registrationupdate;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public UserReg getUserreg() {
		return userreg;
	}

	public void setUserreg(UserReg userreg) {
		this.userreg = userreg;
	}
}
