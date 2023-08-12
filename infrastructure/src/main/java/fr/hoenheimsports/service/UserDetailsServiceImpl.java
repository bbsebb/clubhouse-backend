package fr.hoenheimsports.service;

import fr.hoenheimsports.userdomain.api.AccountService;
import fr.hoenheimsports.userdomain.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountService accountService;

    public UserDetailsServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = this.accountService.loadByUsername(username);
        Collection<GrantedAuthority> authorities= List.of(new SimpleGrantedAuthority("test"));
        return optUser
                .map(user ->  new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
