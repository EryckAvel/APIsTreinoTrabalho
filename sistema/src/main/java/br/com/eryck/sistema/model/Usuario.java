package br.com.eryck.sistema.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usuario", nullable = false, length = 255)
    private String usuario;
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    public Usuario() {
    }

    public Usuario(Long id, String usuario, String email, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
}
