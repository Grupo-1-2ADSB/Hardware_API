package dao;

import model.ConexaoBanco;
import model.usuario.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario retornaUsuario(String userVerificar, String senhaVerificar) throws SQLException {
        try(Connection conexao = ConexaoBanco.con()){
            PreparedStatement queryBanco = conexao.prepareStatement("SELECT * FROM usuario WHERE nomeUser = ? AND senha = ?");
            queryBanco.setString(1,userVerificar);
            queryBanco.setString(2, senhaVerificar);

            ResultSet resultSet = queryBanco.executeQuery();
            if (resultSet.next()){
                Usuario usuario = new Usuario(resultSet.getInt("idUsuario"),resultSet.getString("nomeUser"),
                        resultSet.getString("email"),resultSet.getString("senha"));
                return usuario;
            }


        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
        return null;
    }

}
