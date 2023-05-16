package telas;

import java.sql.*;
import javax.swing.JOptionPane;
import Classes.Conexao;
import javafx.scene.control.RadioButton;
import net.proteanit.sql.DbUtils;

public class TelaFuncionario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaFuncionario() {
        initComponents();

        conexao = Conexao.conector();

        procurarFunc();
    }

    private void procurarFunc(){

        String procurar = "select id, nome, sexo, idade, naturalidade, morada, cargo from funcionario where nome like ? order by nome";

        try {
            pst = conexao.prepareStatement(procurar);

            pst.setString(1, txtnome1.getText() + "%");

            rs = pst.executeQuery();

            tblfunc.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    private void adicionar() {

        String adicionar = "insert into funcionario (nome, sexo, idade, nacionalidade, naturalidade, salario, telefone, morada, cod_dep, cargo, usuario, senha) values(?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(adicionar);

            pst.setString(1, txtnome.getText());

            if (rb1.isSelected()) {
                pst.setString(2, "M");
            } else if (rb2.isSelected()) {
                pst.setString(2, "F");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Escolha o sexo!");
            }

            pst.setString(3, txtidade.getText());
            pst.setString(4, txtnac.getText());
            pst.setString(5, cbnat.getSelectedItem().toString());
            pst.setString(6, txtsal.getText());
            pst.setString(7, txttel.getText());
            pst.setString(8, txtmor.getText());
            pst.setInt(9, cbdep.getSelectedIndex() + 1);
            pst.setString(10, txtcargo.getText());
            pst.setString(11, txtusu.getText());
            pst.setString(12, txtsenha.getText());

            if (txtnome.getText().isEmpty() || txtsenha.getText().isEmpty() || txtusu.getText().isEmpty() || txtnac.getText().isEmpty() || txtidade.getText().isEmpty() || txtsal.getText().isEmpty() || txttel.getText().isEmpty() || txtmor.getText().isEmpty() || txtcargo.getText().isEmpty()) {

                JOptionPane.showMessageDialog(rootPane, "Encontrados campos vazios ou não selecionados");

            } else {

                int add = JOptionPane.showConfirmDialog(null, "Registar novo Funcionário? ", "Cadastro", JOptionPane.YES_NO_OPTION);
                if (add == JOptionPane.YES_OPTION) {

                    int added = pst.executeUpdate();

                    if (added > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Funcionário registado com Sucesso", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                        txtnome.setText(null);
                        txtsenha.setText(null);
                        txtusu.setText(null);
                        txtmor.setText(null);
                        txtcargo.setText(null);
                        txtnac.setText(null);
                        txtidade.setText(null);
                        txtsal.setText(null);
                        txttel.setText(null);
                        buttonGroup1.clearSelection();

                        cbnat.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Falha ao registar novo funcionario");
                    }
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    private void limpar() {

        int limp = JOptionPane.showConfirmDialog(null, "Limpar campos?", "Limpar", JOptionPane.YES_NO_OPTION);

        if (limp == JOptionPane.YES_OPTION) {
            txtnome.setText(null);
            txtsenha.setText(null);
            txtusu.setText(null);
            txtmor.setText(null);
            txtnac.setText(null);
            txtidade.setText(null);
            txtcargo.setText(null);
            txtsal.setText(null);
            txttel.setText(null);
            buttonGroup1.clearSelection();
            cbnat.setSelectedIndex(0);
        }
    }

    private void deletar() {

        int linha = tblfunc.getSelectedRow();
        String id = tblfunc.getModel().getValueAt(linha, 0).toString();
        String delet = "delete from funcionario where id = ?";

        try {
            conexao.prepareStatement(delet);

            pst.setString(1, id);

            int pergunta = JOptionPane.showConfirmDialog(rootPane, "Remover funcionário? " + tblfunc.getModel().getValueAt(linha, 1).toString() + "? ", "Remoção", JOptionPane.YES_NO_OPTION);

            if (pergunta == JOptionPane.YES_OPTION) {
                int removido = pst.executeUpdate();

                if (removido > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Funcionário removido com Sucesso! ");

                    txtnome.setText(null);
                    txtsenha.setText(null);
                    txtusu.setText(null);
                    txtmor.setText(null);
                    txtnac.setText(null);
                    txtidade.setText(null);
                    txtcargo.setText(null);
                    txtsal.setText(null);
                    txttel.setText(null);
                    buttonGroup1.clearSelection();
                    cbnat.setSelectedIndex(0);
                }
            }
        } catch (Exception e) {
        }

    }

    private void actualizar() {

        int linha = tblfunc.getSelectedRow();

        String id = tblfunc.getModel().getValueAt(linha, 0).toString();

        String act = "update funcionario set nome = ?, sexo = ?, idade = ?, nacionalidade = ?, naturalidade = ?, salario = ?, telefone = ?, morada = ?, cod_dep = ?, cargo = ?, usuario = ?, senha = ? where id = ?";

        try {
            pst = conexao.prepareStatement(act);

            pst.setString(1, txtnome.getText());

            if (rb1.isSelected()) {
                pst.setString(2, "M");
            } else if (rb2.isSelected()) {
                pst.setString(2, "F");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Escolha o sexo!");
            }

            pst.setString(3, txtidade.getText());
            pst.setString(4, txtnac.getText());
            //pst.setString(5, cbnat.getSelectedItem().toString());
            
            if (cbnat.getSelectedIndex() == 0) {
                pst.setString(5, "Maputo");
            } else if (cbnat.getSelectedIndex() == 1) {
                pst.setString(5, "Gaza");
            } else if (cbnat.getSelectedIndex() == 2) {
                pst.setString(5, "Inhambane");
            } else if (cbnat.getSelectedIndex() == 3) {
                pst.setString(5, "Sofala");
            } else if (cbnat.getSelectedIndex() == 4) {
                pst.setString(5, "Manica");
            } else if (cbnat.getSelectedIndex() == 5) {
                pst.setString(5, "Tete");
            } else if (cbnat.getSelectedIndex() == 6) {
                pst.setString(5, "Zambezia");
            } else if (cbnat.getSelectedIndex() == 7) {
                pst.setString(5, "Nampula");
            } else if (cbnat.getSelectedIndex() == 8) {
                pst.setString(5, "Cabo Delgado");
            } else if (cbnat.getSelectedIndex() == 9) {
                pst.setString(5, "Niassa");
            }

            
            pst.setString(6, txtsal.getText());
            pst.setString(7, txttel.getText());
            pst.setString(8, txtmor.getText());
            pst.setInt(9, cbdep.getSelectedIndex() + 1);
            pst.setString(10, txtcargo.getText());
            pst.setString(11, txtusu.getText());
            pst.setString(12, txtsenha.getText());

            pst.setString(13, id);

            if (txtnome.getText().isEmpty() || txtsenha.getText().isEmpty() || txtusu.getText().isEmpty() || txtnac.getText().isEmpty() || txtidade.getText().isEmpty() || txtsal.getText().isEmpty() || txttel.getText().isEmpty() || txtmor.getText().isEmpty() || txtcargo.getText().isEmpty()) {

                JOptionPane.showMessageDialog(rootPane, "Encontrados campos vazios ou não selecionados");

            } else {

                int actu = JOptionPane.showConfirmDialog(null, "Actualizar Funcionário? ", "Actualização", JOptionPane.YES_NO_OPTION);
                if (actu == JOptionPane.YES_OPTION) {

                    int acted = pst.executeUpdate();

                    if (acted > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Funcionário actualizado com Sucesso", "Actualização", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Falha ao actualizar funcionario");
                    }
                }

            }

        } catch (Exception e) {
        }
    }

    private void completar(){

        int linha = tblfunc.getSelectedRow();

        String id = tblfunc.getModel().getValueAt(linha, 0).toString();
        
        txtnome.setText(tblfunc.getModel().getValueAt(linha, 1).toString());
        String sex = tblfunc.getModel().getValueAt(linha, 2).toString();
        
        if ("M".equals(sex)) {
            rb1.setSelected(true);
        } else if ("F".equals(sex)) {
            rb2.setSelected(true);
        } else if (sex.isEmpty()) {
            buttonGroup1.clearSelection();
        }
        txtidade.setText(tblfunc.getModel().getValueAt(linha, 3).toString());
        
        String nat = tblfunc.getModel().getValueAt(linha, 4).toString();
        //System.out.println(nat);
                if (nat.equals("Maputo")) {
                    cbnat.setSelectedIndex(0);
                } else if (nat.toLowerCase().equals("gaza")) {
                    cbnat.setSelectedIndex(1);
                } else if (nat.toLowerCase().equals("inhambane")) {
                    cbnat.setSelectedIndex(2);
                } else if (nat.toLowerCase().equals("sofala")) {
                    cbnat.setSelectedIndex(3);
                } else if (nat.toLowerCase().equals("manica")) {
                    cbnat.setSelectedIndex(4);
                } else if (nat.toLowerCase().equals("tete")) {
                    cbnat.setSelectedIndex(5);
                } else if (nat.toLowerCase().equals("zambézia")) {
                    cbnat.setSelectedIndex(6);
                } else if (nat.toLowerCase().equals("nampula")) {
                    cbnat.setSelectedIndex(7);
                } else if (nat.toLowerCase().equals("cabo delgado")) {
                    cbnat.setSelectedIndex(8);
                } else if (nat.toLowerCase().equals("niassa")) {
                    cbnat.setSelectedIndex(9);
                }
        
                txtmor.setText(tblfunc.getModel().getValueAt(linha, 5).toString());
                txtcargo.setText(tblfunc.getModel().getValueAt(linha, 6).toString());
                
        String tudo = "select nacionalidade, salario, telefone, usuario, senha, cod_dep from funcionario where id = ?";

        try {
            pst = conexao.prepareStatement(tudo);

            pst.setString(1, id);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {
                txtnac.setText(rs.getString("nacionalidade"));
                txtsal.setText(rs.getString("salario"));
                txttel.setText(rs.getString("telefone"));
                txtusu.setText(rs.getString("usuario"));
                txtsenha.setText(rs.getString("senha"));
                
                int index = rs.getInt("cod_dep");
                
                cbdep.setSelectedIndex(index-1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblfunc = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtnome = new javax.swing.JTextField();
        txtmor = new javax.swing.JTextField();
        txttel = new javax.swing.JTextField();
        txtsal = new javax.swing.JTextField();
        txtnac = new javax.swing.JTextField();
        txtidade = new javax.swing.JTextField();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        cbnat = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtsenha = new javax.swing.JTextField();
        txtusu = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cbdep = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        txtcargo = new javax.swing.JTextField();
        txtnome1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Funcionário");
        setPreferredSize(new java.awt.Dimension(761, 550));

        tblfunc.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        tblfunc.setForeground(new java.awt.Color(255, 153, 0));
        tblfunc.setModel(new javax.swing.table.DefaultTableModel(
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
        tblfunc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblfuncMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblfunc);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DADOS PESSOAIS");

        jLabel3.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nome");
        jLabel3.setPreferredSize(new java.awt.Dimension(90, 30));

        jLabel4.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Sexo");
        jLabel4.setPreferredSize(new java.awt.Dimension(90, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Idade");
        jLabel5.setPreferredSize(new java.awt.Dimension(90, 30));

        jLabel6.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nacionalidade");
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 30));

        jLabel7.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Naturalidade");
        jLabel7.setPreferredSize(new java.awt.Dimension(90, 30));

        jLabel8.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Salário");
        jLabel8.setPreferredSize(new java.awt.Dimension(90, 30));

        jLabel9.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 204));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Telefone");
        jLabel9.setPreferredSize(new java.awt.Dimension(90, 30));

        jLabel10.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 204));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Morada");
        jLabel10.setPreferredSize(new java.awt.Dimension(90, 30));

        txtnome.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtnome.setForeground(new java.awt.Color(255, 153, 0));
        txtnome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnome.setPreferredSize(new java.awt.Dimension(280, 30));

        txtmor.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtmor.setForeground(new java.awt.Color(255, 153, 0));
        txtmor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmor.setPreferredSize(new java.awt.Dimension(280, 30));

        txttel.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txttel.setForeground(new java.awt.Color(255, 153, 0));
        txttel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttel.setPreferredSize(new java.awt.Dimension(280, 30));

        txtsal.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtsal.setForeground(new java.awt.Color(255, 153, 0));
        txtsal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsal.setPreferredSize(new java.awt.Dimension(280, 30));

        txtnac.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtnac.setForeground(new java.awt.Color(255, 153, 0));
        txtnac.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnac.setPreferredSize(new java.awt.Dimension(280, 30));

        txtidade.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtidade.setForeground(new java.awt.Color(255, 153, 0));
        txtidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtidade.setPreferredSize(new java.awt.Dimension(59, 30));

        buttonGroup1.add(rb1);
        rb1.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        rb1.setForeground(new java.awt.Color(255, 153, 0));
        rb1.setText("Masc");
        rb1.setPreferredSize(new java.awt.Dimension(49, 30));

        buttonGroup1.add(rb2);
        rb2.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        rb2.setForeground(new java.awt.Color(255, 153, 0));
        rb2.setText("Fem");
        rb2.setPreferredSize(new java.awt.Dimension(45, 30));

        cbnat.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbnat.setForeground(new java.awt.Color(255, 153, 0));
        cbnat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "                  Maputo", "                    Gaza", "              Inhambane", "                   Sofala", "                   Manica", "                     Tete", "                 Zambézia", "                  Nampula", "             Cabo Delgado", "                   Niassa" }));
        cbnat.setPreferredSize(new java.awt.Dimension(56, 30));
        cbnat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtsal, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtmor, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rb1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rb2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtidade, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnac, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(cbnat, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbnat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("OUTROS DADOS");
        jLabel11.setPreferredSize(new java.awt.Dimension(40, 30));

        jLabel12.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Senha");
        jLabel12.setPreferredSize(new java.awt.Dimension(90, 30));

        jLabel13.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 204));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Usuário");
        jLabel13.setPreferredSize(new java.awt.Dimension(90, 30));

        txtsenha.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtsenha.setForeground(new java.awt.Color(255, 153, 0));
        txtsenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtusu.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtusu.setForeground(new java.awt.Color(255, 153, 0));
        txtusu.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel14.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 204));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Departamento");
        jLabel14.setPreferredSize(new java.awt.Dimension(125, 30));

        cbdep.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbdep.setForeground(new java.awt.Color(255, 153, 0));
        cbdep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "               Direção", "             Secretaria", "       Higiene e Limpeza", "      Registo Acadêmico" }));

        jLabel15.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 204));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Cargo");
        jLabel15.setPreferredSize(new java.awt.Dimension(40, 30));

        txtcargo.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtcargo.setForeground(new java.awt.Color(255, 153, 0));
        txtcargo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtusu)
                            .addComponent(txtsenha)
                            .addComponent(txtcargo))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbdep, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbdep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcargo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtusu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtnome1.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        txtnome1.setForeground(new java.awt.Color(255, 153, 0));
        txtnome1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnome1ActionPerformed(evt);
            }
        });
        txtnome1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnome1KeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nome");

        jButton1.setBackground(new java.awt.Color(255, 153, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/arrow_refresh.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(65, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/delete.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(65, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnadd.setBackground(new java.awt.Color(255, 153, 0));
        btnadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/add.png"))); // NOI18N
        btnadd.setPreferredSize(new java.awt.Dimension(65, 30));
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 153, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/application_delete.png"))); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(65, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnome1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnome1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbnatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbnatActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnaddActionPerformed

    private void tblfuncMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblfuncMouseClicked
        // TODO add your handling code here:
        completar();
    }//GEN-LAST:event_tblfuncMouseClicked

    private void txtnome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnome1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtnome1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        deletar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        actualizar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtnome1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnome1KeyReleased
        // TODO add your handling code here:
        procurarFunc();
    }//GEN-LAST:event_txtnome1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbdep;
    private javax.swing.JComboBox cbnat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JTable tblfunc;
    private javax.swing.JTextField txtcargo;
    private javax.swing.JTextField txtidade;
    private javax.swing.JTextField txtmor;
    private javax.swing.JTextField txtnac;
    private javax.swing.JTextField txtnome;
    private javax.swing.JTextField txtnome1;
    private javax.swing.JTextField txtsal;
    private javax.swing.JTextField txtsenha;
    private javax.swing.JTextField txttel;
    private javax.swing.JTextField txtusu;
    // End of variables declaration//GEN-END:variables
}
