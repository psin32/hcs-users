package co.uk.app.commerce.users.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import co.uk.app.commerce.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginBean implements Serializable, UserDetails {

	private static final long serialVersionUID = 4804179612436659755L;

	private String username;

	private String password;

	private Users users;

	@Transient
	private Set<GrantedAuthority> authorities;

	@Transient
	private boolean accountNonExpired = true;

	@Transient
	private boolean accountNonLocked = true;

	@Transient
	private boolean credentialsNonExpired = true;

	@Transient
	private boolean enabled = true;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
