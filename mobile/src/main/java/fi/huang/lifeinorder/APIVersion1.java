package fi.huang.lifeinorder;

/**
 * Created by Ken on 20/09/14.
 */
public class APIVersion1 {
    private final String SERVER = "http://192.168.0.18:3000/api/v1/";
    private final String TODO = "todo";
    private final String ADD = "/add/";

    private String POST_TODO;

    public String getPOST_TODO() {
        return POST_TODO;
    }

    public void setPOST_TODO(String userId) {
        this.POST_TODO = SERVER + userId + ADD + TODO ;
    }
}
