package br.com.systemautoma.automasystem.service;

import br.com.systemautoma.automasystem.domain.converter.Converter;
import br.com.systemautoma.automasystem.domain.dtos.UsuarioDto;
import br.com.systemautoma.automasystem.entity.PermissaoUser;
import br.com.systemautoma.automasystem.entity.Usuario;
import br.com.systemautoma.automasystem.exceptions.UsuarioException;
import br.com.systemautoma.automasystem.repository.PermissaoUserRepository;
import br.com.systemautoma.automasystem.repository.UsuarioRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PermissaoUserRepository permissaoRepository;

    public UsuarioDto buscaUsuario(Long id) throws NotFoundException {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Nenhum" +
                "usuario foi localizado com esse id"));
        return Converter.INSTANCE.usuarioToUsuarioDto(usuario);

    }

    public void salvaUsuario(UsuarioDto usuarioDto) throws NotFoundException, UsuarioException {

        if(usuarioRepository.findByLoginAndHabilitado(usuarioDto.getLogin(), true).isPresent()){
            throw new UsuarioException("Usuario ja Cadastrado");
        }

        PermissaoUser permissaoUser = permissaoRepository.findByNomePermissaoIgnoreCase(usuarioDto.getPermissoes().get(0).getNomePermissao())
                .orElseThrow(() -> new UsuarioException("Permissao nao Cadastrada"));

        Usuario usuario = Converter.INSTANCE.usuarioDtoToUsuario(usuarioDto);
        usuario.getPermissoes().add(permissaoUser);

        usuarioRepository.save(usuario);

    }

}
