package telas;

import java.sql.*;
import Classes.Conexao;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class TelaDocente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    PreparedStatement pst3 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;

    public TelaDocente() {
        initComponents();

        conexao = Conexao.conector();

        procurar_docente();
    }

    private void procurar_docente() {

        String sql = "select id, nome, sexo, idade, telefone from docente where nome like ? order by nome";
        String classe = "select * from classe";
        String turma = "select turma from turma where classe_id = ?";

        try {

            pst = conexao.prepareStatement(sql);
            pst1 = conexao.prepareStatement(classe);
            pst2 = conexao.prepareStatement(turma);

            pst.setString(1, txtnome1.getText() + "%");

            rs = pst.executeQuery();
            rs1 = pst1.executeQuery();

            tbldocente.setModel(DbUtils.resultSetToTableModel(rs));

            while (rs1.next()) {
                cbclasse.addItem("   " + rs1.getString("Classe") + " ª");
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

    private void completar() {

        int nome = tbldocente.getSelectedRow();

        txtnome.setText(tbldocente.getModel().getValueAt(nome, 1).toString());
        String sex = tbldocente.getModel().getValueAt(nome, 2).toString();
        txtidade.setText(tbldocente.getModel().getValueAt(nome, 3).toString());
        txttel.setText(tbldocente.getModel().getValueAt(nome, 4).toString());

        String nom = tbldocente.getModel().getValueAt(nome, 1).toString();

        if (sex.equals("M")) {
            rb1.setSelected(true);
        } else if (sex.equals("F")) {
            rb2.setSelected(true);
        }

        String cadeira = "select ca.nome as Disciplina from docentexcadeira inner join cadeira ca on ca.id = id_cadeira where id_docente = ?";
        String dados = "select * from docente where nome = ?";
        String ident = "select id from docente where nome = ?";
        String director = "select cl.classe, tu.turma from docentexdt inner join classe cl on cl.id = id_classe inner join turma tu on tu.id = id_turma where id_docente = ?";

        try {

            pst = conexao.prepareStatement(director);
            pst1 = conexao.prepareStatement(ident);
            pst2 = conexao.prepareStatement(dados);
            pst3 = conexao.prepareStatement(cadeira);

            pst1.setString(1, nom);
            pst2.setString(1, nom);

            rs1 = pst1.executeQuery();

            rs2 = pst2.executeQuery();

            if (rs1.next()) {
                pst.setString(1, rs1.getString(1));
                pst3.setString(1, rs1.getString(1));
            }

            rs = pst.executeQuery();
            rs3 = pst3.executeQuery();

            //while (rs3.next()){
            tblcadeiras.setModel(DbUtils.resultSetToTableModel(rs3));
            //}
            while (rs.next()) {

                /*for(int i = 1; i <= 7; i++){
                    
                 if(i.equals(rs.getString("classe"))){
                 cbclasse.setSelectedIndex(i-1);
                        
                 if(rs.getString("turma").equals("A")){
                 cbturma.setSelectedIndex(0);
                 }
                 if(rs.getString("turma").equals("B")){
                 cbturma.setSelectedIndex(1);
                 }
                 }    
                 
                 }*/
                if ("1".equals(rs.getString("classe"))) {
                    cbclasse.setSelectedIndex(0);

                    if (rs.getString("turma").equals("A")) {
                        cbturma.setSelectedIndex(0);
                    }
                    if (rs.getString("turma").equals("B")) {
                        cbturma.setSelectedIndex(1);
                    }
                } else if ("2".equals(rs.getString("classe"))) {
                    cbclasse.setSelectedIndex(1);

                    if (rs.getString("turma").equals("A")) {
                        cbturma.setSelectedIndex(0);
                    }
                    if (rs.getString("turma").equals("B")) {
                        cbturma.setSelectedIndex(1);
                    }
                } else if ("3".equals(rs.getString("classe"))) {
                    cbclasse.setSelectedIndex(2);

                    if (rs.getString("turma").equals("A")) {
                        cbturma.setSelectedIndex(0);
                    }
                    if (rs.getString("turma").equals("B")) {
                        cbturma.setSelectedIndex(1);
                    }
                } else if ("4".equals(rs.getString("classe"))) {
                    cbclasse.setSelectedIndex(3);

                    if (rs.getString("turma").equals("A")) {
                        cbturma.setSelectedIndex(0);
                    }
                    if (rs.getString("turma").equals("B")) {
                        cbturma.setSelectedIndex(1);
                    }
                } else if ("5".equals(rs.getString("classe"))) {
                    cbclasse.setSelectedIndex(4);

                    if (rs.getString("turma").equals("A")) {
                        cbturma.setSelectedIndex(0);
                    }
                    if (rs.getString("turma").equals("B")) {
                        cbturma.setSelectedIndex(1);
                    }
                } else if ("6".equals(rs.getString("classe"))) {
                    cbclasse.setSelectedIndex(5);

                    if (rs.getString("turma").equals("A")) {
                        cbturma.setSelectedIndex(0);
                    }
                    if (rs.getString("turma").equals("B")) {
                        cbturma.setSelectedIndex(1);
                    }
                } else if ("7".equals(rs.getString("classe"))) {
                    cbclasse.setSelectedIndex(6);

                    if (rs.getString("turma").equals("A")) {
                        cbturma.setSelectedIndex(0);
                    }
                    if (rs.getString("turma").equals("B")) {
                        cbturma.setSelectedIndex(1);
                    }
                }

            }

            while (rs2.next()) {

                txtnac.setText(rs2.getString("nacionalidade"));
                txtsalario.setText(rs2.getString("salario"));
                txtusu.setText(rs2.getString("login"));
                txtsenha.setText(rs2.getString("senha"));

                if (rs2.getString("naturalidade").equals("Maputo")) {
                    cbnat.setSelectedIndex(0);
                } else if (rs2.getString("naturalidade").toLowerCase().equals("gaza")) {
                    cbnat.setSelectedIndex(1);
                } else if (rs2.getString("naturalidade").toLowerCase().equals("inhambane")) {
                    cbnat.setSelectedIndex(2);
                } else if (rs2.getString("naturalidade").toLowerCase().equals("sofala")) {
                    cbnat.setSelectedIndex(3);
                } else if (rs2.getString("naturalidade").toLowerCase().equals("manica")) {
                    cbnat.setSelectedIndex(4);
                } else if (rs2.getString("naturalidade").toLowerCase().equals("tete")) {
                    cbnat.setSelectedIndex(5);
                } else if (rs2.getString("naturalidade").toLowerCase().equals("zambézia")) {
                    cbnat.setSelectedIndex(6);
                } else if (rs2.getString("naturalidade").toLowerCase().equals("nampula")) {
                    cbnat.setSelectedIndex(7);
                } else if (rs2.getString("naturalidade").toLowerCase().equals("niassa")) {
                    cbnat.setSelectedIndex(8);
                } else if (rs2.getString("naturalidade").toLowerCase().equals("cabo delgado")) {
                    cbnat.setSelectedIndex(9);
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    private void cadastrar() {

        String inserir = "insert into docente (nome, idade, login, senha, nacionalidade, naturalidade, sexo, salario, telefone) values(?,?,?,?,?,?,?,?,?)";

        try {

            pst = conexao.prepareStatement(inserir);

            pst.setString(1, txtnome.getText());

            if (rb1.isSelected()) {
                pst.setString(7, "M");
            } else if (rb2.isSelected()) {
                pst.setString(7, "F");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Escolha o sexo");
            }

            pst.setString(2, txtidade.getText());
            pst.setString(3, txtusu.getText());
            pst.setString(4, txtsenha.getText());
            pst.setString(5, txtnac.getText());

            if (cbnat.getSelectedIndex() == 0) {
                pst.setString(6, "Maputo");
            } else if (cbnat.getSelectedIndex() == 1) {
                pst.setString(6, "Gaza");
            } else if (cbnat.getSelectedIndex() == 2) {
                pst.setString(6, "Inhambane");
            } else if (cbnat.getSelectedIndex() == 3) {
                pst.setString(6, "Sofala");
            } else if (cbnat.getSelectedIndex() == 4) {
                pst.setString(6, "Manica");
            } else if (cbnat.getSelectedIndex() == 5) {
                pst.setString(6, "Tete");
            } else if (cbnat.getSelectedIndex() == 6) {
                pst.setString(6, "Zambezia");
            } else if (cbnat.getSelectedIndex() == 7) {
                pst.setString(6, "Nampula");
            } else if (cbnat.getSelectedIndex() == 8) {
                pst.setString(6, "Niassa");
            } else if (cbnat.getSelectedIndex() == 9) {
                pst.setString(6, "Cabo Delgado");
            }

            pst.setString(8, txtsalario.getText());
            pst.setString(9, txttel.getText());

            if (txtnome.getText().isEmpty() || txtsenha.getText().isEmpty() || txtusu.getText().isEmpty() || txtnac.getText().isEmpty() || txtidade.getText().isEmpty() || txtsalario.getText().isEmpty() || txttel.getText().isEmpty()) {

                JOptionPane.showMessageDialog(rootPane, "Encontrados campos vazios ou não selecionados");

            } else {

                int add = JOptionPane.showConfirmDialog(null, "Registar novo Professor? ", "Cadastro", JOptionPane.YES_NO_OPTION);
                if (add == JOptionPane.YES_OPTION) {

                    int added = pst.executeUpdate();

                    if (added > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Professor registado com Sucesso", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                        txtnome.setText(null);
                        txtsenha.setText(null);
                        txtusu.setText(null);

                        txtnac.setText(null);
                        txtidade.setText(null);

                        txtsalario.setText(null);
                        txttel.setText(null);
                        buttonGroup1.clearSelection();

                        cbnat.setSelectedIndex(0);
                    }
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    private void remover() {

        int id = tbldocente.getSelectedRow();

        String remover = "delete from docente where id = ?";

        try {
            pst = conexao.prepareStatement(remover);

            pst.setString(1, tbldocente.getModel().getValueAt(id, 0).toString());

            int apagar = JOptionPane.showConfirmDialog(null, "Remover Professor  " + tbldocente.getModel().getValueAt(id, 1).toString() + "? ", "Remover Professor ", JOptionPane.YES_NO_OPTION);

            if (apagar == JOptionPane.YES_OPTION) {

                int apagado = pst.executeUpdate();

                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Professor removido com Sucesso ");

                    txtnome.setText(null);
                    txtsenha.setText(null);
                    txtusu.setText(null);

                    txtnac.setText(null);
                    txtidade.setText(null);

                    txtsalario.setText(null);
                    txttel.setText(null);
                    buttonGroup1.clearSelection();

                    cbnat.setSelectedIndex(0);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    private void actualizar() {

        int tbl = tbldocente.getSelectedRow();

        String id = tbldocente.getModel().getValueAt(tbl, 0).toString();

        String act = "update docente set nome = ?, idade = ?, login = ?, senha = ?, nacionalidade = ?, naturalidade = ?, sexo = ?, salario = ?, telefone = ? where id = ?";

        String nome = tbldocente.getModel().getValueAt(tbl, 1).toString();

        try {
            pst = conexao.prepareStatement(act);

            pst.setString(1, txtnome.getText());

            if (rb1.isSelected()) {
                pst.setString(7, "M");
            } else if (rb2.isSelected()) {
                pst.setString(7, "F");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Selecione o sexo");
            }

            pst.setString(2, txtidade.getText());
            pst.setString(3, txtusu.getText());
            pst.setString(4, txtsenha.getText());
            pst.setString(5, txtnac.getText());

            if (cbnat.getSelectedIndex() == 0) {
                pst.setString(6, "Maputo");
            } else if (cbnat.getSelectedIndex() == 1) {
                pst.setString(6, "Gaza");
            } else if (cbnat.getSelectedIndex() == 2) {
                pst.setString(6, "Inhambane");
            } else if (cbnat.getSelectedIndex() == 3) {
                pst.setString(6, "Sofala");
            } else if (cbnat.getSelectedIndex() == 4) {
                pst.setString(6, "Manica");
            } else if (cbnat.getSelectedIndex() == 5) {
                pst.setString(6, "Tete");
            } else if (cbnat.getSelectedIndex() == 6) {
                pst.setString(6, "Zambezia");
            } else if (cbnat.getSelectedIndex() == 7) {
                pst.setString(6, "Nampula");
            } else if (cbnat.getSelectedIndex() == 8) {
                pst.setString(6, "Niassa");
            } else if (cbnat.getSelectedIndex() == 9) {
                pst.setString(6, "Cabo Delgado");
            }

            pst.setString(8, txtsalario.getText());
            pst.setString(9, txttel.getText());

            pst.setString(10, id);

            if (txtnome.getText().isEmpty() || txtsenha.getText().isEmpty() || txtusu.getText().isEmpty() || txtnac.getText().isEmpty() || txtidade.getText().isEmpty() || txtsalario.getText().isEmpty() || txttel.getText().isEmpty()) {

                JOptionPane.showMessageDialog(rootPane, "Encontrados campos vazios ou não selecionados");

            } else {

                int actualizar = JOptionPane.showConfirmDialog(null, "Actualizar Professor " + nome + "? ", "Actualização", JOptionPane.YES_NO_OPTION);
                if (actualizar == JOptionPane.YES_OPTION) {

                    int actualizado = pst.executeUpdate();

                    if (actualizado > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Professor actualizado com Sucesso", "Actualização", JOptionPane.INFORMATION_MESSAGE);
                        txtnome.setText(null);
                        txtsenha.setText(null);
                        txtusu.setText(null);

                        txtnac.setText(null);
                        txtidade.setText(null);

                        txtsalario.setText(null);
                        txttel.setText(null);
                        buttonGroup1.clearSelection();

                        cbnat.setSelectedIndex(0);
                    }
                }

            }

        } catch (Exception e) {
        }
    }

    private void limpar() {

        int limp = JOptionPane.showConfirmDialog(null, "Limpar campos?", "Limpar", JOptionPane.YES_NO_OPTION);

        if (limp == JOptionPane.YES_OPTION) {
            txtnome.setText(null);
            txtsenha.setText(null);
            txtusu.setText(null);

            txtnac.setText(null);
            txtidade.setText(null);

            txtsalario.setText(null);
            txttel.setText(null);
            buttonGroup1.clearSelection();

            cbnat.setSelectedIndex(0);
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
        jLabel1 = new javax.swing.JLabel();
        txtnome1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldocente = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtidade = new javax.swing.JTextField();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txtnac = new javax.swing.JTextField();
        cbnat = new javax.swing.JComboBox();
        txtsalario = new javax.swing.JTextField();
        txttel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblcadeiras = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbclasse = new javax.swing.JComboBox();
        cbturma = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtusu = new javax.swing.JTextField();
        txtsenha = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Registro de Professores");
        setPreferredSize(new java.awt.Dimension(761, 550));

        jLabel1.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nome");
        jLabel1.setPreferredSize(new java.awt.Dimension(27, 30));

        txtnome1.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtnome1.setForeground(new java.awt.Color(255, 153, 0));
        txtnome1.setPreferredSize(new java.awt.Dimension(250, 30));
        txtnome1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnome1KeyReleased(evt);
            }
        });

        tbldocente.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        tbldocente.setForeground(new java.awt.Color(255, 153, 0));
        tbldocente.setModel(new javax.swing.table.DefaultTableModel(
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
        tbldocente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldocenteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbldocente);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DADOS PESSOAIS");
        jLabel2.setPreferredSize(new java.awt.Dimension(34, 30));

        jLabel4.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nome");
        jLabel4.setPreferredSize(new java.awt.Dimension(27, 30));

        txtnome.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtnome.setForeground(new java.awt.Color(255, 153, 0));
        txtnome.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Sexo");
        jLabel5.setPreferredSize(new java.awt.Dimension(0, 30));

        txtidade.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtidade.setForeground(new java.awt.Color(255, 153, 0));
        txtidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtidade.setPreferredSize(new java.awt.Dimension(59, 30));

        buttonGroup1.add(rb1);
        rb1.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        rb1.setForeground(new java.awt.Color(255, 153, 0));
        rb1.setText("Masculino");
        rb1.setPreferredSize(new java.awt.Dimension(100, 30));

        buttonGroup1.add(rb2);
        rb2.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        rb2.setForeground(new java.awt.Color(255, 153, 0));
        rb2.setText("Feminino");
        rb2.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel6.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Idade");
        jLabel6.setPreferredSize(new java.awt.Dimension(34, 30));

        txtnac.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtnac.setForeground(new java.awt.Color(255, 153, 0));
        txtnac.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cbnat.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbnat.setForeground(new java.awt.Color(255, 153, 0));
        cbnat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "                            Maputo", "                              Gaza", "                         Inhambane", "                             Sofala", "                            Manica", "                              Tete", "                          Zambézia", "                           Nampula", "                             Niassa", "                       Cabo Delgado" }));
        cbnat.setPreferredSize(new java.awt.Dimension(56, 30));

        txtsalario.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtsalario.setForeground(new java.awt.Color(255, 153, 0));
        txtsalario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txttel.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txttel.setForeground(new java.awt.Color(255, 153, 0));
        txttel.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Nacionalidade");
        jLabel7.setPreferredSize(new java.awt.Dimension(70, 30));

        jLabel8.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Naturalidade");
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 30));

        jLabel9.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 204));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Salário");
        jLabel9.setPreferredSize(new java.awt.Dimension(70, 30));

        jLabel10.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 204));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Telefone");
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(rb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(rb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txttel, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtsalario, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cbnat, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addComponent(txtnac, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtnome)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbnat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ACADEMICO");
        jLabel3.setPreferredSize(new java.awt.Dimension(0, 30));

        tblcadeiras.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        tblcadeiras.setForeground(new java.awt.Color(255, 153, 0));
        tblcadeiras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblcadeiras);

        jLabel11.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Classe");

        jLabel12.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Turma");

        cbclasse.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbclasse.setForeground(new java.awt.Color(255, 153, 0));
        cbclasse.setEnabled(false);
        cbclasse.setPreferredSize(new java.awt.Dimension(56, 30));
        cbclasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbclasseActionPerformed(evt);
            }
        });

        cbturma.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbturma.setForeground(new java.awt.Color(255, 153, 0));
        cbturma.setEnabled(false);
        cbturma.setPreferredSize(new java.awt.Dimension(56, 30));

        jLabel13.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 204));
        jLabel13.setText("Director...");
        jLabel13.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel14.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 204));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Usuário");
        jLabel14.setPreferredSize(new java.awt.Dimension(57, 30));

        jLabel15.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 204));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Senha");
        jLabel15.setPreferredSize(new java.awt.Dimension(57, 30));

        jButton1.setBackground(new java.awt.Color(255, 153, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/arrow_refresh.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/delete.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 153, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/add.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 153, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/application_form_delete.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtusu.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtusu.setForeground(new java.awt.Color(255, 153, 0));
        txtusu.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtsenha.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtsenha.setForeground(new java.awt.Color(255, 153, 0));
        txtsenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbclasse, 0, 70, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbturma, 0, 71, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtusu)
                            .addComponent(txtsenha))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbclasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbturma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnome1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setBounds(0, 0, 761, 550);
    }// </editor-fold>//GEN-END:initComponents

    private void txtnome1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnome1KeyReleased
        // TODO add your handling code here:
        procurar_docente();
    }//GEN-LAST:event_txtnome1KeyReleased

    private void tbldocenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldocenteMouseClicked
        // TODO add your handling code here:
        completar();
    }//GEN-LAST:event_tbldocenteMouseClicked

    private void cbclasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbclasseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbclasseActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        cadastrar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        actualizar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbclasse;
    private javax.swing.JComboBox cbnat;
    private javax.swing.JComboBox cbturma;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JTable tblcadeiras;
    private javax.swing.JTable tbldocente;
    private javax.swing.JTextField txtidade;
    private javax.swing.JTextField txtnac;
    private javax.swing.JTextField txtnome;
    private javax.swing.JTextField txtnome1;
    private javax.swing.JTextField txtsalario;
    private javax.swing.JTextField txtsenha;
    private javax.swing.JTextField txttel;
    private javax.swing.JTextField txtusu;
    // End of variables declaration//GEN-END:variables
}
