import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Optional<HealthcheckParams> healthCheckParams = HealthcheckParamsFactory.create(args);
        if (healthCheckParams.isEmpty()) {
            return;
        }

        HealthCheck healthCheck = new HealthCheck(healthCheckParams.get());
        healthCheck.start();
    }
}