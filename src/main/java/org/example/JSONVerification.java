package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class JSONVerification {
    public static void main(String[] args) {
        String jsonPath = "src/ResourceWithOnlyAsterisk.json";

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

    public static String readJSONFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    public static boolean verifyJSON(String jsonData) {
        try{
            JSONObject jsonObject = new JSONObject(jsonData);

            JSONObject policyDocument = jsonObject.getJSONObject("PolicyDocument");
            JSONArray statementArray = policyDocument.getJSONArray("Statement");

            for(int i = 0; i < statementArray.length(); i++){
                JSONObject statement = statementArray.getJSONObject(i);
                String resource = statement.getString("Resource");

                int asterisksCount = (int) resource.chars().filter(x -> x == '*').count();
                if(asterisksCount == 1){
                    return false;
                }
            }
            return true;
        } catch (JSONException e){
            throw new JSONException("Invalid JSON format: " + e.getMessage());
        }

    }
}
