import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HealthCheck {
    private final HealthcheckParams healthcheckParams;
    private final ScheduledExecutorService scheduler;

    public HealthCheck(HealthcheckParams healthcheckParams){
        this.healthcheckParams = Objects.requireNonNull(healthcheckParams);
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void start(){
        final Runnable healthCheckTask = () -> {
            int responseCode = -1;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) healthcheckParams.getUrl().openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(healthcheckParams.getInterval() * 1000);
                System.out.printf("Checking '%s'. ", healthcheckParams.getUrl().toString());
                responseCode = httpURLConnection.getResponseCode();
            } catch (IOException e){
                System.out.printf("Result: ERROR while sending request: %s", e.getMessage());
            }
            System.out.printf("Result: %s(%s)%n", responseCode == 200 ? "OK" : "ERR", responseCode);
        };

        scheduler.scheduleAtFixedRate(healthCheckTask, 0 , healthcheckParams.getInterval(), TimeUnit.SECONDS);
    }

}
