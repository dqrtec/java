
package DAO;

import java.sql.*;
import Modelo.Promocao;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.JOptionPane;
public class PromocaoDAO extends ExecuteSQL {
    
    public PromocaoDAO(Connection con){
        super (con);
    }

    
    public String Inserir_Promocao(Promocao a){
        String sql = "insert into promocao values (?,?,?,0)";
        try {
            PreparedStatement ps = getCon().prepareStatement (sql);
            ps.setInt(1, a.getId_veiculo());
            ps.setString(2, a.getLimite());
            ps.setString(3, a.getDesconto());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com secesso.";
            } else {
                return "Error o inserir";
            }
        } catch (SQLException e){
            return e.getMessage();
        }
    }
    
    
    
    
    public List<Promocao> ListarPromocao() {
        Date x=new Date();
        String j="";
        j=""+x+"";
        String[] s = j.split(" ");
        switch(s[1]){
            case "Jun":
                s[1]="6";
                break; 
        }
        String data= s[5]+"-0"+s[1]+"-"+s[2];
        //Date a= new getYear();
        //JOptionPane.showMessageDialog(null, j);
        JOptionPane.showMessageDialog(null, data);
        String sql = "select * from promocao where limite >= '"+data+"' ";
        List<Promocao> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            
            if (rs != null) {
                while (rs.next()) {
                    Promocao a = new Promocao();
                   
                    a.setId_promocao(rs.getInt(4));
                    a.setId_veiculo(rs.getInt(1));
                    a.setDesconto(rs.getString(3));
                    a.setLimite(rs.getString(2));
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
    
    
    Date x= new Date();
    
    
    public List<Promocao> Pesquisar_Nome_Promocao(String nome) {
Date x=new Date();
        String j="";
        j=""+x+"";
        String[] s = j.split(" ");
        switch(s[1]){
            case "Jun":
                s[1]="6";
                break; 
        }
        String data= s[5]+"-"+s[1]+"-"+s[2];

        String sql = "select * "
                   + "from promocao where limite >= "+data+" and id_veiculo like'"+nome+"%' ";
           java.util.List<Promocao> lista_nome = new ArrayList<Promocao>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            
            if (rs != null) {
                while (rs.next()) {
                    Promocao a = new Promocao();
                    a.setId_promocao(rs.getInt(4));
                    a.setId_veiculo(rs.getInt(1));
                    a.setDesconto(rs.getString(3));
                    a.setLimite(rs.getString(2));
                    
                    lista_nome.add(a);
                } return lista_nome;
            } else {
                return null;
            }
            } catch(SQLException e) {
                return null;          
        }
       
       }

    public List<Promocao> ListarComboPromocao (int nome){
                Date x=new Date();
        String j="";
        j=""+x+"";
        String[] s = j.split(" ");
        switch(s[1]){
            case "Jun":
                s[1]="6";
                break; 
        }
        String data= s[5]+"-"+s[1]+"-"+s[2];

        String sql = "select * "
                   + "from promocao where limite >= "+data+" and id_veiculo= "+nome+" ";
                List<Promocao> lista = new ArrayList<Promocao>();
                try{
                   PreparedStatement ps = getCon().prepareStatement (sql);
                   ResultSet rs = ps.executeQuery();

                   if(rs != null){
                   while(rs.next()){
                        Promocao a = new Promocao ();
                        a.setDesconto(rs.getString(3));
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
    
   
    
    public List<Promocao> ListarComboPromocao2 (int nome){
                Date x=new Date();
        String j="";
        j=""+x+"";
        String[] s = j.split(" ");
        switch(s[1]){
            case "Jun":
                s[1]="6";
                break; 
        }
        String data= s[5]+"-"+s[1]+"-"+s[2];

        String sql = "select * "
                   + "from promocao where limite >= "+data+" and veiculo.modelo= "+nome+" ";
                List<Promocao> lista = new ArrayList<Promocao>();
                try{
                   PreparedStatement ps = getCon().prepareStatement (sql);
                   ResultSet rs = ps.executeQuery();

                   if(rs != null){
                   while(rs.next()){
                        Promocao a = new Promocao ();
                        a.setDesconto(rs.getString(1));
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
    
    
    
    
    
    
    
    
    
  
}// final**