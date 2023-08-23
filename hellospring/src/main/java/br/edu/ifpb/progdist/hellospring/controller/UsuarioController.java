package br.edu.ifpb.progdist.hellospring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.progdist.hellospring.model.Usuario;
import br.edu.ifpb.progdist.hellospring.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService usuariosService = new UsuarioService();

    @GetMapping
    public List<Usuario> getUsuarios() {
        return this.usuariosService.getUsuarios();
    }

    @GetMapping("/{codigo}")
    public Usuario getUsuarioPorCodigo(@PathVariable int codigo) {
        return this.usuariosService.getUsuarioPorCodigo(codigo);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public Integer inserirUsuario(@RequestBody Usuario usuario) {
        this.usuariosService.inserirUsuario(usuario);
        return usuario.getCodigo();
    }
}
