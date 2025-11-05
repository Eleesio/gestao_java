package model;

import java.sql.Timestamp;

/**
*
* @author Eleesio
*/
public class ModelUsuario {

    private int idUsuario;
    private String usuNome;
    private String usuLogin;
    private String usuSenha;
    private Timestamp usuDataUltimoLogin;
    private Timestamp usuDataUltimaAtividade;
    private String usuIpUltimoLogin;
    private int usuTentativasLogin;
    private boolean usuBloqueado;
    private Timestamp usuDataCriacao;
    private Timestamp usuDataBloqueio;
    private String usuTokenRecuperacao;
    private Timestamp usuExpiracaoToken;

    /**
    * Construtor
    */
    public ModelUsuario(){}

    /**
    * seta o valor de idUsuario
    * @param pIdUsuario
    */
    public void setIdUsuario(int pIdUsuario){
        this.idUsuario = pIdUsuario;
    }
    /**
    * return pk_idUsuario
    */
    public int getIdUsuario(){
        return this.idUsuario;
    }

    /**
    * seta o valor de usuNome
    * @param pUsuNome
    */
    public void setUsuNome(String pUsuNome){
        this.usuNome = pUsuNome;
    }
    /**
    * return usuNome
    */
    public String getUsuNome(){
        return this.usuNome;
    }

    /**
    * seta o valor de usuLogin
    * @param pUsuLogin
    */
    public void setUsuLogin(String pUsuLogin){
        this.usuLogin = pUsuLogin;
    }
    /**
    * return usuLogin
    */
    public String getUsuLogin(){
        return this.usuLogin;
    }

    /**
    * seta o valor de usuSenha
    * @param pUsuSenha
    */
    public void setUsuSenha(String pUsuSenha){
        this.usuSenha = pUsuSenha;
    }
    /**
    * return usuSenha
    */
    public String getUsuSenha(){
        return this.usuSenha;
    }

    /**
    * seta o valor de usuDataUltimoLogin
    * @param pUsuDataUltimoLogin
    */
    public void setUsuDataUltimoLogin(Timestamp pUsuDataUltimoLogin){
        this.usuDataUltimoLogin = pUsuDataUltimoLogin;
    }
    /**
    * return usuDataUltimoLogin
    */
    public Timestamp getUsuDataUltimoLogin(){
        return this.usuDataUltimoLogin;
    }

    /**
    * seta o valor de usuDataUltimaAtividade
    * @param pUsuDataUltimaAtividade
    */
    public void setUsuDataUltimaAtividade(Timestamp pUsuDataUltimaAtividade){
        this.usuDataUltimaAtividade = pUsuDataUltimaAtividade;
    }
    /**
    * return usuDataUltimaAtividade
    */
    public Timestamp getUsuDataUltimaAtividade(){
        return this.usuDataUltimaAtividade;
    }

    /**
    * seta o valor de usuIpUltimoLogin
    * @param pUsuIpUltimoLogin
    */
    public void setUsuIpUltimoLogin(String pUsuIpUltimoLogin){
        this.usuIpUltimoLogin = pUsuIpUltimoLogin;
    }
    /**
    * return usuIpUltimoLogin
    */
    public String getUsuIpUltimoLogin(){
        return this.usuIpUltimoLogin;
    }

    /**
    * seta o valor de usuTentativasLogin
    * @param pUsuTentativasLogin
    */
    public void setUsuTentativasLogin(int pUsuTentativasLogin){
        this.usuTentativasLogin = pUsuTentativasLogin;
    }
    /**
    * return usuTentativasLogin
    */
    public int getUsuTentativasLogin(){
        return this.usuTentativasLogin;
    }

    /**
    * seta o valor de usuBloqueado
    * @param pUsuBloqueado
    */
    public void setUsuBloqueado(boolean pUsuBloqueado){
        this.usuBloqueado = pUsuBloqueado;
    }
    /**
    * return usuBloqueado
    */
    public boolean isUsuBloqueado(){
        return this.usuBloqueado;
    }

    /**
    * seta o valor de usuDataCriacao
    * @param pUsuDataCriacao
    */
    public void setUsuDataCriacao(Timestamp pUsuDataCriacao){
        this.usuDataCriacao = pUsuDataCriacao;
    }
    /**
    * return usuDataCriacao
    */
    public Timestamp getUsuDataCriacao(){
        return this.usuDataCriacao;
    }

    /**
    * seta o valor de usuDataBloqueio
    * @param pUsuDataBloqueio
    */
    public void setUsuDataBloqueio(Timestamp pUsuDataBloqueio){
        this.usuDataBloqueio = pUsuDataBloqueio;
    }
    /**
    * return usuDataBloqueio
    */
    public Timestamp getUsuDataBloqueio(){
        return this.usuDataBloqueio;
    }

    /**
    * seta o valor de usuTokenRecuperacao
    * @param pUsuTokenRecuperacao
    */
    public void setUsuTokenRecuperacao(String pUsuTokenRecuperacao){
        this.usuTokenRecuperacao = pUsuTokenRecuperacao;
    }
    /**
    * return usuTokenRecuperacao
    */
    public String getUsuTokenRecuperacao(){
        return this.usuTokenRecuperacao;
    }

    /**
    * seta o valor de usuExpiracaoToken
    * @param pUsuExpiracaoToken
    */
    public void setUsuExpiracaoToken(Timestamp pUsuExpiracaoToken){
        this.usuExpiracaoToken = pUsuExpiracaoToken;
    }
    /**
    * return usuExpiracaoToken
    */
    public Timestamp getUsuExpiracaoToken(){
        return this.usuExpiracaoToken;
    }

    @Override
    public String toString(){
        return "ModelUsuario {" + 
               "::idUsuario = " + this.idUsuario + 
               "::usuNome = " + this.usuNome + 
               "::usuLogin = " + this.usuLogin + 
               "::usuSenha = " + this.usuSenha + 
               "::usuDataUltimoLogin = " + this.usuDataUltimoLogin + 
               "::usuDataUltimaAtividade = " + this.usuDataUltimaAtividade + 
               "::usuIpUltimoLogin = " + this.usuIpUltimoLogin + 
               "::usuTentativasLogin = " + this.usuTentativasLogin + 
               "::usuBloqueado = " + this.usuBloqueado + 
               "::usuDataCriacao = " + this.usuDataCriacao + 
               "::usuDataBloqueio = " + this.usuDataBloqueio + 
               "::usuTokenRecuperacao = " + this.usuTokenRecuperacao + 
               "::usuExpiracaoToken = " + this.usuExpiracaoToken + 
               "}";
    }
}