

package controller;

import DAO.DAOVendasCliente;
import java.util.ArrayList;
import model.ModelVendasCliente;

/**
 *
 * @author Eleesio
 */
public class ControllerVendasCliente {
    
    private DAOVendasCliente dAOVendasCliente = new DAOVendasCliente();

    public ArrayList<ModelVendasCliente> getListaVendasClienteController(){
        return this.dAOVendasCliente.getListaVendasClienteDAO();
    }

    public ModelVendasCliente getListaVendasClienteController(int pCodigoVenda) {
        return this.dAOVendasCliente.getListaVendasClienteDAO(pCodigoVenda);
    }
    
}
