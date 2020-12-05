package CS4125.group5.service;
import CS4125.group5.dto.LoginRequest;
import CS4125.group5.dto.LoginResponse;
import CS4125.group5.dto.RegisterRequest;
import CS4125.group5.entity.User;
import CS4125.group5.repository.UserRepository;
import CS4125.group5.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setCreateDate(Instant.now());

        userRepository.save(user);
    }
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }

    public LoginResponse createLoginToken(LoginRequest loginRequest) throws Exception {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password");
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtProvider.generateToken(userDetails);
        //final String username = jwtTokenUtil.
        //return ResponseEntity.ok(new AuthenticationResponse(jwt));
        return   LoginResponse.builder()
                .jwt(jwt)
                .username(loginRequest.getUsername())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationinMillis()))
                .build();
    }

}

