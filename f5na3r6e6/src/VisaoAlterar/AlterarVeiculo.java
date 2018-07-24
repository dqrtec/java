
package VisaoAlterar;

import DAO.Conexao;
import DAO.VeiculoDAO;
import Modelo.Veiculo;
import Principal.Menu;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AlterarVeiculo extends javax.swing.JFrame {
    
    
    private void AtualizaCombo(){
    
       Connection con = Conexao.AbrirConexao();
       VeiculoDAO sql = new VeiculoDAO(con);
       List<Veiculo> lista = new ArrayList<>();
       lista = sql.ListarComboVeiculo();
       jCB_Nome.addItem("");
       
       for (Veiculo b : lista){
       
           jCB_Nome.addItem(b.getModelo());
       
       }
        Conexao.FecharConexao(con);
    }
    
    
    private void InserirDados(String ID){
    
        Connection con = Conexao.AbrirConexao();
        VeiculoDAO sql = new VeiculoDAO(con);
        List<Veiculo> lista = new ArrayList<>();
        lista = sql.CapturarVeiculo(ID);
        
        for(Veiculo a : lista){
        
        jTF_Fabricante.setText("" + a.getFabricante());
        jTF_ano.setText("" + a.getAno());
       // jTF_Data.setText("" + a.getData());
        jTF_Cor.setText("" + a.getCor());
      //  jTF_Percentual.setText("" + a.getPercentual());
        jTF_Preco.setText("" + a.getPreco());
        
        }
            Conexao.FecharConexao(con);
    }
    
    
    
    
    public AlterarVeiculo() {
        initComponents();
        AtualizaCombo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jTF_Fabricante = new javax.swing.JTextField();
        nome = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        jTF_Cor = new javax.swing.JTextField();
        jTF_ano = new javax.swing.JTextField();
        CPF = new javax.swing.JLabel();
        telefone = new javax.swing.JLabel();
        jTF_Preco = new javax.swing.JTextField();
        CNH = new javax.swing.JLabel();
        Alterar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jCB_Nome = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setVisible(true);

        jTF_Fabricante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_FabricanteActionPerformed(evt);
            }
        });

        nome.setText("modelo");

        email.setText("fabricante");

        jTF_Cor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_CorActionPerformed(evt);
            }
        });

        jTF_ano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_anoActionPerformed(evt);
            }
        });

        CPF.setText("cor");

        telefone.setText("preço");

        jTF_Preco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_PrecoActionPerformed(evt);
            }
        });

        CNH.setText("ano");

        Alterar.setText("Alterar");
        Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlterarActionPerformed(evt);
            }
        });

        jLabel2.setText("OBS: Não esquecer de digitar o ID depois de buscar");

        jCB_Nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_NomeActionPerformed(evt);
            }
        });

        jButton2.setText("voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setText("      Alterar Cliente");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(telefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CNH, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTF_Preco, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(jTF_ano))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCB_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTF_Fabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Alterar)
                                    .addComponent(jTF_Cor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCB_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTF_Fabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTF_Cor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CPF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTF_ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CNH, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel2)))
                .addGap(289, 289, 289)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTF_Preco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jButton2)
                .addGap(69, 69, 69)
                .addComponent(Alterar)
                .addGap(0, 149, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTF_FabricanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_FabricanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_FabricanteActionPerformed

    private void jTF_CorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_CorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_CorActionPerformed

    private void jTF_anoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_anoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_anoActionPerformed

    private void jTF_PrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_PrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_PrecoActionPerformed

    private void AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlterarActionPerformed

        String modelo = jCB_Nome.getSelectedItem().toString();
        String fabricante = jTF_Fabricante.getText();
        String cor = jTF_Cor.getText();
        String ano = jTF_ano.getText();
        String preco  = jTF_Preco.getText();
        //String percentual = jTF_Percentual.getText();
       // String data = jTF_Data.getText();
        //int cp = Integer.parseInt(cpf);
JOptionPane.showMessageDialog(null, modelo);
        if(cor.equals("")){
            JOptionPane.showMessageDialog(null,"nenhum campo pode estar vazio",
                "Concessionaria", JOptionPane.WARNING_MESSAGE);
        }else {
            Connection con = Conexao.AbrirConexao();
            //int n =  Integer.parseInt(codigo);
            VeiculoDAO sql = new VeiculoDAO(con);
            Veiculo a = new Veiculo();

            a.setModelo(modelo);
            a.setFabricante(fabricante);
            a.setCor(cor);
            a.setAno(ano);
            a.setPreco(preco);
           // a.setPercentual(percentual);
           // a.setData(data);
//System.out.print(modelo+" "+fabricante+" "+ano+" "+percentual+" "+data+" ");
            sql.Alterar_Veiculo(a);
            Conexao.FecharConexao(con);

            jTF_Fabricante.setText("");
            jTF_Cor.setText("");
            jTF_ano.setText("");
            jTF_Preco.setText("");
           // jTF_Percentual.setText("");
            //jTF_Data.setText("");

            JOptionPane.showMessageDialog(null,"Alteração realizada com sucesso",
                "Concessionaria", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        }
    }//GEN-LAST:event_AlterarActionPerformed

    private void jCB_NomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_NomeActionPerformed
        Connection con = Conexao.AbrirConexao();
        VeiculoDAO sql = new VeiculoDAO(con);
        List<Veiculo> lista = new ArrayList<>();
        String nome = jCB_Nome.getSelectedItem().toString();

        lista = sql.consultarCodigoVeiculo(nome);

        //for(Veiculo b : lista){

            //int a = b.getChassi();
            //jTF_Fabricante.setText("" + a);            
        //}
        
        
        jTF_Fabricante.setText("");
        jTF_Cor.setText("");
        jTF_ano.setText("");
       // jTF_Percentual.setText("");
       // jTF_Data.setText("");

        InserirDados(nome);

        
        
        Conexao.FecharConexao(con);

    }//GEN-LAST:event_jCB_NomeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Menu().setVisible(true);        // TODO add your handling code here:
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(AlterarVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlterarVeiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Alterar;
    private javax.swing.JLabel CNH;
    private javax.swing.JLabel CPF;
    private javax.swing.JLabel email;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jCB_Nome;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTF_Cor;
    private javax.swing.JTextField jTF_Fabricante;
    private javax.swing.JTextField jTF_Preco;
    private javax.swing.JTextField jTF_ano;
    private javax.swing.JLabel nome;
    private javax.swing.JLabel telefone;
    // End of variables declaration//GEN-END:variables
}
