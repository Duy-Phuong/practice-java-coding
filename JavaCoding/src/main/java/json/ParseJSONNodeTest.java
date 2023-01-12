package json;

import com.fasterxml.jackson.databind.*;
import java.io.*;
public class ParseJSONNodeTest {
    public static void main(String args[]) {
        String jsonStr = "{ \"name\" : \"Raja\", \"age\" : 30," +
                " \"technologies\" : [\"Java\", \"Scala\", \"Python\"]," +
                " \"nestedObject\" : { \"field\" : \"value\" } }";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode node = objectMapper.readValue(jsonStr, JsonNode.class);
            JsonNode nameNode = node.get("name");
            String name = nameNode.asText();
            System.out.println(name);
            JsonNode ageNode = node.get("age");
            int age = ageNode.asInt();
            System.out.println(age);
            JsonNode array = node.get("technologies");
            JsonNode jsonNode = array.get(1);
            String techStr = jsonNode.asText();
            System.out.println(techStr);
            JsonNode child = node.get("nestedObject");
            JsonNode childField = child.get("field");
            String field = childField.asText();
            System.out.println(field);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}