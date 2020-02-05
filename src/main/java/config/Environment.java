package config;

public class Environment {

    private static final String defaultEnvironmentName = "env";

    public static String getName() {
        String environmentName = System.getenv("environment");

        if (environmentName == null || environmentName.isEmpty()) {
            environmentName = defaultEnvironmentName;
        }
        return environmentName;
    }
}
