package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Atraccion;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import model.TipoDeAtraccion;
import persistence.PromocionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;


public class PromocionDAOImpl implements PromocionDAO {
	
	public List<Promocion> findAll() {
		try {
			String sql = "SELECT * FROM PROMOCION";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promociones = new LinkedList<Promocion>();
				while(resultados.next()) {
					if(resultados.getString(5).equals("AxB")) {
					promociones.add(toPromocionAxB(resultados));
					} if(resultados.getString(5).equals("Porcentual")) {
						promociones.add(toPromocionPorcentual(resultados));
					}if((resultados.getString(5).equals("Absoluta")))  {
					promociones.add(toPromocionAbsoluta(resultados));
					}
		}
			return promociones;

	} catch (SQLException e) {
			throw new MissingDataException(e);
	}
		
}

	@Override
	public Promocion find(Integer id) {
		try {
			String sql = "SELECT * FROM PROMOCION WHERE ID =?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;
			if(resultados.next()) {
				if(resultados.getString(5).equals("AxB")) {
				promocion = toPromocionAxB(resultados);
				} if(resultados.getString(5).equals("Porcentual")) {
					promocion = toPromocionPorcentual(resultados);
				}if((resultados.getString(5).equals("Absoluta")))  {
				promocion = toPromocionAbsoluta(resultados);
				}
			}
			return promocion;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}
	
	public List<Promocion> findPromocionesCompradas(Integer id) {
		try {
			String sql = "SELECT * FROM promocion INNER JOIN itinerario ON promocion.id = itinerario.promo_id WHERE itinerario.user_id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promociones = new LinkedList<Promocion>();
			while(resultados.next()) {
				if(resultados.getString(5).equals("AxB")) {
				promociones.add(toPromocionAxB(resultados));
				} if(resultados.getString(5).equals("Porcentual")) {
					promociones.add(toPromocionPorcentual(resultados));
				}if((resultados.getString(5).equals("Absoluta")))  {
				promociones.add(toPromocionAbsoluta(resultados));
				}
	}
		return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private static Promocion toPromocionPorcentual(ResultSet result) throws SQLException {
		return new PromocionPorcentual(result.getInt(1), result.getString(2),
									TipoDeAtraccion.valueOf(result.getString(3)), result.getInt(4), result.getString(5), result.getBoolean(6));

	}

private static Promocion toPromocionAxB(ResultSet result) throws SQLException {

	return new PromocionAxB(result.getInt(1), result.getString(2),
				TipoDeAtraccion.valueOf(result.getString(3)), DAOFactory.getAtraccionDAO().find((Integer)result.getInt(4)),
				result.getString(5), result.getBoolean(6));
	
	}
private static Promocion toPromocionAbsoluta(ResultSet result) throws SQLException {
		return new PromocionAbsoluta(result.getInt(1), result.getString(2), TipoDeAtraccion.valueOf(result.getString(3)),
				result.getInt(4), result.getString(5), result.getBoolean(6));
	}

	@Override
	public int insert(Promocion promocion) {
		try {
			String sql = "INSERT INTO PROMOCION (NOMBRE, TIPO, PRECIO_DESC, BENEFICIO) VALUES(?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			statement.setString(2, promocion.getTipo().name());
			statement.setInt(3, promocion.getCosto());
			statement.setString(4, promocion.getBeneficio());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promocion promocion) {
		try {
			String sql = "UPDATE PROMOCION SET NOMBRE = ?, TIPO = ?, PRECIO_DESC = ?, BENEFICIO = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, promocion.getNombre());
			statement.setString(i++, promocion.getTipo().name());
			statement.setInt(i++, promocion.getCosto());
			statement.setString(i++, promocion.getBeneficio());
			statement.setInt(i++, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public static void updateCupo(Promocion promo) {
		
		try {
			String sql = "UPDATE ATTRACTIONS SET capacity = capacity - 1 WHERE id = ?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			

			for (Atraccion a : promo.getAtracciones()) {
				statement.setInt(1, a.getId());
				statement.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Promocion promocion) {
		try {
			String sql = "UPDATE PROMOCION SET ACTIVE = 0 WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM ATTRACTIONS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
//	public int insertPromoAtracciones(Promocion promocion) {
//		try {
//			String sql = "INSERT INTO PROMOCION_ATRACCION (ID_PROMO, NOMBRE_PROMO, ID_ATRACCION, NOMBRE_ATRACCION) VALUES(?, ?, ?, ?)";
//			Connection conn = ConnectionProvider.getConnection();
//
//			PreparedStatement statement = conn.prepareStatement(sql);
//			int i = 1;
//			statement.setString(i++, promocion.getNombre());
//			statement.setInt(i++, promocion.getId());
//			statement.setDouble(i++, DAOFactory.getAtraccionDAO().find((Integer)result.getInt(4)));
//			statement.setInt(i++, promocion.getId());
//			int rows = statement.executeUpdate();
//
//			return rows;
//		} catch (Exception e) {
//			throw new MissingDataException(e);
//		}
//	}
	
//	public int deletePromoAtracciones(Promocion promocion) {
//		try {
//			String sql = "DELETE INTO PROMOCION_ATRACCION (ID_PROMO, NOMBRE_PROMO, ID_ATRACCION, NOMBRE_ATRACCION) VALUES(?, ?, ?, ?)";
//			Connection conn = ConnectionProvider.getConnection();
//
//			PreparedStatement statement = conn.prepareStatement(sql);
//			int i = 1;
//			statement.setString(i++, promocion.getNombre());
//			statement.setInt(i++, promocion.getCosto());
//			statement.setDouble(i++, promocion.getTiempo());
//			statement.setInt(i++, promocion.getId());
//			int rows = statement.executeUpdate();
//
//			return rows;
//		} catch (Exception e) {
//			throw new MissingDataException(e);
//		}
//	}
}


