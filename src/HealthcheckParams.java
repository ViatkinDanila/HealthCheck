import java.net.URL;

public class HealthcheckParams {
    private final URL url;
    private final int interval;

    public HealthcheckParams(URL url, int interval){
        this.url = url;
        this.interval = interval;
    }

    public URL getUrl(){
        return url;
    }

    public int getInterval(){
        return interval;
    }
}
