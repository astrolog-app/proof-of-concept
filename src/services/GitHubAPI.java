package services;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class GitHubAPI {
    private static final Logger logger = AppLogger.getLogger();
    private static final String repoOwner = "rouvenspaar";
    private static final String repoName = "AstroLog-web";

    public static String getNewestVersion() {
        List<String> versions = getVersions();

        Collections.sort(versions);

        // Get the last (highest) version from the sorted list
        return versions.get(versions.size() - 1);
    }

    private static List<String> getVersions() {
        List<String> versions = new ArrayList<>();

        String folderPath = "assets/versions"; // Leave empty to get root folder contents

        try {
            URI apiUrl = new URI("https", "api.github.com", "/repos/" + repoOwner + "/" + repoName + "/contents/" + folderPath, null);

            // Create HTTP connection
            URL url = apiUrl.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request headers
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

            // Get response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse JSON response
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.toString());

                // Extract directory names
                for (JsonNode node : jsonNode) {
                    if ("dir".equals(node.get("type").asText())) {
                        versions.add(node.get("name").asText());
                    }
                }
            } else {
                logger.severe("Failed to fetch repository contents. Response code: " + responseCode);
            }
        } catch (Exception e) {
            logger.warning("Error during GitHubAPI fetch:" + "\t" + e.getMessage());
        }

        return versions;
    }

    public static void downloadFile(String path) {
        try {
            URI apiUrl = new URI("https://raw.githubusercontent.com/" + repoOwner + "/" + repoName + "/main/assets/versions/1.1.1/releaseNotes.json");

            // Create HTTP connection
            URL url = apiUrl.toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check if the request was successful
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                FileOutputStream outputStream = new FileOutputStream("downloaded_file.txt"); // Specify the path to save the downloaded file

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();
                inputStream.close();
                System.out.println("File downloaded successfully!");
            } else {
                System.out.println("Failed to download file. HTTP error code: " + connection.getResponseCode());
            }
        } catch (IOException | URISyntaxException e) {
            logger.severe(e.getMessage());
        }
    }
}
