package com.example.domain;

import com.example.convert.db.BooleanConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends Auditable implements UserDetails {

	private static final long serialVersionUID = -903512326871667760L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
    private long userId;

	@Column(length = 400)
	private String name;

	@Column(name = "username", unique = true)
	private String userName;

    @Column
	private String password;

    @Convert(converter = BooleanConverter.class)
    @Column(nullable = false)
    private Boolean isAccountNonExpired = false;

    @Convert(converter = BooleanConverter.class)
    @Column(nullable = false)
    private Boolean isAccountNonLocked = false;

    @Convert(converter = BooleanConverter.class)
    @Column(nullable = false)
    private Boolean isCredentialsNonExpired = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private List<Role> roles = new ArrayList<>();

    public User(long userId) {
        this.userId = userId;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setIsAccountNonExpired(Boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(Boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isAccountNonLocked;
    }

    public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public boolean isEnabled() {
        return this.getIsActive();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }

    @JsonIgnore
    public List<Role> getRoles() {
        return roles;
    }

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}
}
