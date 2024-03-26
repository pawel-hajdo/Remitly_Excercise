package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String jsonPath = "src/input.json";

        try{
            String jsonData = readJSONFile(jsonPath);
            boolean isValid = verifyJSON(jsonData);
            System.out.println("Input JSON is valid: " + isValid);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (JSONException e){
            System.err.println("Invalid JSON format: " + e.getMessage());
        }
    }

    private static String readJSONFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    private static boolean verifyJSON(String jsonData) {
        try{
            JSONObject jsonObject = new JSONObject(jsonData);

            JSONObject policyDocument = jsonObject.getJSONObject("PolicyDocument");
            JSONArray statementArray = policyDocument.getJSONArray("Statement");

            for(int i = 0; i < statementArray.length(); i++){
                JSONObject statement = statementArray.getJSONObject(i);
                String resource = statement.getString("Resource");

                if(resource.contains("*")){
                    return false;
                }
            }
            return true;
        } catch (JSONException e){
            throw new JSONException("Invalid JSON format: " + e.getMessage());
        }

    }
}
