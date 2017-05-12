package me.snowflake.onlinecounter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static org.joda.time.format.DateTimeFormat.forPattern;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * ..
 */
public class StatusProvider {
    private static final Logger LOG = getLogger(StatusProvider.class);
    private static final String SEPARATOR = ";";
    private static final String STATUS_FILE = "./online-status.csv";

    private DateTimeFormatter dtf = forPattern("YYYY-MM-dd HH:mm:ss");

    private final Map<String, String> urlsByServer;

    public StatusProvider(Map<String, String> urlsByServer) {
        this.urlsByServer = new HashMap<>(urlsByServer);
    }

    public String getStatuses() {
        StringBuilder result = new StringBuilder();
        urlsByServer.forEach((server, url) -> {
            JSONObject jsonObject = readJsonFromUrl(url);
            String line = getStatusLine(server, jsonObject);
            result.append(line);
        });
        return result.toString();
    }

    public void writeToFile(String s) {
        try {
            File file = new File(STATUS_FILE);
            FileUtils.writeStringToFile(file, s, Charset.forName("UTF-8"), true);
        } catch (IOException e) {
            LOG.error("File problem", e);
        }

    }

    private String getStatusLine(String server, JSONObject jsonObject) {
        try {
            StringBuilder result = new StringBuilder();

            result
                    .append(server).append(SEPARATOR)
                    .append(DateTime.now().toString(dtf)).append(SEPARATOR)
                    .append(jsonObject.get("total")).append(SEPARATOR)
                    .append(jsonObject.get("now")).append(SEPARATOR)
                    .append(jsonObject.get("monthly")).append(SEPARATOR)
                    .append(jsonObject.get("weekly")).append(SEPARATOR)
                    .append(jsonObject.get("daily")).append(SEPARATOR)
                    .append(jsonObject.get("hourly")).append("\n");

            return result.toString();
        } catch (JSONException e) {
            LOG.warn("Malformed JSON for server " + server);
            return "";
        }
    }

    private JSONObject readJsonFromUrl(String url) {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = IOUtils.toString(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } catch (IOException |JSONException e) {
            LOG.error("Failed to read JSON from URL", e);
            return new JSONObject();
        }
    }
}
