package com.medtech.model.usuario;

public class Usuario {
    private Integer idUsuario;
    private String nomeUser;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nomeUser, String email, String senha) {
        this.idUsuario = idUsuario;
        this.nomeUser = nomeUser;
        this.email = email;
        this.senha = senha;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "\nUsuario{" +
                "idUsuario=" + idUsuario +
                ", nomeUser='" + nomeUser + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
