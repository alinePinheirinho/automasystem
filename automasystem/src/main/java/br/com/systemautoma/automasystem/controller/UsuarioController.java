package br.com.systemautoma.automasystem.controller;

import br.com.systemautoma.automasystem.domain.dtos.UsuarioDto;
import br.com.systemautoma.automasystem.exceptions.UsuarioException;
import br.com.systemautoma.automasystem.service.UsuarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UsuarioDto> buscaUsuario(@PathVariable("id") Long id) throws NotFoundException {

        UsuarioDto usuarioDto = null;

        usuarioDto = usuarioService.buscaUsuario(id);
        return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
        }


    @RequestMapping(value = "/usuario/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity salvaUsuario(@RequestBody UsuarioDto usuarioDto) throws NotFoundException, UsuarioException {

        usuarioService.salvaUsuario(usuarioDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }


}
