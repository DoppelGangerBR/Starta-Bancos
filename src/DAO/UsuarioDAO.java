/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.EntidadeConexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class UsuarioDAO extends EntidadeConexao {

    private String usuario, senha;
    
    
    public void registraUsuario() throws SQLException {
        try{
        criaBanco();
        criaTabelas();
        Connection conexao = Conecta2();
        Statement stmt = conexao.createStatement();
        String sql = "INSERT INTO usuario(id_usuario,usuario,senha) VALUES(1,'"+getUsuario()+"','"+getSenha()+"')";
        stmt.executeUpdate(sql);
        conexao.close();
        
        JOptionPane.showMessageDialog(null, "Banco configurado com sucesso!");
        System.exit(0);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Por favor tente novamente\nPossiveis problemas:\nBanco mal configurado\nSenha padrão do banco não é 123\nMensagem de erro:\n"+e.getMessage());
        }
    }

    private void criaTabelas() throws SQLException {
        Connection conexao = Conecta2();        
        try {
            Statement stmt = conexao.createStatement();                  
            String sqlSequencia1 = "CREATE SEQUENCE public.cliente_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1; ALTER TABLE public.cliente_id_seq OWNER TO postgres;";
            stmt.executeUpdate(sqlSequencia1);           
            
            String sqlSequencia2 = "CREATE SEQUENCE public.ordem_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1; ALTER TABLE public.ordem_id_seq OWNER TO postgres; ";
            stmt.executeUpdate(sqlSequencia2);
            
            String sqlSequencia3 = "CREATE SEQUENCE public.usuario_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1; ALTER TABLE public.usuario_id_seq OWNER TO postgres;";
            stmt.executeUpdate(sqlSequencia3);                 
            
            String sqlTabelaClientes = "CREATE TABLE public.cadastro_clientes ( id_cliente integer NOT NULL DEFAULT nextval('cliente_id_seq'::regclass), nome character varying, telefone character varying, celular character varying, endereco character varying, cpfcnpj character varying, bairro character varying, cidade character varying, estado character varying, ativo integer, numero character varying, CONSTRAINT cadastro_clientes_pkey PRIMARY KEY (id_cliente) ) WITH ( OIDS=FALSE ); ALTER TABLE public.cadastro_clientes OWNER TO postgres; ";
            stmt.executeUpdate(sqlTabelaClientes);
            
            String sqlTabelaOrdensServico = "CREATE TABLE public.ordem_servico ( id_os integer NOT NULL DEFAULT nextval('ordem_id_seq'::regclass), usuario_id integer NOT NULL, cadastro_clientes_id integer NOT NULL, modelo character varying, marca character varying, numero_serie character varying, equipamento character varying, acessorios character varying, problema_reclamado character varying, data_abertura date, status_os integer, aberta_fechada integer, obsos character varying, CONSTRAINT ordem_servico_pkey PRIMARY KEY (id_os), CONSTRAINT ordem_servico_cadastro_clientes_id_fkey FOREIGN KEY (cadastro_clientes_id) REFERENCES public.cadastro_clientes (id_cliente) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION ) WITH ( OIDS=FALSE ); ALTER TABLE public.ordem_servico OWNER TO postgres;";
            stmt.executeUpdate(sqlTabelaOrdensServico);
            
            String sqlTabelaUsuarios = "CREATE TABLE public.usuario ( id_usuario integer NOT NULL DEFAULT nextval('usuario_id_seq'::regclass), usuario character varying, senha character varying, CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario) ) WITH ( OIDS=FALSE ); ALTER TABLE public.usuario OWNER TO postgres;";
            stmt.executeUpdate(sqlTabelaUsuarios);
            
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            conexao.close();
            JOptionPane.showMessageDialog(null, "Por favor tente novamente\nPossiveis problemas:\nBanco mal configurado\nSenha padrão do banco não é 123\nMensagem de erro:\n"+e.getMessage());
            
        }

    }
    private void criaBanco() throws SQLException{
        Connection conexao = Conecta();
        try{        
        Statement stmt = conexao.createStatement();
            String sqlCriaBanco = "CREATE DATABASE ordem_servico_db_teste WITH OWNER = postgres ENCODING = 'UTF8' TABLESPACE = pg_default LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252' CONNECTION LIMIT = -1;";
            stmt.executeUpdate(sqlCriaBanco); 
            stmt.close();
            conexao.close();
            
        }catch(SQLException e){
            conexao.close();
            JOptionPane.showMessageDialog(null, "Por favor tente novamente\nPossiveis problemas:\nBanco mal configurado\nSenha padrão do banco não é 123\nMensagem de erro:\n"+e.getMessage());
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
