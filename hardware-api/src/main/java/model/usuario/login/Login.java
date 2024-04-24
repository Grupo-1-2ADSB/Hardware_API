package model.usuario.login;

public class Login {
    private String usuario;
    private String senha;

    public Login(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
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

    public Boolean verificarCredenciais(String usuarioVar, String senhaVar) {
        if (this.usuario.equals(usuarioVar) && this.senha.equals(senhaVar)) {
            return true;
        } else {
            return false;
        }
    }

}
