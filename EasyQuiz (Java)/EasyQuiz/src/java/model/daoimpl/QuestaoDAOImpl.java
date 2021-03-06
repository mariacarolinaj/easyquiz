
package model.daoimpl;

import model.domain.Questao;
import model.exception.ExcecaoPersistencia;
import util.db.JDBCManterConexao;
import java.awt.image.BufferedImage;
import java.sql.Blob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.DificuldadeDAO;
import model.dao.DisciplinaDAO;
import model.dao.ModuloDAO;
import model.dao.QuestaoDAO;
import model.domain.Dificuldade;
import model.domain.Disciplina;
import model.domain.Modulo;

public class QuestaoDAOImpl implements QuestaoDAO {
    private static QuestaoDAOImpl questaoDAO = null;

    private QuestaoDAOImpl() {
    }

    public static QuestaoDAOImpl getInstance() {

        if (questaoDAO == null) {
            questaoDAO = new QuestaoDAOImpl();
        }

        return questaoDAO;
    }

    @Override
    synchronized public Long insert(Questao questao) throws ExcecaoPersistencia {
        try {
            if (questao == null) {
                throw new ExcecaoPersistencia("Entidade não pode ser nula.");
            }

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "INSERT INTO questao ("
                    + "cod_dificuldade, "
                    + "cod_disciplina, "
                    + "cod_modulo, "
                    + "cod_tipo, "
                    + "txt_enunciado, "
                    + "img_enunciado, "
                    + "seq_questao_correta, "
                    + "txt_resposta_aberta"
                    + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, questao.getDificuldade().getId());
            pstmt.setLong(2, questao.getDisciplina().getId());
            pstmt.setLong(3, questao.getModulo().getId());
            pstmt.setString(4, String.valueOf(questao.getIdTipo()));
            pstmt.setString(5, questao.getTxtEnunciado());
            if(questao.getImgEnunciado()!=null) {
                //pstmt.setBlob(6,  questao.getImgEnunciado());
            } else {
                pstmt.setNull(6, java.sql.Types.NULL);
            }
            if(questao.getSeqQuestaoCorreta()!=null) {
                pstmt.setLong(7, questao.getSeqQuestaoCorreta());
            } else {
                pstmt.setNull(7, java.sql.Types.NULL);
            }
            if(questao.getTxtResposta()!=null) {
                pstmt.setString(8, questao.getTxtResposta());
            } else {
                pstmt.setNull(8, java.sql.Types.NULL);
            }
            
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM questao");
            
            Long id = null;
            if (rs.next()) {
                id = rs.getLong(1);
                questao.setId(id);
            }
            
            rs.close();
            pstmt.close();
            connection.close();
            
            return id;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuestaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    synchronized public boolean update(Questao questao) throws ExcecaoPersistencia {
        try {
            
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "UPDATE questao "
                    + "SET "
                    + "cod_dificuldade=?, "
                    + "cod_disciplina=?, "
                    + "cod_modulo=?, "
                    + "cod_tipo=?, "
                    + "txt_enunciado=?, "
                    + "img_enunciado=?, "
                    + "seq_questao_correta=?, "
                    + "txt_resposta_aberta=?"
                    + " WHERE cod_questao = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, questao.getDificuldade().getId());
            pstmt.setLong(2, questao.getDisciplina().getId());
            pstmt.setLong(3, questao.getModulo().getId());
            pstmt.setString(4, String.valueOf(questao.getIdTipo()));
            pstmt.setString(5, questao.getTxtEnunciado());
            if(questao.getImgEnunciado()!=null) {
                //pstmt.setBlob(6, (Blob) questao.getImgEnunciado());
            } else {
                pstmt.setNull(6, java.sql.Types.NULL);
            }
            if(questao.getSeqQuestaoCorreta()!=null) {
                pstmt.setLong(7, questao.getSeqQuestaoCorreta());
            } else {
                pstmt.setNull(7, java.sql.Types.NULL);
            }
            if(questao.getTxtResposta()!=null) {
                pstmt.setString(8, questao.getTxtResposta());
            } else {
                pstmt.setNull(8, java.sql.Types.NULL);
            }
            pstmt.setLong(9, questao.getId());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuestaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    synchronized public Questao delete(Long cod_Questao) throws ExcecaoPersistencia {
        try {
            Questao questao = this.getQuestaoById(cod_Questao);

            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "DELETE FROM questao WHERE cod_questao = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, cod_Questao);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return questao;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuestaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public Questao getQuestaoById(Long cod_Questao) throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM questao WHERE cod_questao = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cod_Questao);
            ResultSet rs = pstmt.executeQuery();
            
            Questao questao = null;
            DificuldadeDAO dificuldadeDAOImpl = DificuldadeDAOImpl.getInstance();
            DisciplinaDAO disciplinaDAOImpl = DisciplinaDAOImpl.getInstance();
            ModuloDAO moduloDAOImpl = ModuloDAOImpl.getInstance();
            if (rs.next()) {
                questao = new Questao();
                questao.setId(rs.getLong("cod_questao"));
                Dificuldade dificuldade = dificuldadeDAOImpl.getDificuldadeById(rs.getLong("cod_dificuldade"));
                questao.setDificuldade(dificuldade);
                Disciplina disciplina = disciplinaDAOImpl.getDisciplinaById(rs.getLong("cod_disciplina"));
                questao.setDisciplina(disciplina);
                Modulo modulo = moduloDAOImpl.getModuloById(rs.getLong("cod_modulo"));
                questao.setModulo(modulo);
                questao.setIdTipo(rs.getString("cod_tipo").charAt(0));
                questao.setTxtEnunciado(rs.getString("txt_enunciado"));
                questao.setImgEnunciado(rs.getBytes("img_enunciado"));
                questao.setSeqQuestaoCorreta(rs.getLong("seq_questao_correta"));
                questao.setTxtResposta(rs.getString("txt_resposta_aberta"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return questao;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuestaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }

    @Override
    public List<Questao> listAll() throws ExcecaoPersistencia {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM questao ORDER BY cod_questao;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<Questao> listAll = new ArrayList<>();
            DificuldadeDAO dificuldadeDAOImpl = DificuldadeDAOImpl.getInstance();
            DisciplinaDAO disciplinaDAOImpl = DisciplinaDAOImpl.getInstance();
            ModuloDAO moduloDAOImpl = ModuloDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    Questao questao = new Questao();
                    questao.setId(rs.getLong("cod_questao"));
                    Dificuldade dificuldade = dificuldadeDAOImpl.getDificuldadeById(rs.getLong("cod_dificuldade"));
                    questao.setDificuldade(dificuldade);
                    Disciplina disciplina = disciplinaDAOImpl.getDisciplinaById(rs.getLong("cod_disciplina"));
                    questao.setDisciplina(disciplina);
                    Modulo modulo = moduloDAOImpl.getModuloById(rs.getLong("cod_modulo"));
                    questao.setModulo(modulo);
                    questao.setIdTipo(rs.getString("cod_tipo").charAt(0));
                    questao.setTxtEnunciado(rs.getString("txt_enunciado"));
                    questao.setImgEnunciado(rs.getBytes("img_enunciado"));
                    questao.setSeqQuestaoCorreta(rs.getLong("seq_questao_correta"));
                    questao.setTxtResposta(rs.getString("txt_resposta_aberta"));
                    listAll.add(questao);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuestaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }
    
    public List<Questao> listAll(char tipo) throws ExcecaoPersistencia {
        try {
            Connection conexao = JDBCManterConexao.getInstancia().getConexao();

            String sql = "SELECT * FROM questao WHERE cod_tipo = ? ORDER BY cod_questao;";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(tipo));
            ResultSet rs = pstmt.executeQuery();

            List<Questao> listAll = new ArrayList<>();
            DificuldadeDAO dificuldadeDAOImpl = DificuldadeDAOImpl.getInstance();
            DisciplinaDAO disciplinaDAOImpl = DisciplinaDAOImpl.getInstance();
            ModuloDAO moduloDAOImpl = ModuloDAOImpl.getInstance();
            if (rs.next()) {
                do {
                    Questao questao = new Questao();
                    questao.setId(rs.getLong("cod_questao"));
                    Dificuldade dificuldade = dificuldadeDAOImpl.getDificuldadeById(rs.getLong("cod_dificuldade"));
                    questao.setDificuldade(dificuldade);
                    Disciplina disciplina = disciplinaDAOImpl.getDisciplinaById(rs.getLong("cod_disciplina"));
                    questao.setDisciplina(disciplina);
                    Modulo modulo = moduloDAOImpl.getModuloById(rs.getLong("cod_modulo"));
                    questao.setModulo(modulo);
                    questao.setIdTipo(rs.getString("cod_tipo").charAt(0));
                    questao.setTxtEnunciado(rs.getString("txt_enunciado"));
                    questao.setImgEnunciado(rs.getBytes("img_enunciado"));
                    questao.setSeqQuestaoCorreta(rs.getLong("seq_questao_correta"));
                    questao.setTxtResposta(rs.getString("txt_resposta_aberta"));
                    listAll.add(questao);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            conexao.close();

            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuestaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcecaoPersistencia(ex);
        }
    }
}
