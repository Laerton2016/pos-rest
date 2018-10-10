/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.clientrest;

import br.edu.ifpb.entidades.Autor;
import br.edu.ifpb.entidades.Livro;
import static br.edu.ifpb.entidades.Livro_.id;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author laerton
 */
public class ClientRest {
    private static final String urlSetting = "http://localhost:8080/server-rest/api/biblioteca/";
    
    public static List<Livro> allLivros (){
        
        try {
            BufferedReader responseBuffer = getMetodo(urlSetting + "todososlivros/");
            Gson gson = new Gson();
            java.lang.reflect.Type usuariosListType = new TypeToken<ArrayList<Livro>>(){}.getType(); 
            List<Livro> lista =  gson.fromJson(responseBuffer,usuariosListType);
            return lista;
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new LinkedList<>();
    }
    
    private static BufferedReader getMetodo(String urlcomple) throws MalformedURLException, IOException{
            URL url = new URL(urlcomple);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() !=200){
                throw new RuntimeException("HTTP GET erro code: "+ connection.getResponseCode());
            }
            return new BufferedReader(new InputStreamReader((connection.getInputStream())));
            
    }
    public static List<Livro> filtraLivro(String termo) throws IOException{
       BufferedReader responseBuffer = getMetodo(urlSetting + "filtrarlivro/"+termo);
       Gson gson = new Gson();
       java.lang.reflect.Type usuariosListType = new TypeToken<ArrayList<Livro>>(){}.getType(); 
       List<Livro> lista =  gson.fromJson(responseBuffer,usuariosListType);
       return lista;
    }
    
    public static List<Autor> allAltores() throws IOException{
       BufferedReader responseBuffer = getMetodo(urlSetting + "todososautores/");
       Gson gson = new Gson();
       java.lang.reflect.Type usuariosListType = new TypeToken<ArrayList<Autor>>(){}.getType(); 
       List<Autor> lista =  gson.fromJson(responseBuffer,usuariosListType);
       return lista;   
    }
    
    public static Livro vinculaAutor (int idLivro, int idAutor) throws IOException{
       BufferedReader responseBuffer = getMetodo(urlSetting + "vinculaAutor/"  + idAutor + "/" + idLivro);
       Gson gson = new Gson();
       Livro livro =  gson.fromJson(responseBuffer,Livro.class);
       return livro;   
    }
    
    public static List<Autor> findAutorByIdLivro(int id) throws IOException{
       BufferedReader responseBuffer = getMetodo(urlSetting + "findAutorByIdLivro/"+id);
       Gson gson = new Gson();
       java.lang.reflect.Type usuariosListType = new TypeToken<ArrayList<Autor>>(){}.getType(); 
       List<Autor> lista =  gson.fromJson(responseBuffer,usuariosListType);
       return lista;   
    }
    
    public static void salvarlivro(Livro livro) throws MalformedURLException, IOException
    {
        URL url = new URL(urlSetting + "salvarlivro/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() !=200){
                throw new RuntimeException("HTTP GET erro code: "+ connection.getResponseCode());
            }
    }
    
    
    public static void  deleteLivro(int id) throws MalformedURLException, IOException{
            URL url = new URL(urlSetting + "removerlivro/"+id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() !=200){
                throw new RuntimeException("HTTP GET erro code: "+ connection.getResponseCode());
            }
            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String s ;
            StringBuilder sb = new StringBuilder();
            while ((s = responseBuffer.readLine())!= null) {            {
                sb.append(s);
            }
            System.err.println(sb.toString());
            
        }
    }
}
