/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import VO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import control.BDconexao;
import java.awt.HeadlessException;
import view.MenuPrincipal;

/**
 *
 * @author Chigumane
 */
public class PagamentoDAO {
    private Connection conexao;
    
    public PagamentoDAO() throws SQLException, ClassNotFoundException{
        this.conexao=BDconexao.getConnection();
    }
     public void inserir(PagamentoVO c){
        String sql = "INSERT INTO pagamento (id, horasExtras, Faltas, bonus, desconto, salarioBruto, salarioLiquido) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, c.getid());
            ps.setInt(2, c.getHorasExtras());
            ps.setInt(3, c.getFaltas());
            ps.setInt(4, c.getBonus());
            ps.setInt(5, c.getDesconto());
            ps.setDouble(6, c.getSalariobruto());
            ps.setDouble(7, c.getSalarioLiquido());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
        public void apagar (int c){
        String sql = "DELETE FROM Pagamento WHERE id = ?";
        try{ 
         PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1,c);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         public List<PagamentoVO> todos(){
        try {
            String sql = "SELECT * FROM Pagamento ORDER BY pagamento.id ASC";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<PagamentoVO> lista = new ArrayList<>();
            while(rs.next()){
                PagamentoVO ct=new PagamentoVO();
                ct.setid(rs.getString("id"));
                ct.setHorasExtras(rs.getInt("horasExtras"));
                ct.setFaltas(rs.getInt("faltas"));
                ct.setBonus(rs.getInt("bonus"));
                ct.setDesconto(rs.getInt("desconto"));
                ct.setSalariobruto(rs.getFloat("salarioBruto"));
                ct.setSalarioLiquido(rs.getFloat("salarioLiquido"));
                lista.add(ct);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
           public void actualizar(PagamentoVO c) {
        try {
            String sql = "UPDATE pagamento horasExtras = ?, Faltas = ?, bonus = ?, desconto = ?, salarioBruto = ?, salarioLiquido = ? WHERE pagamento.nome = ?";
            
            
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(7, c.getid());
            ps.setInt(1, c.getHorasExtras());
            ps.setInt(2, c.getFaltas());
            ps.setInt(3, c.getBonus());
            ps.setInt(4, c.getDesconto());
            ps.setDouble(5, c.getSalariobruto());
            ps.setDouble(6, c.getSalarioLiquido());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
              public Boolean verificar( String id) {
                   boolean a = false;
      
            String sql = "select * from funcionario where idFuncionario=?";
           try {   
            conexao = control.BDconexao.getConnection();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, id);
           
            ResultSet rs = ps.executeQuery();
            rs = ps.executeQuery();
            if (rs.next()) {
                a= true;
                
            } else {
                JOptionPane.showMessageDialog(null, "O Usuario nao existe","Erro", JOptionPane.ERROR_MESSAGE);
                a= false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return a;
    }
    
}
