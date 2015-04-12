package keymanager.view;

/**
 * @author Isabelle Tingzon
 * @author Angelu Kaye Tiu
 */

public class Main {
    public static void main(String[] arg){        
        KeyManager kmapi = new KeyManager();
        KGCClient kgc = new KGCClient();
        //kgc.setVisible(true);
        kmapi.setVisible(true);
    }
}
        /*Command com = new Command();
         String filename = "a.txt";
         String[] attributes = {"friend", "colleague"};
         String[] revoked_users = {"issas_key.id"};
         System.out.println("Setting up master key and public key...");
         System.out.println(com.setup("pub_key", "master_key"));
         System.out.println("Generating private (secret) key...");
         System.out.println(com.keygen("issas_key", "pub_key", "master_key", attributes));
         System.out.println("Generating proxy key...");
         System.out.println(com.revoke("proxy_key", "pub_key", "master_key", revoked_users));
         System.out.println("Encrypting file " + filename + " ...");
         System.out.println(com.encrypt("pub_key", filename , "friend and colleague"));
         System.out.println("Converting to proxy...");
         System.out.println(com.convert("lambda_k", "pub_key", filename, "proxy_key", "issas_key"+".id"));
         System.out.println("Decrypting file " + filename + " ...");
         System.out.println(com.decrypt("pub_key" , "issas_key", "lambda_k", filename));
         */
        

