package model;

/**
 *
 * @author Eleesio
 */
public class ModelSessaoUsuario {
    
    public static int codigo;
    public static String nome;
    public static String login;

    public ModelSessaoUsuario() {
    }
    
    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}