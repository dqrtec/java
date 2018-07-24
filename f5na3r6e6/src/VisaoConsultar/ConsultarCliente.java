package VisaoConsultar;

import DAO.ClienteDAO;
import DAO.Conexao;
import Modelo.Cliente;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paulo
 */
public class ConsultarCliente extends javax.swing.JFrame {

        private void AtualizaTable(){
           
                Connection con = Conexao.AbrirConexao();
                ClienteDAO bd = new ClienteDAO(con);
                List<Cliente> lista = new ArrayList<>();
                lista = bd.ListarCliente();
                DefaultTableModel tbm =
                        (DefaultTableModel) jtable.getModel();
                while (tbm.getRowCount() > 0){
                    tbm.removeRow(0);
                }
                int i = 0;
                for (Cliente tab : lista){
                    
                    tbm.addRow(new String[i]);
                    jtable.setValueAt(tab.getNome(), i, 1);
                    jtable.setValueAt(tab.getEmail(), i, 2);
                    jtable.setValueAt(tab.getCpf(), i, 3);
                    jtable.setValueAt(tab.getTelefone(), i, 4);
                    jtable.setValueAt(tab.getRg(), i, 5);
                    jtable.setValueAt(tab.getCidade(), i, 6);
                    jtable.setValueAt(tab.getEstado(), i, 7);
                    jtable.setValueAt(tab.getCnh(), i, 8);
                    jtable.setValueAt(tab.getId_cliente(), i, 0);
                    i++;
                }
                    Conexao.FecharConexao(con);
           
           }
         private void pesquisarNome(){
                String nome1 = nome.getText();
                Connection con = Conexao.AbrirConexao();
                ClienteDAO bd = new ClienteDAO(con);
                List<Cliente> lista = new ArrayList<>();
                lista = bd.Pesquisar_Nome_Cliente(nome1);
                DefaultTableModel tbm =
                        (DefaultTableModel) jtable.getModel();
                while (tbm.getRowCount() > 0){
                    tbm.removeRow(0);
                }
                int i = 0;
                for (Cliente tab : lista){
                    
                    tbm.addRow(new String[i]);
                    jtable.setValueAt(tab.getNome(), i, 1);
                    jtable.setValueAt(tab.getEmail(), i, 2);
                    jtable.setValueAt(tab.getCpf(), i, 3);
                    jtable.setValueAt(tab.getTelefone(), i, 4);
                    jtable.setValueAt(tab.getRg(), i, 5);
                    jtable.setValueAt(tab.getCidade(), i, 6);
                    jtable.setValueAt(tab.getEstado(), i, 7);
                    jtable.setValueAt(tab.getCnh(), i, 8);
                    jtable.setValueAt(tab.getId_cliente(), i, 0);
                    i++;
                }
                    Conexao.FecharConexao(con);
           
           }
         private void pesquisarid(){
                String id = jTF_id.getText();
                Connection con = Conexao.AbrirConexao();
                ClienteDAO bd = new ClienteDAO(con);
                int n = Integer.parseInt(id);
                List<Cliente> lista = new ArrayList<>();
                lista = bd.Pesquisar_Cod_Cliente(n);
                DefaultTableModel tbm =
                        (DefaultTableModel) jtable.getModel();
                while (tbm.getRowCount() > 0){
                    tbm.removeRow(0);
                }
                int i = 0;
                for (Cliente tab : lista){
                    
                    tbm.addRow(new String[i]);
                    jtable.setValueAt(tab.getNome(), i, 1);
                    jtable.setValueAt(tab.getEmail(), i, 2);
                    jtable.setValueAt(tab.getCpf(), i, 3);
                    jtable.setValueAt(tab.getTelefone(), i, 4);
                    jtable.setValueAt(tab.getRg(), i, 5);
                    jtable.setValueAt(tab.getCidade(), i, 6);
                    jtable.setValueAt(tab.getEstado(), i, 7);
                    jtable.setValueAt(tab.getCnh(), i, 8);
                    jtable.setValueAt(tab.getId_cliente(), i, 0);
                    i++;
                }
                    Conexao.FecharConexao(con);
           
           }
    
    public ConsultarCliente() {
        initComponents();
        AtualizaTable();
    }
    
            
    


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        Pesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();
        PesTodos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTF_id = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setVisible(true);

        jLabel1.setText("Consultar por nome:");

        Pesquisar.setText("Pesquisar");
        Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisarActionPerformed(evt);
            }
        });

        jtable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Email", "CPF", "Telefone", "RG", "Cidade", "Estado", "CNH"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtable);

        PesTodos.setText("Todos");
        PesTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesTodosActionPerformed(evt);
            }
        });

        jLabel2.setText("Id:");

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel12.setText("Consulta  Cliente");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PesTodos)))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pesquisar)
                    .addComponent(PesTodos)
                    .addComponent(jLabel2)
                    .addComponent(jTF_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConsultarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PesTodos;
    private javax.swing.JButton Pesquisar;
    private javax.swing.JButton jButton1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTF_id;
    private javax.swing.JTable jtable;
    private javax.swing.JTextField nome;
    // End of variables declaration//GEN-END:variables
}
