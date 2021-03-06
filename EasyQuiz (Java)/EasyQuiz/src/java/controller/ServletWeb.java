package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletWeb extends HttpServlet {
    private String jsp = "";
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");
        System.out.println("ACAO: "+acao);
        
        if(acao.equals("PaginaInicial"))
            jsp = PaginaInicial.execute(request);
        else if(acao.equals("ProximaPagina"))
            jsp = ProximaPagina.execute(request);
        else if(acao.equals("PaginaAnterior"))
            jsp = PaginaAnterior.execute(request);
        else if(acao.equals("GravarQuestaoFechadaResposta"))
            jsp = GravarQuestaoFechadaResposta.execute(request);
        else if(acao.equals("ContadorRespostaQuestao"))
            jsp = ContadorRespostaQuestao.execute(request);
        else if(acao.equals("ListarForum"))
            jsp = ListarForum.execute(request);
        else if(acao.equals("Logar"))
            jsp = Login.execute(request);
        else if(acao.equals("CarregaImagem"))
            jsp = CarregaImagem.execute(request, response);
        else if(acao.equals("ListarPerfil"))
            jsp = ListarPerfil.execute(request);
        else if(acao.equals("AlterarUsuario"))
            jsp = AlterarUsuario.execute(request);
        else if (acao.equals("GerenciarQuestoes"))
            jsp = GerenciarQuestoes.execute(request);
        else if (acao.equals("ExcluirQuestao"))
            jsp = ExcluirQuestao.execute(request);
        else if (acao.equals("AdicionarQuestao"))
            jsp = AdicionarQuestao.execute(request);
        else if (acao.equals("CadastroQuestao")){
             System.out.println("Entrei no CadastroQuestao!!!");
             jsp = CadastroQuestao.execute(request);
         }
         else if (acao.equals("EditarQuestao")){
             jsp = EditarQuestao.execute(request);
		 }
        else if(acao.equals("Sair"))
            jsp = Logout.execute(request);
        else if(acao.equals("GravarPostAvulso"))
            jsp = GravarPostAvulso.execute(request);
        else if (acao.equals("GravarPostResposta"))
            jsp = GravarPostResposta.execute(request);
        else if(acao.equals("FiltrarDesempenho"))
            jsp = FiltrarDesempenho.execute(request);
        
        if(!(jsp.equals("notFoward"))) {
            RequestDispatcher rd = request.getRequestDispatcher(jsp);
            rd.forward(request, response);  
        }
    }    
}
