/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testrestmaven.services;

import com.mycompany.testrestmaven.daos.DaoProduto;
import com.mycompany.testrestmaven.models.Produto;
import java.util.List;

/**
 *
 * @author jonat
 */
public class ProdutoService {
    
    private DaoProduto daoProduto = new DaoProduto();
    
    public String testRest() {
        
        return "Teste bem sucedido!!!";
    }
    
    public List<Produto> listarTodos() throws Exception {
      
        return daoProduto.listarTodos();
    }
}
