
package DAO;

import java.sql.*;
import Modelo.Funcionario;
import java.util.ArrayList;
import java.util.List;
public class FuncionarioDAO extends ExecuteSQL {
    
    public FuncionarioDAO(Connection con){
        super (con);
    }
    public boolean Logar(String login, String senha){
        boolean finalResult = false;
        try{
            String consulta = "select login, senha from funcionario"
           +" where login ='"+ login +"' and senha = '"+ senha + "'";
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while (rs.next()){
                    Funcionario a = new Funcionario ();
                    a.setLogin(rs.getString(1));
                    a.setLogin(rs.getString(2));
                    finalResult  = true;
                }
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return finalResult;
    }
    public String Inserir_Funcionario(Funcionario a){
        String sql = "insert into funcionario values (?,?,?,?,?,?,?,?,?,0,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement (sql);
            ps.setString(1, a.getSenha());
            ps.setString(2, a.getLogin());
            ps.setString(3, a.getNome());
            ps.setString(4, a.getEmail());
            ps.setString(5, a.getCpf());
            ps.setString(6, a.getTelefone());
            ps.setString(7, a.getRg());
            ps.setString(8, a.getPis());
            ps.setString(9, a.getEndereco());
            ps.setString(10, a.getTipo());
            
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com secesso.";
            } else {
                return "Error o inserir";
            }
        } catch (SQLException e){
            return e.getMessage();
        }
    }
        public List<Funcionario> ListarFuncionario(){
            String sql = "select * from funcionario";
            List<Funcionario> lista = new ArrayList<Funcionario>();
            try {
                PreparedStatement ps = getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if(rs != null) {
                    while (rs.next()){
                        Funcionario a = new Funcionario();
                        a.setNome(rs.getString(1));
                        a.setLogin(rs.getString(2));
                        a.setSenha(rs.getString(3));
                        a.setEmail(rs.getString(4));
                        a.setCpf(rs.getString(5));
                        a.setTelefone(rs.getString(6));
                        a.setRg(rs.getString(7));
                        a.setPis(rs.getString(8));
                        a.setEndereco(rs.getString(9));
                        a.setTipo(rs.getString(10));
                        a.setId_funcionario(rs.getInt(11));
                        
                        lista.add(a);
                        }
                    return lista;
                    
            }else{
                    return null;
                }
        }catch(SQLException e){
            return null;
        }
        }
        public List<Funcionario> Pesquisar_Nome_Funcionario(String nome) {
            String sql = "select * "
                    +"from funcionario where nome like '" + nome + "%'";
            List<Funcionario> lista = new ArrayList<Funcionario>();
             try {
                PreparedStatement ps = getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if(rs != null) {
                    while (rs.next()){
                        Funcionario a = new Funcionario();
                        a.setNome(rs.getString(1));
                        a.setLogin(rs.getString(2));
                        a.setSenha(rs.getString(3));
                        a.setEmail(rs.getString(4));
                        a.setCpf(rs.getString(5));
                        a.setTelefone(rs.getString(6));
                        a.setRg(rs.getString(7));
                        a.setPis(rs.getString(8));
                        a.setEndereco(rs.getString(9));
                        a.setTipo(rs.getString(10));
                        a.setId_funcionario(rs.getInt(11));
                        
                        lista.add(a);
                        }
                    return lista;
                    
            }else{
                    return null;
                }
        }catch(SQLException e){
            return null;
        }
        }
        public List<Funcionario> Pesquisar_Cod_Funcionario(int cod) {
            String sql = "select * "
                    + "from funcionario where id_funcionario = '" + cod + "'";
              List<Funcionario> lista = new ArrayList<Funcionario>();
             try {
                PreparedStatement ps = getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                if(rs != null) {
                    while (rs.next()){
                        Funcionario a = new Funcionario();
                        a.setNome(rs.getString(1));
                        a.setLogin(rs.getString(2));
                        a.setSenha(rs.getString(3));
                        a.setEmail(rs.getString(4));
                        a.setCpf(rs.getString(5));
                        a.setTelefone(rs.getString(6));
                        a.setRg(rs.getString(7));
                        a.setPis(rs.getString(8));
                        a.setEndereco(rs.getString(9));
                        a.setTipo(rs.getString(10));
                        a.setId_funcionario(rs.getInt(11));
                        
                        lista.add(a);
                        }
                    return lista;
                    
            }else{
                    return null;
                }
        }catch(SQLException e){
            return null;
        }
        }
        public boolean Testar_Funcionario(int cod) {
            boolean Resultado = false;
            try {
                String sql = "select * from funcionario where idfuncionario = " + cod + "";
                PreparedStatement ps = getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
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


        public List<Funcionario>CapturaFuncionario(int cod){
            String sql = "select * from funcionario where idfuncionario=" + cod + "";
            List<Funcionario> lista = new ArrayList();
            try{
                PreparedStatement ps = getCon().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        Funcionario a = new Funcionario ();
                        a.setNome (rs.getString (1));
                        a.setLogin(rs.getString(2));
                        a.setSenha(rs.getString(3));
 
                        lista.add(a);
                    }
                    return lista;
                }else{
                    return null;
                }
                
                }catch(SQLException e){
                    return null;
                }
        }
            public String Alterar_Funcionario(Funcionario a){
                String sql = "update funcionario set nome = ?, login = ?, senha = ?"
                        + " where idfuncionario = ?";
                try{
                    PreparedStatement ps = getCon().prepareStatement(sql);
                    ps.setString(1 , a.getNome());
                    ps.setString(2 , a.getLogin());
                    ps.setString(3 , a.getSenha());
                    
                    if(ps.executeUpdate() > 0){
                        return "Atualizado com sucesso. ";
                    }else{
                        return "Erro ao Atualizar";
                    }
                }catch(SQLException e){
                    return e.getMessage();
                }
            }
            
            
          public List<Funcionario> ListarComboFuncionario (){
                
                String sql = "select nome from funcionario order by nome";
                List<Funcionario> lista = new ArrayList<Funcionario>();
                try{
                   PreparedStatement ps = getCon().prepareStatement (sql);
                   ResultSet rs = ps.executeQuery();

                   if(rs != null){
                   while(rs.next()){
                        Funcionario a = new Funcionario ();
                        a.setNome(rs.getString(1));
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

     public List<Funcionario> consultarCodigoFuncionario(String nome){
        
        String sql="select idfuncionario from funcionario where nome ='" + nome + "'";
        List<Funcionario> lista = new ArrayList<Funcionario>();
        try{
            PreparedStatement ps= getCon ().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs != null){
               while(rs.next()){
                Funcionario a = new Funcionario ();
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
     
     
     public List<Funcionario> consultarCodigoFuncionario2(String nome){
        
        String sql="select * from funcionario where nome ='" + nome + "'";
        List<Funcionario> lista = new ArrayList<Funcionario>();
        try{
            PreparedStatement ps= getCon ().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs != null){
               while(rs.next()){
                Funcionario a = new Funcionario ();
                a.setId_funcionario(rs.getInt(11));
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
     
     
     
    public String Excluir_Funcionario(Funcionario a){
        String sql = "delete from funcionario where idfuncionario = ? and nome = ?";
        
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(2, a.getNome());
            if(ps.executeUpdate() > 0){
                return "Excluido com sucesso.";
            }else{
                 return "Erro ao excluir";   
            }
    }catch(SQLException e){
            return e.getMessage();
        }
    } 
}
