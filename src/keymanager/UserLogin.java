package keymanager;

import java.util.Arrays;

/**
 * @author Isabelle Tngzon
 */
public class UserLogin {
    
    private String USERNAME = "issa";
    private char[] PASSWORD = {'p','a','s','s','w','o','r','d'}; 
    
    public UserLogin(){}
    
    //TODO make this legit/secure. Use JAAS or some secure athentication of some sort. 
    
    public int login(String username, char[] password){
        if (!username.equals(USERNAME) || !Arrays.equals(PASSWORD,password)){
            return 1;
        } else {
            System.out.println("Authentication success.");
            return 0;
        }
    }
}
