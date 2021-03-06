<%@page import="java.sql.Timestamp"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Instant"%>
<%@page import="model.domain.QuestaoFechadaResposta"%>
<%@page import="model.serviceimpl.ManterQuestaoFechadaRespostaImpl"%>
<%@page import="model.daoimpl.QuestaoFechadaRespostaDAOImpl"%>
<%@page import="model.daoimpl.QuestaoFechadaRespostaDAOImpl"%>
<%@page import="model.service.ManterQuestaoFechadaResposta"%>
<%@page import="model.domain.Escolaridade"%>
<%@page import="java.util.List"%>
<%@page import="model.serviceimpl.ManterEscolaridadeImpl"%>
<%@page import="model.service.ManterEscolaridade"%>
<%@page import="model.service.ManterEscolaridade"%>
<%@page import="model.daoimpl.EscolaridadeDAOImpl"%>
<%@page import="model.daoimpl.EscolaridadeDAOImpl"%>
<%@page import="model.domain.Usuario"%>
<%@page import="model.serviceimpl.ManterUsuarioImpl"%>
<%@page import="model.service.ManterUsuario"%>
<%@page import="model.daoimpl.UsuarioDAOImpl"%>
<%@page import="model.daoimpl.UsuarioDAOImpl"%>
<%@page import="controller.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int logado = Login.validarSessao(request, response);
    Usuario usuario = null;
    List<Escolaridade> listEscolaridade = null;
    String primeiroNome = null;
    int total=0;
    int acertos=0;
    int erros=0;
    int porcentagem=0;
    if(logado==1) {
        Long cod_Usuario = (Long) request.getSession().getAttribute("cod_Usuario");
        ManterUsuario manterUsuario = new ManterUsuarioImpl(UsuarioDAOImpl.getInstance());
        usuario = manterUsuario.getUsuarioById(cod_Usuario);
        
        ManterEscolaridade manterEscolaridade = new ManterEscolaridadeImpl(EscolaridadeDAOImpl.getInstance());
        listEscolaridade = manterEscolaridade.getAll();
        
        primeiroNome = usuario.getNome().split(" ")[0];
        
        
        if(request.getSession().getAttribute("Desempenho") != null) {
            List<QuestaoFechadaResposta> listQuestaoFechadaResposta = 
                (List<QuestaoFechadaResposta>) request.getSession().getAttribute("Desempenho");
            if(listQuestaoFechadaResposta.size()!=0) {
                total = listQuestaoFechadaResposta.size();
                Long seqResposta;
                Long seqCorreta;
                for(int i=0; i<listQuestaoFechadaResposta.size(); i++) {
                    seqResposta = listQuestaoFechadaResposta.get(i).getSeqQuestaoResposta();
                    seqCorreta = listQuestaoFechadaResposta.get(i).getQuestao().getSeqQuestaoCorreta();
                    if(seqResposta==seqCorreta) {
                        acertos++;
                    } else {
                        erros++;
                    }
                }
                porcentagem = (acertos*100)/total;
            }
        }
        
        
        System.out.println("TOTAL: "+total);
        System.out.println("ACERTOS: "+acertos);
        System.out.println("ERROS: "+erros);
        System.out.println("PORCENTAGEM "+porcentagem);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page ="Menu.jsp"/>
        <H4 style="color: #47525E; padding-left: 35px;">Aproveitamento:</H4>
        <div class="container">
            <label for="dataInicio">De:</label>
            <label for="dataFim">Até:</label>
            <form method="post" name="formDesempenho">
                <input type='hidden' name='acao' value=''>
                <div class="row">
                    <div class="input-field col s6">
                        <input name="dataInicio" type="date" value="<%= LocalDate.now() %>">
                    </div>
                    <div class="input-field col s6">
                        <input name="dataFim" type="date" value="<%= LocalDate.now() %>">
                    </div>
                    <div class="input-field col s6">
                        <button class="btn waves-effect waves-light" type="button" onclick="filtrarDesempenho(document.formDesempenho)">Filtrar</button>
                    </div>
                </div>
            </form>
            
            
            <H5 style="color: #47525E; display: inline;">Questões respondidas:</H5> <H5 style="color: #C55353; display: inline;"><%=total%></H5>
            <H5 style="color: #47525E; display: inline; padding-left: 50px;">Questões acertadas:</H5> <H5 style="color: #C55353; display: inline;"><%=acertos%></H5>
            <H5 style="color: #47525E; display: inline; padding-left: 50px;">Questões erradas:</H5> <H5 style="color: #C55353; display: inline;"><%=erros%></H5>
            <br>
            <br>
            <H5 style="color: #47525E; display: inline;">Coeficiente de acerto:</H5> <H5 style="color: #C55353; display: inline;"><%=(porcentagem)%>%</H5>
            <div class="progress" style="z-index: -1;">
              <div class="determinate" id="porcentagem" style="z-index: -11;"></div>
            </div>
            <script type="text/javascript">
                document.getElementById("porcentagem").style.width = "<%=(porcentagem)%>%";
            </script>
            
        </div>

        <br>
        <br>

        <H4 style="color: #47525E; padding-left: 80px; display: inline;">O que deseja alterar </H4> <H4 style="color: #C55353; display: inline;"><%= primeiroNome %></H4> 
        <H4 style="color: #47525E; display: inline;">?</h4>
        <div class="container">
            <form class="col s12" method="post" name="formPerfil">
                <input type='hidden' name='table' value='Usuario'>
                <input type='hidden' name='acao' value='alterar'>
                
                <div class="input-field col s6">
                    <i class="material-icons prefix">account_circle</i>
                    <input name="nome" type="text" class="validate" value="<%= usuario.getNome() %>">
                    <label for="nome">Nome completo</label>
                </div>

                <div class="input-field col s6">
                    <i class="material-icons prefix">email</i>
                    <input name="email" type="email" class="validate" value="<%= usuario.getEmail() %>">
                    <label for="email">E-mail</label>
                </div>

                <br>

                <label for="escolhe_data">Data de nascimento:</label>
                <div class="input-field col s6">
                    <i class="material-icons prefix">perm_contact_calendar</i>
                    <input name="dataNascimento" type="date" class="validate" value="<%= usuario.getDataNascimento() %>">
                </div>
                
                <label for="escolaridade">Escolaridade:</label>
                <input type='hidden' name="escolaridadeInput" value=''>
                <div class="input-field col s6">
                    <i class="material-icons prefix">class</i>
                    <select id="escolaridade">
                        <%
                        for(int i=0; i<listEscolaridade.size(); i++) {
                        %>
                            <option value="<%= listEscolaridade.get(i).getId() %>"><%= listEscolaridade.get(i).getNome() %></option>
                        <%
                        }
                        %>
                    </select>
                </div>
                
                <script type="text/javascript">
                    var select = document.getElementById("escolaridade");
                    var escolaridade = select.options[<%= usuario.getEscolaridade().getId()-1 %>];
                    escolaridade.selected = true;
                </script>

                <br>

                <label for="senha">Senha:</label>
                <div class="input-field col s6">
                    <i class="material-icons prefix">lock_outline</i>
                    <input name="senha" type="password" class="validate">
                </div>
                <label for="senha">Confirmar senha:</label>
                <div class="input-field col s6">
                    <input name="confirmarSenha" type="password">
                </div>

                <br>
                <br>
                <br>

                <div align="right">
                    <button class="btn waves-effect waves-light" type="button" onclick="paginaInicial(document.formPerfil)">Cancelar</button>

                    <button class="btn waves-effect waves-light" type="button" name="action" onclick="validarCamposPerfil(document.formPerfil)">Alterar
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </form>
        </div>

        <br>
        <br>

        <jsp:include page ="Footer.jsp"/>
    </body>
</html>
