package co.uk.app.commerce.users.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Address implements Serializable {

	private static final long serialVersionUID = 4811471290422893136L;

	@ManyToOne
	@JoinColumn(name = "users_id")
	@NotNull
	@JsonIgnore
	private Users users;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private Long addressId;

	@NotNull
	private String addresstype;

	@NotNull
	private String status;

	@NotNull
	@Column(name = "isprimary", columnDefinition = "int default '0'")
	private Integer isprimary;

	@NotNull
	@Column(name = "selfaddress", columnDefinition = "int default '0'")
	private Integer selfaddress;

	private String title;

	private String firstname;

	private String lastname;

	@NotNull
	private String email1;

	private String email2;

	private String phone1;

	private String phone2;

	private String address1;

	private String address2;

	private String address3;

	private String city;

	private String state;

	private String zipcode;

	private String country;

	@JsonIgnore
	@Column(name = "lastcreate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date lastcreate;

	@JsonIgnore
	private String field1;

	@JsonIgnore
	private String field2;

	@JsonIgnore
	private String field3;
}
