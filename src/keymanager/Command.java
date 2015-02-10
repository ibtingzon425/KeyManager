package keymanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * @author Isabelle Tingzon
 * @edited Angelu Kaye Tiu
 */
public class Command {
    
    // Executes bash commands in java
    // PIRATTE Command Line Tool by Sonia Jahid, University of Illinois at Urbana-Champaign.
    // For more information, visit: http://www.soniajahid.com	
    
    private final String abeLocation;

    public Command() {
        this.abeLocation = System.getProperty("user.dir")+"/piratte/";
    }
    
    public String encrypt(String pub_key, String file, String policy){
        String[] command = {abeLocation + "easier-enc", pub_key, file, policy};
        return execute(command, "easier-enc"); 
    }
        
    public String decrypt(String pub_key, String private_key, String lambda_k, String file){
        String[] command = {abeLocation + "easier-dec", pub_key, private_key, lambda_k, file+".cpabe.proxy"};
        return execute(command, "easier-dec"); 
    } 
    
    public String execute(String[] command, String strcom){
        StringBuilder output = new StringBuilder();
        ProcessBuilder pb = new ProcessBuilder(command);
        try {
            Process p = pb.start();
            BufferedReader reader = 
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = 
                            new BufferedReader(new InputStreamReader(p.getErrorStream()));

            //Read output of commands (if status is not 0)
            String line;           
            while ((line = reader.readLine())!= null || (line = error.readLine())!= null) {
                output.append(line).append("\n");
            }

            //returns status code of command; 0 if successful
            int status_code = p.waitFor();
            System.out.println(strcom + " exited with status code " + status_code + ".");
        } catch (IOException | InterruptedException e) {}
        return output.toString();
    }
}