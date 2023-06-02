package dao;

import model.Alimento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDAO extends DAO {	
	public AlimentoDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Alimento alimento) {
		boolean status = false;
		try {
			String sql = "INSERT INTO alimentos (id, nome, calorias, proteinas, carboidratos, gorduras ) "
		               + "VALUES ('" + alimento.getNome() + "', "
		               + alimento.getGorduras() + ", " + alimento.getCarboidratos() + ", " + alimento.getProteinas() + ", "
					   + alimento.getCalorias() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Alimento get(int id) {
		Alimento alimento = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM alimento WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 alimento = new Alimento(rs.getInt("id"), rs.getString("nome"), (float) rs.getDouble("gorduras"),
						                (float) rs.getDouble("carboidratos"),
						 				(float) rs.getDouble("proteinas"),
						 				(float) rs.getDouble("calorias"));
//	        			               rs.getTimestamp("datafabricacao").toLocalDateTime(),
//	        			               rs.getDate("datavalidade").toLocalDate());
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return alimento;
	}
	
	
	public List<Alimento> get() {
		return get("");
	}

	
	public List<Alimento> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Alimento> getOrderByNome() {
		return get("nome");
	}
	
	
	public List<Alimento> getOrderByCarboidrato() {
		return get("carboidrato");
	}
	
	
	private List<Alimento> get(String orderBy) {
		List<Alimento> alimentos = new ArrayList<Alimento>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM alimento" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {
				Alimento a = new Alimento(rs.getInt("id"), rs.getString("nome"), (float) rs.getDouble("gorduras"),
						(float) rs.getDouble("carboidratos"),
						(float) rs.getDouble("proteinas"),
						(float) rs.getDouble("calorias"));
	            alimentos.add(a);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return alimentos;
	}
	
	
	public boolean update(Alimento alimento) {
		boolean status = false;
		try {
			String sql = "UPDATE alimento SET nome = '" + alimento.getNome() + "', "
					   + "gorduras = " + alimento.getGorduras() + ", "
					   + "carboidratos = " + alimento.getCarboidratos() + ","
					   + "proteinas = " + alimento.getCarboidratos() + ","
					   + "calorias = " + alimento.getCalorias() + ","
					   + "WHERE id = " + alimento.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM alimento WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}