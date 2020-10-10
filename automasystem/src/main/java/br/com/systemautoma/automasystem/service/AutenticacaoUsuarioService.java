package br.com.systemautoma.automasystem.service;

import br.com.systemautoma.automasystem.entity.Usuario;
import br.com.systemautoma.automasystem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AutenticacaoUsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByLoginAndHabilitado(username, true);

        if(usuario.isPresent()){
            return usuario.get();
        }

    throw new UsernameNotFoundException("Usuario ou Senha invalida");
    }
}
