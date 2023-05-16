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
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Arlindo Halar
 */

public class TelaAluno extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaAluno
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;

    PreparedStatement pstturma = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    
    PreparedStatement pstid = null;
    ResultSet rsid = null;
    
    public TelaAluno() {
        initComponents();
        conexao = Conexao.conector();

        procurar_aluno();
    }
    private static String id;
    
    private void salvar() {
        String inserir = "insert into aluno (nome, senha, login, naturalidade, nacionalidade, idade, sexo, turma_id, encarregado, tel_encarregado, classe_id) values(?,?,?,?,?,?,?,?,?,?,?)";
        String turma = "select turma from turma where turma = ? and classe_id = ?, ano_id = ?";
        //String id_tuma = "insert into ";
        String id_turma = "select id from turma where turma = ? and classe_id = ? and id_ano = ?";
        //String id_turma = "select nome from aluno";
//String consultar = "";
        
        try {
            pstid = conexao.prepareStatement(id_turma);
            pstid.setString(3, String.valueOf(cbano.getSelectedIndex()+1));
            
            if(cbturma.getSelectedIndex() == 0){
                pstid.setString(1, "A");
            }
            else if(cbturma.getSelectedIndex() == 1){
                pstid.setString(1, "B");
            }
            
            if(cbclasse.getSelectedIndex() == 0){
                pstid.setString(2, "1");
            }
            else if(cbclasse.getSelectedIndex() == 1){
                pstid.setString(2, "2");
            }
            else if(cbclasse.getSelectedIndex() == 2){
                pstid.setString(2, "3");
            }else if(cbclasse.getSelectedIndex() == 3){
                pstid.setString(2, "4");
            }else if(cbclasse.getSelectedIndex() == 4){
                pstid.setString(2, "5");
            }else if(cbclasse.getSelectedIndex() == 5){
                pstid.setString(2, "6");
            }else if(cbclasse.getSelectedIndex() == 6){
                pstid.setString(2, "7");
            }
            
            rsid = pstid.executeQuery();
            if(rsid.next()){
                System.out.println(rsid.getString("id"));
                id = rsid.getString("id");
            }
            
//id = rs1.getString("id");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(rootPane, e + "\n Ao pegar o id da tuma");
        }
        
        
        try {
            pst = conexao.prepareStatement(inserir);
            
            pstturma = conexao.prepareStatement(turma);

            //pst.setString(12, String.valueOf(cbano.getSelectedIndex()+1));
            
            
            
            pst.setString(1, txtnome.getText());
            pst.setString(2, txtsenha.getText());
            pst.setString(3, txtusuario.getText().toLowerCase());
          
            //System.out.print(String.valueOf(cbnat.getSelectedItem())) ;
            //pst.setString(4, txtnat.getText());

            
            if (cbnat.getSelectedIndex() == 0) {
                pst.setString(4, "Maputo");
            } else if (cbnat.getSelectedIndex() == 1) {
                pst.setString(4, "Gaza");
            } else if (cbnat.getSelectedIndex() == 2) {
                pst.setString(4, "Inhambane");
            } else if (cbnat.getSelectedIndex() == 3) {
                pst.setString(4, "Sofala");
            } else if (cbnat.getSelectedIndex() == 4) {
                pst.setString(4, "Manica");
            } else if (cbnat.getSelectedIndex() == 5) {
                pst.setString(4, "Tete");
            } else if (cbnat.getSelectedIndex() == 6) {
                pst.setString(4, "Zambézia");
            } else if (cbnat.getSelectedIndex() == 7) {
                pst.setString(4, "Nampula");
            } else if (cbnat.getSelectedIndex() == 8) {
                pst.setString(4, "Niassa");
            } else if (cbnat.getSelectedIndex() == 9) {
                pst.setString(4, "Cabo Delgado");
            }

            pst.setString(5, txtnac.getText());
            pst.setString(6, txtidade.getText());

            //pstturma.setString(1, txtturma.getText().toUpperCase());
            if (rb2.isSelected()) {
                pst.setString(7, "F");
            } else if (rb1.isSelected()) {
                pst.setString(7, "M");
            }

            pst.setString(9, txtenc.getText());
            pst.setString(10, txttelenc.getText());
            pst.setString(8, id);
            if (cbclasse.getSelectedIndex() == 0) {
                pst.setString(11, "1");
                pstturma.setString(2, "1");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "1");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "2");
                }*/

            } else if (cbclasse.getSelectedIndex() == 1) {
                pst.setString(11, "2");
                pstturma.setString(2, "2");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "3");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "4");
                }*/

            } else if (cbclasse.getSelectedIndex() == 2) {
                pst.setString(11, "3");
                pstturma.setString(2, "3");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "5");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "6");
                }*/

            } else if (cbclasse.getSelectedIndex() == 3) {
                pst.setString(11, "4");
                pstturma.setString(2, "4");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "7");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "8");
                }*/

            } else if (cbclasse.getSelectedIndex() == 4) {
                pst.setString(11, "5");
                pstturma.setString(2, "5");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "9");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "10");
                }*/

            } else if (cbclasse.getSelectedIndex() == 5) {
                pst.setString(11, "6");
                pstturma.setString(2, "6");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "11");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "12");
                }*/

            } else if (cbclasse.getSelectedIndex() == 6) {
                pst.setString(11, "7");
                pstturma.setString(2, "7");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "13");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "14");
                }*/
            }

            //  rs = pstturma.executeQuery();
            //     if(rs.next()){
            if (txtnome.getText().isEmpty() || txtsenha.getText().isEmpty() || txtusuario.getText().isEmpty() || txtidade.getText().isEmpty() || /*rb1.isSelected() == false && rb2.isSelected() == false ||*/ buttonGroup1.getSelection() == null || txtenc.getText().isEmpty() || txttelenc.getText().isEmpty()) {

                JOptionPane.showMessageDialog(rootPane, "Encontrados campos não preenchidos!");
            } else {

                int matricular = JOptionPane.showConfirmDialog(null, "Matricular novo aluno?", "Comfirmação", JOptionPane.YES_NO_OPTION);
                if (matricular == JOptionPane.YES_OPTION) {
                    
                    int matriculado = pst.executeUpdate();

                    if (matriculado > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Aluno matriculado com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        txtnome.setText(null);
                        txtsenha.setText(null);
                        txtusuario.setText(null);
                        //txtnat.setText(null);
                        txtnac.setText(null);
                        txtidade.setText(null);
                        //txtturma.setText(null);
                        txtenc.setText(null);
                        txttelenc.setText(null);
                        buttonGroup1.clearSelection();
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Matrícula não sucedida", "Falha", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void procurar_aluno() {
        String sql = "select al.nome, al.sexo, cl.classe, t.turma from aluno al inner join turma t on t.id = al.turma_id inner join classe cl on cl.id = al.classe_id where nome like ? order by nome";
        String classe = "select * from classe";
        String turma = "select turma from turma where classe_id = ?";

        try {

            pst = conexao.prepareStatement(sql);
            pst1 = conexao.prepareStatement(classe);
            pst2 = conexao.prepareStatement(turma);

            pst.setString(1, txtprocurar.getText() + "%");

            rs = pst.executeQuery();
            rs1 = pst1.executeQuery();

            tblalunos.setModel(DbUtils.resultSetToTableModel(rs));

            while (rs1.next()) {
                cbclasse.addItem(rs1.getString("Classe") + " ª Classe");
            }

            if (cbclasse.getSelectedIndex() == 0) {
                pst2.setString(1, "1");
            } else if (cbclasse.getSelectedIndex() == 1) {
                pst2.setString(1, "2");
            } else if (cbclasse.getSelectedIndex() == 2) {
                pst2.setString(1, "3");
            } else if (cbclasse.getSelectedIndex() == 3) {
                pst2.setString(1, "4");
            } else if (cbclasse.getSelectedIndex() == 4) {
                pst2.setString(1, "5");
            } else if (cbclasse.getSelectedIndex() == 5) {
                pst2.setString(1, "6");
            } else if (cbclasse.getSelectedIndex() == 6) {
                pst2.setString(1, "7");
            }

            rs2 = pst2.executeQuery();

            while (rs2.next()) {

                if (rs2.getString("turma").equals(cbturma.getItemAt(0)) || rs2.getString("turma").equals(cbturma.getItemAt(1)) || rs2.getString("turma").equals(cbturma.getItemAt(2))) {

                } else {
                    cbturma.addItem(rs2.getString("turma"));
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void completar() {

        int completar = tblalunos.getSelectedRow();

        String sex = tblalunos.getModel().getValueAt(completar, 1).toString();

        txtnome.setText(tblalunos.getModel().getValueAt(completar, 0).toString());
        String clas = tblalunos.getModel().getValueAt(completar, 2).toString();

        String idade = "select * from aluno where nome = ?";

        String nom = tblalunos.getModel().getValueAt(completar, 0).toString();

        if (clas.equals("1")) {
            cbclasse.setSelectedIndex(0);

            if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("A")) {
                cbturma.setSelectedIndex(0);
            } else if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("B")) {
                cbturma.setSelectedIndex(1);
            }
        } else if (clas.equals("2")) {
            cbclasse.setSelectedIndex(1);

            if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("A")) {
                cbturma.setSelectedIndex(0);
            } else if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("B")) {
                cbturma.setSelectedIndex(1);
            }
        } else if (clas.equals("3")) {
            cbclasse.setSelectedIndex(2);

            if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("A")) {
                cbturma.setSelectedIndex(0);
            } else if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("B")) {
                cbturma.setSelectedIndex(1);
            }
        } else if (clas.equals("4")) {
            cbclasse.setSelectedIndex(3);

            if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("A")) {
                cbturma.setSelectedIndex(0);
            } else if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("B")) {
                cbturma.setSelectedIndex(1);
            }
        } else if (clas.equals("5")) {
            cbclasse.setSelectedIndex(4);

            if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("A")) {
                cbturma.setSelectedIndex(0);
            } else if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("B")) {
                cbturma.setSelectedIndex(1);
            }
        } else if (clas.equals("6")) {
            cbclasse.setSelectedIndex(5);

            if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("A")) {
                cbturma.setSelectedIndex(0);
            } else if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("B")) {
                cbturma.setSelectedIndex(1);
            }
        } else if (clas.equals("7")) {
            cbclasse.setSelectedIndex(6);

            if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("A")) {
                cbturma.setSelectedIndex(0);
            } else if (tblalunos.getModel().getValueAt(completar, 3).toString().equals("B")) {
                cbturma.setSelectedIndex(1);
            }
        }

        if (sex.equals("M")) {
            rb1.setSelected(true);
            rb2.setSelected(false);
        } else if (sex.equals("F")) {
            rb2.setSelected(true);
            rb1.setSelected(false);
        }

        try {
            pst = conexao.prepareStatement(idade);

            pst.setString(1, nom);

            rs = pst.executeQuery();

            while (rs.next()) {

                txtidade.setText(rs.getString("idade"));

                if (rs.getString("nacionalidade").isEmpty()) {
                    txtnac.setText("Moçambique");
                } else {
                    txtnac.setText(rs.getString("nacionalidade"));
                }

                if (rs.getString("naturalidade").equals("Maputo")) {
                    cbnat.setSelectedIndex(0);
                } else if (rs.getString("naturalidade").toLowerCase().equals("gaza")) {
                    cbnat.setSelectedIndex(1);
                } else if (rs.getString("naturalidade").toLowerCase().equals("inhambane")) {
                    cbnat.setSelectedIndex(2);
                } else if (rs.getString("naturalidade").toLowerCase().equals("sofala")) {
                    cbnat.setSelectedIndex(3);
                } else if (rs.getString("naturalidade").toLowerCase().equals("manica")) {
                    cbnat.setSelectedIndex(4);
                } else if (rs.getString("naturalidade").toLowerCase().equals("tete")) {
                    cbnat.setSelectedIndex(5);
                } else if (rs.getString("naturalidade").toLowerCase().equals("zambézia")) {
                    cbnat.setSelectedIndex(6);
                } else if (rs.getString("naturalidade").toLowerCase().equals("nampula")) {
                    cbnat.setSelectedIndex(7);
                } else if (rs.getString("naturalidade").toLowerCase().equals("niassa")) {
                    cbnat.setSelectedIndex(8);
                } else if (rs.getString("naturalidade").toLowerCase().equals("cabo delgado")) {
                    cbnat.setSelectedIndex(9);
                }

                txtenc.setText(rs.getString("encarregado"));
                txttelenc.setText(rs.getString("tel_encarregado"));
                txtsenha.setText(rs.getString("senha"));
                txtusuario.setText(rs.getString("login"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void remover(){

        if (tblalunos.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(null, "Selecione antes um aluno na tabela para remover");
        } 
        else{
            int completar = tblalunos.getSelectedRow();
        
        String nom = tblalunos.getModel().getValueAt(completar, 0).toString();

        String deletar = "delete from aluno where nome = ?";

        try {
            pst = conexao.prepareStatement(deletar);

            pst.setString(1, nom);

            int remover = JOptionPane.showConfirmDialog(null, "Remover aluno " + nom + "?", "Comfirmação", JOptionPane.YES_NO_OPTION);
            if (remover == JOptionPane.YES_OPTION) {
                int removido = pst.executeUpdate();

                if (removido > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Aluno removido com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    txtnome.setText(null);
                    txtidade.setText(null);
                    txtnac.setText(null);
                    txtenc.setText(null);
                    txttelenc.setText(null);
                    txtsenha.setText(null);
                    txtusuario.setText(null);
                    buttonGroup1.clearSelection();
                }
            }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        }
        
        
    }

    public void actualizar() {

        String actualizar = "update aluno set nome = ?, senha = ?, login = ?, naturalidade = ?, nacionalidade = ?, idade = ?, sexo = ?, turma_id = ?, encarregado = ?, tel_encarregado = ?, classe_id = ? where id = ?";
        String ident = "select id from aluno where nome = ?";

        if(tblalunos.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(rootPane, "Selecione antes um aluno na tabela para actualizar");
        } 
        else{
            int tabela = tblalunos.getSelectedRow();

        String nom = tblalunos.getModel().getValueAt(tabela, 0).toString();

        
        String id_turma = "select id from turma where turma = ? and classe_id = ? and id_ano = ?";
        //String id_turma = "select nome from aluno";
//String consultar = "";
        
        try {
            pstid = conexao.prepareStatement(id_turma);
            pstid.setString(3, String.valueOf(cbano.getSelectedIndex()+1));
            
            if(cbturma.getSelectedIndex() == 0){
                pstid.setString(1, "A");
            }
            else if(cbturma.getSelectedIndex() == 1){
                pstid.setString(1, "B");
            }
            
            if(cbclasse.getSelectedIndex() == 0){
                pstid.setString(2, "1");
            }
            else if(cbclasse.getSelectedIndex() == 1){
                pstid.setString(2, "2");
            }
            else if(cbclasse.getSelectedIndex() == 2){
                pstid.setString(2, "3");
            }else if(cbclasse.getSelectedIndex() == 3){
                pstid.setString(2, "4");
            }else if(cbclasse.getSelectedIndex() == 4){
                pstid.setString(2, "5");
            }else if(cbclasse.getSelectedIndex() == 5){
                pstid.setString(2, "6");
            }else if(cbclasse.getSelectedIndex() == 6){
                pstid.setString(2, "7");
            }
            
            rsid = pstid.executeQuery();
            if(rsid.next()){
                System.out.println(rsid.getString("id"));
                id = rsid.getString("id");
            }
            
//id = rs1.getString("id");
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(rootPane, e + "\n Ao pegar o id da tuma");
        }
        
        
        try {
            pst = conexao.prepareStatement(actualizar);
            pst1 = conexao.prepareStatement(ident);

            pst.setString(1, txtnome.getText());
            pst.setString(2, txtsenha.getText());
            pst.setString(3, txtusuario.getText());

            pst1.setString(1, nom);

            if (cbnat.getSelectedIndex() == 0) {
                pst.setString(4, "Maputo");
            } else if (cbnat.getSelectedIndex() == 1) {
                pst.setString(4, "Gaza");
            } else if (cbnat.getSelectedIndex() == 2) {
                pst.setString(4, "Inhambane");
            } else if (cbnat.getSelectedIndex() == 3) {
                pst.setString(4, "Sofala");
            } else if (cbnat.getSelectedIndex() == 4) {
                pst.setString(4, "Manica");
            } else if (cbnat.getSelectedIndex() == 5) {
                pst.setString(4, "Tete");
            } else if (cbnat.getSelectedIndex() == 6) {
                pst.setString(4, "Zambézia");
            } else if (cbnat.getSelectedIndex() == 7) {
                pst.setString(4, "Nampula");
            } else if (cbnat.getSelectedIndex() == 8) {
                pst.setString(4, "Niassa");
            } else if (cbnat.getSelectedIndex() == 9) {
                pst.setString(4, "Cabo Delgado");
            }

            pst.setString(5, txtnac.getText());
            pst.setString(6, txtidade.getText());
            pst.setString(8, id);
            if (rb2.isSelected()) {
                pst.setString(7, "F");
            } else if (rb1.isSelected()) {
                pst.setString(7, "M");
            }

            pst.setString(9, txtenc.getText());
            pst.setString(10, txttelenc.getText());

            if (cbclasse.getSelectedIndex() == 0) {
                pst.setString(11, "1");
                //pstturma.setString(2, "1");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "1");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "2");
                }*/

            } else if (cbclasse.getSelectedIndex() == 1) {
                pst.setString(11, "2");
                //pstturma.setString(2, "2");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "3");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "4");
                }*/

            } else if (cbclasse.getSelectedIndex() == 2) {
                pst.setString(11, "3");
                //pstturma.setString(2, "3");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "5");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "6");
                }*/

            } else if (cbclasse.getSelectedIndex() == 3) {
                pst.setString(11, "4");
                //pstturma.setString(2, "4");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "7");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "8");
                }*/

            } else if (cbclasse.getSelectedIndex() == 4) {
                pst.setString(11, "5");
                //pstturma.setString(2, "5");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "9");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "10");
                }*/

            } else if (cbclasse.getSelectedIndex() == 5) {
                pst.setString(11, "6");
                //pstturma.setString(2, "6");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "11");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "12");
                }*/

            } else if (cbclasse.getSelectedIndex() == 6) {
                pst.setString(11, "7");
                //pstturma.setString(2, "7");
                /*if (cbturma.getSelectedIndex() == 0) {
                    pst.setString(8, "13");
                } else if (cbturma.getSelectedIndex() == 1) {
                    pst.setString(8, "14");
                }*/
            }

            rs = pst1.executeQuery();

            if (rs.next()) {
                pst.setString(12, rs.getString(1));
            }

            if (txtnome.getText().isEmpty() || txtsenha.getText().isEmpty() || txtusuario.getText().isEmpty() || txtidade.getText().isEmpty() || buttonGroup1.getSelection() == null || txtenc.getText().isEmpty() || txttelenc.getText().isEmpty()) {

                JOptionPane.showMessageDialog(rootPane, "Encontrados campos não preenchidos!");
            } else {

                int actualize = JOptionPane.showConfirmDialog(null, "Actualizar aluno " + txtnome.getText() + "?", "Comfirmação", JOptionPane.YES_NO_OPTION);
                if (actualize == JOptionPane.YES_OPTION) {
                    
                    int actualizado = pst.executeUpdate();

                    if (actualizado > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Aluno actualizado com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        txtnome.setText(null);
                        txtsenha.setText(null);
                        txtusuario.setText(null);
                        txtnac.setText(null);
                        txtidade.setText(null);
                        txtenc.setText(null);
                        txttelenc.setText(null);
                        buttonGroup1.clearSelection();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtnome = new javax.swing.JTextField();
        txtidade = new javax.swing.JTextField();
        txtnac = new javax.swing.JTextField();
        txttelenc = new javax.swing.JTextField();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        txtenc = new javax.swing.JTextField();
        cbnat = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtsenha = new javax.swing.JTextField();
        txtusuario = new javax.swing.JTextField();
        cbturma = new javax.swing.JComboBox();
        cbclasse = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        cbano = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtprocurar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblalunos = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        btnlimpar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Registros de alunos");
        setPreferredSize(new java.awt.Dimension(700, 400));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DADOS PESSOAIS");

        jLabel3.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nome");
        jLabel3.setPreferredSize(new java.awt.Dimension(110, 30));

        jLabel4.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Sexo");
        jLabel4.setPreferredSize(new java.awt.Dimension(110, 20));

        jLabel5.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Idade");
        jLabel5.setPreferredSize(new java.awt.Dimension(110, 30));

        jLabel6.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nacionalidade");
        jLabel6.setPreferredSize(new java.awt.Dimension(110, 30));

        jLabel7.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Naturalidade");
        jLabel7.setPreferredSize(new java.awt.Dimension(110, 30));

        jLabel8.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Encarregado");
        jLabel8.setPreferredSize(new java.awt.Dimension(110, 30));

        jLabel9.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 204));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tel. encarregado");
        jLabel9.setPreferredSize(new java.awt.Dimension(110, 20));

        txtnome.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtnome.setForeground(new java.awt.Color(255, 153, 0));
        txtnome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnome.setPreferredSize(new java.awt.Dimension(200, 30));

        txtidade.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtidade.setForeground(new java.awt.Color(255, 153, 0));
        txtidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtidade.setPreferredSize(new java.awt.Dimension(110, 30));

        txtnac.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtnac.setForeground(new java.awt.Color(255, 153, 0));
        txtnac.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnac.setText("Moçambique");
        txtnac.setPreferredSize(new java.awt.Dimension(110, 30));

        txttelenc.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txttelenc.setForeground(new java.awt.Color(255, 153, 0));
        txttelenc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttelenc.setPreferredSize(new java.awt.Dimension(200, 20));

        buttonGroup1.add(rb1);
        rb1.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        rb1.setForeground(new java.awt.Color(255, 153, 0));
        rb1.setText("   M   ");

        buttonGroup1.add(rb2);
        rb2.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        rb2.setForeground(new java.awt.Color(255, 153, 0));
        rb2.setText("   F");

        txtenc.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtenc.setForeground(new java.awt.Color(255, 153, 0));
        txtenc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtenc.setPreferredSize(new java.awt.Dimension(200, 20));

        cbnat.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbnat.setForeground(new java.awt.Color(255, 153, 0));
        cbnat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "                            Maputo", "                              Gaza", "                         Inhambane", "                             Sofala", "                            Manica", "                              Tete", "                          Zambézia", "                           Nampula", "                             Niassa", "                       Cabo Delgado" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttelenc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtenc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbnat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(rb1)
                        .addGap(18, 18, 18)
                        .addComponent(rb2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb2)
                    .addComponent(rb1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbnat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtenc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelenc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ACADÉMICO");

        jLabel10.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 204));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Turma");
        jLabel10.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel11.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Usuário");
        jLabel11.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel12.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Classe");
        jLabel12.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel13.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 204));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Senha");
        jLabel13.setPreferredSize(new java.awt.Dimension(50, 20));

        txtsenha.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtsenha.setForeground(new java.awt.Color(255, 153, 0));
        txtsenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsenha.setPreferredSize(new java.awt.Dimension(193, 30));
        txtsenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsenhaActionPerformed(evt);
            }
        });

        txtusuario.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtusuario.setForeground(new java.awt.Color(255, 153, 0));
        txtusuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtusuario.setPreferredSize(new java.awt.Dimension(80, 20));

        cbturma.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbturma.setForeground(new java.awt.Color(255, 153, 0));

        cbclasse.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbclasse.setForeground(new java.awt.Color(255, 153, 0));

        jLabel16.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 204));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Ano Lectivo");
        jLabel16.setPreferredSize(new java.awt.Dimension(50, 30));

        cbano.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbano.setForeground(new java.awt.Color(255, 153, 0));
        cbano.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2022", "2023", "2024", "2025", "2026" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtusuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbclasse, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbturma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbano, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbclasse, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbturma, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbano))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(255, 153, 0));
        jButton1.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/add.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 102, 204));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/arrow_refresh.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 153, 0));
        jButton3.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 102, 204));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/delete.png"))); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 204));
        jLabel14.setText("Nome");

        txtprocurar.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        txtprocurar.setForeground(new java.awt.Color(255, 153, 0));
        txtprocurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprocurarActionPerformed(evt);
            }
        });
        txtprocurar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprocurarKeyReleased(evt);
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
        tblalunos.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblalunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblalunosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblalunos);

        jLabel15.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 204));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Painel de Procura");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtprocurar, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtprocurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnlimpar.setBackground(new java.awt.Color(255, 153, 0));
        btnlimpar.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        btnlimpar.setForeground(new java.awt.Color(0, 102, 204));
        btnlimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/application_form_delete.png"))); // NOI18N
        btnlimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnlimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnlimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 761, 550);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        salvar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtprocurarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprocurarKeyReleased
        // TODO add your handling code here:

        procurar_aluno();
    }//GEN-LAST:event_txtprocurarKeyReleased

    private void tblalunosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblalunosMouseClicked
        // TODO add your handling code here:
        completar();
    }//GEN-LAST:event_tblalunosMouseClicked

    private void txtprocurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprocurarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprocurarActionPerformed

    private void btnlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimparActionPerformed
        // TODO add your handling code here:
        txtnome.setText(null);
        txtidade.setText(null);
        txtnac.setText(null);
        txtenc.setText(null);
        txttelenc.setText(null);
        txtsenha.setText(null);
        txtusuario.setText(null);

    }//GEN-LAST:event_btnlimparActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        remover();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        actualizar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtsenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsenhaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlimpar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbano;
    private javax.swing.JComboBox cbclasse;
    private javax.swing.JComboBox cbnat;
    private javax.swing.JComboBox cbturma;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JTable tblalunos;
    private javax.swing.JTextField txtenc;
    private javax.swing.JTextField txtidade;
    private javax.swing.JTextField txtnac;
    private javax.swing.JTextField txtnome;
    private javax.swing.JTextField txtprocurar;
    private javax.swing.JTextField txtsenha;
    private javax.swing.JTextField txttelenc;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
