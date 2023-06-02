package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DAO {
	public UsuarioDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	public boolean insert(Usuario usuario) {
		boolean status = false;
		try {
			String sql = "INSERT INTO usuario (nome, id, email, senha, telefone, cidade, assinatura, cpf) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement st = conexao.prepareStatement(sql);

			st.setString(1, usuario.getNome());
			st.setInt(2, usuario.getID());
			st.setString(3, usuario.getEmail());
			st.setString(4, usuario.getSenha());
			st.setInt(5, usuario.getTelefone());
			st.setString(6, usuario.getCidade());
			st.setString(7, usuario.getAssinatura());
			st.setInt(8, usuario.getCpf());
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public Usuario get(int id) {
		Usuario usuario = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE id=" + id;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
						rs.getInt("telefone"), rs.getString("cidade"), rs.getString("assinatura"), rs.getInt("cpf"),
						rs.getString("senha"));
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}

	public List<Usuario> get() {
		return get("");
	}

	public List<Usuario> getOrderByID() {
		return get("id");
	}

	public List<Usuario> getOrderByNome() {
		return get("nome");
	}

	private List<Usuario> get(String orderBy) {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Usuario a = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
						rs.getInt("telefone"),
						rs.getString("cidade"), rs.getString("assinatura"), rs.getInt("cpf"), rs.getString("senha"));
				usuarios.add(a);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return usuarios;
	}

	public boolean update(Usuario usuario) {
		boolean status = false;
		try {
			String sql = "UPDATE usuario SET nome = '" + usuario.getNome() + "', "
					+ "email = '" + usuario.getEmail() + "', "
					+ "telefone = " + usuario.getTelefone() + ", "
					+ "cidade = '" + usuario.getCidade() + "', "
					+ "assinatura = '" + usuario.getAssinatura() + "', "
					+ "cpf = " + usuario.getCpf() + ", "
					+ "WHERE id = " + usuario.getID();

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
			st.executeUpdate("DELETE FROM usuario WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
}