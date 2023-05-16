/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;
import java.sql.*;
import javax.swing.*;
import Classes.Conexao;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Arlindo Halar
 */
public class TelaCadeira extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    ResultSet rs1 = null;
    /**
     * Creates new form TelaCadeira
     * @param num
     */
    
    private static int classeT;
    
    public TelaCadeira(int num) {
        initComponents();
        
        conexao = Conexao.conector();
        preencher_cadeira();
        prencher_DxC();
        
        classeT = num;
        
        if(classeT == 1){
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
        }
    }
    
    private void preencher_cadeira(){
        
        String classe = "select * from classe";
        String cadeiras = "select ca.id, ca.nome as Disciplinas from classexcadeira inner join cadeira ca on ca.id = id_cadeira where id_classe = ? order by ca.id";
        
        try {
            pst = conexao.prepareStatement(cadeiras);
            pst1 = conexao.prepareStatement(classe);
            
            
            rs1 = pst1.executeQuery();
          
            /*while (rs1.next()){
                
                
                    if(rs1.getString("Classe").equals(cbclasse.getItemAt(i))){
                        cbclasse.addItem(rs1.getString("classe"));
                    }
                i++;
                 
            }*/
            
            int indice = cbclasse.getSelectedIndex();
            
            if(indice == 0){
                pst.setString(1, "1");
            }
            else if(indice == 1){
                pst.setString(1, "2");
            }
            else if(indice == 2){
                pst.setString(1, "3");
            }
            else if(indice == 3){
                pst.setString(1, "4");
            }
            else if(indice == 4){
                pst.setString(1, "5");
            }
            else if(indice == 5){
                pst.setString(1, "6");
            }
            else if(indice == 6){
                pst.setString(1, "7");
            }
            
            rs = pst.executeQuery();
            
            
            tblcadeira.setModel(DbUtils.resultSetToTableModel(rs));
       
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        
    }
    
    private void prencher_docente(){
       
        int tabela = tblcadeira.getSelectedRow();
        
        String id = tblcadeira.getModel().getValueAt(tabela, 0).toString();
        //System.out.println(id);
        String docente = "select do.id, do.nome as Professor from docentexcadeira inner join docente do on do.id = id_docente where id_cadeira = ? order by do.id";
        try {
            pst = conexao.prepareStatement(docente);
            
            pst.setString(1, id);
            
            rs = pst.executeQuery();
            
            //while(rs.next()){
                tbldocente.setModel(DbUtils.resultSetToTableModel(rs));
            //}
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    //DxC - tabela docentes e de cadeiras no painel
    private void prencher_DxC(){
        String prof = "select id, nome, sexo from docente order by id";
        String cad = "select id, nome as Disciplinas from cadeira order by id ";
        
        try {
            pst = conexao.prepareStatement(prof);
            pst1 = conexao.prepareStatement(cad);
        
            rs = pst.executeQuery();
            rs1 = pst1.executeQuery();
            
            tblprof.setModel(DbUtils.resultSetToTableModel(rs));
            tblcad.setModel(DbUtils.resultSetToTableModel(rs1));
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void mudarNome(){
        int nome = tblcad.getSelectedRow();
        
        String id = tblcad.getModel().getValueAt(nome, 0).toString();
        
        String act = "update cadeira set nome = ? where id = ?";
        
        try {
            
            pst = conexao.prepareStatement(act);
            
            pst.setString(1, tblcad.getModel().getValueAt(nome, 1).toString());
            pst.setString(2, id);
            
            int mudar = JOptionPane.showConfirmDialog(rootPane, "Mudar nome da Disciplina? ","Mudança de nome", JOptionPane.YES_NO_OPTION);
            
            if (mudar == JOptionPane.YES_OPTION){
                int mudado = pst.executeUpdate();
                
                if (mudado > 0){
                    JOptionPane.showMessageDialog(rootPane,"Nome da cadeira mudado com sucesso!");
                }
            }
        } catch (Exception e) {
        }
        
    }
    
    private void atribuir(){
        
        int prof = tblprof.getSelectedRow();
        int cad = tblcad.getSelectedRow();
        
        String id_docente = tblprof.getModel().getValueAt(prof, 0).toString();
        String id_cadeira = tblcad.getModel().getValueAt(cad, 0).toString();
        String nome_docente = tblprof.getModel().getValueAt(prof, 1).toString();
        String nome_cadeira = tblcad.getModel().getValueAt(cad, 1).toString();
        String sex_docente = tblprof.getModel().getValueAt(prof, 2).toString();
        
        String sexo;
        
        
            
        String inserir = "insert into docentexcadeira(id_cadeira, id_docente) values(?, ?)";
        
        try {
            pst = conexao.prepareStatement(inserir);
            
            pst.setString(1, id_cadeira);
            pst.setString(2, id_docente);
            
            if(("F").equals(sex_docente)){
                sexo = " a Professora ";
            
                int pergunta = JOptionPane.showConfirmDialog(rootPane, "Atribuir "+ nome_cadeira + sexo + nome_docente +"?","Atribuição de cadeiras", JOptionPane.YES_NO_OPTION);
                
                if(pergunta == JOptionPane.YES_OPTION){
                    int atribuido = pst.executeUpdate();
                    
                    if(atribuido > 0){
                        JOptionPane.showMessageDialog(rootPane, "Disciplina "+nome_cadeira +" atribuida com sucesso a Professora " + nome_docente);
                    }
                }
            
            }
            else if("M".equals(sex_docente)){
                sexo = " ao Professor ";
                
                int pergunta = JOptionPane.showConfirmDialog(rootPane, "Atribuir "+ nome_cadeira + sexo + nome_docente +"?","Atribuição de cadeiras", JOptionPane.YES_NO_OPTION);
                
                if(pergunta == JOptionPane.YES_OPTION){
                    int atribuido = pst.executeUpdate();
                    
                    if(atribuido > 0){
                        JOptionPane.showMessageDialog(rootPane, "Disciplina "+nome_cadeira +" atribuida com sucesso ao Professor " + nome_docente);
                    }
                }
            }
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void ExcluirC(){
        
        int docente = tbldocente.getSelectedRow();
        int cadeira = tblcadeira.getSelectedRow();
        
        String id_docente = tbldocente.getModel().getValueAt(docente, 0).toString();
        String id_cadeira = tblcadeira.getModel().getValueAt(cadeira, 0).toString();
        String nome_cadeira = tblcadeira.getModel().getValueAt(cadeira, 1).toString();
        String nome_docente = tbldocente.getModel().getValueAt(docente, 1).toString();
        
        
        String excluir = "delete from docentexcadeira where id_cadeira = ? and id_docente = ?";
        
        try {
            
            pst = conexao.prepareStatement(excluir);
            
            pst.setString(1, id_cadeira);
            pst.setString(2, id_docente);
            
            int pergunta = JOptionPane.showConfirmDialog(null, "Excluir " + nome_cadeira + " do(a) Professor(a) " + nome_docente + "?", "Remover cadeira do Professor", JOptionPane.YES_NO_OPTION);
            
            if(pergunta == JOptionPane.YES_OPTION){
                int Excluir = pst.executeUpdate();
                
                if(Excluir > 0){
                    JOptionPane.showMessageDialog(rootPane, "Professor(a) " + nome_docente + " excluido da disciplina " + nome_cadeira);
                } 
            }
            
        } catch (Exception e) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblcadeira = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbclasse = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldocente = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblprof = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblcad = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Inspeção de Disciplinas");
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(761, 550));

        tblcadeira.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        tblcadeira.setForeground(new java.awt.Color(255, 153, 0));
        tblcadeira.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcadeira.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcadeiraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblcadeira);

        jLabel1.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DISCIPLINAS");

        jLabel2.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Classe");

        cbclasse.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        cbclasse.setForeground(new java.awt.Color(255, 153, 0));
        cbclasse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "    1ª Classe ", "    2ª Classe", "    3ª Classe", "    4ª Classe", "    5ª Classe", "    6ª Classe", "    7ª Classe", " " }));
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

        jLabel3.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 204));
        jLabel3.setText("PROFESSORES");

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
        jScrollPane2.setViewportView(tbldocente);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ATRIBUIR CADEIRAS A DOCENTES");

        tblprof.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        tblprof.setForeground(new java.awt.Color(255, 153, 0));
        tblprof.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblprof);

        tblcad.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        tblcad.setForeground(new java.awt.Color(255, 153, 0));
        tblcad.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblcad);

        jButton1.setText("MxN");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setFont(new java.awt.Font("Sylfaen", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/arrow_right.png"))); // NOI18N
        jButton2.setText("Atribuir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(75, 75, 75)
                                .addComponent(jButton1))))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jButton3.setBackground(new java.awt.Color(255, 153, 0));
        jButton3.setFont(new java.awt.Font("Sylfaen", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cancel.png"))); // NOI18N
        jButton3.setText("Excluir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(118, 118, 118)
                                        .addComponent(jButton3)
                                        .addGap(0, 120, Short.MAX_VALUE))))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbclasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(81, 81, 81))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbclasse, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbclasseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbclasseItemStateChanged
        // TODO add your handling code here:
        preencher_cadeira();
    }//GEN-LAST:event_cbclasseItemStateChanged

    private void cbclasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbclasseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbclasseActionPerformed

    private void tblcadeiraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcadeiraMouseClicked
        // TODO add your handling code here:
        prencher_docente();
    }//GEN-LAST:event_tblcadeiraMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        mudarNome();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        if(tblprof.getSelectedRow() == -1 || tblcad.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(rootPane, "Selecione o docente e a respectiva cadeira!");
        }else{
            atribuir();
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(tblcadeira.getSelectedRow() == -1 || tbldocente.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(rootPane, "Selecione a cadeira e o respectivo docente!");
        }else{
            ExcluirC();
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbclasse;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblcad;
    private javax.swing.JTable tblcadeira;
    private javax.swing.JTable tbldocente;
    private javax.swing.JTable tblprof;
    // End of variables declaration//GEN-END:variables
}
