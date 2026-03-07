package api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Type {
	  private String key;
	  
	  public String getKey() {
	        return key;
	    }

	    public void setKey(String key) {
	        this.key = key;
	    }
}
