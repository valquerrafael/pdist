package com.gugawag.pdist.servlets;

import com.gugawag.pdist.ejb.session.UsuarioService;
import com.gugawag.pdist.modelo.Usuario;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/usuario.do"})
public class UsuarioServlet extends HttpServlet {

    @EJB(lookup="java:module/usuarioService")
    private UsuarioService usuarioService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String operacao = request.getParameter("oper");
        PrintWriter resposta = response.getWriter();
        switch (operacao) {
            case "1": { // inserir
                long id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                usuarioService.inserir(id, nome);
            }
            case "2": { // listar
                for(Usuario usuario: usuarioService.listar()){
                    resposta.println(usuario.getNome());
                }
                break;
            }
        }
    }
}
