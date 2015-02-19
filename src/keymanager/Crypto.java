package keymanager;

/**
 * @author Isabelle Tingzon
 */
public class Encrypt {
   
   Command cmd = new Command();
   public String mk;
   public String pk;
   public String dir;
    
   public Encrypt(){
       dir = System.getProperty("user.dir");
       pk = "/pub_key";
   }
   
   public void encryptFile(String filename, String policy){
       System.out.println(cmd.encrypt("pub_key", filename , policy));
   }
}
