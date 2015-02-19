package keymanager.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author issa
 * @edited angelukayetiu
 */
public class CommandDaoBethenImpl implements CommandDao {
    
    private final String ABEIMPL;
    private final String ENCRYPT;
    private final String DECRYPT;

    public CommandDaoBethenImpl() {
        this.ABEIMPL = System.getProperty("user.dir")+"/cpabe-0.11";
        this.ENCRYPT = ABEIMPL + "/cpabe-enc";
        this.DECRYPT  = ABEIMPL + "/cpabe-dec";
    }
    
    // Executes bash commands in java
    // Used for executing CP-ABE Command Line Tool by Bethencourt.
    // See http://acsc.cs.utexas.edu/cpabe/ for more details.
    
    @Override
    public void execute(String[] command, String strcom) throws CommandFailedException{
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

            if (status_code!=0) throw new CommandFailedException();
        } catch (IOException | InterruptedException e) {}
        
    }
    
    @Override
    public void encrypt(String pub_key, String file, String policy) throws CommandFailedException {
        String[] command = {ENCRYPT, pub_key, file, policy};
        execute(command, ENCRYPT); 
    }
    
    @Override
    public void decrypt(String pub_key, String private_key, String lambda_k, String file) throws CommandFailedException {
        String[] command = {DECRYPT, pub_key, private_key, file};
        execute(command, DECRYPT); 
    }     
}