package co.uk.app.commerce.users.entity;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

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
}
