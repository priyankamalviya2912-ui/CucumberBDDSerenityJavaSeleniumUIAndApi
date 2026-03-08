package api.pojo.spacexpojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Core {

    private String core;
    private Integer flight;
    private Boolean reused;

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public Integer getFlight() {
        return flight;
    }

    public void setFlight(Integer flight) {
        this.flight = flight;
    }

    public Boolean getReused() {
        return reused;
    }

    public void setReused(Boolean reused) {
        this.reused = reused;
    }
    
}