package VisaoCadastrar;

import DAO.ClienteDAO;
import DAO.Conexao;
import DAO.FuncionarioDAO;
import DAO.PromocaoDAO;
import DAO.VeiculoDAO;
import DAO.VendaDAO;
import Modelo.Cliente;
import Modelo.Funcionario;
import Modelo.Promocao;
import Modelo.Veiculo;
import Modelo.Venda;
import Principal.Menu;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.*;

public class Fecharvenda extends javax.swing.JFrame {

    
    private void AtualizaCombo(){
    
       Connection con = Conexao.AbrirConexao();
       VeiculoDAO sql = new VeiculoDAO(con);
       List<Veiculo> lista = new ArrayList<>();
       lista = sql.ListarComboVeiculo();
       jCB_Veiculo.addItem("");
       
       for (Veiculo b : lista){
       
           jCB_Veiculo.addItem(b.getModelo());
       
       }
        Conexao.FecharConexao(con);
    }
    
    private void AtualizaCombo2(){
    
       Connection con = Conexao.AbrirConexao();
       ClienteDAO sql = new ClienteDAO(con);
       List<Cliente> lista = new ArrayList<>();
       lista = sql.ListarComboCliente();
       jCB_Cliente.addItem("");
       
       for (Cliente b : lista){
       
           jCB_Cliente.addItem(b.getNome());
       
       }
        Conexao.FecharConexao(con);
    }
    
    
    
    private void AtualizaCombo3(){
       String nome = jLabel7.getText();
       //JOptionPane.showMessageDialog(null, nome);
       int n = Integer.parseInt(nome);
       Connection con = Conexao.AbrirConexao();
       PromocaoDAO sql = new PromocaoDAO(con);
       List<Promocao> lista = new ArrayList<>();
       lista = sql.ListarComboPromocao(n);
       jCB_Desconto.removeAllItems();
       jCB_Desconto.addItem("");
       
       
       
       for (Promocao b : lista){
       
           jCB_Desconto.addItem(b.getDesconto());
       
       }
        Conexao.FecharConexao(con);
    }
    
    
    public Fecharvenda() {
        initComponents();
        AtualizaCombo();
        AtualizaCombo2();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jCB_Cliente = new javax.swing.JComboBox();
        ok = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jCB_Veiculo = new javax.swing.JComboBox();
        jCB_Desconto = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        servicos = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        radio_vista = new javax.swing.JRadioButton();
        radio_praso = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setVisible(true);

        jLabel1.setText("modelo");

        jCB_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_ClienteActionPerformed(evt);
            }
        });

        ok.setText("Fechar venda");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        jLabel2.setText("Cliente");

        jButton1.setText("voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setText("Cadastrar Promoção");

        jCB_Veiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_VeiculoActionPerformed(evt);
            }
        });

        jCB_Desconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_DescontoActionPerformed(evt);
            }
        });

        jLabel3.setText("desconto");

        jLabel9.setText("Forma de pagamento");

        servicos.setColumns(20);
        servicos.setRows(5);
        jScrollPane1.setViewportView(servicos);

        jLabel10.setText("Serviços adicionais");

        buttonGroup1.add(radio_vista);
        radio_vista.setText("Vista");
        radio_vista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_vistaActionPerformed(evt);
            }
        });

        buttonGroup1.add(radio_praso);
        radio_praso.setText("Praso");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCB_Desconto, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(9, 9, 9)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jCB_Veiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                            .addComponent(ok)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton1))
                                        .addComponent(jCB_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio_vista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_praso)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCB_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCB_Veiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCB_Desconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(radio_vista)
                    .addComponent(radio_praso))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ok)
                    .addComponent(jButton1))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCB_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_ClienteActionPerformed
        Connection con = Conexao.AbrirConexao();
        ClienteDAO sql = new ClienteDAO(con);
        List<Cliente> lista = new ArrayList<>();
        String nome = jCB_Cliente.getSelectedItem().toString();

        lista = sql.consultarCodigoCliente(nome);

        for(Cliente b : lista){

            String a = b.getCpf();
            int c = b.getId_cliente();
            jLabel5.setText(a);
            jLabel8.setText(""+c);

        }
        Conexao.FecharConexao(con);

    }//GEN-LAST:event_jCB_ClienteActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        String cliente = jLabel8.getText();
        String preco = jLabel6.getText();
        String veiculo = jLabel7.getText();
        String desconto = jCB_Desconto.getSelectedItem().toString();
        int cli = Integer.parseInt(cliente);
        int vei = Integer.parseInt(veiculo);
        int des = Integer.parseInt(desconto);
        int pr = Integer.parseInt(preco);
        double fim;
        if(desconto.equals("")){
                fim = pr;
            }else{
        fim= pr-(pr*des/100);
    }
        String forma=null;
        if(radio_vista.isSelected()){
            forma="vista";
        }else{
            forma="praso";
        }
        String servico=servicos.getText();
        JOptionPane.showMessageDialog(null,veiculo+" + "+cliente+" + "+fim+" + "+forma+" + "+ servico );
        
        Connection con = Conexao.AbrirConexao();
        VendaDAO sql = new VendaDAO(con);
        Venda a = new Venda();

        int j = numero();
            a.setId_veiculo(vei);
            a.setId_funcionario(j);
            a.setId_cliente(cli);
            a.setValor(fim);
            a.setServico(servico);
            a.setForma(forma);
            
            sql.Inserir_Venda(a);
          //  Conexao.FecharConexao(con);
            //dispose();

        //}
        //}
    }//GEN-LAST:event_okActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Menu().setVisible(true);        // TODO add your handling code here:
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCB_VeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_VeiculoActionPerformed
        Connection con = Conexao.AbrirConexao();
        VeiculoDAO sql = new VeiculoDAO(con);
        List<Veiculo> lista = new ArrayList<>();
        String nome = jCB_Veiculo.getSelectedItem().toString();

        lista = sql.consultarCodigoVeiculo2(nome);

        for(Veiculo b : lista){

            String a = b.getPreco();
            int c = b.getId_veiculo();
            jLabel6.setText(""+a);
            jLabel7.setText(""+c);
        }
        Conexao.FecharConexao(con);

        
        
        
        AtualizaCombo3();        
    }//GEN-LAST:event_jCB_VeiculoActionPerformed

    private void jCB_DescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_DescontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCB_DescontoActionPerformed

    private void radio_vistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_vistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_vistaActionPerformed

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
            java.util.logging.Logger.getLogger(Fecharvenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fecharvenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fecharvenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fecharvenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fecharvenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jCB_Cliente;
    private javax.swing.JComboBox jCB_Desconto;
    private javax.swing.JComboBox jCB_Veiculo;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton ok;
    private javax.swing.JRadioButton radio_praso;
    private javax.swing.JRadioButton radio_vista;
    private javax.swing.JTextArea servicos;
    // End of variables declaration//GEN-END:variables

    private int numero() {
        Connection con = Conexao.AbrirConexao();
        FuncionarioDAO sql = new FuncionarioDAO(con);
        Funcionario a = new Funcionario();

        return a.getId_funcionario();
    }
}
