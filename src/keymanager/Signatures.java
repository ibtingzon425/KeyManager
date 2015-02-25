package keymanager;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author issa
 */
public class Signatures {
    
    public Signatures(){}
    
    public void sign(String privkeyfile, String file) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, InvalidKeyException, SignatureException{
        //Digital Signature
        
        FileInputStream keyfis = new FileInputStream(privkeyfile);
        byte[] encKey = new byte[keyfis.available()];
        keyfis.read(encKey);
        keyfis.close();

        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(encKey);

        //KeyFactory keyFactory = KeyFactory.getInstance(encKey, "AES");
        //PrivateKey privKey = keyFactory.generatePrivate(privKeySpec);
        
        SecretKeySpec key = new SecretKeySpec(encKey, "AES");
        
        
        Signature dsa = Signature.getInstance("AES");
        dsa.initSign((PrivateKey) key);
        
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bufin = new BufferedInputStream(fis);
        byte[] buffer = new byte[1024];
        int len;
        while (bufin.available() != 0) {
            len = bufin.read(buffer);
            dsa.update(buffer, 0, len);
        }
        
        byte[] realSig = dsa.sign();
        
        FileOutputStream sigfos = new FileOutputStream("sig");
        sigfos.write(realSig);
 
        sigfos.close();        
    }
    
}
