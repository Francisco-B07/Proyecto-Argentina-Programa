package utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ClimateApi {
	
	Map<String, Object> location;
	Map<String, Object> current;
	Map<String, Object> condition;

	public Map<String, Object> getLocation() {
		return location;
	}

	public void setLocation(Map<String, Object> location) {
		this.location = location;
	}

	public Map<String, Object> getCurrent() {
		return current;
	}

	public void setCurrent(Map<String, Object> current) {
		this.current = current;
	}
	
	public Map<String, Object> getCondition(ClimateApi c) {
		Map<String, Object> con = (Map<String, Object>) c.getCurrent().get("condition");
		return con;
	}

	public void setCondition(Map<String, Object> current) {
		this.current = current;
	}

	@Override
	public String toString() {
		return location + ", "+ current;
	}

}


