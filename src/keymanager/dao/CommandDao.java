package keymanager.dao;

/**
 *
 * @author angelukayetiu
 */
public interface CommandDao {

    public void encrypt(String pub_key, String file, String policy) throws CommandFailedException;
    public void decrypt(String pub_key, String private_key, String lambda_k, String file) throws CommandFailedException;
    public void execute(String[] command, String strcom) throws CommandFailedException;

}
