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
public class TelaNotas extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaNotas
     */
    private static int ClasseT;
    private static int id;

    public TelaNotas(int num, int id) {
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
            TelaNotas.id = id;

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
                System.out.println(e);
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
                        p1.setEnabled(true); //p1 - nota1 de Português
                        p2.setEnabled(true); //p2 - nota2 de Português
                        pt.setEnabled(true); //pt - trabalho de Português
                        pm.setEnabled(true); //pt - média de Português

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(false);
                        cn.setEnabled(false);
                        cn1.setEnabled(false);
                        cn2.setEnabled(false);
                        cnt.setEnabled(false);
                        cnm.setEnabled(false);

                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        cs1.setEnabled(false);
                        cs2.setEnabled(false);
                        cst.setEnabled(false);
                        csm.setEnabled(false);

                        li.setEnabled(false);
                        i.setEnabled(false);
                        i1.setEnabled(false);
                        i2.setEnabled(false);
                        it.setEnabled(false);
                        im.setEnabled(false);

                        lem.setEnabled(false);
                        em.setEnabled(false);
                        em1.setEnabled(false);
                        em2.setEnabled(false);
                        emt.setEnabled(false);
                        emm.setEnabled(false);

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        emc1.setEnabled(false);
                        emc2.setEnabled(false);
                        emct.setEnabled(false);
                        emcm.setEnabled(false);

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        ev1.setEnabled(false);
                        evt.setEnabled(false);
                        ev2.setEnabled(false);
                        evm.setEnabled(false);

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

                        lp.setEnabled(true); //lp - botão lançarPortuguês 
                        p.setEnabled(true);  // p - JLabel Português 
                        p1.setEnabled(true); //p1 - nota1 de Português
                        p2.setEnabled(true); //p2 - nota2 de Português
                        pt.setEnabled(true); //pt - trabalho de Português
                        pm.setEnabled(true); //pt - média de Português

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(false);
                        cn.setEnabled(false);
                        cn1.setEnabled(false);
                        cn2.setEnabled(false);
                        cnt.setEnabled(false);
                        cnm.setEnabled(false);

                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        cs1.setEnabled(false);
                        cs2.setEnabled(false);
                        cst.setEnabled(false);
                        csm.setEnabled(false);

                        li.setEnabled(false);
                        i.setEnabled(false);
                        i1.setEnabled(false);
                        i2.setEnabled(false);
                        it.setEnabled(false);
                        im.setEnabled(false);

                        lem.setEnabled(false);
                        em.setEnabled(false);
                        em1.setEnabled(false);
                        em2.setEnabled(false);
                        emt.setEnabled(false);
                        emm.setEnabled(false);

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        emc1.setEnabled(false);
                        emc2.setEnabled(false);
                        emct.setEnabled(false);
                        emcm.setEnabled(false);

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        ev1.setEnabled(false);
                        evt.setEnabled(false);
                        ev2.setEnabled(false);
                        evm.setEnabled(false);

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

                        lp.setEnabled(true); //lp - botão lançarPortuguês 
                        p.setEnabled(true);  // p - JLabel Português 
                        p1.setEnabled(true); //p1 - nota1 de Português
                        p2.setEnabled(true); //p2 - nota2 de Português
                        pt.setEnabled(true); //pt - trabalho de Português
                        pm.setEnabled(true); //pt - média de Português

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        cn1.setEnabled(true);
                        cn2.setEnabled(true);
                        cnt.setEnabled(true);
                        cnm.setEnabled(true);

                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        cs1.setEnabled(false);
                        cs2.setEnabled(false);
                        cst.setEnabled(false);
                        csm.setEnabled(false);

                        li.setEnabled(false);
                        i.setEnabled(false);
                        i1.setEnabled(false);
                        i2.setEnabled(false);
                        it.setEnabled(false);
                        im.setEnabled(false);

                        lem.setEnabled(false);
                        em.setEnabled(false);
                        em1.setEnabled(false);
                        em2.setEnabled(false);
                        emt.setEnabled(false);
                        emm.setEnabled(false);

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        emc1.setEnabled(false);
                        emc2.setEnabled(false);
                        emct.setEnabled(false);
                        emcm.setEnabled(false);

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        ev1.setEnabled(false);
                        evt.setEnabled(false);
                        ev2.setEnabled(false);
                        evm.setEnabled(false);

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

                        lp.setEnabled(true); //lp - botão lançarPortuguês 
                        p.setEnabled(true);  // p - JLabel Português 
                        p1.setEnabled(true); //p1 - nota1 de Português
                        p2.setEnabled(true); //p2 - nota2 de Português
                        pt.setEnabled(true); //pt - trabalho de Português
                        pm.setEnabled(true); //pt - média de Português

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        cn1.setEnabled(true);
                        cn2.setEnabled(true);
                        cnt.setEnabled(true);
                        cnm.setEnabled(true);

                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        cs1.setEnabled(false);
                        cs2.setEnabled(false);
                        cst.setEnabled(false);
                        csm.setEnabled(false);

                        li.setEnabled(false);
                        i.setEnabled(false);
                        i1.setEnabled(false);
                        i2.setEnabled(false);
                        it.setEnabled(false);
                        im.setEnabled(false);

                        lem.setEnabled(false);
                        em.setEnabled(false);
                        em1.setEnabled(false);
                        em2.setEnabled(false);
                        emt.setEnabled(false);
                        emm.setEnabled(false);

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        emc1.setEnabled(false);
                        emc2.setEnabled(false);
                        emct.setEnabled(false);
                        emcm.setEnabled(false);

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        ev1.setEnabled(false);
                        evt.setEnabled(false);
                        ev2.setEnabled(false);
                        evm.setEnabled(false);

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

                        lp.setEnabled(true); //lp - botão lançarPortuguês 
                        p.setEnabled(true);  // p - JLabel Português 
                        p1.setEnabled(true); //p1 - nota1 de Português
                        p2.setEnabled(true); //p2 - nota2 de Português
                        pt.setEnabled(true); //pt - trabalho de Português
                        pm.setEnabled(true); //pt - média de Português

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        cn1.setEnabled(true);
                        cn2.setEnabled(true);
                        cnt.setEnabled(true);
                        cnm.setEnabled(true);

                        lcs.setEnabled(true);
                        cs.setEnabled(true);
                        cs1.setEnabled(true);
                        cs2.setEnabled(true);
                        cst.setEnabled(true);
                        csm.setEnabled(true);

                        li.setEnabled(true);
                        i.setEnabled(true);
                        i1.setEnabled(true);
                        i2.setEnabled(true);
                        it.setEnabled(true);
                        im.setEnabled(true);

                        lem.setEnabled(true);
                        em.setEnabled(true);
                        em1.setEnabled(true);
                        em2.setEnabled(true);
                        emt.setEnabled(true);
                        emm.setEnabled(true);

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        emc1.setEnabled(false);
                        emc2.setEnabled(false);
                        emct.setEnabled(false);
                        emcm.setEnabled(false);

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        ev1.setEnabled(false);
                        evt.setEnabled(false);
                        ev2.setEnabled(false);
                        evm.setEnabled(false);

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
                        p1.setEnabled(true);
                        p2.setEnabled(true);
                        pt.setEnabled(true);
                        pm.setEnabled(true);

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        cn1.setEnabled(true);
                        cn2.setEnabled(true);
                        cnt.setEnabled(true);
                        cnm.setEnabled(true);

                        lcs.setEnabled(true);
                        cs.setEnabled(true);
                        cs1.setEnabled(true);
                        cs2.setEnabled(true);
                        cst.setEnabled(true);
                        csm.setEnabled(true);

                        li.setEnabled(true);
                        i.setEnabled(true);
                        i1.setEnabled(true);
                        i2.setEnabled(true);
                        it.setEnabled(true);
                        im.setEnabled(true);

                        lem.setEnabled(true);
                        em.setEnabled(true);
                        em1.setEnabled(true);
                        em2.setEnabled(true);
                        emt.setEnabled(true);
                        emm.setEnabled(true);

                        lemc.setEnabled(true);
                        emc.setEnabled(true);
                        emc1.setEnabled(true);
                        emc2.setEnabled(true);
                        emct.setEnabled(true);
                        emcm.setEnabled(true);

                        lev.setEnabled(true);
                        ev.setEnabled(true);
                        ev1.setEnabled(true);
                        evt.setEnabled(true);
                        ev2.setEnabled(true);
                        evm.setEnabled(true);

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
                        p1.setEnabled(true);
                        p2.setEnabled(true);
                        pt.setEnabled(true);
                        pm.setEnabled(true);

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        cn1.setEnabled(true);
                        cn2.setEnabled(true);
                        cnt.setEnabled(true);
                        cnm.setEnabled(true);

                        lcs.setEnabled(true);
                        cs.setEnabled(true);
                        cs1.setEnabled(true);
                        cs2.setEnabled(true);
                        cst.setEnabled(true);
                        csm.setEnabled(true);

                        li.setEnabled(true);
                        i.setEnabled(true);
                        i1.setEnabled(true);
                        i2.setEnabled(true);
                        it.setEnabled(true);
                        im.setEnabled(true);

                        lem.setEnabled(true);
                        em.setEnabled(true);
                        em1.setEnabled(true);
                        em2.setEnabled(true);
                        emt.setEnabled(true);
                        emm.setEnabled(true);

                        lemc.setEnabled(true);
                        emc.setEnabled(true);
                        emc1.setEnabled(true);
                        emc2.setEnabled(true);
                        emct.setEnabled(true);
                        emcm.setEnabled(true);

                        lev.setEnabled(true);
                        ev.setEnabled(true);
                        ev1.setEnabled(true);
                        evt.setEnabled(true);
                        ev2.setEnabled(true);
                        evm.setEnabled(true);

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
                    System.out.println(rs.getString("id"));
                }

            } catch (Exception e) {
                System.out.println(e);
            }

            try {
                pst = conexao.prepareStatement(aluno);
                pst.setString(2, tu_id);
                switch (cbclasse.getSelectedIndex()) {
                    case 0: {
                        pst.setString(1, "1");

                        lp.setEnabled(true); //lp - botão lançarPortuguês 
                        p.setEnabled(true);  // p - JLabel Português 
                        p1.setEnabled(true); //p1 - nota1 de Português
                        p2.setEnabled(true); //p2 - nota2 de Português
                        pt.setEnabled(true); //pt - trabalho de Português
                        pm.setEnabled(true); //pt - média de Português

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(false);
                        cn.setEnabled(false);
                        cn1.setEnabled(false);
                        cn2.setEnabled(false);
                        cnt.setEnabled(false);
                        cnm.setEnabled(false);

                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        cs1.setEnabled(false);
                        cs2.setEnabled(false);
                        cst.setEnabled(false);
                        csm.setEnabled(false);

                        li.setEnabled(false);
                        i.setEnabled(false);
                        i1.setEnabled(false);
                        i2.setEnabled(false);
                        it.setEnabled(false);
                        im.setEnabled(false);

                        lem.setEnabled(false);
                        em.setEnabled(false);
                        em1.setEnabled(false);
                        em2.setEnabled(false);
                        emt.setEnabled(false);
                        emm.setEnabled(false);

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        emc1.setEnabled(false);
                        emc2.setEnabled(false);
                        emct.setEnabled(false);
                        emcm.setEnabled(false);

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        ev1.setEnabled(false);
                        evt.setEnabled(false);
                        ev2.setEnabled(false);
                        evm.setEnabled(false);

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "1");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "2");
                         }*/
                        break;
                    }
                    case 1: {
                        pst.setString(1, "2");

                        lp.setEnabled(true); //lp - botão lançarPortuguês 
                        p.setEnabled(true);  // p - JLabel Português 
                        p1.setEnabled(true); //p1 - nota1 de Português
                        p2.setEnabled(true); //p2 - nota2 de Português
                        pt.setEnabled(true); //pt - trabalho de Português
                        pm.setEnabled(true); //pt - média de Português

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(false);
                        cn.setEnabled(false);
                        cn1.setEnabled(false);
                        cn2.setEnabled(false);
                        cnt.setEnabled(false);
                        cnm.setEnabled(false);

                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        cs1.setEnabled(false);
                        cs2.setEnabled(false);
                        cst.setEnabled(false);
                        csm.setEnabled(false);

                        li.setEnabled(false);
                        i.setEnabled(false);
                        i1.setEnabled(false);
                        i2.setEnabled(false);
                        it.setEnabled(false);
                        im.setEnabled(false);

                        lem.setEnabled(false);
                        em.setEnabled(false);
                        em1.setEnabled(false);
                        em2.setEnabled(false);
                        emt.setEnabled(false);
                        emm.setEnabled(false);

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        emc1.setEnabled(false);
                        emc2.setEnabled(false);
                        emct.setEnabled(false);
                        emcm.setEnabled(false);

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        ev1.setEnabled(false);
                        evt.setEnabled(false);
                        ev2.setEnabled(false);
                        evm.setEnabled(false);

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "3");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "4");
                         }*/
                        break;
                    }
                    case 2: {
                        pst.setString(1, "3");

                        lp.setEnabled(true); //lp - botão lançarPortuguês 
                        p.setEnabled(true);  // p - JLabel Português 
                        p1.setEnabled(true); //p1 - nota1 de Português
                        p2.setEnabled(true); //p2 - nota2 de Português
                        pt.setEnabled(true); //pt - trabalho de Português
                        pm.setEnabled(true); //pt - média de Português

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        cn1.setEnabled(true);
                        cn2.setEnabled(true);
                        cnt.setEnabled(true);
                        cnm.setEnabled(true);

                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        cs1.setEnabled(false);
                        cs2.setEnabled(false);
                        cst.setEnabled(false);
                        csm.setEnabled(false);

                        li.setEnabled(false);
                        i.setEnabled(false);
                        i1.setEnabled(false);
                        i2.setEnabled(false);
                        it.setEnabled(false);
                        im.setEnabled(false);

                        lem.setEnabled(false);
                        em.setEnabled(false);
                        em1.setEnabled(false);
                        em2.setEnabled(false);
                        emt.setEnabled(false);
                        emm.setEnabled(false);

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        emc1.setEnabled(false);
                        emc2.setEnabled(false);
                        emct.setEnabled(false);
                        emcm.setEnabled(false);

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        ev1.setEnabled(false);
                        evt.setEnabled(false);
                        ev2.setEnabled(false);
                        evm.setEnabled(false);

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "5");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "6");
                         }*/
                        break;
                    }
                    case 3: {
                        pst.setString(1, "4");

                        lp.setEnabled(true); //lp - botão lançarPortuguês 
                        p.setEnabled(true);  // p - JLabel Português 
                        p1.setEnabled(true); //p1 - nota1 de Português
                        p2.setEnabled(true); //p2 - nota2 de Português
                        pt.setEnabled(true); //pt - trabalho de Português
                        pm.setEnabled(true); //pt - média de Português

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        cn1.setEnabled(true);
                        cn2.setEnabled(true);
                        cnt.setEnabled(true);
                        cnm.setEnabled(true);

                        lcs.setEnabled(false);
                        cs.setEnabled(false);
                        cs1.setEnabled(false);
                        cs2.setEnabled(false);
                        cst.setEnabled(false);
                        csm.setEnabled(false);

                        li.setEnabled(false);
                        i.setEnabled(false);
                        i1.setEnabled(false);
                        i2.setEnabled(false);
                        it.setEnabled(false);
                        im.setEnabled(false);

                        lem.setEnabled(false);
                        em.setEnabled(false);
                        em1.setEnabled(false);
                        em2.setEnabled(false);
                        emt.setEnabled(false);
                        emm.setEnabled(false);

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        emc1.setEnabled(false);
                        emc2.setEnabled(false);
                        emct.setEnabled(false);
                        emcm.setEnabled(false);

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        ev1.setEnabled(false);
                        evt.setEnabled(false);
                        ev2.setEnabled(false);
                        evm.setEnabled(false);

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "7");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "8");
                         }*/
                        break;
                    }
                    case 4: {
                        pst.setString(1, "5");

                        lp.setEnabled(true); //lp - botão lançarPortuguês 
                        p.setEnabled(true);  // p - JLabel Português 
                        p1.setEnabled(true); //p1 - nota1 de Português
                        p2.setEnabled(true); //p2 - nota2 de Português
                        pt.setEnabled(true); //pt - trabalho de Português
                        pm.setEnabled(true); //pt - média de Português

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        cn1.setEnabled(true);
                        cn2.setEnabled(true);
                        cnt.setEnabled(true);
                        cnm.setEnabled(true);

                        lcs.setEnabled(true);
                        cs.setEnabled(true);
                        cs1.setEnabled(true);
                        cs2.setEnabled(true);
                        cst.setEnabled(true);
                        csm.setEnabled(true);

                        li.setEnabled(true);
                        i.setEnabled(true);
                        i1.setEnabled(true);
                        i2.setEnabled(true);
                        it.setEnabled(true);
                        im.setEnabled(true);

                        lem.setEnabled(true);
                        em.setEnabled(true);
                        em1.setEnabled(true);
                        em2.setEnabled(true);
                        emt.setEnabled(true);
                        emm.setEnabled(true);

                        lemc.setEnabled(false);
                        emc.setEnabled(false);
                        emc1.setEnabled(false);
                        emc2.setEnabled(false);
                        emct.setEnabled(false);
                        emcm.setEnabled(false);

                        lev.setEnabled(false);
                        ev.setEnabled(false);
                        ev1.setEnabled(false);
                        evt.setEnabled(false);
                        ev2.setEnabled(false);
                        evm.setEnabled(false);

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "9");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "10");
                         }*/
                        break;
                    }
                    case 5: {
                        pst.setString(1, "6");

                        lp.setEnabled(true);
                        p.setEnabled(true);
                        p1.setEnabled(true);
                        p2.setEnabled(true);
                        pt.setEnabled(true);
                        pm.setEnabled(true);

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        cn1.setEnabled(true);
                        cn2.setEnabled(true);
                        cnt.setEnabled(true);
                        cnm.setEnabled(true);

                        lcs.setEnabled(true);
                        cs.setEnabled(true);
                        cs1.setEnabled(true);
                        cs2.setEnabled(true);
                        cst.setEnabled(true);
                        csm.setEnabled(true);

                        li.setEnabled(true);
                        i.setEnabled(true);
                        i1.setEnabled(true);
                        i2.setEnabled(true);
                        it.setEnabled(true);
                        im.setEnabled(true);

                        lem.setEnabled(true);
                        em.setEnabled(true);
                        em1.setEnabled(true);
                        em2.setEnabled(true);
                        emt.setEnabled(true);
                        emm.setEnabled(true);

                        lemc.setEnabled(true);
                        emc.setEnabled(true);
                        emc1.setEnabled(true);
                        emc2.setEnabled(true);
                        emct.setEnabled(true);
                        emcm.setEnabled(true);

                        lev.setEnabled(true);
                        ev.setEnabled(true);
                        ev1.setEnabled(true);
                        evt.setEnabled(true);
                        ev2.setEnabled(true);
                        evm.setEnabled(true);

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "11");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "12");
                         }*/
                        break;
                    }
                    case 6: {
                        pst.setString(1, "7");

                        lp.setEnabled(true);
                        p.setEnabled(true);
                        p1.setEnabled(true);
                        p2.setEnabled(true);
                        pt.setEnabled(true);
                        pm.setEnabled(true);

                        lm.setEnabled(true);
                        m.setEnabled(true);
                        m1.setEnabled(true);
                        m2.setEnabled(true);
                        mt.setEnabled(true);
                        mm.setEnabled(true);

                        lo.setEnabled(true);
                        o.setEnabled(true);
                        o1.setEnabled(true);
                        o2.setEnabled(true);
                        ot.setEnabled(true);
                        om.setEnabled(true);

                        lef.setEnabled(true);
                        ef.setEnabled(true);
                        ef1.setEnabled(true);
                        ef2.setEnabled(true);
                        eft.setEnabled(true);
                        efm.setEnabled(true);

                        lcn.setEnabled(true);
                        cn.setEnabled(true);
                        cn1.setEnabled(true);
                        cn2.setEnabled(true);
                        cnt.setEnabled(true);
                        cnm.setEnabled(true);

                        lcs.setEnabled(true);
                        cs.setEnabled(true);
                        cs1.setEnabled(true);
                        cs2.setEnabled(true);
                        cst.setEnabled(true);
                        csm.setEnabled(true);

                        li.setEnabled(true);
                        i.setEnabled(true);
                        i1.setEnabled(true);
                        i2.setEnabled(true);
                        it.setEnabled(true);
                        im.setEnabled(true);

                        lem.setEnabled(true);
                        em.setEnabled(true);
                        em1.setEnabled(true);
                        em2.setEnabled(true);
                        emt.setEnabled(true);
                        emm.setEnabled(true);

                        lemc.setEnabled(true);
                        emc.setEnabled(true);
                        emc1.setEnabled(true);
                        emc2.setEnabled(true);
                        emct.setEnabled(true);
                        emcm.setEnabled(true);

                        lev.setEnabled(true);
                        ev.setEnabled(true);
                        ev1.setEnabled(true);
                        evt.setEnabled(true);
                        ev2.setEnabled(true);
                        evm.setEnabled(true);

                        /*if (cbturma.getSelectedIndex() == 0) {
                         pst.setString(2, "13");
                         } else if (cbturma.getSelectedIndex() == 1) {
                         pst.setString(2, "14");
                         }*/
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

    private static DecimalFormat formato = new DecimalFormat("#.##");
    
    String sql_lancar_ind;
    
    private void lancar_ind(String id_cadeira) {
        String sql = "insert into nota(n1, n2, t, m, id_classe, id_turma, id_cadeira, id_aluno, id_trimestre) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        
        
        double n1, n2, t, m;
        int tabela = tblaluno.getSelectedRow();
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(7, id_cadeira);
            pst.setString(8, tblaluno.getModel().getValueAt(tabela, 0).toString());
            pst.setString(9, cbtrimestre.getSelectedItem().toString());
            
            switch (id_cadeira) {

                case "1": {
                    n1 = Double.parseDouble(p1.getText());
                    n2 = Double.parseDouble(p2.getText());
                    t = Double.parseDouble(pt.getText());
                    m = ((n1 * 0.3 + n2 * 0.3) + 0.4 * t);
                    
                    //m = formato(m);
                    pm.setText(String.valueOf(m));
                    pst.setString(4, String.valueOf(m));
                    pst.setString(1, String.valueOf(n1));
                    pst.setString(2, String.valueOf(n2));
                    pst.setString(3, String.valueOf(t));
                    break;
                }

                case "2": {
                    n1 = Double.parseDouble(m1.getText());
                    n2 = Double.parseDouble(m2.getText());
                    t = Double.parseDouble(mt.getText());
                    m = ((n1 * 0.3 + n2 * 0.3) + 0.4 * t);
                    mm.setText(String.valueOf(m));
                    pst.setString(4, String.valueOf(m));
                    pst.setString(1, String.valueOf(n1));
                    pst.setString(2, String.valueOf(n2));
                    pst.setString(3, String.valueOf(t));
                    break;
                }

                case "3": {
                    n1 = Double.parseDouble(o1.getText());
                    n2 = Double.parseDouble(o2.getText());
                    t = Double.parseDouble(ot.getText());
                    m = ((n1 * 0.3 + n2 * 0.3) + 0.4 * t);
                    om.setText(String.valueOf(m));
                    pst.setString(4, String.valueOf(m));
                    pst.setString(1, String.valueOf(n1));
                    pst.setString(2, String.valueOf(n2));
                    pst.setString(3, String.valueOf(t));
                    break;
                }

                case "4": {
                    n1 = Double.parseDouble(i1.getText());
                    n2 = Double.parseDouble(i2.getText());
                    t = Double.parseDouble(it.getText());
                    m = ((n1 * 0.3 + n2 * 0.3) + 0.4 * t);
                    im.setText(String.valueOf(m));
                    pst.setString(4, String.valueOf(m));
                    pst.setString(1, String.valueOf(n1));
                    pst.setString(2, String.valueOf(n2));
                    pst.setString(3, String.valueOf(t));
                    break;
                }

                case "5": {
                    n1 = Double.parseDouble(cn1.getText());
                    n2 = Double.parseDouble(cn2.getText());
                    t = Double.parseDouble(cnt.getText());
                    m = ((n1 * 0.3 + n2 * 0.3) + 0.4 * t);
                    cnm.setText(String.valueOf(m));
                    pst.setString(4, String.valueOf(m));
                    pst.setString(1, String.valueOf(n1));
                    pst.setString(2, String.valueOf(n2));
                    pst.setString(3, String.valueOf(t));
                    break;
                }

                case "6": {
                    n1 = Double.parseDouble(cs1.getText());
                    n2 = Double.parseDouble(cs2.getText());
                    t = Double.parseDouble(cst.getText());
                    m = ((n1 * 0.3 + n2 * 0.3) + 0.4 * t);
                    csm.setText(String.valueOf(m));
                    pst.setString(4, String.valueOf(m));
                    pst.setString(1, String.valueOf(n1));
                    pst.setString(2, String.valueOf(n2));
                    pst.setString(3, String.valueOf(t));
                    break;
                }

                case "7": {
                    n1 = Double.parseDouble(em1.getText());
                    n2 = Double.parseDouble(em2.getText());
                    t = Double.parseDouble(emt.getText());
                    m = ((n1 * 0.3 + n2 * 0.3) + 0.4 * t);
                    emm.setText(String.valueOf(m));
                    pst.setString(4, String.valueOf(m));
                    pst.setString(1, String.valueOf(n1));
                    pst.setString(2, String.valueOf(n2));
                    pst.setString(3, String.valueOf(t));
                    break;
                }

                case "8": {
                    n1 = Double.parseDouble(ev1.getText());
                    n2 = Double.parseDouble(ev2.getText());
                    t = Double.parseDouble(evt.getText());
                    pst.setString(1, String.valueOf(n1));
                    m = ((n1 * 0.3 + n2 * 0.3) + 0.4 * t);
                    evm.setText(String.valueOf(m));
                    pst.setString(4, String.valueOf(m));
                    pst.setString(2, String.valueOf(n2));
                    pst.setString(3, String.valueOf(t));
                    break;
                }

                case "9": {
                    n1 = Double.parseDouble(ef1.getText());
                    n2 = Double.parseDouble(ef2.getText());
                    t = Double.parseDouble(eft.getText());
                    m = ((n1 * 0.3 + n2 * 0.3) + 0.4 * t);
                    efm.setText(String.valueOf(m));
                    pst.setString(4, String.valueOf(m));
                    pst.setString(1, String.valueOf(n1));
                    pst.setString(2, String.valueOf(n2));
                    pst.setString(3, String.valueOf(t));
                    break;
                }

                case "10": {
                    n1 = Double.parseDouble(emc1.getText());
                    n2 = Double.parseDouble(emc2.getText());
                    t = Double.parseDouble(emct.getText());
                    m = ((n1 * 0.3 + n2 * 0.3) + 0.4 * t);
                    emcm.setText(String.valueOf(m));
                    pst.setString(4, String.valueOf(m));
                    pst.setString(1, String.valueOf(n1));
                    pst.setString(2, String.valueOf(n2));
                    pst.setString(3, String.valueOf(t));
                    break;
                }
            }

            if (cbclasse.getSelectedIndex() == 0) {
                pst.setString(5, "1");

                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "1");

                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "2");

                }
            } else if (cbclasse.getSelectedIndex() == 1) {
                pst.setString(5, "2");

                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "3");

                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "4");

                }
            } else if (cbclasse.getSelectedIndex() == 2) {
                pst.setString(5, "3");

                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "5");

                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "6");

                }
            } else if (cbclasse.getSelectedIndex() == 3) {
                pst.setString(5, "4");

                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "7");

                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "8");

                }
            } else if (cbclasse.getSelectedIndex() == 4) {
                pst.setString(5, "5");

                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "9");

                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "10");

                }
            } else if (cbclasse.getSelectedIndex() == 5) {
                pst.setString(5, "6");

                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "11");

                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "12");

                }
            } else if (cbclasse.getSelectedIndex() == 6) {
                pst.setString(5, "7");

                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "13");

                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "14");

                }
            }

            int lancar = JOptionPane.showConfirmDialog(rootPane, "Lançar nota? ", "Notas", JOptionPane.YES_NO_OPTION);

            if (lancar == JOptionPane.YES_OPTION) {
                int lancado = pst.executeUpdate();

                if (lancado > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Nota lançada com sucesso");
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, "Preencha os campos em branco! ");
        }

    }

    private void lancar_tudo() {
        String sql = "insert into nota(n1, n2, t, m, id_classe, id_turma, id_cadeira, id_aluno) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?),(?, ?, ?, ?, ?, ?, ?, ?)";

        /*if(cbclasse.getSelectedIndex()==0 || cbclasse.getSelectedIndex()==1){
         sql = "insert into nota(n1, n2, t, m, id_classe, id_turma, id_cadeira, id_aluno) "
         + "values
         (?, ?, ?, ?, ?, ?, ?, ?),
         (?, ?, ?, ?, ?, ?, ?, ?),
         (?, ?, ?, ?, ?, ?, ?, ?),
         (?, ?, ?, ?, ?, ?, ?, ?)";
        
         }*/
        int tabela = tblaluno.getSelectedRow();
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, p1.getText());
            pst.setString(2, p2.getText());
            pst.setString(3, pt.getText());

            pst.setString(9, m1.getText());
            pst.setString(10, m2.getText());
            pst.setString(11, mt.getText());

            int pt1 = Integer.parseInt(p1.getText());
            int pt2 = Integer.parseInt(p2.getText());
            int ptr = Integer.parseInt(pt.getText());

            double pmdoub = pt1 * 0.3 + pt2 * 0.3 + ptr * 0.4;
            String pmstr = String.valueOf(pmdoub);

            int pmint = Integer.parseInt(pmstr);

            int mt1 = Integer.parseInt(m1.getText());
            int mt2 = Integer.parseInt(m2.getText());
            int mtr = Integer.parseInt(mt.getText());

            double mmdoub = mt1 * 0.3 + mt2 * 0.3 + mtr * 0.4;
            String mmstr = String.valueOf(mmdoub);

            int mmint = Integer.parseInt(mmstr);
            pst.setString(4, String.valueOf(pmint));
            pst.setString(12, String.valueOf(mmint));

            if (cbclasse.getSelectedIndex() == 0) {
                pst.setString(5, "1");
                pst.setString(13, "1");
                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "1");
                    pst.setString(14, "1");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "2");
                    pst.setString(14, "2");
                }
            } else if (cbclasse.getSelectedIndex() == 1) {
                pst.setString(5, "2");
                pst.setString(13, "2");
                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "3");
                    pst.setString(14, "3");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "4");
                    pst.setString(14, "4");
                }
            } else if (cbclasse.getSelectedIndex() == 2) {
                pst.setString(5, "3");
                pst.setString(13, "3");
                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "5");
                    pst.setString(14, "5");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "6");
                    pst.setString(14, "6");
                }
            } else if (cbclasse.getSelectedIndex() == 3) {
                pst.setString(5, "4");
                pst.setString(13, "4");
                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "7");
                    pst.setString(14, "7");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "8");
                    pst.setString(14, "8");
                }
            } else if (cbclasse.getSelectedIndex() == 4) {
                pst.setString(5, "5");
                pst.setString(13, "5");
                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "9");
                    pst.setString(14, "9");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "10");
                    pst.setString(14, "10");
                }
            } else if (cbclasse.getSelectedIndex() == 5) {
                pst.setString(5, "6");
                pst.setString(13, "6");
                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "11");
                    pst.setString(14, "11");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "12");
                    pst.setString(14, "11");
                }
            } else if (cbclasse.getSelectedIndex() == 6) {
                pst.setString(5, "7");
                pst.setString(13, "7");
                if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(6, "13");
                    pst.setString(14, "13");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(6, "14");
                    pst.setString(14, "14");
                }
            }
            pst.setString(7, "1");
            pst.setString(15, "2");
            pst.setString(8, tblaluno.getModel().getValueAt(tabela, 0).toString());
            pst.setString(16, tblaluno.getModel().getValueAt(tabela, 0).toString());

            int lancar = JOptionPane.showConfirmDialog(rootPane, "Lançar nota? ", "Notas", JOptionPane.YES_NO_OPTION);

            if (lancar == JOptionPane.YES_OPTION) {
                int lancado = pst.executeUpdate();

                if (lancado > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Nota lançada com sucesso");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    private double mediaf(String t1, String t2, String tr) {

        /*if(t1.isEmpty()){
         t1.setText(" ");
         }
        
         else if(p2.getText().isEmpty()){
         p2.setText(" ");
         }
        
         else if(pt.getText().isEmpty()){
         pt.setText(" ");
         }
         if(p1.getText() == " " && p2.getText() == " " && pt.getText() == " "){
         pm.setText(" ");
         }*/
        double m = Double.parseDouble(t1) * 0.3 + Double.parseDouble(t2) * 0.3 + Double.parseDouble(tr) * 0.4;

        return m;
    }
    private static String idaluno;

    private void mostrar_notas(/*String id_al, String id_cl, String id_tu*/) {

        int trimestre = Integer.parseInt(cbtrimestre.getSelectedItem().toString());

        String buscar = "select al.id as id_do_aluno, c.id as id_da_cadeira, n.n1, n.n2, n.t, n.m from nota n inner join classe cl on cl.id = n.id_classe inner join turma tu on tu.id = n.id_turma inner join cadeira c on c.id = n.id_cadeira inner join aluno al on al.id = n.id_aluno where id_aluno = ? and id_classe = ? and id_turma = ? and id_trimestre = ?";

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
            JOptionPane.showMessageDialog(rootPane, "TelaTurma, Método Alunos\n" + e);
            //System.out.println(e);
        }

        
        int id;

        try {
            id = tblaluno.getSelectedRow();
            idaluno = tblaluno.getModel().getValueAt(id, 0).toString();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(rootPane, "Selecione a classe");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

        /*
         int id = tblaluno.getSelectedRow();
         String idaluno = tblaluno.getModel().getValueAt(id, 0).toString();
         */
        try {
            pst = conexao.prepareStatement(buscar);

            pst.setString(1, idaluno);
            pst.setString(4, String.valueOf(trimestre));
            pst.setString(3, tu_id);
            if (cbclasse.getSelectedIndex() == 0) {
                pst.setString(2, "1");

                /*if (cbturma.getSelectedIndex() == 0) {
                 pst.setString(3, "1");

                 } else if (cbturma.getSelectedIndex() == 1) {
                 pst.setString(3, "2");

                 }*/
            } else if (cbclasse.getSelectedIndex() == 1) {
                pst.setString(2, "2");

                /*if (cbturma.getSelectedIndex() == 0) {
                 pst.setString(3, "3");

                 } else if (cbturma.getSelectedIndex() == 1) {
                 pst.setString(3, "4");

                 }*/
            } else if (cbclasse.getSelectedIndex() == 2) {
                pst.setString(2, "3");

                /*if (cbturma.getSelectedIndex() == 0) {
                 pst.setString(3, "5");

                 } else if (cbturma.getSelectedIndex() == 1) {
                 pst.setString(3, "6");

                 }*/
            } else if (cbclasse.getSelectedIndex() == 3) {
                pst.setString(2, "4");

                /*if (cbturma.getSelectedIndex() == 0) {
                 pst.setString(3, "7");

                 } else if (cbturma.getSelectedIndex() == 1) {
                 pst.setString(3, "8");

                 }*/
            } else if (cbclasse.getSelectedIndex() == 4) {
                pst.setString(2, "5");

                /*if (cbturma.getSelectedIndex() == 0) {
                 pst.setString(3, "9");

                 } else if (cbturma.getSelectedIndex() == 1) {
                 pst.setString(3, "10");

                 }*/
            } else if (cbclasse.getSelectedIndex() == 5) {
                pst.setString(2, "6");

                /*if (cbturma.getSelectedIndex() == 0) {
                 pst.setString(3, "11");

                 } else if (cbturma.getSelectedIndex() == 1) {
                 pst.setString(3, "12");

                 }*/
            } else if (cbclasse.getSelectedIndex() == 6) {
                pst.setString(2, "7");

                /*if (cbturma.getSelectedIndex() == 0) {
                 pst.setString(3, "13");

                 } else if (cbturma.getSelectedIndex() == 1) {
                 pst.setString(3, "14");

                 }*/
            }

            rs = pst.executeQuery();

            while (rs.next()) {

                String id_d = rs.getString("id_da_cadeira");

                switch (id_d) {

                    case "1":
                        p1.setText(rs.getString("n1"));
                        p2.setText(rs.getString("n2"));
                        pt.setText(rs.getString("t"));
                        pm.setText(rs.getString("m"));

                        if (Double.parseDouble(rs.getString("n1")) >= 10) {
                            p1.setForeground(Color.blue);
                        } else {
                            p1.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("n2")) >= 10) {
                            p2.setForeground(Color.blue);
                        } else {
                            p2.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("t")) >= 10) {
                            pt.setForeground(Color.blue);
                        } else {
                            pt.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("m")) >= 10) {
                            pm.setForeground(Color.blue);
                        } else {
                            pm.setForeground(Color.red);
                        }

                        break;

                    case "2":
                        m1.setText(rs.getString("n1"));
                        m2.setText(rs.getString("n2"));
                        mt.setText(rs.getString("t"));
                        mm.setText(rs.getString("m"));

                        if (Double.parseDouble(rs.getString("n1")) >= 10) {
                            m1.setForeground(Color.blue);
                        } else {
                            m1.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("n2")) >= 10) {
                            m2.setForeground(Color.blue);
                        } else {
                            m2.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("t")) >= 10) {
                            mt.setForeground(Color.blue);
                        } else {
                            mt.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("m")) >= 10) {
                            mm.setForeground(Color.blue);
                        } else {
                            mm.setForeground(Color.red);
                        }

                        break;

                    case "3":
                        o1.setText(rs.getString("n1"));
                        o2.setText(rs.getString("n2"));
                        ot.setText(rs.getString("t"));
                        om.setText(rs.getString("m"));

                        if (Double.parseDouble(rs.getString("n1")) >= 10) {
                            o1.setForeground(Color.blue);
                        } else {
                            o1.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("n2")) >= 10) {
                            o2.setForeground(Color.blue);
                        } else {
                            o2.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("t")) >= 10) {
                            ot.setForeground(Color.blue);
                        } else {
                            ot.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("m")) >= 10) {
                            om.setForeground(Color.blue);
                        } else {
                            om.setForeground(Color.red);
                        }

                        break;

                    case "4":
                        i1.setText(rs.getString("n1"));
                        i2.setText(rs.getString("n2"));
                        it.setText(rs.getString("t"));
                        im.setText(rs.getString("m"));

                        if (Double.parseDouble(rs.getString("n1")) >= 10) {
                            i1.setForeground(Color.blue);
                        } else {
                            i1.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("n2")) >= 10) {
                            i2.setForeground(Color.blue);
                        } else {
                            i2.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("t")) >= 10) {
                            it.setForeground(Color.blue);
                        } else {
                            it.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("m")) >= 10) {
                            im.setForeground(Color.blue);
                        } else {
                            im.setForeground(Color.red);
                        }

                        break;

                    case "5":
                        cn1.setText(rs.getString("n1"));
                        cn2.setText(rs.getString("n2"));
                        cnt.setText(rs.getString("t"));
                        cnm.setText(rs.getString("m"));

                        if (Double.parseDouble(rs.getString("n1")) >= 10) {
                            cn1.setForeground(Color.blue);
                        } else {
                            cn1.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("n2")) >= 10) {
                            cn2.setForeground(Color.blue);
                        } else {
                            cn2.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("t")) >= 10) {
                            cnt.setForeground(Color.blue);
                        } else {
                            cnt.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("m")) >= 10) {
                            cnm.setForeground(Color.blue);
                        } else {
                            cnm.setForeground(Color.red);
                        }

                        break;

                    case "6":
                        cs1.setText(rs.getString("n1"));
                        cs2.setText(rs.getString("n2"));
                        cst.setText(rs.getString("t"));
                        csm.setText(rs.getString("m"));

                        if (Double.parseDouble(rs.getString("n1")) >= 10) {
                            cs1.setForeground(Color.blue);
                        } else {
                            cs1.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("n2")) >= 10) {
                            cs2.setForeground(Color.blue);
                        } else {
                            cs2.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("t")) >= 10) {
                            cst.setForeground(Color.blue);
                        } else {
                            cst.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("m")) >= 10) {
                            csm.setForeground(Color.blue);
                        } else {
                            csm.setForeground(Color.red);
                        }

                        break;

                    case "7":
                        em1.setText(rs.getString("n1"));
                        em2.setText(rs.getString("n2"));
                        emt.setText(rs.getString("t"));
                        emm.setText(rs.getString("m"));

                        if (Double.parseDouble(rs.getString("n1")) >= 10) {
                            em1.setForeground(Color.blue);
                        } else {
                            em1.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("n2")) >= 10) {
                            em2.setForeground(Color.blue);
                        } else {
                            em2.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("t")) >= 10) {
                            emt.setForeground(Color.blue);
                        } else {
                            emt.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("m")) >= 10) {
                            emm.setForeground(Color.blue);
                        } else {
                            emm.setForeground(Color.red);
                        }

                        break;

                    case "8":
                        ev1.setText(rs.getString("n1"));
                        ev2.setText(rs.getString("n2"));
                        evt.setText(rs.getString("t"));
                        evm.setText(rs.getString("m"));

                        if (Double.parseDouble(rs.getString("n1")) >= 10) {
                            ev1.setForeground(Color.blue);
                        } else {
                            ev1.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("n2")) >= 10) {
                            ev2.setForeground(Color.blue);
                        } else {
                            ev2.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("t")) >= 10) {
                            evt.setForeground(Color.blue);
                        } else {
                            evt.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("m")) >= 10) {
                            evm.setForeground(Color.blue);
                        } else {
                            evm.setForeground(Color.red);
                        }

                        break;

                    case "9":
                        ef1.setText(rs.getString("n1"));
                        ef2.setText(rs.getString("n2"));
                        eft.setText(rs.getString("t"));
                        efm.setText(rs.getString("m"));

                        if (Double.parseDouble(rs.getString("n1")) >= 10) {
                            ef1.setForeground(Color.blue);
                        } else {
                            ef1.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("n2")) >= 10) {
                            ef2.setForeground(Color.blue);
                        } else {
                            ef2.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("t")) >= 10) {
                            eft.setForeground(Color.blue);
                        } else {
                            eft.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("m")) >= 10) {
                            efm.setForeground(Color.blue);
                        } else {
                            efm.setForeground(Color.red);
                        }

                        break;

                    case "10":
                        emc1.setText(rs.getString("n1"));
                        emc2.setText(rs.getString("n2"));
                        emct.setText(rs.getString("t"));
                        emcm.setText(rs.getString("m"));

                        if (Double.parseDouble(rs.getString("n1")) >= 10) {
                            emc1.setForeground(Color.blue);
                        } else {
                            emc1.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("n2")) >= 10) {
                            emc2.setForeground(Color.blue);
                        } else {
                            emc2.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("t")) >= 10) {
                            emct.setForeground(Color.blue);
                        } else {
                            emct.setForeground(Color.red);
                        }

                        if (Double.parseDouble(rs.getString("m")) >= 10) {
                            emcm.setForeground(Color.blue);
                        } else {
                            emcm.setForeground(Color.red);
                        }

                        break;
                }

            }

        } /*catch (NullPointerException e){
         JOptionPane.showMessageDialog(rootPane, "Selecione a classe");
         }*/ catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Mostrar_notas" + e);
        }
    }

    private void clean() {
        if (tblaluno.getSelectedRow() == -1) {
            limpar();
        }
    }

    private void limpar() {

        p1.setText(null);
        p2.setText(null);
        pt.setText(null);
        pm.setText(null);

        m1.setText(null);
        m2.setText(null);
        mt.setText(null);
        mm.setText(null);

        o1.setText(null);
        o2.setText(null);
        ot.setText(null);
        om.setText(null);

        i1.setText(null);
        i2.setText(null);
        it.setText(null);
        im.setText(null);

        ef1.setText(null);
        ef2.setText(null);
        eft.setText(null);
        efm.setText(null);

        cn1.setText(null);
        cn2.setText(null);
        cnt.setText(null);
        cnm.setText(null);

        cs1.setText(null);
        cs2.setText(null);
        cst.setText(null);
        csm.setText(null);

        em1.setText(null);
        em2.setText(null);
        emt.setText(null);
        emm.setText(null);

        ev1.setText(null);
        ev2.setText(null);
        evt.setText(null);
        evm.setText(null);

        emc1.setText(null);
        emc2.setText(null);
        emct.setText(null);
        emcm.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblaluno = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbclasse = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbturma = new javax.swing.JComboBox();
        panelnotas = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
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
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        p1 = new javax.swing.JTextField();
        p2 = new javax.swing.JTextField();
        pt = new javax.swing.JTextField();
        pm = new javax.swing.JTextField();
        m1 = new javax.swing.JTextField();
        m2 = new javax.swing.JTextField();
        mt = new javax.swing.JTextField();
        mm = new javax.swing.JTextField();
        o1 = new javax.swing.JTextField();
        o2 = new javax.swing.JTextField();
        ot = new javax.swing.JTextField();
        om = new javax.swing.JTextField();
        ef1 = new javax.swing.JTextField();
        cn1 = new javax.swing.JTextField();
        cs1 = new javax.swing.JTextField();
        i1 = new javax.swing.JTextField();
        em1 = new javax.swing.JTextField();
        emc1 = new javax.swing.JTextField();
        ev1 = new javax.swing.JTextField();
        ef2 = new javax.swing.JTextField();
        cn2 = new javax.swing.JTextField();
        cs2 = new javax.swing.JTextField();
        i2 = new javax.swing.JTextField();
        em2 = new javax.swing.JTextField();
        emc2 = new javax.swing.JTextField();
        evt = new javax.swing.JTextField();
        eft = new javax.swing.JTextField();
        efm = new javax.swing.JTextField();
        cnt = new javax.swing.JTextField();
        cst = new javax.swing.JTextField();
        it = new javax.swing.JTextField();
        emt = new javax.swing.JTextField();
        emct = new javax.swing.JTextField();
        ev2 = new javax.swing.JTextField();
        cnm = new javax.swing.JTextField();
        csm = new javax.swing.JTextField();
        im = new javax.swing.JTextField();
        emm = new javax.swing.JTextField();
        emcm = new javax.swing.JTextField();
        evm = new javax.swing.JTextField();
        lp = new javax.swing.JButton();
        lm = new javax.swing.JButton();
        lo = new javax.swing.JButton();
        lef = new javax.swing.JButton();
        lcn = new javax.swing.JButton();
        lcs = new javax.swing.JButton();
        li = new javax.swing.JButton();
        lem = new javax.swing.JButton();
        lemc = new javax.swing.JButton();
        lev = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbtrimestre = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbano = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(java.awt.Color.white);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Lançamento de Notas");
        setPreferredSize(new java.awt.Dimension(761, 550));
        setVisible(true);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblaluno.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        tblaluno.setForeground(new java.awt.Color(255, 153, 0));
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
        tblaluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblalunoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblaluno);
        if (tblaluno.getColumnModel().getColumnCount() > 0) {
            tblaluno.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            tblaluno.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            tblaluno.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            tblaluno.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Classe");

        cbclasse.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbclasse.setForeground(new java.awt.Color(255, 153, 0));
        cbclasse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "    1ª", "    2ª", "    3ª", "    4ª", "    5ª", "    6ª", "    7ª" }));
        cbclasse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbclasseItemStateChanged(evt);
            }
        });
        cbclasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbclasseActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Turma");

        cbturma.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbturma.setForeground(new java.awt.Color(255, 153, 0));
        cbturma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "    A", "    B" }));
        cbturma.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbturmaItemStateChanged(evt);
            }
        });

        panelnotas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Disciplinas");
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 25));

        p.setBackground(new java.awt.Color(255, 255, 255));
        p.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        p.setForeground(new java.awt.Color(0, 102, 204));
        p.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p.setText("Português");
        p.setPreferredSize(new java.awt.Dimension(80, 25));

        m.setBackground(new java.awt.Color(255, 255, 255));
        m.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        m.setForeground(new java.awt.Color(0, 102, 204));
        m.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m.setText("Matemática");
        m.setPreferredSize(new java.awt.Dimension(80, 25));

        o.setBackground(new java.awt.Color(255, 255, 255));
        o.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        o.setForeground(new java.awt.Color(0, 102, 204));
        o.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        o.setText("Ofícios");
        o.setPreferredSize(new java.awt.Dimension(80, 25));

        ef.setBackground(new java.awt.Color(255, 255, 255));
        ef.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        ef.setForeground(new java.awt.Color(0, 102, 204));
        ef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ef.setText("E. Física");
        ef.setPreferredSize(new java.awt.Dimension(80, 25));

        cn.setBackground(new java.awt.Color(255, 255, 255));
        cn.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cn.setForeground(new java.awt.Color(0, 102, 204));
        cn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cn.setText("C. Naturais");
        cn.setPreferredSize(new java.awt.Dimension(80, 25));

        cs.setBackground(new java.awt.Color(255, 255, 255));
        cs.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cs.setForeground(new java.awt.Color(0, 102, 204));
        cs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cs.setText("C. Sociais");
        cs.setPreferredSize(new java.awt.Dimension(80, 25));

        i.setBackground(new java.awt.Color(255, 255, 255));
        i.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        i.setForeground(new java.awt.Color(0, 102, 204));
        i.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        i.setText("Inglês");
        i.setPreferredSize(new java.awt.Dimension(80, 25));

        em.setBackground(new java.awt.Color(255, 255, 255));
        em.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        em.setForeground(new java.awt.Color(0, 102, 204));
        em.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        em.setText("E. Musical");
        em.setPreferredSize(new java.awt.Dimension(80, 25));

        emc.setBackground(new java.awt.Color(255, 255, 255));
        emc.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        emc.setForeground(new java.awt.Color(0, 102, 204));
        emc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emc.setText("E. M. Cívica");
        emc.setPreferredSize(new java.awt.Dimension(80, 25));

        ev.setBackground(new java.awt.Color(255, 255, 255));
        ev.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        ev.setForeground(new java.awt.Color(0, 102, 204));
        ev.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ev.setText("E. Visual");
        ev.setPreferredSize(new java.awt.Dimension(80, 25));

        jLabel14.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 153, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("T 1");
        jLabel14.setPreferredSize(new java.awt.Dimension(60, 25));

        jLabel15.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 153, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("T 2");
        jLabel15.setPreferredSize(new java.awt.Dimension(60, 25));

        jLabel16.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 153, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("T");
        jLabel16.setPreferredSize(new java.awt.Dimension(60, 25));

        jLabel17.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 153, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("M");
        jLabel17.setPreferredSize(new java.awt.Dimension(52, 25));

        p1.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        p1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        p1.setPreferredSize(new java.awt.Dimension(60, 25));
        p1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                p1KeyReleased(evt);
            }
        });

        p2.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        p2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        p2.setPreferredSize(new java.awt.Dimension(60, 25));
        p2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                p2KeyReleased(evt);
            }
        });

        pt.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        pt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt.setPreferredSize(new java.awt.Dimension(60, 25));
        pt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ptKeyReleased(evt);
            }
        });

        pm.setEditable(false);
        pm.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        pm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pm.setPreferredSize(new java.awt.Dimension(60, 25));
        pm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmActionPerformed(evt);
            }
        });

        m1.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        m1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        m1.setPreferredSize(new java.awt.Dimension(60, 25));
        m1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m1ActionPerformed(evt);
            }
        });

        m2.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        m2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        m2.setPreferredSize(new java.awt.Dimension(60, 25));
        m2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m2ActionPerformed(evt);
            }
        });

        mt.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        mt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mt.setPreferredSize(new java.awt.Dimension(60, 25));

        mm.setEditable(false);
        mm.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        mm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mm.setPreferredSize(new java.awt.Dimension(60, 25));

        o1.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        o1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        o1.setPreferredSize(new java.awt.Dimension(60, 25));

        o2.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        o2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        o2.setPreferredSize(new java.awt.Dimension(60, 25));
        o2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o2ActionPerformed(evt);
            }
        });

        ot.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        ot.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ot.setPreferredSize(new java.awt.Dimension(60, 25));

        om.setEditable(false);
        om.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        om.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        om.setPreferredSize(new java.awt.Dimension(60, 25));
        om.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                omActionPerformed(evt);
            }
        });

        ef1.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        ef1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ef1.setPreferredSize(new java.awt.Dimension(60, 25));

        cn1.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        cn1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cn1.setPreferredSize(new java.awt.Dimension(60, 25));

        cs1.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        cs1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cs1.setPreferredSize(new java.awt.Dimension(60, 25));

        i1.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        i1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        i1.setPreferredSize(new java.awt.Dimension(60, 25));

        em1.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        em1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        em1.setPreferredSize(new java.awt.Dimension(60, 25));

        emc1.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        emc1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emc1.setPreferredSize(new java.awt.Dimension(60, 25));

        ev1.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        ev1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ev1.setPreferredSize(new java.awt.Dimension(60, 25));

        ef2.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        ef2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ef2.setPreferredSize(new java.awt.Dimension(60, 25));

        cn2.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        cn2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cn2.setPreferredSize(new java.awt.Dimension(60, 25));

        cs2.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        cs2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cs2.setPreferredSize(new java.awt.Dimension(60, 25));

        i2.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        i2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        i2.setPreferredSize(new java.awt.Dimension(60, 25));

        em2.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        em2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        em2.setPreferredSize(new java.awt.Dimension(60, 25));

        emc2.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        emc2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emc2.setPreferredSize(new java.awt.Dimension(60, 25));

        evt.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        evt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        evt.setPreferredSize(new java.awt.Dimension(60, 25));

        eft.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        eft.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        eft.setPreferredSize(new java.awt.Dimension(60, 25));

        efm.setEditable(false);
        efm.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        efm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        efm.setPreferredSize(new java.awt.Dimension(60, 25));
        efm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efmActionPerformed(evt);
            }
        });

        cnt.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        cnt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cnt.setPreferredSize(new java.awt.Dimension(60, 25));

        cst.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        cst.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cst.setPreferredSize(new java.awt.Dimension(60, 25));

        it.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        it.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        it.setPreferredSize(new java.awt.Dimension(60, 25));

        emt.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        emt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emt.setPreferredSize(new java.awt.Dimension(60, 25));

        emct.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        emct.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emct.setPreferredSize(new java.awt.Dimension(60, 25));

        ev2.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        ev2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ev2.setPreferredSize(new java.awt.Dimension(60, 25));

        cnm.setEditable(false);
        cnm.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        cnm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cnm.setPreferredSize(new java.awt.Dimension(60, 25));

        csm.setEditable(false);
        csm.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        csm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        csm.setPreferredSize(new java.awt.Dimension(60, 25));

        im.setEditable(false);
        im.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        im.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        im.setPreferredSize(new java.awt.Dimension(60, 25));
        im.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imActionPerformed(evt);
            }
        });

        emm.setEditable(false);
        emm.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        emm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emm.setPreferredSize(new java.awt.Dimension(60, 25));

        emcm.setEditable(false);
        emcm.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        emcm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emcm.setPreferredSize(new java.awt.Dimension(60, 25));

        evm.setEditable(false);
        evm.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        evm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        evm.setPreferredSize(new java.awt.Dimension(60, 25));

        lp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        lp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lpActionPerformed(evt);
            }
        });

        lm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        lm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lmActionPerformed(evt);
            }
        });

        lo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        lo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loActionPerformed(evt);
            }
        });

        lef.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        lef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lefActionPerformed(evt);
            }
        });

        lcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        lcn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lcnActionPerformed(evt);
            }
        });

        lcs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        lcs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lcsActionPerformed(evt);
            }
        });

        li.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        li.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liActionPerformed(evt);
            }
        });

        lem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        lem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lemActionPerformed(evt);
            }
        });

        lemc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        lemc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lemcActionPerformed(evt);
            }
        });

        lev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        lev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                levActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout panelnotasLayout = new javax.swing.GroupLayout(panelnotas);
        panelnotas.setLayout(panelnotasLayout);
        panelnotasLayout.setHorizontalGroup(
            panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelnotasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cs, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(em, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emc, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ev, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ef, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(o, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelnotasLayout.createSequentialGroup()
                        .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mt, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelnotasLayout.createSequentialGroup()
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pt, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelnotasLayout.createSequentialGroup()
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(emc1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(em1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(ev1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emc2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(em2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ev2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(emct, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                .addComponent(evt, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addComponent(emt, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelnotasLayout.createSequentialGroup()
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelnotasLayout.createSequentialGroup()
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(o1, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                    .addComponent(ef1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(o2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ef2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelnotasLayout.createSequentialGroup()
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(i1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cs1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cn1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cs2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cn2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eft, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ot, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cnt, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cst, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(it, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(emcm, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                        .addComponent(evm, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addComponent(emm, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(efm, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                        .addComponent(im, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(csm, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(cnm, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(om, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(pm, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mm, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lp)
                    .addComponent(lm)
                    .addComponent(lo)
                    .addComponent(lef)
                    .addComponent(lcn)
                    .addComponent(lcs)
                    .addComponent(li)
                    .addComponent(lem)
                    .addComponent(lemc)
                    .addComponent(lev)))
        );
        panelnotasLayout.setVerticalGroup(
            panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelnotasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelnotasLayout.createSequentialGroup()
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(pm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lm, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(m2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(mm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelnotasLayout.createSequentialGroup()
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(panelnotasLayout.createSequentialGroup()
                                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(o, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(o1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(o2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelnotasLayout.createSequentialGroup()
                                        .addComponent(lo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ef1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ef2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(eft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lef))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lcn)
                                    .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelnotasLayout.createSequentialGroup()
                                .addComponent(om, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(efm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cnm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelnotasLayout.createSequentialGroup()
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lcs)
                                    .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cs1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cs2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelnotasLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(i, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(i1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(i2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(it, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panelnotasLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(li)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lem)
                                    .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(em, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(em1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(em2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lemc)
                                    .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(emc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emcm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ev1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ev2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lev)))
                            .addGroup(panelnotasLayout.createSequentialGroup()
                                .addComponent(csm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(im, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelnotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(evt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(evm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("NOTAS DOS ALUNOS");

        jLabel7.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Trimestre");

        cbtrimestre.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbtrimestre.setForeground(new java.awt.Color(255, 153, 0));
        cbtrimestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        cbtrimestre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbtrimestreItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ano Lectivo");

        cbano.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbano.setForeground(new java.awt.Color(255, 153, 0));
        cbano.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2022", "2023", "2024", "2025", "2026" }));
        cbano.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbanoItemStateChanged(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbclasse, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbturma, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelnotas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbtrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbclasse, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbturma, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbano, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbtrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelnotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbclasseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbclasseItemStateChanged
        // TODO add your handling code here:
        alunos();
    }//GEN-LAST:event_cbclasseItemStateChanged

    private void cbturmaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbturmaItemStateChanged
        // TODO add your handling code here:
        alunos();
    }//GEN-LAST:event_cbturmaItemStateChanged

    private void imActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imActionPerformed

    private void efmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_efmActionPerformed

    private void omActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_omActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_omActionPerformed

    private void m1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m1ActionPerformed
        // TODO add your handling code here:
        /*double mf = mediaf(m1.getText().toString(),m2.getText().toString(), mt.getText().toString()); 
         mm.setText(String.valueOf(mf));*/
    }//GEN-LAST:event_m1ActionPerformed

    private void lpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lpActionPerformed
        // TODO add your handling code here:
        
        lancar_ind("1");
    }//GEN-LAST:event_lpActionPerformed

    private void lmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lmActionPerformed
        // TODO add your handling code here:
        lancar_ind("2");
    }//GEN-LAST:event_lmActionPerformed

    private void loActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loActionPerformed
        // TODO add your handling code here:
        lancar_ind("3");
    }//GEN-LAST:event_loActionPerformed

    private void lefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lefActionPerformed
        // TODO add your handling code here:
        lancar_ind("9");
    }//GEN-LAST:event_lefActionPerformed

    private void lcnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lcnActionPerformed
        // TODO add your handling code here:
        lancar_ind("5");
    }//GEN-LAST:event_lcnActionPerformed

    private void lcsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lcsActionPerformed
        // TODO add your handling code here:
        lancar_ind("6");
    }//GEN-LAST:event_lcsActionPerformed

    private void liActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_liActionPerformed
        // TODO add your handling code here:
        lancar_ind("4");
    }//GEN-LAST:event_liActionPerformed

    private void lemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lemActionPerformed
        // TODO add your handling code here:
        lancar_ind("7");
    }//GEN-LAST:event_lemActionPerformed

    private void lemcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lemcActionPerformed
        // TODO add your handling code here:
        lancar_ind("10");
    }//GEN-LAST:event_lemcActionPerformed

    private void levActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_levActionPerformed
        // TODO add your handling code here:
        lancar_ind("8");
        /* if(evm.getText().isEmpty()){
         lev.setEnabled(false);
         }else{
         lev.setEnabled(true);
         }*/
    }//GEN-LAST:event_levActionPerformed

    private void p1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p1KeyReleased
        // TODO add your handling code here:
        /*double mf = mediaf(p1.getText().toString(),p2.getText().toString(), pt.getText().toString()); 
         pm.setText(String.valueOf(mf));*/
    }//GEN-LAST:event_p1KeyReleased

    private void pmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_pmActionPerformed

    private void p2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p2KeyReleased
        // TODO add your handling code here:
        /*double mf = mediaf(p1.getText().toString(),p2.getText().toString(), pt.getText().toString()); 
         pm.setText(String.valueOf(mf));*/

    }//GEN-LAST:event_p2KeyReleased

    private void ptKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptKeyReleased
        // TODO add your handling code here:
        //mediap();
        /*double mf = mediaf(p1.getText().toString(),p2.getText().toString(), pt.getText().toString()); 
         pm.setText(String.valueOf(mf));*/
    }//GEN-LAST:event_ptKeyReleased

    private void m2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m2ActionPerformed

    private void tblalunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblalunoMouseClicked
        // TODO add your handling code here:
        limpar();
        mostrar_notas();

    }//GEN-LAST:event_tblalunoMouseClicked

    private void cbclasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbclasseActionPerformed
        // TODO add your handling code here:

        alunos();
    }//GEN-LAST:event_cbclasseActionPerformed

    private void o2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_o2ActionPerformed

    private void cbtrimestreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbtrimestreItemStateChanged
        // TODO add your handling code here:
        limpar();
        try {
            mostrar_notas();
        } catch (ArrayIndexOutOfBoundsException e) {

        }

    }//GEN-LAST:event_cbtrimestreItemStateChanged

    private void cbanoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbanoItemStateChanged
        // TODO add your handling code here:
        //tblaluno.setModel(null);
        alunos();
    }//GEN-LAST:event_cbanoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbano;
    private javax.swing.JComboBox cbclasse;
    private javax.swing.JComboBox cbtrimestre;
    private javax.swing.JComboBox cbturma;
    private javax.swing.JLabel cn;
    private javax.swing.JTextField cn1;
    private javax.swing.JTextField cn2;
    private javax.swing.JTextField cnm;
    private javax.swing.JTextField cnt;
    private javax.swing.JLabel cs;
    private javax.swing.JTextField cs1;
    private javax.swing.JTextField cs2;
    private javax.swing.JTextField csm;
    private javax.swing.JTextField cst;
    private javax.swing.JLabel ef;
    private javax.swing.JTextField ef1;
    private javax.swing.JTextField ef2;
    private javax.swing.JTextField efm;
    private javax.swing.JTextField eft;
    private javax.swing.JLabel em;
    private javax.swing.JTextField em1;
    private javax.swing.JTextField em2;
    private javax.swing.JLabel emc;
    private javax.swing.JTextField emc1;
    private javax.swing.JTextField emc2;
    private javax.swing.JTextField emcm;
    private javax.swing.JTextField emct;
    private javax.swing.JTextField emm;
    private javax.swing.JTextField emt;
    private javax.swing.JLabel ev;
    private javax.swing.JTextField ev1;
    private javax.swing.JTextField ev2;
    private javax.swing.JTextField evm;
    private javax.swing.JTextField evt;
    private javax.swing.JLabel i;
    private javax.swing.JTextField i1;
    private javax.swing.JTextField i2;
    private javax.swing.JTextField im;
    private javax.swing.JTextField it;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton lcn;
    private javax.swing.JButton lcs;
    private javax.swing.JButton lef;
    private javax.swing.JButton lem;
    private javax.swing.JButton lemc;
    private javax.swing.JButton lev;
    private javax.swing.JButton li;
    private javax.swing.JButton lm;
    private javax.swing.JButton lo;
    private javax.swing.JButton lp;
    private javax.swing.JLabel m;
    private javax.swing.JTextField m1;
    private javax.swing.JTextField m2;
    private javax.swing.JTextField mm;
    private javax.swing.JTextField mt;
    private javax.swing.JLabel o;
    private javax.swing.JTextField o1;
    private javax.swing.JTextField o2;
    private javax.swing.JTextField om;
    private javax.swing.JTextField ot;
    private javax.swing.JLabel p;
    private javax.swing.JTextField p1;
    private javax.swing.JTextField p2;
    private javax.swing.JPanel panelnotas;
    private javax.swing.JTextField pm;
    private javax.swing.JTextField pt;
    private javax.swing.JTable tblaluno;
    // End of variables declaration//GEN-END:variables
}
