package fr.hoenheimsports.service.user;

import fr.hoenheimsports.userdomain.api.AccountService;
import fr.hoenheimsports.userdomain.exception.UserNotFoundException;
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
        User user;
        try {
            user = this.accountService.loadByLogin(username);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }

        Collection<GrantedAuthority> authorities= List.of(new SimpleGrantedAuthority("test"));
        return  new CustomUserDetails(user.getId().toString(),user.getUsername(),user.getPassword(),authorities);
    }
}