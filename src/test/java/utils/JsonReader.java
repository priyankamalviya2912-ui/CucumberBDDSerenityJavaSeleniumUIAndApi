package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class JsonReader {

    private static JsonNode data;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readTree(new File("src/test/resources/testdata/authorData.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static JsonNode empData() {
    	try {
    		 ObjectMapper mapper = new ObjectMapper();
             data = mapper.readTree(new File("src/test/resources/payload/createEmployee.json"));
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return data;
    }

    public static String getValue(String key) {
        return data.get("author").get(key).asText();
    }
    
    public static void main(String args[]) {
    	JsonReader reader = new JsonReader();
    	reader.getValue("authorId");
    	System.out.println();
    }
}