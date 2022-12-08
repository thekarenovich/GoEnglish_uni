package Data;

public class ConfigUser {
    public String access_token;
    public String email;
    public String user_id;

    private static ConfigUser instance;

    public static ConfigUser getInstance(){
        if (instance == null){
            instance = new ConfigUser();
        }
        return instance;
    }
}
