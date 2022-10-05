package br.com.cerberusit.security;

import br.com.cerberusit.domain.model.auth.ProfileMenuAccess;
import br.com.cerberusit.domain.model.auth.User;
import br.com.cerberusit.dto.MenuAccessLoginDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

    public Set<MenuAccessLoginDto> getMenuAccess(){
        return this.getUser().getProfile().getProfileMenuAccesses()
                .stream().map(MenuAccessLoginDto::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authoritySet = new HashSet<SimpleGrantedAuthority>();
        authoritySet.add(new SimpleGrantedAuthority(this.getUser().getProfile().getDescription()));
        return authoritySet;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.user.isAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.user.isPasswordExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.getUser().isActive();
    }
}
