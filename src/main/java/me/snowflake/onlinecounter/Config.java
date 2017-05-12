package me.snowflake.onlinecounter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ..
 */
public class Config {
    private static final Map<String, String> SERVERS = new HashMap<>();
    static {
        SERVERS.put("Altior",  "http://wts-prod-altior.eu-central-1.elasticbeanstalk.com/api/v1/performance/online");
        SERVERS.put("Novus",   "http://wts-prod-novus.eu-central-1.elasticbeanstalk.com/api/v1/performance/online");
        SERVERS.put("Paceum",  "http://wts-prod-paceum.eu-central-1.elasticbeanstalk.com/api/v1/performance/online");
        SERVERS.put("Vitios",  "http://wts-prod-vitios.eu-central-1.elasticbeanstalk.com/api/v1/performance/online");
        SERVERS.put("Sarenus", "http://wts-prod-sarenus.us-east-1.elasticbeanstalk.com/api/v1/performance/online");
        SERVERS.put("Golunar", "http://wts-prod-golunar.us-east-1.elasticbeanstalk.com/api/v1/performance/online");
        SERVERS.put("Bonium",  "http://wts-prod-bonium.sa-east-1.elasticbeanstalk.com/api/v1/performance/online");
        SERVERS.put("Lopius",  "http://wts-prod-lopius.sa-east-1.elasticbeanstalk.com/api/v1/performance/online");
        SERVERS.put("Tranum",  "http://wts-prod-tranum.ap-northeast-2.elasticbeanstalk.com/api/v1/performance/online");
        SERVERS.put("Incenas", "http://wts-prod-incenas.ap-northeast-2.elasticbeanstalk.com/api/v1/performance/online");
    }

    public static Map<String, String> getServers() {
        return Collections.unmodifiableMap(SERVERS);
    }
}
