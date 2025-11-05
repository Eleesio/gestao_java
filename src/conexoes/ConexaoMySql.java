package conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Classe de conexão com o banco de dados MySQL
 * 
 * @autor Br
 */
public class ConexaoMySql {

    private boolean status = false;
    private String mensagem = "";   // Variável que vai informar o status da conexão
    private Connection con = null;  // Variável para conexão
    private Statement statement;
    private ResultSet resultSet;

    private String servidor = "localhost";
    private String nomeDoBanco = "vendas_pdv";
    private String usuario = "root";
    private String senha = "";

    public ConexaoMySql() {}

    public ConexaoMySql(String pServidor, String pNomeDoBanco, String pUsuario, String pSenha) {
        this.servidor = pServidor;
        this.nomeDoBanco = pNomeDoBanco;
        this.usuario = pUsuario;
        this.senha = pSenha;
    }

    /**
     * Abre uma conexão com o banco de dados
     * 
     * @return Connection
     */
    public Connection conectar() {
        try {
            // Driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Local do banco, nome do banco, usuário e senha, incluindo parâmetros de codificação
            String url = "jdbc:mysql://" + servidor + "/" + nomeDoBanco + "?useUnicode=true&characterEncoding=UTF-8";
            this.con = DriverManager.getConnection(url, usuario, senha);

            // Se ocorrer tudo bem, a linha a seguir é executada
            this.status = true;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return this.con;
    }

    /**
     * Executa consultas SQL
     * 
     * @param pSQL
     * @return boolean
     */
    public boolean executarSQL(String pSQL) {
        try {
            // CreateStatement de con para criar o Statement
            this.statement = con.createStatement();

            // Definido o Statement, executamos a query no banco de dados
            this.resultSet = this.statement.executeQuery(pSQL);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Executa comandos SQL de atualização ou exclusão
     * 
     * @param pSQL
     * @return boolean
     */
    public boolean executarUpdateDeleteSQL(String pSQL) {
        try {
            // CreateStatement de con para criar o Statement
            this.statement = con.createStatement();

            // Definido o Statement, executamos a query no banco de dados
            this.statement.executeUpdate(pSQL);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Executa comandos SQL de inserção
     * 
     * @param pSQL
     * @return int
     */
    public int insertSQL(String pSQL) {
        int status = 0;
        try {
            // CreateStatement de con para criar o Statement
            this.statement = con.createStatement();

            // Definido o Statement, executamos a query no banco de dados
            this.statement.executeUpdate(pSQL);

            // Consulta o último id inserido
            this.resultSet = this.statement.executeQuery("SELECT last_insert_id();");

            // Recupera o último id inserido
            while (this.resultSet.next()) {
                status = this.resultSet.getInt(1);
            }

            // Retorna o último id inserido
            return status;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return status;
        }
    }

    /**
     * Encerra a conexão corrente
     * 
     * @return boolean
     */
    public boolean fecharConexao() {
        try {
            if ((this.resultSet != null) && (this.statement != null)) {
                this.resultSet.close();
                this.statement.close();
            }
            this.con.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }

    // Getters e Setters

    public boolean isStatus() {
        return this.status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getNomeDoBanco() {
        return nomeDoBanco;
    }

    public void setNomeDoBanco(String nomeDoBanco) {
        this.nomeDoBanco = nomeDoBanco;
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
