/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.sql.*;
import Classes.Conexao;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Arlindo Halar
 */
public class TelaTurma extends JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pstturma = null;
    ResultSet rs = null;
    int adicionado;

    private static int id_docente;

    /*public void adicionar_t() {

     String sql = "insert into turma (turma, seccao, classe_id) values(?,?,?)";
     String turma = "select turma from turma where turma = ? and classe_id = ?";
     try {
     pst = conexao.prepareStatement(sql);
     pstturma = conexao.prepareStatement(turma);

     pst.setString(1, txtturma.getText().toUpperCase());
     pst.setString(2, txtseccao.getText());

     pstturma.setString(1, txtturma.getText().toUpperCase());

     //            String cbclass;

     if (cbclasse.getSelectedIndex() == 0) {
     //              cbclass = "1";
     pst.setString(3, "1");
     pstturma.setString(2, "1");
     } else if (cbclasse.getSelectedIndex() == 1) {
     pst.setString(3, "2");
     pstturma.setString(2, "2");
     //                cbclass = "2";
     } else if (cbclasse.getSelectedIndex() == 2) {
     pst.setString(3, "3");
     pstturma.setString(2, "3");
     //                cbclass = "3";
     } else if (cbclasse.getSelectedIndex() == 3) {
     pst.setString(3, "4");
     pstturma.setString(2, "4");
     //                cbclass = "4";
     } else if (cbclasse.getSelectedIndex() == 4) {
     pst.setString(3, "5");
     pstturma.setString(2, "5");
     //                cbclass = "5";
     } else if (cbclasse.getSelectedIndex() == 5) {
     pst.setString(3, "6");
     pstturma.setString(2, "6");
     //                cbclass = "6";
     } else if (cbclasse.getSelectedIndex() == 6) {
     pst.setString(3, "7");
     pstturma.setString(2, "7");
     //                cbclass = "7";
     }

     rs = pstturma.executeQuery();

     if (rs.next()) {
     JOptionPane.showMessageDialog(rootPane, "A turma " + "'"+txtturma.getText().toUpperCase() +"'"+ " ja foi criada para esta classe");
     }
     else{
     if (txtturma.getText().isEmpty()) {
     JOptionPane.showMessageDialog(rootPane, "Campo 'turma' não preenchidos!");
     } else {
     adicionado = pst.executeUpdate();

     if (adicionado > 0) {
     JOptionPane.showMessageDialog(rootPane, "Turma criada com Sucesso");
     txtturma.setText(null);
     txtseccao.setText(null);
     }
     }
     }

     } catch (Exception e) {
     JOptionPane.showMessageDialog(rootPane, e);
     }
     }*/
    /**
     * Creates new form TelaTurma
     */
    public TelaTurma() {
        initComponents();
        conexao = Conexao.conector();
        alunos();
        selectDT();
        lblDirector.setHorizontalAlignment(JLabel.CENTER);
        tblCadeiraXDocente();

    }

    private void selectDT() {
        String director = "select d.nome from docente d inner join docentexdt dt on d.id = dt.id_docente where dt.id_classe = ? and dt.id_turma = ? and id_ano = ?";

        try {
            pst = conexao.prepareStatement(director);
            pst.setString(3, String.valueOf(cbano.getSelectedIndex() + 1));

            switch (cbclasse.getSelectedIndex()) {
                case 0: {
                    pst.setString(1, "1");

                    if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "1");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "2");
                    }

                    break;
                }
                case 1: {
                    pst.setString(1, "2");

                    if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "1");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "2");
                    }

                    break;
                }
                case 2: {
                    pst.setString(1, "3");

                    if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "1");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "2");
                    }

                    break;
                }
                case 3: {
                    pst.setString(1, "4");

                    if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "1");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "2");
                    }

                    break;
                }
                case 4: {
                    pst.setString(1, "5");

                    if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "1");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "2");
                    }

                    break;
                }
                case 5: {
                    pst.setString(1, "6");

                    if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "1");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "2");
                    }

                    break;
                }
                case 6: {
                    pst.setString(1, "7");

                    if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "1");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "2");
                    }

                    break;
                }
            }

            rs = pst.executeQuery();

            if (rs.next()) {
                lblDirector.setText(rs.getString("nome"));
            } else {
                lblDirector.setText("Turma sem D.T. atribuido");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "TelaTurma, Método SelectDt\n" + e);
        }
    }

    private static String tu_id;

    private void alunos() {
        String aluno = "select al.id, al.sexo,al.nome from aluno al inner join classe cl on cl.id = classe_id inner join turma tu on tu.id = turma_id where al.classe_id = ? and al.turma_id = ? and tu.id_ano = ?";
        
        String tu = "select id from turma where id_ano = ? and classe_id = ? and turma = ?";

        try {
            pst = conexao.prepareStatement(tu);

            switch (cbano.getSelectedIndex()) {
                case 0:
                    pst.setString(1, "1");

                    switch (cbclasse.getSelectedIndex()) {
                        case 0:
                            pst.setString(2, "1");
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
                            
                            switch (cbturma.getSelectedIndex()){
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
            
            if(rs.next()){
                tu_id = rs.getString("id");
                //System.out.println(rs.getString("id"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "TelaTurma, Método Alunos\n" + e);
            //System.out.println(e);
        }

        try {
            pst = conexao.prepareStatement(aluno);

            switch (cbano.getSelectedIndex()) {
                case 0: {
                    pst.setString(3, "1");
                    break;
                }
                case 1: {
                    pst.setString(3, "2");
                    break;
                }
                case 2: {
                    pst.setString(3, "3");
                    //System.out.println("3");
                    break;
                }
                case 3: {
                    pst.setString(3, "4");
                    break;
                }
                case 4: {
                    pst.setString(3, "5");
                    break;
                }
            }
            
            pst.setString(2, tu_id);
            
            switch (cbclasse.getSelectedIndex()) {
                case 0: {
                    pst.setString(1, "1");

                    /*if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "1");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "2");
                    }*/

                    break;
                }
                case 1: {
                    pst.setString(1, "2");

                   /* if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "3");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "4");
                    }*/

                    break;
                }
                case 2: {
                    pst.setString(1, "3");

                   /* if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "5");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "6");
                    }*/

                    break;
                }
                case 3: {
                    pst.setString(1, "4");

                    /*if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "7");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "8");
                    }*/

                    break;
                }
                case 4: {
                    pst.setString(1, "5");

                   /* if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "9");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "10");
                    }*/

                    break;
                }
                case 5: {
                    pst.setString(1, "6");

                    /*if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "11");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "12");
                    }*/

                    break;
                }
                case 6: {
                    pst.setString(1, "7");

                    /*if (cbturma.getSelectedIndex() == 0) {
                        pst.setString(2, "13");
                    } else if (cbturma.getSelectedIndex() == 1) {
                        pst.setString(2, "14");
                    }*/

                    break;
                }
            }

            rs = pst.executeQuery();

            tblalunos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "TelaTurma, Método Alunos\n" + e);
        }
    }

    private void actualizarDT() {

        String actualizar = "update docentexdt set id_docente = ? where id_classe = ? and id_turma = ? and id_ano = ?";
        if (lblDirector.getText().equals("Turma sem D.T. atribuido")) {

            JOptionPane.showMessageDialog(rootPane, "Esta turma ainda não tem um Director definido");

        } else {
            try {
                id_docente = Integer.parseInt(tblcadeirasxdocentes.getModel().getValueAt(tblcadeirasxdocentes.getSelectedRow(), 2).toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "Por favor, Selecione o Professor primeiro");
            }

            //System.out.println("Id Docente: "+id_docente);
            try {
                pst = conexao.prepareStatement(actualizar);

                pst.setString(1, String.valueOf(id_docente));
                pst.setString(4, String.valueOf(cbano.getSelectedIndex() + 1));

                switch (cbclasse.getSelectedIndex()) {
                    case 0: {
                        pst.setString(2, "1");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                    case 1: {
                        pst.setString(2, "2");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                    case 2: {
                        pst.setString(2, "3");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                    case 3: {
                        pst.setString(2, "4");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                    case 4: {
                        pst.setString(2, "5");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                    case 5: {
                        pst.setString(2, "6");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                    case 6: {
                        pst.setString(2, "7");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                }

                String turma = cbturma.getSelectedItem().toString();
                String classe = cbclasse.getSelectedItem().toString();

                int row = tblcadeirasxdocentes.getSelectedRow();

                int pergunta = JOptionPane.showConfirmDialog(rootPane, "Actualizar e Definir o Professor " + tblcadeirasxdocentes.getModel().getValueAt(row, 3).toString() + " como DT da turma " + turma + " da " + classe + " classe?", "Confirme", JOptionPane.YES_NO_OPTION);

                if (pergunta == JOptionPane.YES_OPTION) {
                    int exec = pst.executeUpdate();

                    if (exec > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Actualização sucedida");

                        selectDT();
                    }
                }

            } catch (ArrayIndexOutOfBoundsException e) {

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Um Professor nao pode ser Director de mais de uma turma");
                //System.out.println(e);
            }
        }
    }

    private void atribuirDT() {

        String atribuir = "insert into docentexdt values(?, ?, ?, ?)";

        if (lblDirector.getText().equals("Turma sem D.T. atribuido")) {
            try {
                id_docente = Integer.parseInt(tblcadeirasxdocentes.getModel().getValueAt(tblcadeirasxdocentes.getSelectedRow(), 2).toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "Por favor, Selecione o Professor primeiro");

            }

            //System.out.println("Id Docente: "+id_docente);
            try {
                pst = conexao.prepareStatement(atribuir);

                pst.setString(1, String.valueOf(id_docente));
                pst.setString(4, String.valueOf(cbano.getSelectedIndex() + 1));
                switch (cbclasse.getSelectedIndex()) {
                    case 0: {
                        pst.setString(2, "1");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                    case 1: {
                        pst.setString(2, "2");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                    case 2: {
                        pst.setString(2, "3");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                    case 3: {
                        pst.setString(2, "4");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                    case 4: {
                        pst.setString(2, "5");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                    case 5: {
                        pst.setString(2, "6");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                    case 6: {
                        pst.setString(2, "7");

                        if (cbturma.getSelectedIndex() == 0) {
                            pst.setString(3, "1");
                        } else if (cbturma.getSelectedIndex() == 1) {
                            pst.setString(3, "2");
                        }

                        break;
                    }
                }

                if (lblDirector.getText().equals("Turma sem D.T. atribuido")) {

                    String turma = cbturma.getSelectedItem().toString();
                    String classe = cbclasse.getSelectedItem().toString();

                    int row = tblcadeirasxdocentes.getSelectedRow();

                    int pergunta = JOptionPane.showConfirmDialog(rootPane, "Definir o Professor " + tblcadeirasxdocentes.getModel().getValueAt(row, 3).toString() + " como DT da turma " + turma + " da " + classe + " classe?", "Confirme", JOptionPane.YES_NO_OPTION);

                    if (pergunta == JOptionPane.YES_OPTION) {
                        int exec = pst.executeUpdate();

                        if (exec > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Definição sucedida");

                            selectDT();
                        } else {

                        }
                    }

                }

            } catch (ArrayIndexOutOfBoundsException e) {

            } catch (Exception e) {
                //JOptionPane.showMessageDialog(rootPane, "TelaTurma, Método AtribuirDT\n" + e);
                JOptionPane.showMessageDialog(rootPane, "Um Professor nao pode ser director de mais de uma classe");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Esta turma já tem um Director");
        }
    }

    private static String docentexcadeira;

    private void tblCadeiraXDocente() {

        if (cbclasse.getSelectedIndex() == 0 || cbclasse.getSelectedIndex() == 1) {
            docentexcadeira = "select c.id as ID, c.nome as Disciplina, d.id as ID, d.nome as Professor from docentexcadeira dxc inner join cadeira c on c.id = dxc.id_cadeira inner join docente d on d.id = dxc.id_docente where c.id = 1 or c.id = 2 or c.id = 3 or c.id = 9 order by c.id";
        } else if (cbclasse.getSelectedIndex() == 2 || cbclasse.getSelectedIndex() == 3) {
            docentexcadeira = "select c.id as ID, c.nome as Disciplina, d.id as ID, d.nome as Professor from docentexcadeira dxc inner join cadeira c on c.id = dxc.id_cadeira inner join docente d on d.id = dxc.id_docente where c.id = 1 or c.id = 2 or c.id = 3 or c.id = 9 or c.id = 5 order by c.id";
        } else if (cbclasse.getSelectedIndex() == 4) {
            docentexcadeira = "select c.id as ID, c.nome as Disciplina, d.id as ID, d.nome as Professor from docentexcadeira dxc inner join cadeira c on c.id = dxc.id_cadeira inner join docente d on d.id = dxc.id_docente where c.id = 1 or c.id = 2 or c.id = 3 or c.id = 9 or c.id = 5 or c.id = 6 or c.id = 4 or c.id = 7 order by c.id";
        } else if (cbclasse.getSelectedIndex() == 5 || cbclasse.getSelectedIndex() == 6) {
            docentexcadeira = "select c.id as ID, c.nome as Disciplina, d.id as ID, d.nome as Professor from docentexcadeira dxc inner join cadeira c on c.id = dxc.id_cadeira inner join docente d on d.id = dxc.id_docente where c.id = 1 or c.id = 2 or c.id = 3 or c.id = 9 or c.id = 5 or c.id = 6 or c.id = 4 or c.id = 7 or c.id = 10 or c.id = 8 order by c.id";
        }

        try {
            pst = conexao.prepareStatement(docentexcadeira);

            rs = pst.executeQuery();

            tblcadeirasxdocentes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, "TelaTurma, Método docentesxcadeiras\n" + e);
        }

    }

    private void apagarDT() {

        String apagar = "delete from docentexdt where id_docente = ?";
        if (lblDirector.getText().equals("Turma sem D.T. atribuido")) {

            JOptionPane.showMessageDialog(rootPane, "Esta turma ainda não tem um Director definido");

        } else {
            try {
                id_docente = Integer.parseInt(tblcadeirasxdocentes.getModel().getValueAt(tblcadeirasxdocentes.getSelectedRow(), 2).toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "Por favor, Selecione o D.T. primeiro");
            }
            try {
                pst = conexao.prepareStatement(apagar);

                pst.setString(1, String.valueOf(id_docente));

                String turma = cbturma.getSelectedItem().toString();
                String classe = cbclasse.getSelectedItem().toString();

                int row = tblcadeirasxdocentes.getSelectedRow();

                int pergunta = JOptionPane.showConfirmDialog(rootPane, "Excluir o D.T. " + tblcadeirasxdocentes.getModel().getValueAt(row, 3).toString() + " da turma " + turma + " da " + classe + " classe?", "Confirme", JOptionPane.YES_NO_OPTION);

                if (pergunta == JOptionPane.YES_OPTION) {
                    int exec = pst.executeUpdate();

                    if (exec > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Exclusão sucedida");

                        selectDT();
                    }
                }

            } catch (ArrayIndexOutOfBoundsException e) {

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "TelaTurma, Método apagarDT()\n" + e);
            }
        }
    }

    private void verTurma() {

        String command = "select dt.id_classe, t.turma from docentexdt dt inner join turma t on t.id = dt.id_turma where id_docente = ?";

        if (tblcadeirasxdocentes.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(rootPane, "Selecione o Professor na tabela");
        } else {

            id_docente = Integer.parseInt(tblcadeirasxdocentes.getModel().getValueAt(tblcadeirasxdocentes.getSelectedRow(), 2).toString());

            try {
                pst = conexao.prepareStatement(command);

                pst.setString(1, String.valueOf(id_docente));

                rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(rootPane, "Turma " + rs.getString("turma") + " da " + rs.getString("id_classe") + "ª Classe");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "O Professor não é D.T. de nenhuma turma ");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "TelaTurma, Método verTurma()\n" + e);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbclasse = new javax.swing.JComboBox();
        cbturma = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblalunos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbano = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblDirector = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblcadeirasxdocentes = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Turmas");
        setPreferredSize(new java.awt.Dimension(761, 550));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Turma");
        jLabel1.setPreferredSize(new java.awt.Dimension(73, 30));

        jLabel3.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Classe");

        cbclasse.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbclasse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "      1ª", "      2ª", "      3ª", "      4ª", "      5ª", "      6ª", "      7ª" }));
        cbclasse.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        cbturma.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbturma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "       A", "       B" }));
        cbturma.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbturmaItemStateChanged(evt);
            }
        });

        tblalunos.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        tblalunos.setForeground(new java.awt.Color(0, 102, 204));
        tblalunos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblalunos);

        jLabel6.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Alunos");

        jLabel5.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ano lectivo");
        jLabel5.setPreferredSize(new java.awt.Dimension(60, 30));

        cbano.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbano.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2022", "2023", "2024", "2025", "2026" }));
        cbano.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbanoItemStateChanged(evt);
            }
        });
        cbano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbanoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbclasse, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbano, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbturma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbano, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbclasse, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbturma, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Director da T.");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 30));

        lblDirector.setEditable(false);
        lblDirector.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        lblDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblDirectorActionPerformed(evt);
            }
        });

        tblcadeirasxdocentes.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        tblcadeirasxdocentes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblcadeirasxdocentes);

        jLabel4.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Professores por disciplina");
        jLabel4.setPreferredSize(new java.awt.Dimension(34, 30));

        jButton1.setText("Atribuir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Actualizar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setText("Apagar");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jButton2.setText("Ver turma");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDirector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jToggleButton1)
                    .addComponent(jToggleButton2)
                    .addComponent(jButton2))
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        setBounds(0, 0, 761, 550);
    }// </editor-fold>//GEN-END:initComponents

    private void cbclasseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbclasseItemStateChanged
        // TODO add your handling code here:
        alunos();
        selectDT();
        tblCadeiraXDocente();
    }//GEN-LAST:event_cbclasseItemStateChanged

    private void cbturmaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbturmaItemStateChanged
        // TODO add your handling code here:
        alunos();
        selectDT();
        tblCadeiraXDocente();
    }//GEN-LAST:event_cbturmaItemStateChanged

    private void cbclasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbclasseActionPerformed
        // TODO add your handling code here:
        selectDT();
        tblCadeiraXDocente();
    }//GEN-LAST:event_cbclasseActionPerformed

    private void lblDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblDirectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblDirectorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        atribuirDT();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        actualizarDT();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        apagarDT();
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        verTurma();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbanoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbanoItemStateChanged
        // TODO add your handling code here:
        alunos();
        selectDT();
    }//GEN-LAST:event_cbanoItemStateChanged

    private void cbanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbanoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbanoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbano;
    private javax.swing.JComboBox cbclasse;
    private javax.swing.JComboBox cbturma;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JTextField lblDirector;
    private javax.swing.JTable tblalunos;
    private javax.swing.JTable tblcadeirasxdocentes;
    // End of variables declaration//GEN-END:variables
}
