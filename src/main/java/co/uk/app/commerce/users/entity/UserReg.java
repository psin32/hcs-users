package co.uk.app.commerce.users.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "userreg")
@Data
public class UserReg implements Serializable {

	private static final long serialVersionUID = -7055340810021125326L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "users_id")
	@NotNull
	@JsonIgnore
	private Users users;

	@NotNull
	private String password;

	@Column(name = "passwordexpired", columnDefinition = "int default '0'")
	private Integer passwordexpired;

	@Column(name = "passwordretries", columnDefinition = "int default '0'")
	private Integer passwordretries;

	@Column(name = "lastpasswordresetdate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date lastPasswordResetDate;
}
