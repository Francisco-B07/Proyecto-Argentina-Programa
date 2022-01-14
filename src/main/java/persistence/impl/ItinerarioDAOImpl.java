package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Atraccion;
import model.Promocion;
import model.Sugerible;
import model.User;
import model.nullobjects.NullUser;
import persistence.ItinerarioDAO;
import persistence.UserDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	public int insert(User t) {
		try {
			String query = "INSERT INTO ITINERARIO (user_id, atraccion_id, promo_id) VALUES(?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			
			for(Sugerible s : t.getCompras()) {
				if(s.esPromocion()) {
					statement.setInt(1, t.getId());
					statement.setString(2, "NULL");
					statement.setInt(3, s.getId());
				}
				else {
					statement.setInt(1, t.getId());
					statement.setInt(2, s.getId());
					statement.setString(3, "NULL");
				}
			}
			
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new MissingDataException(e);
			}
		}

	public int delete(User t) {
		try {
			String query = "DELETE FROM ITINERARIO WHERE ID_USUARIO = ?";
			Connection conn = ConnectionProvider.getConnection();			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, t.getId());
			
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}
	
	

	@Override
	public User find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User t) {
		// TODO Auto-generated method stub
		return 0;
	}







}

