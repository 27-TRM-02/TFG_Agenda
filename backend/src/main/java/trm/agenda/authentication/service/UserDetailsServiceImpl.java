package trm.agenda.authentication.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import trm.agenda.authentication.domain.model.Usuario;
import trm.agenda.authentication.domain.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        Usuario user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User '" + username + "' does not exist."));
        return UserDetailsImpl.build(user);
    }

}
