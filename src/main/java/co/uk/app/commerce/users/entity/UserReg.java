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

@Entity
@Table(name = "userreg")
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

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPasswordexpired() {
		return passwordexpired;
	}

	public void setPasswordexpired(Integer passwordexpired) {
		this.passwordexpired = passwordexpired;
	}

	public Integer getPasswordretries() {
		return passwordretries;
	}

	public void setPasswordretries(Integer passwordretries) {
		this.passwordretries = passwordretries;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
