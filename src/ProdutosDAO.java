/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    
    // Salva um produto no banco. Retorna true se deu certo e false se deu erro.
    public boolean cadastrarProduto (ProdutosDTO produto){
        
        conn = new conectaDAO().connectDB();
        
        if (conn == null) {
            return false;
        }
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar produto: " + erro.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao salvar no banco: " + erro.getMessage());
            return false;
        } finally {
            try {
                if (prep != null) prep.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }
    
    // Busca todos os produtos cadastrados no banco.
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        conn = new conectaDAO().connectDB();
        
        if (conn == null) {
            return listagem;
        }
        
        String sql = "SELECT * FROM produtos";
        
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao listar produtos: " + erro.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar do banco: " + erro.getMessage());
        } finally {
            try {
                if (resultset != null) resultset.close();
                if (prep != null) prep.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
        
        return listagem;
    }
    
}