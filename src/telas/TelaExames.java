/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import Classes.Conexao;
import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.text.DecimalFormat;

/**
 *
 * @author Arlindo Halar
 */
public class TelaExames extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    /**
     * Creates new form TelaExames
     */
    
    private static int ClasseT;
    private static int id;
    
    public TelaExames(int num, int id) {
        initComponents();
        conexao = Conexao.conector();
        
        ClasseT = num;
        
        if (ClasseT == 2 || ClasseT == 3) {
            /*lp.setVisible(false);
            lm.setVisible(false);
            lo.setVisible(false);
            lef.setVisible(false);
            lcs.setVisible(false);
            lcn.setVisible(false);
            li.setVisible(false);
            lem.setVisible(false);
            lemc.setVisible(false);
            lev.setVisible(false);*/

            lp.setEnabled(false);
            lm.setEnabled(false);
            lo.setEnabled(false);
            lef.setEnabled(false);
            lcs.setEnabled(false);
            lcn.setEnabled(false);
            li.setEnabled(false);
            lem.setEnabled(false);
            lemc.setEnabled(false);
            lev.setEnabled(false);
        }
        
        if (-1 == tblaluno.getSelectedRow()) {

        } else {
            alunos();
        }
        
        if (tblaluno.getSelectedRow() == -1) {
            limpar();
        }
        
        if (ClasseT == 3) {
            TelaExames.id = id;

            //tblaluno.setEnabled(false);
            //tblaluno.setRowSelectionInterval(1, 1);
            cbclasse.setEnabled(false);
            cbturma.setEnabled(false);

            String classe;
            String turma;

            String sql = "select al.nome, c.Classe, t.turma from aluno al inner join classe c on c.id = al.classe_id inner join turma t on t.id = al.turma_id where al.id = ?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, String.valueOf(id));

                rs = pst.executeQuery();

                if (rs.next()) {
                    classe = rs.getString("Classe");
                    turma = rs.getString("turma");

                    if ("1".equals(classe)) {
                        cbclasse.setSelectedIndex(0);

                        if (null != turma) {
                            switch (turma) {
                                case "A":
                                    cbturma.setSelectedIndex(0);
                                    break;
                                case "B":
                                    cbturma.setSelectedIndex(1);
                                    break;
                            }
                        }

                    } else if ("2".equals(classe)) {
                        cbclasse.setSelectedIndex(1);

                        if (null != turma) {
                            switch (turma) {
                                case "A":
                                    cbturma.setSelectedIndex(0);
                                    break;
                                case "B":
                                    cbturma.setSelectedIndex(1);
                                    break;
                            }
                        }

                    } else if ("3".equals(classe)) {
                        cbclasse.setSelectedIndex(2);

                        if (null != turma) {
                            switch (turma) {
                                case "A":
                                    cbturma.setSelectedIndex(0);
                                    break;
                                case "B":
                                    cbturma.setSelectedIndex(1);
                                    break;
                            }
                        }

                    } else if ("4".equals(classe)) {
                        cbclasse.setSelectedIndex(3);

                        if (null != turma) {
                            switch (turma) {
                                case "A":
                                    cbturma.setSelectedIndex(0);
                                    break;
                                case "B":
                                    cbturma.setSelectedIndex(1);
                                    break;
                            }
                        }

                    } else if ("5".equals(classe)) {
                        cbclasse.setSelectedIndex(4);

                        if (null != turma) {
                            switch (turma) {
                                case "A":
                                    cbturma.setSelectedIndex(0);
                                    break;
                                case "B":
                                    cbturma.setSelectedIndex(1);
                                    break;
                            }
                        }
                    } else if ("6".equals(classe)) {
                        cbclasse.setSelectedIndex(5);

                        if (null != turma) {
                            switch (turma) {
                                case "A":
                                    cbturma.setSelectedIndex(0);
                                    break;
                                case "B":
                                    cbturma.setSelectedIndex(1);
                                    break;
                            }
                        }

                    } else if ("7".equals(classe)) {
                        cbclasse.setSelectedIndex(6);

                        if (null != turma) {
                            switch (turma) {
                                case "A":
                                    cbturma.setSelectedIndex(0);
                                    break;
                                case "B":
                                    cbturma.setSelectedIndex(1);
                                    break;
                            }
                        }

                    }

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

        }
        
    }
    
    private void limpar() {

        e1p.setText(null);
        e2p.setText(null);
        
        e1m.setText(null);
        e2m.setText(null);
        
        e1o.setText(null);
        e2o.setText(null);
        
        e1i.setText(null);
        e2i.setText(null);
        
        e1ef.setText(null);
        e2ef.setText(null);
        
        e1cn.setText(null);
        e2cn.setText(null);
        
        e1cs.setText(null);
        e2cs.setText(null);
        
        e1em.setText(null);
        e2em.setText(null);
       
        e1ev.setText(null);
        e2ev.setText(null);
        
        e1emc.setText(null);
        e2emc.setText(null);
        
    }
    
    private static String aluno;
    private static String tu_id;
    
    private void alunos() {

        if (ClasseT == 3) {
            aluno = "select al.id, al.nome from aluno al inner join classe cl on cl.id = classe_id inner join turma tu on tu.id = turma_id where al.classe_id = ? and al.turma_id = ? and tu.id_ano = ? and al.id = ?";

            String tu = "select id from turma where id_ano = ? and classe_id = ? and turma = ?";

            try {
                pst = conexao.prepareStatement(tu);

                switch (cbano.getSelectedIndex()) {
                    case 0:
                        pst.setString(1, "1");

                        switch (cbclasse.getSelectedIndex()) {
                            case 0:
                                pst.setString(2, "1");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 1:

                                pst.setString(2, "2");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 2:
                                pst.setString(2, "3");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 3:
                                pst.setString(2, "4");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;

                            case 4:
                                pst.setString(2, "5");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;

                            case 5:
                                pst.setString(2, "6");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 6:
                                pst.setString(2, "7");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                        }
                        break;

                    case 1:
                        pst.setString(1, "2");
                        switch (cbclasse.getSelectedIndex()) {
                            case 0:
                                pst.setString(2, "1");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 1:

                                pst.setString(2, "2");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 2:
                                pst.setString(2, "3");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 3:
                                pst.setString(2, "4");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;

                            case 4:
                                pst.setString(2, "5");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;

                            case 5:
                                pst.setString(2, "6");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 6:
                                pst.setString(2, "7");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                        }
                        break;
                    case 2:
                        pst.setString(1, "3");
                        switch (cbclasse.getSelectedIndex()) {
                            case 0:
                                pst.setString(2, "1");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 1:

                                pst.setString(2, "2");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 2:
                                pst.setString(2, "3");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 3:
                                pst.setString(2, "4");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;

                            case 4:
                                pst.setString(2, "5");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;

                            case 5:
                                pst.setString(2, "6");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 6:
                                pst.setString(2, "7");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                        }
                        break;
                    case 3:
                        pst.setString(1, "4");
                        switch (cbclasse.getSelectedIndex()) {
                            case 0:
                                pst.setString(2, "1");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 1:

                                pst.setString(2, "2");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 2:
                                pst.setString(2, "3");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 3:
                                pst.setString(2, "4");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;

                            case 4:
                                pst.setString(2, "5");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;

                            case 5:
                                pst.setString(2, "6");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 6:
                                pst.setString(2, "7");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                        }
                        break;
                    case 4:
                        pst.setString(1, "5");
                        switch (cbclasse.getSelectedIndex()) {
                            case 0:
                                pst.setString(2, "1");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 1:

                                pst.setString(2, "2");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 2:
                                pst.setString(2, "3");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 3:
                                pst.setString(2, "4");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;

                            case 4:
                                pst.setString(2, "5");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;

                            case 5:
                                pst.setString(2, "6");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 6:
                                pst.setString(2, "7");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                        }
                        break;

                }
                rs = pst.executeQuery();

                if (rs.next()) {
                    tu_id = rs.getString("id");
                    //System.out.println(rs.getString("id"));
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
                //System.out.println(e);
            }

            try {

                pst = conexao.prepareStatement(aluno);
                //pst.setString(4, String.valueOf(cbano.getSelectedIndex()+1));
                pst.setString(3, String.valueOf(cbano.getSelectedIndex() + 1));

                pst.setString(4, String.valueOf(id));

                pst.setString(2, tu_id);

                switch (cbclasse.getSelectedIndex()) {
                    case 0: {
                        pst.setString(1, "1");

                        lp.setEnabled(true); //lp - botão lançarPortuguês 
                        p.setEnabled(true);  // p - JLabel Português 
                        nfp.setEnabled(true); //nfp - Nota de Frequencia de Portugues
                        mfp.setEnabled(true); //mfp - Média final de Portugues
                        e1p.setEnabled(true); //e1p - 1ª época de Português
                        e2p.setEnabled(true); //e2p - 2ª época de Português
                       
                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfm.setEnabled(true);
                        mfm.setEnabled(true);
                        e1m.setEnabled(true);
                        nfm.setEnabled(true);
                        e2m.setEnabled(true);
                        
                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e1o.setEnabled(true);
                        
                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e1ef.setEnabled(true);
                        
                        lcn.setEnabled(false);
                        cn.setEnabled(false);
                        nfcn.setEnabled(false);
                        mfcn.setEnabled(false);
                        e1cn.setEnabled(false);
                        e2cn.setEnabled(false);
                        
                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        nfcs.setEnabled(false);
                        mfcs.setEnabled(false);
                        e1cs.setEnabled(false);
                        e2cs.setEnabled(false);
                        
                        li.setEnabled(false);
                        i.setEnabled(false);
                        nfi.setEnabled(false);
                        mfi.setEnabled(false);
                        e1i.setEnabled(false);
                        e2i.setEnabled(false);
                        
                        lem.setEnabled(false);
                        em.setEnabled(false);
                        nfem.setEnabled(false);
                        mfem.setEnabled(false);
                        e1em.setEnabled(false);
                        e2em.setEnabled(false);
                        
                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        nfemc.setEnabled(false);
                        mfemc.setEnabled(false);
                        e1emc.setEnabled(false);
                        e2emc.setEnabled(false);
                        
                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        nfev.setEnabled(false);
                        mfev.setEnabled(false);
                        e1ev.setEnabled(false);
                        e2ev.setEnabled(false);
                        

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "1");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "2");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }
                    case 1: {
                        pst.setString(1, "2");

                        lp.setEnabled(true);  
                        p.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1p.setEnabled(true);
                        e2p.setEnabled(true);
                        
                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1m.setEnabled(true);
                        e2m.setEnabled(true);
                       
                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e2o.setEnabled(true);
                        
                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e2ef.setEnabled(true);
                        
                        lcn.setEnabled(false);
                        cn.setEnabled(false);
                        nfcn.setEnabled(false);
                        mfcn.setEnabled(false);
                        e1cn.setEnabled(false);
                        e2cn.setEnabled(false);
                        
                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        nfcs.setEnabled(false);
                        mfcs.setEnabled(false);
                        e1cs.setEnabled(false);
                        e2cs.setEnabled(false);
                        
                        li.setEnabled(false);
                        i.setEnabled(false);
                        nfi.setEnabled(false);
                        mfi.setEnabled(false);
                        e1i.setEnabled(false);
                        e2i.setEnabled(false);
                        
                        lem.setEnabled(false);
                        em.setEnabled(false);
                        nfem.setEnabled(false);
                        mfem.setEnabled(false);
                        e1em.setEnabled(false);
                        e2em.setEnabled(false);
                        

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        nfemc.setEnabled(false);
                        mfemc.setEnabled(false);
                        e1emc.setEnabled(false);
                        e2emc.setEnabled(false);
                        
                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        nfev.setEnabled(false);
                        mfev.setEnabled(false);
                        e1ev.setEnabled(false);
                        e2ev.setEnabled(false);
                        
                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "3");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "4");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }
                    case 2: {
                        pst.setString(1, "3");

                        lp.setEnabled(true);  
                        p.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1p.setEnabled(true); 
                        e2p.setEnabled(true); 
                       

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfm.setEnabled(true);
                        mfm.setEnabled(true);
                        e1m.setEnabled(true);
                        e2m.setEnabled(true);
                       
                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e2o.setEnabled(true);
                       
                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e2ef.setEnabled(true);
                        

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        nfcn.setEnabled(true);
                        mfcn.setEnabled(true);
                        e1cn.setEnabled(true);
                        e2cn.setEnabled(true);
                       

                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        nfcs.setEnabled(false);
                        mfcs.setEnabled(false);
                        e1cs.setEnabled(false);
                        e2cs.setEnabled(false);
                        

                        li.setEnabled(false);
                        i.setEnabled(false);
                        nfi.setEnabled(false);
                        mfi.setEnabled(false);
                        e1i.setEnabled(false);
                        e2i.setEnabled(false);
                        
                        lem.setEnabled(false);
                        em.setEnabled(false);
                        nfem.setEnabled(false);
                        mfem.setEnabled(false);
                        e1em.setEnabled(false);
                        e2em.setEnabled(false);

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        nfemc.setEnabled(false);
                        mfemc.setEnabled(false);
                        e1emc.setEnabled(false);
                        e2emc.setEnabled(false);

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        nfev.setEnabled(false);
                        mfev.setEnabled(false);
                        e1ev.setEnabled(false);
                        e2ev.setEnabled(false);
                        

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "5");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "6");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }
                    case 3: {
                        pst.setString(1, "4");

                        lp.setEnabled(true); 
                        p.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1p.setEnabled(true);
                        e2p.setEnabled(true); 
                        

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfm.setEnabled(true);
                        mfm.setEnabled(true);
                        e1m.setEnabled(true);
                        e2m.setEnabled(true);
                        

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e2o.setEnabled(true);
                        

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e2ef.setEnabled(true);
                       

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        nfcn.setEnabled(true);
                        mfcn.setEnabled(true);
                        e1cn.setEnabled(true);
                        e2cn.setEnabled(true);
                       

                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        nfcs.setEnabled(false);
                        mfcs.setEnabled(false);
                        e1cs.setEnabled(false);
                        e2cs.setEnabled(false);
                       

                        li.setEnabled(false);
                        i.setEnabled(false);
                        nfi.setEnabled(false);
                        mfi.setEnabled(false);
                        e1i.setEnabled(false);
                        e2i.setEnabled(false);
                       

                        lem.setEnabled(false);
                        em.setEnabled(false);
                        nfem.setEnabled(false);
                        mfem.setEnabled(false);
                        e1em.setEnabled(false);
                        e2em.setEnabled(false);
                       

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        nfemc.setEnabled(false);
                        mfemc.setEnabled(false);
                        e1emc.setEnabled(false);
                        e2emc.setEnabled(false);
                        

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        nfev.setEnabled(false);
                        mfev.setEnabled(false);
                        e1ev.setEnabled(false);
                        e2ev.setEnabled(false);
                        

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "7");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "8");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }
                    case 4: {
                        pst.setString(1, "5");

                        lp.setEnabled(true); 
                        p.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1p.setEnabled(true); 
                        e2p.setEnabled(true); 
                

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfm.setEnabled(true);
                        mfm.setEnabled(true);
                        e1m.setEnabled(true);
                        e2m.setEnabled(true);
                      

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e2o.setEnabled(true);
                       

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e2ef.setEnabled(true);
                        

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        nfcn.setEnabled(true);
                        mfcn.setEnabled(true);
                        e1cn.setEnabled(true);
                        e2cn.setEnabled(true);
                        

                        lcs.setEnabled(true);
                        cs.setEnabled(true);
                        nfcs.setEnabled(true);
                        mfcs.setEnabled(true);
                        e1cs.setEnabled(true);
                        e2cs.setEnabled(true);
                        

                        li.setEnabled(true);
                        i.setEnabled(true);
                        nfi.setEnabled(true);
                        mfi.setEnabled(true);
                        e1i.setEnabled(true);
                        e2i.setEnabled(true);
                       

                        lem.setEnabled(true);
                        em.setEnabled(true);
                        nfem.setEnabled(true);
                        mfem.setEnabled(true);
                        e1em.setEnabled(true);
                        e2em.setEnabled(true);
                       

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        nfemc.setEnabled(false);
                        mfemc.setEnabled(false);
                        e1emc.setEnabled(false);
                        e2emc.setEnabled(false);
                        
                        
                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        nfev.setEnabled(false);
                        mfev.setEnabled(false);
                        e1ev.setEnabled(false);
                        e2ev.setEnabled(false);
                       

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "9");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "10");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }
                    case 5: {
                        pst.setString(1, "6");

                        lp.setEnabled(true);
                        p.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1p.setEnabled(true);
                        e2p.setEnabled(true);
                        

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfm.setEnabled(true);
                        mfm.setEnabled(true);
                        e1m.setEnabled(true);
                        e2m.setEnabled(true);
                        

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e2o.setEnabled(true);
                       

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e2ef.setEnabled(true);
                       

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        nfcn.setEnabled(true);
                        mfcn.setEnabled(true);
                        e1cn.setEnabled(true);
                        e2cn.setEnabled(true);
                        

                        lcs.setEnabled(true);
                        cs.setEnabled(true);
                        nfcs.setEnabled(true);
                        mfcs.setEnabled(true);
                        e1cs.setEnabled(true);
                        e2cs.setEnabled(true);
                      

                        li.setEnabled(true);
                        i.setEnabled(true);
                        nfi.setEnabled(true);
                        mfi.setEnabled(true);
                        e1i.setEnabled(true);
                        e2i.setEnabled(true);
                       

                        lem.setEnabled(true);
                        em.setEnabled(true);
                        nfem.setEnabled(true);
                        mfem.setEnabled(true);
                        e1em.setEnabled(true);
                        e2em.setEnabled(true);
                        

                        lemc.setEnabled(true);
                        emc.setEnabled(true);
                        nfemc.setEnabled(true);
                        mfemc.setEnabled(true);
                        e1emc.setEnabled(true);
                        e2emc.setEnabled(true);
                       

                        lev.setEnabled(true);
                        ev.setEnabled(true);
                        nfev.setEnabled(true);
                        mfev.setEnabled(true);
                        e1ev.setEnabled(true);
                        e2ev.setEnabled(true);
                        

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "11");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "12");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }
                    case 6: {
                        pst.setString(1, "7");

                        lp.setEnabled(true);
                        p.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1p.setEnabled(true);
                        e2p.setEnabled(true);
                        

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfm.setEnabled(true);
                        mfm.setEnabled(true);
                        e1m.setEnabled(true);
                        e2m.setEnabled(true);
                        

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e2o.setEnabled(true);
                       

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e2ef.setEnabled(true);
                       

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        nfcn.setEnabled(true);
                        mfcn.setEnabled(true);
                        e1cn.setEnabled(true);
                        e2cn.setEnabled(true);
                        

                        lcs.setEnabled(true);
                        cs.setEnabled(true);
                        nfcs.setEnabled(true);
                        mfcs.setEnabled(true);
                        e1cs.setEnabled(true);
                        e2cs.setEnabled(true);
                      

                        li.setEnabled(true);
                        i.setEnabled(true);
                        nfi.setEnabled(true);
                        mfi.setEnabled(true);
                        e1i.setEnabled(true);
                        e2i.setEnabled(true);
                       

                        lem.setEnabled(true);
                        em.setEnabled(true);
                        nfem.setEnabled(true);
                        mfem.setEnabled(true);
                        e1em.setEnabled(true);
                        e2em.setEnabled(true);
                        

                        lemc.setEnabled(true);
                        emc.setEnabled(true);
                        nfemc.setEnabled(true);
                        mfemc.setEnabled(true);
                        e1emc.setEnabled(true);
                        e2emc.setEnabled(true);
                       

                        lev.setEnabled(true);
                        ev.setEnabled(true);
                        nfev.setEnabled(true);
                        mfev.setEnabled(true);
                        e1ev.setEnabled(true);
                        e2ev.setEnabled(true);
                        

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "13");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "14");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }

                }

                rs = pst.executeQuery();

                tblaluno.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

        } else if (ClasseT == 1 || ClasseT == 2) {

            aluno = "select al.id, al.sexo, al.nome from aluno al inner join classe cl on cl.id = classe_id inner join turma tu on tu.id = turma_id where al.classe_id = ? and al.turma_id = ?";

            String tu = "select id from turma where id_ano = ? and classe_id = ? and turma = ?";

            try {
                pst = conexao.prepareStatement(tu);

                switch (cbano.getSelectedIndex()) {
                    case 0:
                        pst.setString(1, "1");

                        switch (cbclasse.getSelectedIndex()) {
                            case 0:
                                pst.setString(2, "1");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 1:

                                pst.setString(2, "2");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 2:
                                pst.setString(2, "3");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 3:
                                pst.setString(2, "4");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 4:
                                pst.setString(2, "5");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 5:
                                pst.setString(2, "6");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 6:
                                pst.setString(2, "7");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                        }
                        break;

                    case 1:
                        pst.setString(1, "2");
                        switch (cbclasse.getSelectedIndex()) {
                            case 0:
                                pst.setString(2, "1");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 1:

                                pst.setString(2, "2");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 2:
                                pst.setString(2, "3");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 3:
                                pst.setString(2, "4");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 4:
                                pst.setString(2, "5");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 5:
                                pst.setString(2, "6");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 6:
                                pst.setString(2, "7");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                        }
                        break;
                    case 2:
                        pst.setString(1, "3");
                        switch (cbclasse.getSelectedIndex()) {
                            case 0:
                                pst.setString(2, "1");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 1:

                                pst.setString(2, "2");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 2:
                                pst.setString(2, "3");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 3:
                                pst.setString(2, "4");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 4:
                                pst.setString(2, "5");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 5:
                                pst.setString(2, "6");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 6:
                                pst.setString(2, "7");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                        }
                        break;
                    case 3:
                        pst.setString(1, "4");
                        switch (cbclasse.getSelectedIndex()) {
                            case 0:
                                pst.setString(2, "1");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 1:

                                pst.setString(2, "2");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 2:
                                pst.setString(2, "3");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 3:
                                pst.setString(2, "4");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 4:
                                pst.setString(2, "5");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 5:
                                pst.setString(2, "6");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 6:
                                pst.setString(2, "7");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                        }
                        break;
                    case 4:
                        pst.setString(1, "5");
                        switch (cbclasse.getSelectedIndex()) {
                            case 0:
                                pst.setString(2, "1");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 1:

                                pst.setString(2, "2");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 2:
                                pst.setString(2, "3");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 3:
                                pst.setString(2, "4");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 4:
                                pst.setString(2, "5");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 5:
                                pst.setString(2, "6");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                            case 6:
                                pst.setString(2, "7");

                                switch (cbturma.getSelectedIndex()) {
                                    case 0:
                                        pst.setString(3, "A");
                                        break;
                                    case 1:
                                        pst.setString(3, "B");
                                        break;
                                }
                                break;
                        }
                        break;

                }
                rs = pst.executeQuery();

                if (rs.next()) {
                    tu_id = rs.getString("id");
                    //System.out.println(rs.getString("id"));
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

            try {
                pst = conexao.prepareStatement(aluno);
                pst.setString(2, tu_id);
                switch (cbclasse.getSelectedIndex()) {
                    case 0: {
                        pst.setString(1, "1");

                        lp.setEnabled(true); //lp - botão lançarPortuguês 
                        p.setEnabled(true);  // p - JLabel Português 
                        nfp.setEnabled(true); //nfp - Nota de Frequencia de Portugues
                        mfp.setEnabled(true); //mfp - Média final de Portugues
                        e1p.setEnabled(true); //e1p - 1ª época de Português
                        e2p.setEnabled(true); //e2p - 2ª época de Português
                       
                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfm.setEnabled(true);
                        mfm.setEnabled(true);
                        e1m.setEnabled(true);
                        nfm.setEnabled(true);
                        e2m.setEnabled(true);
                        
                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e1o.setEnabled(true);
                        
                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e1ef.setEnabled(true);
                        
                        lcn.setEnabled(false);
                        cn.setEnabled(false);
                        nfcn.setEnabled(false);
                        mfcn.setEnabled(false);
                        e1cn.setEnabled(false);
                        e2cn.setEnabled(false);
                        
                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        nfcs.setEnabled(false);
                        mfcs.setEnabled(false);
                        e1cs.setEnabled(false);
                        e2cs.setEnabled(false);
                        
                        li.setEnabled(false);
                        i.setEnabled(false);
                        nfi.setEnabled(false);
                        mfi.setEnabled(false);
                        e1i.setEnabled(false);
                        e2i.setEnabled(false);
                        
                        lem.setEnabled(false);
                        em.setEnabled(false);
                        nfem.setEnabled(false);
                        mfem.setEnabled(false);
                        e1em.setEnabled(false);
                        e2em.setEnabled(false);
                        
                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        nfemc.setEnabled(false);
                        mfemc.setEnabled(false);
                        e1emc.setEnabled(false);
                        e2emc.setEnabled(false);
                        
                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        nfev.setEnabled(false);
                        mfev.setEnabled(false);
                        e1ev.setEnabled(false);
                        e2ev.setEnabled(false);
                        

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "1");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "2");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }
                    case 1: {
                        pst.setString(1, "2");

                        lp.setEnabled(true);  
                        p.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1p.setEnabled(true);
                        e2p.setEnabled(true);
                        
                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1m.setEnabled(true);
                        e2m.setEnabled(true);
                       
                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e2o.setEnabled(true);
                        
                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e2ef.setEnabled(true);
                        
                        lcn.setEnabled(false);
                        cn.setEnabled(false);
                        nfcn.setEnabled(false);
                        mfcn.setEnabled(false);
                        e1cn.setEnabled(false);
                        e2cn.setEnabled(false);
                        
                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        nfcs.setEnabled(false);
                        mfcs.setEnabled(false);
                        e1cs.setEnabled(false);
                        e2cs.setEnabled(false);
                        
                        li.setEnabled(false);
                        i.setEnabled(false);
                        nfi.setEnabled(false);
                        mfi.setEnabled(false);
                        e1i.setEnabled(false);
                        e2i.setEnabled(false);
                        
                        lem.setEnabled(false);
                        em.setEnabled(false);
                        nfem.setEnabled(false);
                        mfem.setEnabled(false);
                        e1em.setEnabled(false);
                        e2em.setEnabled(false);
                        

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        nfemc.setEnabled(false);
                        mfemc.setEnabled(false);
                        e1emc.setEnabled(false);
                        e2emc.setEnabled(false);
                        
                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        nfev.setEnabled(false);
                        mfev.setEnabled(false);
                        e1ev.setEnabled(false);
                        e2ev.setEnabled(false);
                        
                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "3");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "4");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }
                    case 2: {
                        pst.setString(1, "3");

                        lp.setEnabled(true);  
                        p.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1p.setEnabled(true); 
                        e2p.setEnabled(true); 
                       

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfm.setEnabled(true);
                        mfm.setEnabled(true);
                        e1m.setEnabled(true);
                        e2m.setEnabled(true);
                       
                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e2o.setEnabled(true);
                       
                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e2ef.setEnabled(true);
                        

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        nfcn.setEnabled(true);
                        mfcn.setEnabled(true);
                        e1cn.setEnabled(true);
                        e2cn.setEnabled(true);
                       

                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        nfcs.setEnabled(false);
                        mfcs.setEnabled(false);
                        e1cs.setEnabled(false);
                        e2cs.setEnabled(false);
                        

                        li.setEnabled(false);
                        i.setEnabled(false);
                        nfi.setEnabled(false);
                        mfi.setEnabled(false);
                        e1i.setEnabled(false);
                        e2i.setEnabled(false);
                        
                        lem.setEnabled(false);
                        em.setEnabled(false);
                        nfem.setEnabled(false);
                        mfem.setEnabled(false);
                        e1em.setEnabled(false);
                        e2em.setEnabled(false);

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        nfemc.setEnabled(false);
                        mfemc.setEnabled(false);
                        e1emc.setEnabled(false);
                        e2emc.setEnabled(false);

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        nfev.setEnabled(false);
                        mfev.setEnabled(false);
                        e1ev.setEnabled(false);
                        e2ev.setEnabled(false);
                        

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "5");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "6");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }
                    case 3: {
                        pst.setString(1, "4");

                        lp.setEnabled(true); 
                        p.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1p.setEnabled(true);
                        e2p.setEnabled(true); 
                        

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfm.setEnabled(true);
                        mfm.setEnabled(true);
                        e1m.setEnabled(true);
                        e2m.setEnabled(true);
                        

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e2o.setEnabled(true);
                        

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e2ef.setEnabled(true);
                       

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        nfcn.setEnabled(true);
                        mfcn.setEnabled(true);
                        e1cn.setEnabled(true);
                        e2cn.setEnabled(true);
                       

                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        nfcs.setEnabled(false);
                        mfcs.setEnabled(false);
                        e1cs.setEnabled(false);
                        e2cs.setEnabled(false);
                       

                        li.setEnabled(false);
                        i.setEnabled(false);
                        nfi.setEnabled(false);
                        mfi.setEnabled(false);
                        e1i.setEnabled(false);
                        e2i.setEnabled(false);
                       

                        lem.setEnabled(false);
                        em.setEnabled(false);
                        nfem.setEnabled(false);
                        mfem.setEnabled(false);
                        e1em.setEnabled(false);
                        e2em.setEnabled(false);
                       

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        nfemc.setEnabled(false);
                        mfemc.setEnabled(false);
                        e1emc.setEnabled(false);
                        e2emc.setEnabled(false);
                        

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        nfev.setEnabled(false);
                        mfev.setEnabled(false);
                        e1ev.setEnabled(false);
                        e2ev.setEnabled(false);
                        

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "7");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "8");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }
                    case 4: {
                        pst.setString(1, "5");

                        lp.setEnabled(true); 
                        p.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1p.setEnabled(true); 
                        e2p.setEnabled(true); 
                

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfm.setEnabled(true);
                        mfm.setEnabled(true);
                        e1m.setEnabled(true);
                        e2m.setEnabled(true);
                      

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e2o.setEnabled(true);
                       

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e2ef.setEnabled(true);
                        

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        nfcn.setEnabled(true);
                        mfcn.setEnabled(true);
                        e1cn.setEnabled(true);
                        e2cn.setEnabled(true);
                        

                        lcs.setEnabled(true);
                        cs.setEnabled(true);
                        nfcs.setEnabled(true);
                        mfcs.setEnabled(true);
                        e1cs.setEnabled(true);
                        e2cs.setEnabled(true);
                        

                        li.setEnabled(true);
                        i.setEnabled(true);
                        nfi.setEnabled(true);
                        mfi.setEnabled(true);
                        e1i.setEnabled(true);
                        e2i.setEnabled(true);
                       

                        lem.setEnabled(true);
                        em.setEnabled(true);
                        nfem.setEnabled(true);
                        mfem.setEnabled(true);
                        e1em.setEnabled(true);
                        e2em.setEnabled(true);
                       

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        nfemc.setEnabled(false);
                        mfemc.setEnabled(false);
                        e1emc.setEnabled(false);
                        e2emc.setEnabled(false);
                        
                        
                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        nfev.setEnabled(false);
                        mfev.setEnabled(false);
                        e1ev.setEnabled(false);
                        e2ev.setEnabled(false);
                       

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "9");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "10");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }
                    case 5: {
                        pst.setString(1, "6");

                        lp.setEnabled(true);
                        p.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1p.setEnabled(true);
                        e2p.setEnabled(true);
                        

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfm.setEnabled(true);
                        mfm.setEnabled(true);
                        e1m.setEnabled(true);
                        e2m.setEnabled(true);
                        

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e2o.setEnabled(true);
                       

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e2ef.setEnabled(true);
                       

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        nfcn.setEnabled(true);
                        mfcn.setEnabled(true);
                        e1cn.setEnabled(true);
                        e2cn.setEnabled(true);
                        

                        lcs.setEnabled(true);
                        cs.setEnabled(true);
                        nfcs.setEnabled(true);
                        mfcs.setEnabled(true);
                        e1cs.setEnabled(true);
                        e2cs.setEnabled(true);
                      

                        li.setEnabled(true);
                        i.setEnabled(true);
                        nfi.setEnabled(true);
                        mfi.setEnabled(true);
                        e1i.setEnabled(true);
                        e2i.setEnabled(true);
                       

                        lem.setEnabled(true);
                        em.setEnabled(true);
                        nfem.setEnabled(true);
                        mfem.setEnabled(true);
                        e1em.setEnabled(true);
                        e2em.setEnabled(true);
                        

                        lemc.setEnabled(true);
                        emc.setEnabled(true);
                        nfemc.setEnabled(true);
                        mfemc.setEnabled(true);
                        e1emc.setEnabled(true);
                        e2emc.setEnabled(true);
                       

                        lev.setEnabled(true);
                        ev.setEnabled(true);
                        nfev.setEnabled(true);
                        mfev.setEnabled(true);
                        e1ev.setEnabled(true);
                        e2ev.setEnabled(true);
                        

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "11");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "12");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }
                    case 6: {
                        pst.setString(1, "7");

                        lp.setEnabled(true);
                        p.setEnabled(true);
                        nfp.setEnabled(true);
                        mfp.setEnabled(true);
                        e1p.setEnabled(true);
                        e2p.setEnabled(true);
                        

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        nfm.setEnabled(true);
                        mfm.setEnabled(true);
                        e1m.setEnabled(true);
                        e2m.setEnabled(true);
                        

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        nfo.setEnabled(true);
                        mfo.setEnabled(true);
                        e1o.setEnabled(true);
                        e2o.setEnabled(true);
                       

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        nfef.setEnabled(true);
                        mfef.setEnabled(true);
                        e1ef.setEnabled(true);
                        e2ef.setEnabled(true);
                       

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        nfcn.setEnabled(true);
                        mfcn.setEnabled(true);
                        e1cn.setEnabled(true);
                        e2cn.setEnabled(true);
                        

                        lcs.setEnabled(true);
                        cs.setEnabled(true);
                        nfcs.setEnabled(true);
                        mfcs.setEnabled(true);
                        e1cs.setEnabled(true);
                        e2cs.setEnabled(true);
                      

                        li.setEnabled(true);
                        i.setEnabled(true);
                        nfi.setEnabled(true);
                        mfi.setEnabled(true);
                        e1i.setEnabled(true);
                        e2i.setEnabled(true);
                       

                        lem.setEnabled(true);
                        em.setEnabled(true);
                        nfem.setEnabled(true);
                        mfem.setEnabled(true);
                        e1em.setEnabled(true);
                        e2em.setEnabled(true);
                        

                        lemc.setEnabled(true);
                        emc.setEnabled(true);
                        nfemc.setEnabled(true);
                        mfemc.setEnabled(true);
                        e1emc.setEnabled(true);
                        e2emc.setEnabled(true);
                       

                        lev.setEnabled(true);
                        ev.setEnabled(true);
                        nfev.setEnabled(true);
                        mfev.setEnabled(true);
                        e1ev.setEnabled(true);
                        e2ev.setEnabled(true);
                        

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "13");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "14");
                         }*/
                        //pst.setString(3, String.valueOf(TelaNotas.id));
                        break;
                    }

                }

                rs = pst.executeQuery();

                tblaluno.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

        }

    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbclasse = new javax.swing.JComboBox();
        cbturma = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblaluno = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        p = new javax.swing.JLabel();
        m = new javax.swing.JLabel();
        o = new javax.swing.JLabel();
        ef = new javax.swing.JLabel();
        cn = new javax.swing.JLabel();
        cs = new javax.swing.JLabel();
        i = new javax.swing.JLabel();
        em = new javax.swing.JLabel();
        emc = new javax.swing.JLabel();
        ev = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        nfp = new javax.swing.JTextField();
        nfm = new javax.swing.JTextField();
        nfo = new javax.swing.JTextField();
        nfef = new javax.swing.JTextField();
        nfcn = new javax.swing.JTextField();
        nfcs = new javax.swing.JTextField();
        nfi = new javax.swing.JTextField();
        nfem = new javax.swing.JTextField();
        nfemc = new javax.swing.JTextField();
        nfev = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        e1p = new javax.swing.JTextField();
        e1m = new javax.swing.JTextField();
        e1o = new javax.swing.JTextField();
        e1ef = new javax.swing.JTextField();
        e1cn = new javax.swing.JTextField();
        e1cs = new javax.swing.JTextField();
        e1i = new javax.swing.JTextField();
        e1em = new javax.swing.JTextField();
        e1emc = new javax.swing.JTextField();
        e1ev = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        e2p = new javax.swing.JTextField();
        e2m = new javax.swing.JTextField();
        e2o = new javax.swing.JTextField();
        e2ef = new javax.swing.JTextField();
        e2cn = new javax.swing.JTextField();
        e2cs = new javax.swing.JTextField();
        e2i = new javax.swing.JTextField();
        e2em = new javax.swing.JTextField();
        e2emc = new javax.swing.JTextField();
        e2ev = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        mfp = new javax.swing.JTextField();
        mfm = new javax.swing.JTextField();
        mfo = new javax.swing.JTextField();
        mfef = new javax.swing.JTextField();
        mfcn = new javax.swing.JTextField();
        mfcs = new javax.swing.JTextField();
        mfi = new javax.swing.JTextField();
        mfem = new javax.swing.JTextField();
        mfemc = new javax.swing.JTextField();
        mfev = new javax.swing.JTextField();
        lp = new javax.swing.JToggleButton();
        lm = new javax.swing.JToggleButton();
        lef = new javax.swing.JToggleButton();
        lo = new javax.swing.JToggleButton();
        lcn = new javax.swing.JToggleButton();
        li = new javax.swing.JToggleButton();
        lcs = new javax.swing.JToggleButton();
        lem = new javax.swing.JToggleButton();
        lemc = new javax.swing.JToggleButton();
        lev = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbano = new javax.swing.JComboBox();
        cbtrimestre = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Exames");
        setPreferredSize(new java.awt.Dimension(761, 550));

        jLabel1.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Classe");
        jLabel1.setPreferredSize(new java.awt.Dimension(80, 30));

        jLabel2.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Turma");
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 30));

        cbclasse.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbclasse.setForeground(new java.awt.Color(255, 153, 51));
        cbclasse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "     1ª", "     2ª", "     3ª", "     4ª", "     5ª", "     6ª", "     7ª" }));
        cbclasse.setPreferredSize(new java.awt.Dimension(80, 30));
        cbclasse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbclasseItemStateChanged(evt);
            }
        });

        cbturma.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbturma.setForeground(new java.awt.Color(255, 153, 51));
        cbturma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "     A", "     B" }));
        cbturma.setPreferredSize(new java.awt.Dimension(80, 30));
        cbturma.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbturmaItemStateChanged(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblaluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblaluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblaluno);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Disciplinas");
        jLabel6.setPreferredSize(new java.awt.Dimension(92, 25));

        p.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        p.setForeground(new java.awt.Color(0, 102, 204));
        p.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p.setText("Português");
        p.setPreferredSize(new java.awt.Dimension(92, 25));

        m.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        m.setForeground(new java.awt.Color(0, 102, 204));
        m.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m.setText("Matemática");
        m.setPreferredSize(new java.awt.Dimension(92, 25));

        o.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        o.setForeground(new java.awt.Color(0, 102, 204));
        o.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        o.setText("Ofícios");
        o.setPreferredSize(new java.awt.Dimension(92, 25));

        ef.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        ef.setForeground(new java.awt.Color(0, 102, 204));
        ef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ef.setText("E. Física");
        ef.setPreferredSize(new java.awt.Dimension(92, 25));

        cn.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cn.setForeground(new java.awt.Color(0, 102, 204));
        cn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cn.setText("C. Naturais");
        cn.setPreferredSize(new java.awt.Dimension(92, 25));

        cs.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cs.setForeground(new java.awt.Color(0, 102, 204));
        cs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cs.setText("C. Sociais");
        cs.setPreferredSize(new java.awt.Dimension(92, 25));

        i.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        i.setForeground(new java.awt.Color(0, 102, 204));
        i.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        i.setText("Inglês");
        i.setPreferredSize(new java.awt.Dimension(92, 25));

        em.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        em.setForeground(new java.awt.Color(0, 102, 204));
        em.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        em.setText("E. Musical");
        em.setPreferredSize(new java.awt.Dimension(92, 25));

        emc.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        emc.setForeground(new java.awt.Color(0, 102, 204));
        emc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emc.setText("E. M. Cívica");
        emc.setPreferredSize(new java.awt.Dimension(92, 25));

        ev.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        ev.setForeground(new java.awt.Color(0, 102, 204));
        ev.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ev.setText("E. Visual");
        ev.setPreferredSize(new java.awt.Dimension(92, 25));

        jLabel17.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 204));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("N. F");
        jLabel17.setPreferredSize(new java.awt.Dimension(52, 25));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        nfp.setEditable(false);
        nfp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nfp.setPreferredSize(new java.awt.Dimension(52, 25));

        nfm.setEditable(false);
        nfm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nfm.setPreferredSize(new java.awt.Dimension(52, 25));

        nfo.setEditable(false);
        nfo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nfo.setPreferredSize(new java.awt.Dimension(52, 25));

        nfef.setEditable(false);
        nfef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nfef.setPreferredSize(new java.awt.Dimension(52, 25));

        nfcn.setEditable(false);
        nfcn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nfcn.setPreferredSize(new java.awt.Dimension(52, 25));

        nfcs.setEditable(false);
        nfcs.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nfcs.setPreferredSize(new java.awt.Dimension(52, 25));

        nfi.setEditable(false);
        nfi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nfi.setPreferredSize(new java.awt.Dimension(52, 25));

        nfem.setEditable(false);
        nfem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nfem.setPreferredSize(new java.awt.Dimension(52, 25));

        nfemc.setEditable(false);
        nfemc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nfemc.setPreferredSize(new java.awt.Dimension(52, 25));

        nfev.setEditable(false);
        nfev.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nfev.setPreferredSize(new java.awt.Dimension(52, 25));

        jLabel18.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 204));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("1ª e.");

        e1p.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e1p.setPreferredSize(new java.awt.Dimension(52, 25));

        e1m.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e1m.setPreferredSize(new java.awt.Dimension(52, 25));

        e1o.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e1o.setPreferredSize(new java.awt.Dimension(52, 25));

        e1ef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e1ef.setPreferredSize(new java.awt.Dimension(52, 25));

        e1cn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e1cn.setPreferredSize(new java.awt.Dimension(52, 25));

        e1cs.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e1cs.setPreferredSize(new java.awt.Dimension(52, 25));

        e1i.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e1i.setPreferredSize(new java.awt.Dimension(52, 25));

        e1em.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e1em.setPreferredSize(new java.awt.Dimension(52, 25));

        e1emc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e1emc.setPreferredSize(new java.awt.Dimension(52, 25));

        e1ev.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e1ev.setPreferredSize(new java.awt.Dimension(52, 25));

        jLabel19.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("2ª e.");

        e2p.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e2p.setPreferredSize(new java.awt.Dimension(52, 25));

        e2m.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e2m.setPreferredSize(new java.awt.Dimension(52, 25));

        e2o.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e2o.setPreferredSize(new java.awt.Dimension(52, 25));

        e2ef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e2ef.setPreferredSize(new java.awt.Dimension(52, 25));

        e2cn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e2cn.setPreferredSize(new java.awt.Dimension(52, 25));

        e2cs.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e2cs.setPreferredSize(new java.awt.Dimension(52, 25));

        e2i.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e2i.setPreferredSize(new java.awt.Dimension(52, 25));

        e2em.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e2em.setPreferredSize(new java.awt.Dimension(52, 25));

        e2emc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e2emc.setPreferredSize(new java.awt.Dimension(52, 25));

        e2ev.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        e2ev.setPreferredSize(new java.awt.Dimension(52, 25));

        jLabel20.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 102, 204));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("M. F");
        jLabel20.setPreferredSize(new java.awt.Dimension(52, 25));

        mfp.setEditable(false);
        mfp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mfp.setPreferredSize(new java.awt.Dimension(52, 25));

        mfm.setEditable(false);
        mfm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mfm.setPreferredSize(new java.awt.Dimension(52, 25));

        mfo.setEditable(false);
        mfo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mfo.setPreferredSize(new java.awt.Dimension(52, 25));

        mfef.setEditable(false);
        mfef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mfef.setPreferredSize(new java.awt.Dimension(52, 25));

        mfcn.setEditable(false);
        mfcn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mfcn.setPreferredSize(new java.awt.Dimension(52, 25));

        mfcs.setEditable(false);
        mfcs.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mfcs.setPreferredSize(new java.awt.Dimension(52, 25));

        mfi.setEditable(false);
        mfi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mfi.setPreferredSize(new java.awt.Dimension(52, 25));

        mfem.setEditable(false);
        mfem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mfem.setPreferredSize(new java.awt.Dimension(52, 25));

        mfemc.setEditable(false);
        mfemc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mfemc.setPreferredSize(new java.awt.Dimension(52, 25));

        mfev.setEditable(false);
        mfev.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mfev.setPreferredSize(new java.awt.Dimension(52, 25));

        lp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N

        lm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N

        lef.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        lef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lefActionPerformed(evt);
            }
        });

        lo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N

        lcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N

        li.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N

        lcs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N

        lem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N

        lemc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N

        lev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(p, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ef, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(o, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(emc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(em, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(i, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(m, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nfemc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nfp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nfm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nfef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nfcn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nfcs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nfi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nfem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nfev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(e1emc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e1p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e1m, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e1o, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e1ef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e1cn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e1cs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e1i, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e1em, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e1ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(e2emc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e2p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e2m, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e2o, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e2ef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e2cn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e2cs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e2i, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e2em, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e2ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mfemc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mfp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mfm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mfef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mfcn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mfcs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mfi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mfem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mfev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lev, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lemc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(li, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcs, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lcn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lef, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lm, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mfp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lp))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lm)
                                    .addComponent(mfm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lo, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mfef, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lef, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mfcn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lcn, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mfcs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lcs, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mfi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(li, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mfem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lem, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mfemc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lemc, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mfev, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lev, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(e2p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(e2m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(e2o, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(e2ef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(e2cn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(e2cs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(e2i, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(e2em, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(e2emc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(e2ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(nfp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nfm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nfef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nfcn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nfcs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nfi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nfem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nfemc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nfev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(e1p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(e1m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(e1o, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(e1ef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(e1cn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(e1cs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(e1i, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(e1em, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(e1emc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(e1ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(o, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(i, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(em, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Ano Lectivo");
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 30));

        jLabel4.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Trimestre");
        jLabel4.setPreferredSize(new java.awt.Dimension(80, 30));

        cbano.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbano.setForeground(new java.awt.Color(255, 153, 51));
        cbano.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2022", "2023", "2024", "2025", "2026" }));
        cbano.setPreferredSize(new java.awt.Dimension(80, 30));
        cbano.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbanoItemStateChanged(evt);
            }
        });

        cbtrimestre.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbtrimestre.setForeground(new java.awt.Color(255, 153, 51));
        cbtrimestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        cbtrimestre.setPreferredSize(new java.awt.Dimension(80, 30));
        cbtrimestre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbtrimestreItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("NOTAS DOS EXAMES");
        jLabel5.setPreferredSize(new java.awt.Dimension(80, 30));

        jSeparator1.setForeground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbclasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbturma, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbtrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbclasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbturma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbtrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lefActionPerformed

    private void cbclasseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbclasseItemStateChanged
        // TODO add your handling code here:
        alunos();
    }//GEN-LAST:event_cbclasseItemStateChanged

    private void cbturmaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbturmaItemStateChanged
        // TODO add your handling code here:
        alunos();
    }//GEN-LAST:event_cbturmaItemStateChanged

    private void cbanoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbanoItemStateChanged
        // TODO add your handling code here:
        alunos();
    }//GEN-LAST:event_cbanoItemStateChanged

    private void cbtrimestreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbtrimestreItemStateChanged
        // TODO add your handling code here:
        //alunos();
    }//GEN-LAST:event_cbtrimestreItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbano;
    private javax.swing.JComboBox cbclasse;
    private javax.swing.JComboBox cbtrimestre;
    private javax.swing.JComboBox cbturma;
    private javax.swing.JLabel cn;
    private javax.swing.JLabel cs;
    private javax.swing.JTextField e1cn;
    private javax.swing.JTextField e1cs;
    private javax.swing.JTextField e1ef;
    private javax.swing.JTextField e1em;
    private javax.swing.JTextField e1emc;
    private javax.swing.JTextField e1ev;
    private javax.swing.JTextField e1i;
    private javax.swing.JTextField e1m;
    private javax.swing.JTextField e1o;
    private javax.swing.JTextField e1p;
    private javax.swing.JTextField e2cn;
    private javax.swing.JTextField e2cs;
    private javax.swing.JTextField e2ef;
    private javax.swing.JTextField e2em;
    private javax.swing.JTextField e2emc;
    private javax.swing.JTextField e2ev;
    private javax.swing.JTextField e2i;
    private javax.swing.JTextField e2m;
    private javax.swing.JTextField e2o;
    private javax.swing.JTextField e2p;
    private javax.swing.JLabel ef;
    private javax.swing.JLabel em;
    private javax.swing.JLabel emc;
    private javax.swing.JLabel ev;
    private javax.swing.JLabel i;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToggleButton lcn;
    private javax.swing.JToggleButton lcs;
    private javax.swing.JToggleButton lef;
    private javax.swing.JToggleButton lem;
    private javax.swing.JToggleButton lemc;
    private javax.swing.JToggleButton lev;
    private javax.swing.JToggleButton li;
    private javax.swing.JToggleButton lm;
    private javax.swing.JToggleButton lo;
    private javax.swing.JToggleButton lp;
    private javax.swing.JLabel m;
    private javax.swing.JTextField mfcn;
    private javax.swing.JTextField mfcs;
    private javax.swing.JTextField mfef;
    private javax.swing.JTextField mfem;
    private javax.swing.JTextField mfemc;
    private javax.swing.JTextField mfev;
    private javax.swing.JTextField mfi;
    private javax.swing.JTextField mfm;
    private javax.swing.JTextField mfo;
    private javax.swing.JTextField mfp;
    private javax.swing.JTextField nfcn;
    private javax.swing.JTextField nfcs;
    private javax.swing.JTextField nfef;
    private javax.swing.JTextField nfem;
    private javax.swing.JTextField nfemc;
    private javax.swing.JTextField nfev;
    private javax.swing.JTextField nfi;
    private javax.swing.JTextField nfm;
    private javax.swing.JTextField nfo;
    private javax.swing.JTextField nfp;
    private javax.swing.JLabel o;
    private javax.swing.JLabel p;
    private javax.swing.JTable tblaluno;
    // End of variables declaration//GEN-END:variables
}
