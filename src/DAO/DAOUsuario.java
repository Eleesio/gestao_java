package DAO;

import model.ModelUsuario;
import conexoes.ConexaoMySql;
import java.util.ArrayList;

/**
 *
 * @author Eleesio
 */
public class DAOUsuario extends ConexaoMySql {



    /**
     * Atualiza dados de login com segurança
     */
    public boolean atualizarDadosLoginDAO(int idUsuario, String ip) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                "UPDATE tbl_usuario SET "
                    + "usu_data_ultimo_login = NOW(),"
                    + "usu_data_ultima_atividade = NOW(),"
                    + "usu_ip_ultimo_login = '" + ip + "',"
                    + "usu_tentativas_login = 0,"
                    + "usu_bloqueado = 0"
                + " WHERE pk_id_usuario = '" + idUsuario + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Incrementa tentativas de login
     */
    public boolean incrementarTentativasLoginDAO(String login) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                "UPDATE tbl_usuario SET "
                    + "usu_tentativas_login = usu_tentativas_login + 1"
                + " WHERE usu_login = '" + login + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Bloqueia usuário após múltiplas tentativas
     */
    public boolean bloquearUsuarioDAO(String login) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                "UPDATE tbl_usuario SET "
                    + "usu_bloqueado = 1,"
                    + "usu_data_bloqueio = NOW()"
                + " WHERE usu_login = '" + login + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Verifica se usuário está bloqueado
     */
    public boolean isUsuarioBloqueadoDAO(String login) {
        try {
            this.conectar();
            this.executarSQL(
                "SELECT usu_bloqueado FROM tbl_usuario WHERE usu_login = '" + login + "'"
            );
            
            if (this.getResultSet().next()) {
                return this.getResultSet().getBoolean(1);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
    /**
     * Atualiza a data do último login do usuário
     * @param pIdUsuario
     * @return boolean
     */
    public boolean atualizarDataUltimoLoginDAO(int pIdUsuario) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                "UPDATE tbl_usuario SET "
                    + "usu_data_ultimo_login = NOW()"
                + " WHERE "
                    + "pk_id_usuario = '" + pIdUsuario + "'"
                + ";"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * grava Usuario
     *
     * @param pModelUsuario return int
     */
    public int salvarUsuarioDAO(ModelUsuario pModelUsuario) {
        try {
            this.conectar();
            return this.insertSQL(
                    "INSERT INTO tbl_usuario ("
                    + "usu_nome,"
                    + "usu_login,"
                    + "usu_senha"
                    + ") VALUES ("
                    + "'" + pModelUsuario.getUsuNome() + "',"
                    + "'" + pModelUsuario.getUsuLogin() + "',"
                    + "'" + pModelUsuario.getUsuSenha() + "'"
                    + ");"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * recupera Usuario
     *
     * @param pIdUsuario return ModelUsuario
     */
    public ModelUsuario getUsuarioDAO(int pIdUsuario) {
        ModelUsuario modelUsuario = new ModelUsuario();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_usuario,"
                    + "usu_nome,"
                    + "usu_login,"
                    + "usu_senha"
                    + " FROM"
                    + " tbl_usuario"
                    + " WHERE"
                    + " pk_id_usuario = '" + pIdUsuario + "'"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelUsuario.setIdUsuario(this.getResultSet().getInt(1));
                modelUsuario.setUsuNome(this.getResultSet().getString(2));
                modelUsuario.setUsuLogin(this.getResultSet().getString(3));
                modelUsuario.setUsuSenha(this.getResultSet().getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelUsuario;
    }
    
    /**
     * recupera Usuario
     * @param pLogin
     * return ModelUsuario
     */
    public ModelUsuario getUsuarioDAO(String pLogin) {
        ModelUsuario modelUsuario = new ModelUsuario();
        try {
            this.conectar();
            this.executarSQL(
                "SELECT "
                    + "pk_id_usuario,"
                    + "usu_nome,"
                    + "usu_login,"
                    + "usu_senha,"
                    + "usu_data_ultimo_login" // ADICIONE ESTE CAMPO
                + " FROM"
                    + " tbl_usuario"
                + " WHERE"
                    + " usu_login = '" + pLogin + "'"
                + ";"
            );

            while(this.getResultSet().next()){
                modelUsuario.setIdUsuario(this.getResultSet().getInt(1));
                modelUsuario.setUsuNome(this.getResultSet().getString(2));
                modelUsuario.setUsuLogin(this.getResultSet().getString(3));
                modelUsuario.setUsuSenha(this.getResultSet().getString(4));
                modelUsuario.setUsuDataUltimoLogin(this.getResultSet().getTimestamp(5)); // ADICIONE ESTE
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.fecharConexao();
        }
        return modelUsuario;
    }
 
    /**
     * recupera uma lista de Usuario return ArrayList
     */
    public ArrayList<ModelUsuario> getListaUsuarioDAO() {
        ArrayList<ModelUsuario> listamodelUsuario = new ArrayList();
        ModelUsuario modelUsuario = new ModelUsuario();
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_usuario,"
                    + "usu_nome,"
                    + "usu_login,"
                    + "usu_senha"
                    + " FROM"
                    + " tbl_usuario"
                    + ";"
            );

            while (this.getResultSet().next()) {
                modelUsuario = new ModelUsuario();
                modelUsuario.setIdUsuario(this.getResultSet().getInt(1));
                modelUsuario.setUsuNome(this.getResultSet().getString(2));
                modelUsuario.setUsuLogin(this.getResultSet().getString(3));
                modelUsuario.setUsuSenha(this.getResultSet().getString(4));
                listamodelUsuario.add(modelUsuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listamodelUsuario;
    }

    /**
     * atualiza Usuario
     *
     * @param pModelUsuario return boolean
     */
    public boolean atualizarUsuarioDAO(ModelUsuario pModelUsuario) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tbl_usuario SET "
                    + "pk_id_usuario = '" + pModelUsuario.getIdUsuario() + "',"
                    + "usu_nome = '" + pModelUsuario.getUsuNome() + "',"
                    + "usu_login = '" + pModelUsuario.getUsuLogin() + "',"
                    + "usu_senha = '" + pModelUsuario.getUsuSenha() + "'"
                    + " WHERE "
                    + "pk_id_usuario = '" + pModelUsuario.getIdUsuario() + "'"
                    + ";"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * exclui Usuario
     *
     * @param pIdUsuario return boolean
     */
    public boolean excluirUsuarioDAO(int pIdUsuario) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tbl_usuario "
                    + " WHERE "
                    + "pk_id_usuario = '" + pIdUsuario + "'"
                    + ";"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
    
    /**
     * Validar login e senha do usuario
     * @param pModelUsuario
     * @return 
     */
    public boolean getValidarUsuarioDAO(ModelUsuario pModelUsuario) {
        try {
            this.conectar();
            this.executarSQL(
                    "SELECT "
                    + "pk_id_usuario,"
                    + "usu_nome,"
                    + "usu_login,"
                    + "usu_senha"
                    + " FROM"
                    + " tbl_usuario"
                    + " WHERE"
                    + " usu_login = '" + pModelUsuario.getUsuLogin() + "' AND usu_senha = '" + pModelUsuario.getUsuSenha() + "'"
                    + ";"
            );
            if (getResultSet().next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
}
