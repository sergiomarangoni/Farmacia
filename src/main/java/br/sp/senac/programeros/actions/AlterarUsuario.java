package br.sp.senac.programeros.actions;

import br.sp.senac.programeros.connection.ConexaoBD;
import br.sp.senac.programeros.dao.UsuarioDAO;
import br.sp.senac.programeros.model.Usuario;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlterarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Variavel do id
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        //Request diretorio
        request.getRequestDispatcher("Usuarios/usuarioAlterar.jsp").forward(request, response);  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Variaves
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String ativo =  request.getParameter("ativo");
        Usuario usuario = new Usuario();
        usuario.setCodigo(id);
        usuario.setLogin(login);
        usuario.setNome(nome);
        usuario.setAtivo(ativo);
        //Comando do banco
        ConexaoBD conn = new ConexaoBD();
        Connection conexao = conn.obterConexao();
        //Objeto
        UsuarioDAO dao = new UsuarioDAO(conexao);
        dao.alterar(usuario);
        
        conn.fecharConexao();
        //Diretorio
        response.sendRedirect("/Farmacia/Usuarios/usuarios.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
