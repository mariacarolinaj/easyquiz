/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.serviceimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.UsuarioDAO;
import model.daoimpl.UsuarioDAOImpl;
//import model.dao.UsuarioDAOImpl;
import model.domain.Usuario;
import model.exception.ExcecaoNegocio;
import model.exception.ExcecaoPersistencia;
import model.service.ManterUsuario;

/**
 *
 * @author Anonymous
 */
public class ManterUsuarioImplTest {
    /*
    private static ManterUsuario usuarioManagement;
    private static UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private static List<Long> usuarioList;
    
    public ManterUsuarioImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        usuarioDAO = UsuarioDAOImpl.getInstancia();
        usuarioManagement = new ManterUsuarioImpl(usuarioDAO);
        usuarioList = new ArrayList<>();
    }

    @Before
    public void setUp() {
        this.usuario = new Usuario();
    }

    @After
    public void tearDown() throws ExcecaoPersistencia {
        /*TearDown para o PersistenceSQL*/
        
        /*TearDown para o PersistenceRAM*/
        
        /*for(Long id: usuarioList)
            try {
                usuarioDAO.delete(id);
            } catch (ExcecaoPersistencia ex) {
                Logger.getLogger(ManterUsuarioImplTest.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testCadastrarUsuario() throws Exception {
        
        // ao registrar um usuario, é obrigatório que se retorne o id do contato gerado pelo mecanismo de persistência
        Long id = null;
        try {
            Usuario u = new Usuario();
            u.setNome("José Silva");
            u.setSenha("1234abcd");
            u.setEmail("dss23@gmail.com");
            u.setDataNascimento(new Date(1998, 1, 27));
            id = usuarioManagement.cadastrarUsuario(u); //erro comunicacao JDBC
            System.out.println(id);
        } catch (ExcecaoNegocio ex){
            System.out.println(ex.getMessage());
            fail("Exceção inesperada foi lançada");
        }
        assertTrue(id!=null);
        // inscreve contato para remoção após teste
        usuarioList.add(id);
    }    

    @Test
    public void testCadastrarUsuario1() throws Exception {
        
        try {
            //testa entrada de usuario null
            usuario = null;
            usuarioManagement.cadastrarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            return;
        }
        
        fail("A exceção esperada não foi capturada.");
    }

    @Test
    public void testCadastrarUsuario2() throws Exception {
        
        try {
            // testa entrada de nome null
            usuario.setNome(null);
            usuarioManagement.cadastrarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "O nome nao pode ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }
    
    @Test
    public void testCadastrarUsuario3() throws Exception {
        
        try {
            // testa entrada nome vazio
            usuario.setNome("");
            usuarioManagement.cadastrarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "O nome nao pode ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }

    @Test
    public void testCadastrarUsuario4() throws Exception {
        
        try {
            // testa a entrada de uma senha null
            usuario.setSenha(null);
            usuarioManagement.cadastrarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "A senha nao pode ser nula.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }    

    @Test
    public void testCadastrarUsuario5() throws Exception {
        
        try {
            // testa a entrada de uma senha com menos de 8 caracteres
            usuario.setSenha("12345");
            usuarioManagement.cadastrarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "A senha é muito curta.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }    
    
    public void testCadastrarUsuario6() throws Exception {
        
        try {
            // testa entrada de email null
            usuario.setEmail(null);
            usuarioManagement.cadastrarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "O e-mail nao pode ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }
    
    @Test
    public void testCadastrarUsuario7() throws Exception {
        
        try {
            // testa entrada de email vazio
            usuario.setEmail("");
            usuarioManagement.cadastrarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "O e-mail nao pode ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }
    
    @Test
    public void testCadastrarUsuario8() throws Exception {
        
        try {
            // testa entrada de data de nascimento null
            usuario.setDataNascimento(null);
            usuarioManagement.cadastrarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "A data de nascimento nao pode ser nula.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    } 
    
    @Test
    public void testCadastrarUsuario9() throws Exception {
        
        try {
            // testa a entrada de um id pre-definido na criacao de um novo usuario
            usuario.setId(new Long(111));
            usuarioManagement.cadastrarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "Na criaçao de um novo usuario o ID deve ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }   
    
    @Test
    public void testCadastrarUsuario10() throws Exception {
        
        try {
            // testa a entrada de um numero de questoes acertadas na criacao de um novo usuario
            usuario.setQuestoesAcertadas(new Long (1));
            usuarioManagement.cadastrarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "Na criaçao de um novo usuario o número de questoes acertadas deve ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }   
    
    @Test
    public void testCadastrarUsuario11() throws Exception {
        
        try {
            // testa a entrada de um numero de questoes respondidas na criacao de um novo usuario
            usuario.setQuestoesRespondidas(new Long(2));
            usuarioManagement.cadastrarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "Na criaçao de um novo usuario o numero de questoes respondidas deve ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }   

    @Test
    public void testAlterarUsuario() throws Exception {
        
        try {
            //testa entrada de usuario null
            usuario = null;
            usuarioManagement.alterarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            return;
        }
        
        fail("A exceção esperada não foi capturada.");
    }

    @Test
    public void testAlterarUsuario2() throws Exception {
        
        try {
            // testa entrada de nome null
            usuario.setNome(null);
            usuarioManagement.alterarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "O nome nao pode ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }
    
    @Test
    public void testAlterarUsuario3() throws Exception {
        
        try {
            // testa entrada nome vazio
            usuario.setNome("");
            usuarioManagement.alterarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "O nome nao pode ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }

    @Test
    public void testAlterarUsuario4() throws Exception {
        
        try {
            // testa a entrada de uma senha null
            usuario.setSenha(null);
            usuarioManagement.alterarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "A senha nao pode ser nula!";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }    

    @Test
    public void testAlterarUsuario5() throws Exception {
        
        try {
            // testa a entrada de uma senha com menos de 8 caracteres
            usuario.setSenha("12345");
            usuarioManagement.alterarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "A senha e' muito curta.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }    
    
    public void testAlterarUsuario6() throws Exception {
        
        try {
            // testa entrada de email null
            usuario.setEmail(null);
            usuarioManagement.alterarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "O e-mail nao pode ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }
    
    @Test
    public void testAlterarUsuario7() throws Exception {
        
        try {
            // testa entrada de email vazio
            usuario.setEmail("");
            usuarioManagement.alterarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "O e-mail nao pode ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }
    
    @Test
    public void testAlterarUsuario8() throws Exception {
        
        try {
            // testa entrada de data de nascimento null
            usuario.setDataNascimento(null);
            usuarioManagement.alterarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "A data de nascimento nao pode ser nula.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    } 
    
    @Test
    public void testAlterarUsuario9() throws Exception {
        
        try {
            // testa a entrada de um id null na alteracao de um usuario
            usuario.setId(null);
            usuarioManagement.alterarUsuario(usuario);
        } catch(ExcecaoNegocio ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "Na alteraçao de um novo usuario o ID nao pode ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }  
    
    @Test
    public void testDeletarUsuario1() throws Exception {
        
        try {
            // testa a entrada de um id null na exclusao de um usuario
            usuarioManagement.deletarUsuario(null); //NumberFormat exception.
        } catch(ExcecaoPersistencia ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "ID não pode ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }  

    @Test
    public void testGetUsuarioById1() throws Exception {
        
        try {
            // testa a entrada de um id null na busca de um usuario
            usuarioManagement.getUsuarioById(null); //problema em NumberFormat
        } catch(ExcecaoPersistencia ex) {
            String msgErr = ex.getMessage();
            String msgEsperada = "ID não pode ser nulo.";
            assertTrue(msgErr.contains(msgEsperada));
            return;
        }
        
        fail("Falha não identificada.");
    }  
    */
}
