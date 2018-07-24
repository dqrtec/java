package DAO;
import Modelo.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VeiculoDAO extends ExecuteSQL{
    public VeiculoDAO(Connection con){
        super(con);
    }
    public String Inserir_Veiculo(Veiculo a){
        String sql = "insert into veiculo values(?,?,?,?,?,?,0)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, a.getChassi());
            ps.setString(2, a.getModelo());
            ps.setString(3, a.getFabricante());
            ps.setString(4, a.getCor());
            ps.setString(5, a.getAno());
            ps.setString(6, a.getPreco());
            
            //JOptionPane.showMessageDialog(null, ps);
            //System.out.println(ps);
        if(ps.executeUpdate() > 0 ) {   
            return "Inserido com sucesso.";
        }else {
            return "Error ao inserir";
        }
        }catch (SQLException e) {
            return e.getMessage();
        }
    
    }
    
    public List<Veiculo> ListarVeiculo() {
        String sql = "select * from veiculo";
        List<Veiculo> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            
            if (rs != null) {
                while (rs.next()) {
                    Veiculo a = new Veiculo();
                   
                    a.setChassi(rs.getInt(1));
                    a.setModelo(rs.getString(2));
                    a.setFabricante(rs.getString(3));
                    a.setCor(rs.getString(4));
                    a.setAno(rs.getString(5));
                    a.setPreco(rs.getString(6));
                   // a.setPercentual(rs.getString(7));
                   // a.setData(rs.getString(8));
                    a.setId_veiculo(rs.getInt(7));
                    
                    lista.add(a);
                    
                } 
                return lista;
            } else {
                return null;
            }
            } catch(SQLException e) {
                return null;          
        }
       }
            public List<Veiculo> Pesquisar_Cod_Veiculo(int cod) {
           String sql = "select * from veiculo where chassi = '" + cod + "'";
           List<Veiculo> lista_cod = new ArrayList<Veiculo>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            
            if (rs != null) {
                while (rs.next()) {
                    Veiculo a = new Veiculo();
                    
                    a.setChassi(rs.getInt(1));
                    a.setModelo(rs.getString(2));
                    a.setFabricante(rs.getString(3));
                    a.setCor(rs.getString(4));
                    a.setAno(rs.getString(5));
                    a.setPreco(rs.getString(6));
                   // a.setPercentual(rs.getString(7));
                   // a.setData(rs.getString(8));
                    a.setId_veiculo(rs.getInt(7));
                    
                    lista_cod.add(a);
                    
                } return lista_cod;
            } else {
                return null;
            }
            } catch(SQLException e) {
                return null;          
        }
       
       }        
       public List<Veiculo> Pesquisar_Nome_Veiculo(String nome) {
           String sql = "select * "
                   + "from veiculo where modelo Like '" + nome + "%'";
           java.util.List<Veiculo> lista_nome = new ArrayList<Veiculo>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            
            if (rs != null) {
                while (rs.next()) {
                    Veiculo a = new Veiculo();
                 
                    a.setChassi(rs.getInt(1));
                    a.setModelo(rs.getString(2));
                    a.setFabricante(rs.getString(3));
                    a.setCor(rs.getString(4));
                    a.setAno(rs.getString(5));
                    a.setPreco(rs.getString(6));
                    //a.setPercentual(rs.getString(7));
                    //a.setData(rs.getString(8));
                    a.setId_veiculo(rs.getInt(7));
                    
                    lista_nome.add(a);
                } return lista_nome;
            } else {
                return null;
            }
            } catch(SQLException e) {
                return null;          
        }
       
       }
       
       public boolean Testar_Veiculo(int cod) {
           boolean Resultado = false;
           try {
               String sql = "select * from Veiculo where modelo = " + cod + "";
               PreparedStatement ps = getCon().prepareStatement(sql);
               ResultSet rs  = ps.executeQuery();
               
               if (rs != null) {
                   while (rs.next()) {
                       Resultado = true;
                   }
               }
           } catch (SQLException ex) {
               ex.getMessage();
           }
           return Resultado;
       }
           public List<Veiculo> CapturarVeiculo(String cod) {
               String sql = "select * from veiculo where modelo like '" + cod + "%'";
               List<Veiculo> lista = new ArrayList<Veiculo>();
               try {
                   PreparedStatement ps = getCon().prepareStatement(sql);
                   ResultSet rs = ps.executeQuery();
                   if (rs != null) {
                       while (rs.next()) {
                           Veiculo a = new Veiculo();
                           
                    a.setChassi(rs.getInt(1));
                    a.setModelo(rs.getString(2));
                    a.setFabricante(rs.getString(3));
                    a.setCor(rs.getString(4));
                    a.setAno(rs.getString(5));
                    a.setPreco(rs.getString(6));
                    a.setPercentual(rs.getString(7));
                    a.setData(rs.getString(8));
                    
                           lista.add(a);
                       }
                       return lista;
                   } else {
                       return null;
                   }
               } catch (SQLException e) {
                   return null;
               }
           }
           
            public String  Alterar_Veiculo(Veiculo a){
            
                String sql = "update veiculo set modelo = ?, fabricante = ?, ano = ?, preco = ?, percentual = ?, data = ?  where modelo like'?%'";
            try{
            
                PreparedStatement ps = getCon().prepareStatement(sql);
            
            ps.setString(1, a.getModelo());
            ps.setString(2, a.getFabricante());
            ps.setString(3, a.getAno());
            ps.setString(4, a.getPreco());
            ps.setString(5, a.getPercentual());
            ps.setString(6, a.getData());
            ps.setString(7, a.getModelo());

                
              
                if(ps.executeUpdate() > 0) {
                
                    return "Atualizado com sucesso";
                
                }else{
                        return "Erro ao atualizar";
                
                }
            
            }catch (SQLException ex){
            return ex.getMessage();
            }
            
            }
                public List<Veiculo> ListarComboVeiculo (){
                
                    String sql = "select * from veiculo";
                List<Veiculo> lista = new ArrayList<Veiculo>();
                try{
                   PreparedStatement ps = getCon().prepareStatement (sql);
                   ResultSet rs = ps.executeQuery();

                   if(rs != null){
                   while(rs.next()){
                        Veiculo a = new Veiculo ();
                        a.setModelo(rs.getString(1));
                        
                        a.setId_veiculo(rs.getInt(9));
                        lista.add(a);
                       }
                       return lista; 
                    }else{
                    return null;   
                    }
                }catch(Exception e){
                return null;
                }
            }
                public List<Veiculo> consultarCodigoVeiculo(String modelo){
        
        String sql="select * from veiculo where modelo like '" + modelo + "%'";
        List<Veiculo> lista = new ArrayList<Veiculo>();
        try{
            PreparedStatement ps= getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
               while(rs.next()){
                Veiculo a = new Veiculo();
                a.setChassi(rs.getInt(1));
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
                
                public List<Veiculo> consultarCodigoVeiculo2(String modelo){
        
        String sql="select * from veiculo where modelo like '" + modelo + "%'";
        List<Veiculo> lista = new ArrayList<Veiculo>();
        try{
            PreparedStatement ps= getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
               while(rs.next()){
                Veiculo a = new Veiculo();
                a.setId_veiculo(rs.getInt(9));
                a.setPreco(rs.getString(6));
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
                
                
    public String Excluir_Veiculo(Veiculo a){
        String sql = "delete from veiculo where chassi = ? and modelo = ?";
        
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, a.getChassi());
            ps.setString(2, a.getModelo());
            if(ps.executeUpdate() > 0){
                return "Excluido com sucesso.";
            }else{
                 return "Erro ao excluir";   
            }
    }catch(SQLException e){
            return e.getMessage();
        }
    } 

    public List<Veiculo> Pesquisar_Cod_Veiculo(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

        
        