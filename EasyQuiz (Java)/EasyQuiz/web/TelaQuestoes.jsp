<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <script type="text/javascript" language="JavaScript" src="js/webvalida.js"></script>
        <title>EasyQuiz</title>

        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/materialize.css"/>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>

        <style type="text/css">
            [type="radio"].with-gap:checked + label:before,
            [type="radio"].with-gap:checked + label:after {
              border: 2px solid #f4511e;
            }

            [type="radio"].with-gap:checked + label:after {
              background-color: #f4511e;
            }
            .row {
              z-index: 1;
            }
            .card {
              width: 700px;
              z-index: 0;
            }
            .card-panel {
              width: 100%;
              height: 45px;
              padding-top: 0%;
            }
            .card-image {
              height: 250px;
            }
            .card-image img {
              max-height: 100%;
              max-width: 100%;
            }
            .card-content {
                height: 60%;
            }
            .card-action {
              height: 70px;
            }
            .container{
              width: 100%;
            }
            label{
              color: black;
            }
        </style>
    </head>
    <body>
        <nav class="nav-extended" style="background-color:#FFFFFF;">
            <div class="container" style="display: inline; margin-left: 50px;">
                <a id="logo-container" href="#" style="color:#47525E; font-size: 32px;">EasyQuiz</a>
                <ul id="side-nav" class="right hide-on-med-and-down">
                    <li> 
                        <input id="email" placeholder="email" type="email" class="form-control" style="color: #696969;border-color:#D3D3D3;">
                    </li>
                    <li>&ensp;</li>
                    <li>
                        <input id="password" placeholder="senha" type="password" class="validate" style="color: #696969;border-color:#D3D3D3;">
                    </li>
                    <a class="btn-small waves-effect waves-light grey darken-2 btn" href="#">Login</a>
                    <a class="btn-small waves-effect waves-light grey darken-2 btn" href="#">Cadastrar</a>
                </ul>
            </div>
            <div class="row" style="background-color: #EE6363; margin-top: 15px;" >
                <div class="col s8" >Matérias comuns:
                    <a id="botao-matematica" class="waves-effect waves-light" style="margin-left: 5px; margin-right: 5px; padding-left: 5px; padding-right: 5px; padding-top: 2px; background-color: #E5E9F2; color: #47525E">Matemática</a>
                    <a id="botao-portugues" class="waves-effect waves-light" style="margin-left: 5px; margin-right: 5px; padding-left: 5px; padding-right: 5px; padding-top: 2px; background-color: #E5E9F2; color: #47525E">Português</a>
                    <a id="botao-fisica" class="waves-effect waves-light" style="margin-left: 5px; margin-right: 5px; padding-left: 5px; padding-right: 5px; padding-top:    2px; background-color: #E5E9F2; color: #47525E">Física</a>
                </div>
                <div class="col s4">
                    <a class="btn-small waves-effect waves-light grey darken-2 btn" onclick="mostrarform()">Filtrar</a></li>  			
                </div>
            </div>
            <div class="row " id="pesquisa" style="display: none; position: absolute;   width: 47%; background: rgba(255, 255, 255, 0.7);">
                <div class="col s12 offset-s12" id="pesquisa" style="border: 4px solid; border-color:#D3D3D3; background: rgba(255, 255, 255, 1.0); ; text-align:center;border-radius: 10px; z-index: 2;" >
                    <form>
                        <input id="search" type="search" placeholder="Busque por palavras chave" style="color: #696969;">
                        <div class="row">
                            <div class="input-field col s6">
                                <select id="nivel">
                                    <option value="" disabled selected>Escolha uma Dificuldade</option>
                                    <option value="1">Fácil</option>
                                    <option value="2">Médio</option>
                                    <option value="3">Difícil</option>
                                    <option value="4">Desafio</option>
                                    <option value="0">Nenhum</option>
                                </select>
                            </div>
                            <div class="input-field col s6">
                                <select id="materia">
                                    <option value="" disabled selected>Escolha uma Matéria</option>
                                    <option value="1">Matemática</option>
                                    <option value="2">Português</option>
                                    <option value="3">Física</option>
                                    <option value="4">Geografia</option>
                                    <option value="5">História</option>
                                    <option value="6">Química</option>
                                    <option value="7">Inglês</option>
                                    <option value="0">Nenhum</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <select id="tipo">
                                    <option value="" disabled selected>Escolha um tipo</option>
                                    <option value="1">Aberta</option>
                                    <option value="2">Fechada</option>
                                    <option value="3">Nenhum</option>
                                </select>
                            </div>
                            <div class="input-field col s6">
                                <select id="materia">
                                    <option value="" disabled selected>Escolha um módulo</option>
                                    <option value="0">Nenhum</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <button class="btn-large waves-effect waves-light grey darken-2" type="submit" name="action">avançar 
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </div>
        </nav>

        <div class="container" >
            <div class="row" >
                <div class="col s12 m4" > 
                    <div class="card" >
                        <div class="card-panel #f4511e deep-orange darken-1 white-text">
                            <div style="">
                                <b style="padding-top: 0px">Disciplina: </b><span id="disciplina1">Física</span>
                                <b style="padding-left:50%;padding-top: 0px">Dificuldade: </b><span id="dificuldade1">Desafio</span>
                            </div>
                            <div>
                                <span id="modulo1" style=""><b>Módulo: </b>Termodinâmica</span>
                            </div>
                        </div>
                        <div class="card-image">
                            <img id="imagem1" class="responsive-img" src="img/Geladeira.png">
                        </div>
                        <div class="card-content">
                            <div id="enunciado1">
                                <p><b>A invenção da geladeira proporcionou uma revolução no aproveitamento dos alimentos, ao permitir que fossem armazenados e transportados por longos períodos. A figura apresentada ilustra o processo cíclico de funcionamento de uma geladeira, em que um gás no interior de uma tubulação é forçado a circular entre o congelador e a parte externa da geladeira. É por meio dos processos de compressão, que ocorre na parte externa, e de expansão, que ocorre na parte interna, que o gás proporciona a troca de calor entre o interior e o exterior da geladeira. Nos processos de transformação de energia envolvidos no funcionamento da geladeira, </b></p>
                            </div>
                            <div id="alternativas1">
                                <form action="#" id="form1">
                                    <p>
                                        <input class="with-gap" name="grupo1" type="radio" id="alternativa1"  />
                                        <label for="alternativa1"><b>a)</b> a expansão do gás é um processo que cede a energia necessária ao resfriamento da parte interna da geladeira. </label>
                                    </p>
                                    <p>
                                        <input class="with-gap" name="grupo1" type="radio" id="alternativa2"  />
                                        <label for="alternativa2"><b>b)</b> o calor flui de forma não espontânea da parte mais fria, no interior, para a mais quente, no exterior da geladeira. </label>
                                    </p>
                                    <p>
                                        <input class="with-gap" name="grupo1" type="radio" id="alternativa3"  />
                                        <label for="alternativa3"><b>c)</b> a quantidade de calor cedida ao meio externo é igual ao calor retirado da geladeira.</label>
                                    </p>
                                    <p >
                                        <input class="with-gap" name="grupo1" type="radio" id="alternativa4"  />
                                        <label for="alternativa4"><b>d)</b> a eficiência é tanto maior quanto menos isolado termicamente do ambiente externo for o seu compartimento interno.</label>
                                    </p>
                                    <p>
                                        <input class="with-gap" name="grupo1" type="radio" id="alternativa5"  />
                                        <label for="alternativa5"><b>e)</b> a energia retirada do interior pode ser devolvida à geladeira abrindo-se a sua porta, o que reduz seu consumo de energia.</label>
                                    </p>
                                </form>
                            </div>
                        </div>
                        <div class="card-action">
                            <button onclick="teste()" class="btn waves-effect waves-orange orange right" style="background-color: #f4511e !important;" id="responder1">Responder</button>
                            <a id="forum1" href="#"><b>Forum</b></a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col s12 m4">
                    <div class="card">
                        <div class="card-panel #f4511e deep-orange darken-1 white-text">
                            <div style="">
                                <b style="padding-top: 0px">Disciplina: </b><span id="disciplina2">História</span>
                                <b style="padding-left:50%;padding-top: 0px">Dificuldade: </b><span id="dificuldade2">Difícil</span>
                            </div>
                            <div>
                                <span id="modulo2" style=""><b>Módulo: </b>Segunda Guerra Mundial</span>
                            </div>
                        </div>
                        <!--
                        <div class="card-image">
                            <img id="imagem2" class="responsive-img" src="">
                        </div>
                        -->
                        <div class="card-content">
                            <div id="enunciado2">
                                <b>Baseado nos conhecimentos previamente adquiridos relativos a disciplina, discurse sobre o Nazismo.</b>
                            </div>
                            <div id="resposta2">
                                <form action="#" id="form2">
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <textarea id="textarea2" class="materialize-textarea"></textarea>
                                            <label for="textarea2">Resposta:</label>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div class="card-action">
                            <button class="btn waves-effect waves-orange orange right" form="form2" style="background-color: #f4511e !important;" type="submit" id="responder2">Responder</button>
                            <a href="#"><b>Forum</b></a>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <br>
        <br>

        <footer class="page-footer orange">
            <div class="container">
                <div class="row">
                    <div class="col l6 s12">
                        <h5 class="white-text">Quem somos</h5>
                        <p class="grey-text text-lighten-4">Luiz Augusto Dias Berto</p>
                        <p class="grey-text text-lighten-4">Maria Carolina</p>
                        <p class="grey-text text-lighten-4">Rafael Gontijo</p>
                        <p class="grey-text text-lighten-4">Victor César</p>
                        <p class="grey-text text-lighten-4">Victor Gabriel</p>
                    </div>
                    <div class="col l3 s12">
                        <h5 class="white-text">Conecte-se</h5>
                        <ul>
                            <li><a class="white-text" href="http://www.cefetmg.br/">CEFET-MG</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="footer-copyright">
                <div class="container">
                    Feito com <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
                </div>
            </div>
        </footer>


          <!--  Scripts-->
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script type="text/javascript" language="JavaScript" src="js/materialize.js"></script>
        <script type="text/javascript" language="JavaScript" src="js/init.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('select').material_select();
            });
            function mostrarform() {
                var element = document.getElementById("pesquisa");
                if (element.style.display =="none") {element.style.display = "block";} else {element.style.display = "none";}
            }
        </script>
    </body>
</html>

