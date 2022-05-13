package trm.agenda.authentication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trm.agenda.authentication.domain.model.Usuario;
import trm.agenda.authentication.domain.model.dto.AuthRequest;
import trm.agenda.authentication.domain.model.dto.JwtResponse;
import trm.agenda.authentication.domain.model.dto.SignupResponse;
import trm.agenda.authentication.domain.model.dto.UserAlreadyExistsResponse;
import trm.agenda.authentication.domain.repository.UsuarioRepository;
import trm.agenda.authentication.service.UserDetailsImpl;
import trm.agenda.authentication.utility.JwtUtility;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UsuarioRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody AuthRequest loginRequest,
            HttpServletRequest request) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = this.jwtUtility.tokenIsValid(request) ? this.jwtUtility.extractJwtFromRequest(request)
                : this.jwtUtility.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername()));
    }

    @PutMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        ResponseEntity<?> response = null;
        if (this.userRepository.existsByUsername(signUpRequest.getUsername())) {
            response = ResponseEntity
                    .status(HttpStatus.CONFLICT.value())
                    .body(new UserAlreadyExistsResponse(HttpStatus.CONFLICT.value(),
                            "Username '" + signUpRequest.getUsername()
                                    + "' is already taken"));
        } else {
            Usuario user = new Usuario(signUpRequest.getUsername(),
                    this.passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail());
            this.userRepository.save(user);
            response = ResponseEntity.status(HttpStatus.CREATED.value())
                    .body(new SignupResponse(user.getId(), user.getUsername()));
        }
        return response;
    }

}
