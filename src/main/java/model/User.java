package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.Crypt;

public class User {

	private Integer id;
	private String username, password;
	private Boolean admin;
	private Integer coins;
	private Double time;
	private Boolean active;
	private Integer monedasGastadas;
	private Double horasNecesarias;
	protected TipoDeAtraccion tipo;
	private List<Sugerible> sugerenciasCompradas = new LinkedList<Sugerible>();
	private List<Atraccion> atraccionesCompradas = new LinkedList<Atraccion>();
	private Map<String, String> errors;


	public User(Integer id, String username, String password, Integer coins, Double time, Boolean admin, TipoDeAtraccion tipo, Boolean active) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.coins = coins;
		this.time = time;
		this.admin = admin;
		this.tipo = tipo;
		this.active = active;
	}
	
	public void comprarSugerible(Sugerible s) {
		this.time -= s.getTiempo();
		this.coins -= s.getCosto();
		s.restarCupo();
		
		sugerenciasCompradas.add(s);
		if (s.esPromocion()) {
			atraccionesCompradas.add(s.getAtraccionGratis()); 
			Promocion promo = (Promocion) s;
			for (Atraccion a : promo.getAtracciones()) {
				atraccionesCompradas.add(a);
				
				//System.out.println(a.getNombre() + a.getCupo());
			}
		} else {
			Atraccion atraccion = (Atraccion) s;
			atraccionesCompradas.add(atraccion);
			//System.out.println(atraccion.getNombre() + atraccion.getCupo());
			
		}
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (coins <= 0) {
			errors.put("monedas", "Debe ser positivo");
		}
		if (time <= 0) {
			errors.put("tiempo", "Debe ser positivo");
		}
		
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void addToItinerary(Atraccion atraccion) {
		this.coins -= atraccion.getCosto();
		this.time -= atraccion.getTiempo();
		// TODO agregar a su lista
	}

	public boolean canAfford(Sugerible s) {
		return s.getCosto() <= this.coins;
	}

	public boolean canAttend(Sugerible s) {
		return s.getTiempo() <= this.time;
	}

	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.password);
	}

	public Boolean getAdmin() {
		return admin;
	}

	public Integer getCoins() {
		return coins;
	}

	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public Double getTime() {
		return time;
	}
	
	public TipoDeAtraccion getTipo() {
		return this.tipo;
	}

	public String getUsername() {
		return username;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public boolean isNull() {
		return false;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public void setTipo(TipoDeAtraccion tipo) {
		this.tipo = tipo;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = Crypt.hash(password);
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isActive() {
		return active;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", admin=" + admin + "]";
	}

	public void setActive(Boolean active) {
		this.active = active;
		
	}

	public void addToItinerary(Promocion promocion) {
		List<Atraccion> atracciones = promocion.getAtracciones();
		for (Atraccion atraccion : atracciones) {
			this.coins -= atraccion.getCosto();
			this.time -= atraccion.getTiempo();
			
			//TODO cambiar a sugerible
		}
	}
	
	public boolean yaCompro(Sugerible sugerencia) {
		for (Sugerible s : atraccionesCompradas) {
			if (sugerencia.esOContiene(s)) 
				return true;
		}
		return false;
	}
	
	public List<Sugerible> getCompras() {
		// TODO Auto-generated method stub
		return this.sugerenciasCompradas;
	}
}
