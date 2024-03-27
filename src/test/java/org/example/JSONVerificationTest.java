package org.example;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JSONVerificationTest {
    @Test
    void testJSONWithOnlyAsterisk() throws IOException {
        String jsonPath = "src/JSON_files/ResourceWithOnlyAsterisk.json";
        String jsonData = JSONVerification.readJSONFile(jsonPath);

        assertFalse(JSONVerification.verifyJSON(jsonData));
    }

    @Test
    void testJSONWithoutAsterisks() throws IOException{
        String jsonPath = "src/JSON_files/ResourceWithoutAsterisks.json";
        String jsonData = JSONVerification.readJSONFile(jsonPath);

        assertTrue(JSONVerification.verifyJSON(jsonData));
    }

    @Test
    void testJSONWithAsteriskInText() throws IOException{
        String jsonPath = "src/JSON_files/ResourceWithAsteriskInText.json";
        String jsonData = JSONVerification.readJSONFile(jsonPath);

        assertFalse(JSONVerification.verifyJSON(jsonData));
    }

    @Test
    void testJSONWithMultipleAsterisks() throws IOException{
        String jsonPath = "src/JSON_files/ResourceWithMultipleAsterisks.json";
        String jsonData = JSONVerification.readJSONFile(jsonPath);

        assertTrue(JSONVerification.verifyJSON(jsonData));
    }

    @Test
    void testInvalidJSON() throws IOException {
        String jsonPath = "src/JSON_files/InvalidJSON.json";
        String jsonData = JSONVerification.readJSONFile(jsonPath);

        assertThrows(JSONException.class, ()->{
            JSONVerification.verifyJSON(jsonData);
        });
    }
}
