package keymanager;

import java.io.*;
import java.util.concurrent.Callable;
import javax.net.ssl.*;

/**
 * @author issa
 */
public class SSLClient{
    
    private final String HOST;
    private final String PUBKEY;
    private final String PWD;
    private final int PORT;
    
    private DataInputStream  console   = null;
    private DataOutputStream streamOut = null;
    private DataInputStream streamIn =  null;
    private SSLSocketFactory factory;
    
    public SSLClient(String host, int port, String pubkey, String password){
        HOST = host;
        PORT = port;
        PUBKEY = pubkey;
        PWD = password;
    }
            
    public boolean generateKeys() throws Exception {
         try {
             //Initialize SSL Properties
            System.setProperty("javax.net.ssl.trustStore", PUBKEY);
            System.setProperty("javax.net.ssl.keyStorePassword", PWD);
            factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(HOST, PORT);
            socket.startHandshake();
            
            streamOut = new DataOutputStream(socket.getOutputStream());
            streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                        
            //Client sends message "master_key"
            String writeLine = "master_key";
            streamOut.writeUTF(writeLine);
            streamOut.flush();

            //Client reads mk filesize
            String filesize = streamIn.readUTF();
            System.out.println(filesize);
            
            //Client recieves master_key and closes socket
            getFile(socket, writeLine, Integer.parseInt(filesize));
            socket.close();
            
            //Client opens new sock
            socket = (SSLSocket) factory.createSocket(HOST, PORT);
            socket.startHandshake();
            streamOut = new DataOutputStream(socket.getOutputStream());
            streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                        
            //Client sends message "public_key"
            writeLine = "public_key";
            streamOut.writeUTF(writeLine);
            streamOut.flush();
            
            //Client reads pk filesize
            filesize = streamIn.readUTF();
            System.out.println(filesize);
            
            //Client recieves public_key
            getFile(socket, writeLine, Integer.parseInt(filesize));
            socket.close();
            
            return true;
         } catch (IOException | NumberFormatException e) {
            System.err.println(e.toString());
         }
         return false;
    }
   
    private void getFile(SSLSocket socket, String get_filename, int fileSize) throws IOException{
        BufferedInputStream get = new BufferedInputStream(socket.getInputStream());
        PrintWriter put = new PrintWriter(socket.getOutputStream(),true);
        
        int bytes = 0;
        String filename = get_filename;
        put.println(filename);
        File file = new File(filename);
        OutputStream  fos = new FileOutputStream(new File(file.toString()));
        byte byte_array[] = new byte[1024];
        int totalbytes = 0;

        while(totalbytes < fileSize){ 
            fos.write(byte_array, 0, bytes);
            bytes = get.read(byte_array, 0, 1024);
            totalbytes += bytes;
        } 
        
        fos.flush();
        System.out.println("File sucessfully received!");
        fos.close();
    }
    
    private static void printSocketInfo(SSLSocket s) {
        System.out.println("Socket class: "+s.getClass());
        System.out.println("   Remote address = "
           +s.getInetAddress().toString());
        System.out.println("   Remote port = "+s.getPort());
        System.out.println("   Local socket address = "
           +s.getLocalSocketAddress().toString());
        System.out.println("   Local address = "
           +s.getLocalAddress().toString());
        System.out.println("   Local port = "+s.getLocalPort());
        System.out.println("   Need client authentication = "
           +s.getNeedClientAuth());
        SSLSession ss = s.getSession();
        System.out.println("   Protocol = "+ss.getProtocol());
   }
}
