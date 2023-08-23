package br.edu.ifpb.progdist.hellospring.service;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.progdist.hellospring.dao.UsuariosDAO;
import br.edu.ifpb.progdist.hellospring.model.Usuario;

public class UsuarioService {
    private UsuariosDAO usuariosDAO = new UsuariosDAO();

    public List<Usuario> getUsuarios() {
        return usuariosDAO.getUsuarios();
    }

    public Usuario getUsuarioPorCodigo(int codigo) {
        return this.getUsuarios()
            .stream()
            .filter(usuario -> usuario.getCodigo() == codigo)
            .collect(Collectors.toList())
            .get(0);
    }

    public void inserirUsuario(Usuario usuario) {
        this.usuariosDAO.inserirUsuario(usuario);
    }
}
