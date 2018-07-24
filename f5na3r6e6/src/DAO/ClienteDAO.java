package DAO;
import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO extends ExecuteSQL{
    public ClienteDAO(Connection con){
        super(con);
    }
    public String Inserir_Cliente(Cliente a){
        String sql = "insert into cliente values(?,?,?,?,?,?,?,?,0)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setString(2, a.getEmail());
            ps.setString(3, a.getCpf());
            ps.setString(4, a.getTelefone());
            ps.setString(5, a.getRg());
            ps.setString(6, a.getCidade());
            ps.setString(7, a.getEstado());
            ps.setString(8, a.getCnh());
           
        if(ps.executeUpdate() > 0 ) {   
            return "Inserido com sucesso.";
        }else {
            return "Error ao inserir";
        }
        }catch (SQLException e) {
            return e.getMessage();
        }
    
    }
    
    public java.util.List<Cliente> ListarCliente() {
        String sql = "select * from cliente";
        java.util.List<Cliente> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            
            if (rs != null) {
                while (rs.next()) {
                    Cliente a = new Cliente();
                    a.setNome(rs.getString(1));
                    a.setEmail(rs.getString(2));
                    a.setCpf(rs.getString(3));
                    a.setTelefone(rs.getString(4));
                    a.setRg(rs.getString(5));
                    a.setCidade(rs.getString(6));
                    a.setEstado(rs.getString(7));
                    a.setCnh(rs.getString(8));
                    a.setId_cliente(rs.getInt(9));
                    
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
            public java.util.List<Cliente> Pesquisar_Cod_Cliente(int cod) {
           String sql = "select * from cliente where id_cliente = '" + cod + "'";
           java.util.List<Cliente> lista_cod = new ArrayList<Cliente>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            
            if (rs != null) {
                while (rs.next()) {
                    Cliente a = new Cliente();
                    
                    a.setNome(rs.getString(1));
                    a.setEmail(rs.getString(2));
                    a.setCpf(rs.getString(3));
                    a.setTelefone(rs.getString(4));
                    a.setRg(rs.getString(5));
                    a.setCidade(rs.getString(6));
                    a.setEstado(rs.getString(7));
                    a.setCnh(rs.getString(8));
                    a.setId_cliente(rs.getInt(9));
                    
                    lista_cod.add(a);
                    
                } return lista_cod;
            } else {
                return null;
            }
            } catch(SQLException e) {
                return null;          
        }
       
       }        
       public java.util.List<Cliente> Pesquisar_Nome_Cliente(String nome) {
           String sql = "select * "
                   + "from cliente where nome Like '" + nome + "%'";
           java.util.List<Cliente> lista_nome = new ArrayList<Cliente>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            
            if (rs != null) {
                while (rs.next()) {
                    Cliente a = new Cliente();
                 
                    a.setNome(rs.getString(1));
                    a.setEmail(rs.getString(2));
                    a.setCpf(rs.getString(3));
                    a.setTelefone(rs.getString(4));
                    a.setRg(rs.getString(5));
                    a.setCidade(rs.getString(6));
                    a.setEstado(rs.getString(7));
                    a.setCnh(rs.getString(8));
                    a.setId_cliente(rs.getInt(9));
                    
                    lista_nome.add(a);
                } return lista_nome;
            } else {
                return null;
            }
            } catch(SQLException e) {
                return null;          
        }
       
       }
       
       public boolean Testar_Cliente(int cod) {
           boolean Resultado = false;
           try {
               String sql = "select * from cliente where idcliente = " + cod + "";
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
       
           public java.util.List<Cliente> CapturarCliente(int cod) {
               String sql = "select * from cliente where id_cliente = " + cod + "";
               java.util.List<Cliente> lista = new ArrayList<Cliente>();
               try {
                   PreparedStatement ps = getCon().prepareStatement(sql);
                   ResultSet rs = ps.executeQuery();
                   if (rs != null) {
                       while (rs.next()) {
                           Cliente a = new Cliente();
                           
                    a.setNome(rs.getString(1));
                    a.setEmail(rs.getString(2));
                    a.setCpf(rs.getString(3));
                    a.setTelefone(rs.getString(4));
                    a.setRg(rs.getString(5));
                    a.setCidade(rs.getString(6));
                    a.setEstado(rs.getString(7));
                    a.setCnh(rs.getString(8));
                    a.setId_cliente(rs.getInt(9));
                           
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
           
            public String  Alterar_Cliente(Cliente a){
            
                String sql = "update cliente set nome = ?, email = ?, cpf = ?, telefone = ?, rg = ?, cidade = ?, estado = ?, cnh = ?  where id_cliente = ?";
            try{
            
                PreparedStatement ps = getCon().prepareStatement(sql);
                ps.setInt(9, a.getId_cliente());
                ps.setString(1, a .getNome());
                ps.setString(2, a.getEmail());
                ps.setString(3, a.getCpf());
                ps.setString(4, a.getTelefone());
                ps.setString(5, a.getRg());
                ps.setString(6, a.getCidade());
                ps.setString(7, a.getEstado());
                ps.setString(8, a.getCnh());
                
              
                if(ps.executeUpdate() > 0) {
                
                    return "Atualizado com sucesso";
                
                }else{
                        return "Erro ao atualizar";
                
                }
            
            }catch (SQLException ex){
            return ex.getMessage();
            }
            
            }
                public List<Cliente> ListarComboCliente (){
                
                    String sql = "select nome from cliente order by nome";
                List<Cliente> lista = new ArrayList<Cliente>();
                try{
                   PreparedStatement ps = getCon().prepareStatement (sql);
                   ResultSet rs = ps.executeQuery();

                   if(rs != null){
                   while(rs.next()){
                        Cliente a = new Cliente ();
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
                public List<Cliente> consultarCodigoCliente(String nome){
        
        String sql="select id_cliente,cpf from cliente where nome like '" + nome + "%'";
        List<Cliente> lista = new ArrayList<Cliente>();
        try{
            PreparedStatement ps= getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
               while(rs.next()){
                Cliente a = new Cliente();
                a.setId_cliente(rs.getInt(1));
                a.setCpf(rs.getString(2));
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
    public String Excluir_Cliente(Cliente a){
        String sql = "delete from cliente where id_cliente = ? and nome = ?";
        
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, a.getId_cliente());
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
    
    
    
    
    
    
    
    
    
    
}//fim do DAO    