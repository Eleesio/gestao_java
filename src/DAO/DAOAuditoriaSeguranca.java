package DAO;

import conexoes.ConexaoMySql;
import java.sql.Timestamp;

public class DAOAuditoriaSeguranca extends ConexaoMySql {

    /**
     * Registra log de segurança
     */
    public boolean registrarLogSeguranca(int idUsuario, String ipConexao, String acao, String detalhes) {
        try {
            this.conectar();
            int result = this.insertSQL(
                "INSERT INTO tbl_log_seguranca ("
                    + "fk_usuario,"
                    + "ip_conexao,"
                    + "acao_realizada,"
                    + "detalhes"
                + ") VALUES ("
                    + "'" + (idUsuario > 0 ? idUsuario : "NULL") + "',"
                    + "'" + ipConexao + "',"
                    + "'" + acao + "',"
                    + "'" + detalhes + "'"
                + ");"
            );
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Cria nova sessão de usuário
     */
    public boolean criarSessaoUsuario(int idUsuario, String token, String ip, String dispositivo) {
        try {
            this.conectar();
            int result = this.insertSQL(
                "INSERT INTO tbl_sessao_usuario ("
                    + "fk_usuario,"
                    + "token_sessao,"
                    + "ip_conexao,"
                    + "data_expiracao,"
                    + "dispositivo_info"
                + ") VALUES ("
                    + "'" + idUsuario + "',"
                    + "'" + token + "',"
                    + "'" + ip + "',"
                    + "DATE_ADD(NOW(), INTERVAL 8 HOUR),"
                    + "'" + dispositivo + "'"
                + ");"
            );
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Atualiza última atividade do usuário
     */
    public boolean atualizarUltimaAtividade(int idUsuario, String ip) {
        try {
            this.conectar();
            // Atualiza usuário
            boolean update1 = this.executarUpdateDeleteSQL(
                "UPDATE tbl_usuario SET "
                    + "usu_data_ultima_atividade = NOW()"
                + " WHERE pk_id_usuario = '" + idUsuario + "'"
            );
            
            // Atualiza sessão
            boolean update2 = this.executarUpdateDeleteSQL(
                "UPDATE tbl_sessao_usuario SET "
                    + "data_ultima_atividade = NOW()"
                + " WHERE fk_usuario = '" + idUsuario + "' AND ativa = 1"
            );
            
            return update1 && update2;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Encerra sessão do usuário
     */
    public boolean encerrarSessao(int idUsuario) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                "UPDATE tbl_sessao_usuario SET "
                    + "ativa = 0,"
                    + "data_expiracao = NOW()"
                + " WHERE fk_usuario = '" + idUsuario + "' AND ativa = 1"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Limpa sessões expiradas
     */
    public boolean limparSessoesExpiradas() {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                "UPDATE tbl_sessao_usuario SET "
                    + "ativa = 0"
                + " WHERE data_expiracao < NOW() AND ativa = 1"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
}