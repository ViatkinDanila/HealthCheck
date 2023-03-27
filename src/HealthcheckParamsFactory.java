import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

public class HealthcheckParamsFactory {
    private static final HealthcheckParamValidator validator = new HealthcheckParamValidator();

    public static Optional<HealthcheckParams> create(String[] healthCheckArgs) {
        Objects.requireNonNull(healthCheckArgs);
        if (healthCheckArgs.length != 2 || !validator.isIntervalValid(healthCheckArgs[0])) {
            return Optional.empty();
        }

        HealthcheckParams healthcheckParams = null;
        try {
            int interval = Integer.parseInt(Objects.requireNonNull(healthCheckArgs[0]));
            URL url = new URL(Objects.requireNonNull(healthCheckArgs[1]));
            healthcheckParams = new HealthcheckParams(url, interval);
        } catch (MalformedURLException e) {
            System.err.println("URL parsing error...");
        }
        return Optional.ofNullable(healthcheckParams);
    }

}

