/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package VisaoConsultar;

//import DAO.ClienteDAO;
import DAO.Conexao;
import DAO.FuncionarioDAO;
//import Modelo.Cliente;
import Modelo.Funcionario;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aluno
 */
public class ConsultarFuncionario extends javax.swing.JFrame {
private void AtualizaTable(){
           
                Connection con = Conexao.AbrirConexao();
                FuncionarioDAO bd = new FuncionarioDAO(con);
                List<Funcionario> lista = new ArrayList<>();
                lista = bd.ListarFuncionario();
                DefaultTableModel tbm =
                        (DefaultTableModel) jtable.getModel();
                while (tbm.getRowCount() > 0){
                    tbm.removeRow(0);
                }
                int i = 0;
                for (Funcionario tab : lista){
                    
                    tbm.addRow(new String[i]);
                    jtable.setValueAt(tab.getSenha(), i, 1);
                    jtable.setValueAt(tab.getEmail(), i, 2);
                    jtable.setValueAt(tab.getCpf(), i, 3);
                    jtable.setValueAt(tab.getTelefone(), i, 4);
                    jtable.setValueAt(tab.getRg(), i, 5);
                    jtable.setValueAt(tab.getPis(), i, 6);
                    jtable.setValueAt(tab.getLogin(), i, 7);
                    jtable.setValueAt(tab.getNome(), i, 8);
                    jtable.setValueAt(tab.getEndereco(), i, 9);
                    jtable.setValueAt(tab.getTipo(), i, 10);
                    jtable.setValueAt(tab.getId_funcionario(), i, 0);
                    i++;
                }
                    Conexao.FecharConexao(con);
           
           }
         private void pesquisarNome(){
                String nome1 = nome.getText();
                Connection con = Conexao.AbrirConexao();
                FuncionarioDAO bd = new FuncionarioDAO(con);
                List<Funcionario> lista = new ArrayList<>();
                lista = bd.Pesquisar_Nome_Funcionario(nome1);
                DefaultTableModel tbm =
                        (DefaultTableModel) jtable.getModel();
                while (tbm.getRowCount() > 0){
                    tbm.removeRow(0);
                }
                int i = 0;
                for (Funcionario tab : lista){
                    
                    tbm.addRow(new String[i]);
                    jtable.setValueAt(tab.getNome(), i, 1);
                    jtable.setValueAt(tab.getEmail(), i, 2);
                    jtable.setValueAt(tab.getCpf(), i, 3);
                    jtable.setValueAt(tab.getTelefone(), i, 4);
                    jtable.setValueAt(tab.getRg(), i, 5);
                    jtable.setValueAt(tab.getPis(), i, 6);
                    jtable.setValueAt(tab.getLogin(), i, 7);
                    jtable.setValueAt(tab.getSenha(), i, 8);
                    jtable.setValueAt(tab.getEndereco(), i, 9);
                    jtable.setValueAt(tab.getTipo(), i, 10);
                    jtable.setValueAt(tab.getId_funcionario(), i, 0);
                    i++;
                }
                    Conexao.FecharConexao(con);
           
           }
         private void pesquisarid(){
               String id = jTF_id.getText();
                Connection con = Conexao.AbrirConexao();
                FuncionarioDAO bd = new FuncionarioDAO(con);
                int n = Integer.parseInt(id);
                List<Funcionario> lista = new ArrayList<>();
                lista = bd.Pesquisar_Cod_Funcionario(n);
                DefaultTableModel tbm =
                        (DefaultTableModel) jtable.getModel();
                while (tbm.getRowCount() > 0){
                    tbm.removeRow(0);
                }
                int i = 0;
                for (Funcionario tab : lista){
                    
                    tbm.addRow(new String[i]);
                    jtable.setValueAt(tab.getNome(), i, 1);
                    jtable.setValueAt(tab.getEmail(), i, 2);
                    jtable.setValueAt(tab.getCpf(), i, 3);
                    jtable.setValueAt(tab.getTelefone(), i, 4);
                    jtable.setValueAt(tab.getRg(), i, 5);
                    jtable.setValueAt(tab.getPis(), i, 6);
                    jtable.setValueAt(tab.getLogin(), i, 7);
                    jtable.setValueAt(tab.getSenha(), i, 8);
                    jtable.setValueAt(tab.getEndereco(), i, 9);
                    jtable.setValueAt(tab.getTipo(), i, 10);
                    jtable.setValueAt(tab.getId_funcionario(), i, 0);
                    i++;
                }
                    Conexao.FecharConexao(con);
           
         }   
public ConsultarFuncionario() {
        initComponents();
        AtualizaTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pesquisar = new javax.swing.JButton();
        nome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTF_id = new javax.swing.JTextField();
        PesTodos = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Pesquisar.setText("Pesquisar");
        Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisarActionPerformed(evt);
            }
        });

        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });

        jLabel2.setText("Id:");

        PesTodos.setText("Todos");
        PesTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesTodosActionPerformed(evt);
            }
        });

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Consultar por nome:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("Consultar Funcionário");

        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "E-mail", "CPF", "Telefone", "RG", "Nº do Pis", "Login", "Senha", "Endereço", "Tipo"
            }
        ));
        jScrollPane1.setViewportView(jtable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Pesquisar)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jTF_id, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addGap(44, 44, 44)
                        .addComponent(PesTodos)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pesquisar)
                    .addComponent(PesTodos)
                    .addComponent(jLabel2)
                    .addComponent(jTF_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisarActionPerformed
        pesquisarNome();
    }//GEN-LAST:event_PesquisarActionPerformed

    private void PesTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesTodosActionPerformed
        AtualizaTable();
    }//GEN-LAST:event_PesTodosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pesquisarid();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PesTodos;
    private javax.swing.JButton Pesquisar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTF_id;
    private javax.swing.JTable jtable;
    private javax.swing.JTextField nome;
    // End of variables declaration//GEN-END:variables
}
