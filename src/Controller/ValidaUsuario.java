
package Controller;

import DAO.UsuarioDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ValidaUsuario {
    private String usuario, senha;

    public void validaRegistro() throws SQLException{
        if(getUsuario().equals("") || getUsuario() == null || getSenha().equals("") || getSenha() == null){
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos");
        }else{
            UsuarioDAO usuariodao = new UsuarioDAO();
            usuariodao.setUsuario(getUsuario());
            usuariodao.setSenha(getSenha());
            usuariodao.registraUsuario();
        }
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
