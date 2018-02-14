/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testrestmaven.daos;

import com.mycompany.testrestmaven.connection.ConnectionUtils;
import com.mycompany.testrestmaven.models.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonat
 */
public class DaoProduto {
    
    private final ConnectionUtils connectionUtils = new ConnectionUtils();

    public List<Produto> executarConsulta(String sql) throws SQLException, Exception {
        List<Produto> produtos = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connection = connectionUtils.conectar();
            statement = connection.createStatement();
            System.out.println("Executando CONSULTA SQL: " + sql);
            result = statement.executeQuery(sql);
            while (result.next()) {
                if (produtos == null) {
                    produtos = new ArrayList<Produto>();
                }
                Produto produto = new Produto();
                produto.setNomeProduto(result.getString("NOME"));
                produto.setDescricaoProduto(result.getString("DESCRICAO"));
                produto.setValorProduto(result.getString("PRECO"));
                produto.setTipoProduto(result.getString("TIPO"));
                produto.setAtivoProduto(result.getString("ATIVO"));
                produto.setCodigoProduto(result.getInt("ID"));
                produtos.add(produto);
            }
        } finally {
            if (result != null && !result.isClosed()) {
                result.close();
            }
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return produtos;
    }

    public List<Produto> listarTodos()
            throws SQLException, Exception {
        String sql = "SELECT * FROM tb_produto ORDER BY ID;";

        return executarConsulta(sql);
    }

    public List<Produto> listarAtivos(String tipo)
            throws SQLException, Exception {
        String sql = "SELECT * FROM tb_produto WHERE ATIVO = 'SIM' AND "
                + "Tipo = '" + tipo + "' ORDER BY ID;";

        return executarConsulta(sql);
    }

    public void adicionar(Produto produto)
            throws SQLException, Exception {

        String sql = "INSERT INTO tb_produto (NOME, DESCRICAO, PRECO, ATIVO, TIPO) "
                + "VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionUtils.conectar();
            statement = connection.prepareStatement(sql);

            statement.setString(1, produto.getNomeProduto());
            statement.setString(2, produto.getDescricaoProduto());
            statement.setString(3, produto.getValorProduto());
            statement.setString(4, "SIM");
            statement.setString(5, produto.getTipoProduto());
            System.out.println(statement.toString());

            System.out.println("Executando COMANDO SQL: " + sql);
            statement.execute();
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public void desativar(int id)
            throws SQLException, Exception {
        String sql = "UPDATE tb_produto SET ATIVO = ?"
                + " WHERE ID = ?; ";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionUtils.conectar();
            statement = connection.prepareStatement(sql);
            statement.setString(1, "NÃO");
            statement.setInt(2, id);
            System.out.println("Executando COMANDO SQL: " + sql);
            statement.execute();
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public void ativar(int id)
            throws SQLException, Exception {
        String sql = "UPDATE tb_produto SET ATIVO = ?"
                + " WHERE ID = ?; ";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionUtils.conectar();
            statement = connection.prepareStatement(sql);
            statement.setString(1, "SIM");
            statement.setInt(2, id);
            System.out.println("Executando COMANDO SQL: " + sql);
            statement.execute();
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public void alterar(Produto pizza)
            throws SQLException, Exception {
        String sql = "UPDATE tb_produto "
                + "SET NOME = ?, "
                + "DESCRICAO = ?, "
                + "PRECO = ?, "
                + "TIPO = ?, "
                + "ATIVO = ? "
                + "WHERE ID = ?;";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionUtils.conectar();
            statement = connection.prepareStatement(sql);

            statement.setString(1, pizza.getNomeProduto());
            statement.setString(2, pizza.getDescricaoProduto());
            statement.setString(3, pizza.getValorProduto());
            statement.setString(4, pizza.getTipoProduto());
            statement.setString(5, pizza.getAtivoProduto());
            statement.setInt(6, pizza.getCodigoProduto());
            System.out.println(statement.toString());

            System.out.println("Executando COMANDO SQL: " + sql);
            statement.execute();
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public Produto obter(int id)
            throws SQLException, Exception {
        String sql = "SELECT * FROM tb_produto WHERE ID = ?;";

        PreparedStatement statement = null;
        Connection connection = null;
        Produto produto = new Produto();
        connection = connectionUtils.conectar();
        statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        System.out.println(statement.toString());
        ResultSet result = null;
        result = statement.executeQuery();

        while (result.next()) {

            produto.setCodigoProduto(result.getInt("ID"));
            produto.setNomeProduto(result.getString("NOME"));
            produto.setDescricaoProduto(result.getString("DESCRICAO"));
            produto.setValorProduto(result.getString("PRECO"));
            produto.setAtivoProduto(result.getString("ATIVO"));
            produto.setTipoProduto(result.getString("TIPO"));
        }

        return produto;
    }
}
