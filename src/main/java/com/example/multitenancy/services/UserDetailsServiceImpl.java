package com.example.multitenancy.services;

import com.example.multitenancy.models.UserTenant;
import com.example.multitenancy.repository.UserTenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserTenantRepository userTenantRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                        .getRequest();

        String serverName = request.getServerName();
        String subdomain = serverName.substring(0, serverName.indexOf("."));
        UserTenant userTenant = userTenantRepository.findByUserAndSubdomain(username, subdomain)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "UserTenant Not Found with username: " + username + " and " + subdomain));

        List<GrantedAuthority> authorities = userTenant.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                userTenant.getUser().getId(),
                userTenant.getUser().getUsername(),
                userTenant.getUser().getPassword(),
                authorities
        );

    }

}
