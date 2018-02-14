/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testrestmaven;

import com.google.gson.Gson;
import com.mycompany.testrestmaven.models.Produto;
import com.mycompany.testrestmaven.services.ProdutoService;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jonat
 */
@Path("produto")
public class TestResource {

    @Context
    private UriInfo context;
    
    private ProdutoService produtoService = new ProdutoService();

    /**
     * Creates a new instance of TestResource
     */
    public TestResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.testrestmaven.TestResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        
        return produtoService.testRest();
    }
    @GET
    @Produces("application/json")
    @Path("listarTodos")
    public String listarTodos() throws Exception {
        Gson gson = new Gson();
        List<Produto> produtos = produtoService.listarTodos();
        System.out.println(gson.toJson(produtos));
        
        return gson.toJson(produtos);
    }

    /**
     * PUT method for updating or creating an instance of TestResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
