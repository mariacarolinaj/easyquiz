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
        System.out.println("CHEGOU NO SERVLET!"+acao);
        if(acao.equals("ProximaPagina"))
            jsp = ProximaPagina.execute(request);
        else if (acao.equals("PaginaAnterior"))
            jsp = PaginaAnterior.execute(request);
        else if (acao.equals("GravarQuestaoFechadaResposta"))
            jsp = GravarQuestaoFechadaResposta.execute(request);
        else if (acao.equals("ContadorRespostaQuestao"))
            jsp = ContadorRespostaQuestao.execute(request);
        
        
        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);  
    }    
}