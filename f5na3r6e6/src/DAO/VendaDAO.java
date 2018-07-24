package DAO;
import Modelo.Cliente;
import Modelo.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VendaDAO extends ExecuteSQL{
    public VendaDAO(Connection con){
        super(con);
    }
    public String Inserir_Venda(Venda a){
        String sql = "insert into venda values(0,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, a.getId_veiculo());
            ps.setInt(2, a.getId_funcionario());
            ps.setInt(3, a.getId_cliente());
            ps.setDouble(4, a.getValor());
            ps.setString(5, a.getServico());
            ps.setString(6, a.getForma());
           
        if(ps.executeUpdate() > 0 ) {   
            return "Inserido com sucesso.";
        }else {
            return "Error ao inserir";
        }
        }catch (SQLException e) {
            return e.getMessage();
        }
    
    }
    
            
       public int consultarVendaData(String nome1,String nome2) {
           String sql = "select * "
                   + "from venda where data >=" + nome1 + " and data<="+nome2+"";
           //java.util.List<Venda> lista_nome = new ArrayList<Venda>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            
            if (rs != null) {
                while (rs.next()) {
                    Venda a = new Venda();
                 
                    a.setId_venda(rs.getInt(1));
                    a.setId_veiculo(rs.getInt(2));
                    //a.setId_cliente(rs.getInt(9));
                    
                   //lista_nome.add(a);
                }
            } else {
                //return null;
            }
            } catch(SQLException e) {
                //return null;          
        }
        //return null;
        return 0;
       
     
}

       
       
       
       
       
       
       
       
       
       public List<Venda> consultarCodigoVenda(String n1,String n2){
        System.out.println(n1+"   "+n2);
        String sql="select sum(valor),count(valor) from venda where data >= '"+n1+"' and data <= '"+n2 +"' ";
        List<Venda> lista = new ArrayList<Venda>();
        System.out.println(n1+"   "+n2);
        try{
            PreparedStatement ps= getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
               while(rs.next()){
                Venda a = new Venda();
                a.setValor(rs.getInt(1));
                a.setId_veiculo(rs.getInt(2));
                lista.add(a);
                   System.out.println(a);
               
            }
            return lista;
            }else{
            return null;
            }
        }catch(Exception e){
            return null;
            }
        }
 
       
       
       public List<Venda> consultarCodigoVenda2(String n1,String n2,int n){
        System.out.println(n1+"   "+n2);
        String sql="select sum(valor),count(valor) from venda where data >= '"+n1+"' and data <= '"+n2 +"' and id_funcionario= "+n+"";
        List<Venda> lista = new ArrayList<Venda>();
        System.out.println(n1+"   "+n2);
        try{
            PreparedStatement ps= getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
               while(rs.next()){
                Venda a = new Venda();
                a.setValor(rs.getInt(1));
                a.setId_veiculo(rs.getInt(2));
                lista.add(a);
                   System.out.println(a);
               
            }
            return lista;
            }else{
            return null;
            }
        }catch(Exception e){
            return null;
            }
        }
       
       
       
    
    
    
    public static boolean isCpf(String cpf) {
    int sm, i, r, num;
    char dig10, dig11;
// calcula o 1o. digito verificador do CPF
    sm = 0;
    for (i=0; i<9; i++) {
        
      num = (int)cpf.charAt(i) - 48; // por exemplo: transforma o caracter '0' no inteiro 0
			                         // (48 eh a posição de '0' na tabela ASCII)
      sm = sm + (num * (10 - i));
    }
    r = 11 - (sm % 11);
    if ((r == 10) || (r == 11))
       dig10 = '0';
    else
       dig10 = (char)(r + 48);
 
// calcula o 2o. digito verificador do CPF
    sm = 0;
    for (i=0; i<10; i++) {
      num = (int)cpf.charAt(i) - 48;
      sm = sm + (num * (11 - i));
    }
    r = 11 - (sm % 11);
    if ((r == 10) || (r == 11))
       dig11 = '0';
    else
       dig11 = (char)(r + 48);
 
// compara os dígitos calculados com os dígitos informados
    if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
       return(true);
    else
       return(false);
  }
 

  public static String ImprimeCpf(String cpf) {
    return(cpf.substring(0, 3) + '.' + cpf.substring(3, 6) + '.' +
      cpf.substring(6, 9) + '-' + cpf.substring(9, 11));
  }

    //public void consultarVendaData(String data1, String data) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   // }
    
    
    
    
    
    
    
    
    
    
}//fim do DAO    