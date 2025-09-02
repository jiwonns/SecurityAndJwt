package kr.co.securityjwt.service;

import kr.co.securityjwt.dto.CustomUserInfoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetails {
    private final CustomUserInfoDto member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_" + member.getRole().toString());

        return roles.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword(){
        return member.getPassword();
    }

    @Override
    public String getUsername(){
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
