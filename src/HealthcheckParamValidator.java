
public class HealthcheckParamValidator {
    public boolean isIntervalValid(String intervalString){
        if(Long.parseLong(intervalString) <= 0){
            return false;
        }
        return true;
    }
}
