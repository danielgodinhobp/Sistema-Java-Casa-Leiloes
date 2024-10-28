import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    
    public void cadastrarProduto (ProdutosDTO produto){
    conn = new conectaDAO().connectDB();
    if (conn != null) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor()); 
            prep.setString(3, produto.getStatus()); 
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados.");
    }       
    }
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        return listagem;
    }   
}

