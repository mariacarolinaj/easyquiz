/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.domain.Questao;
import model.exception.ExcecaoPersistencia;

/**
 *
 * @author Aluno
 */
public interface QuestaoFechadaDAO {
    public void insert(Questao questao) throws ExcecaoPersistencia;
    public void update(Questao questao) throws ExcecaoPersistencia;
    public Questao delete(Long cod_Questao) throws ExcecaoPersistencia;
    public Questao getQuestaoById(Long cod_Questao) throws ExcecaoPersistencia;
    public List<Questao> listAll() throws ExcecaoPersistencia;
    public List<Questao> listAll(char cod_Tipo) throws ExcecaoPersistencia;
}
