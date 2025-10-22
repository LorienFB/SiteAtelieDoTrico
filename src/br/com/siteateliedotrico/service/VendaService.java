package br.com.siteateliedotrico.service;

import br.com.siteateliedotrico.dao.VendaDAO;
import br.com.siteateliedotrico.model.Venda;
import java.sql.SQLException;
import java.util.List;

public class VendaService {

    private VendaDAO vendaDAO;

    public VendaService() {
        try {
            this.vendaDAO = new VendaDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar VendaDAO", e);
        }
    }

    public int cadastrarVenda(Venda venda) {
        return vendaDAO.cadastrarVenda(venda);
    }

    public boolean registrarVendaTransacional(Venda venda) {
        try {
            vendaDAO.registrarVenda(venda);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Venda> listarVendas() {
        return vendaDAO.listarVendas();
    }

}
