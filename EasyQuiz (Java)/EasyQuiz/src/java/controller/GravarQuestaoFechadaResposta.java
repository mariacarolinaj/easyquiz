/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import model.daoimpl.QuestaoDAOImpl;
import model.daoimpl.QuestaoFechadaRespostaDAOImpl;
import model.daoimpl.SessaoDAOImpl;
import model.daoimpl.UsuarioDAOImpl;
import model.domain.Questao;
import model.domain.QuestaoFechadaResposta;
import model.domain.Sessao;
import model.domain.Usuario;
import model.service.ManterQuestao;
import model.service.ManterQuestaoFechadaResposta;
import model.service.ManterSessao;
import model.service.ManterUsuario;
import model.serviceimpl.ManterQuestaoFechadaRespostaImpl;
import model.serviceimpl.ManterQuestaoImpl;
import model.serviceimpl.ManterSessaoImpl;
import model.serviceimpl.ManterUsuarioImpl;

/**
 *
 * @author andro
 */
public class GravarQuestaoFechadaResposta {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            /*
            System.out.println("questao "+request.getParameter("questao"));
            System.out.println("resposta "+request.getParameter("resposta"));
            System.out.println("usuario "+request.getSession().getAttribute("cod_Usuario"));
            System.out.println("dat_inicio "+request.getSession().getAttribute("dat_Inicio"));
            */
            String questaoStr = request.getParameter("questao");
            Long cod_Questao = Long.parseLong(questaoStr);
            ManterQuestao manterQuestao = new ManterQuestaoImpl(QuestaoDAOImpl.getInstance());
            Questao questao = manterQuestao.getQuestaoById(cod_Questao);
            if(questao.getIdTipo()=='F') {
                String alternativaStr = request.getParameter("resposta");
                Long seq_Alternativa = Long.parseLong(alternativaStr);
                
                Long cod_Usuario = (Long) request.getSession().getAttribute("cod_Usuario");
                
                Instant dat_Inicio = (Instant) request.getSession().getAttribute("dat_Inicio");
                ManterSessao manterSessao = 
                        new ManterSessaoImpl(SessaoDAOImpl.getInstance());
                Sessao sessao  = manterSessao.getSessaoByUsuarioData(cod_Usuario, dat_Inicio);
                QuestaoFechadaResposta questaoFechadaResposta = new QuestaoFechadaResposta();
                questaoFechadaResposta.setSessao(sessao);
                questaoFechadaResposta.setQuestao(questao);
                questaoFechadaResposta.setSeqQuestaoResposta(seq_Alternativa);
                
                ManterQuestaoFechadaResposta manterQuestaoFechadaResposta = 
                        new ManterQuestaoFechadaRespostaImpl(QuestaoFechadaRespostaDAOImpl.getInstance());
                    
                manterQuestaoFechadaResposta.cadastrarQuestaoFechadaResposta(questaoFechadaResposta);
            }
            request.getSession().setAttribute("", jsp);
            jsp = ListarQuestao.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
