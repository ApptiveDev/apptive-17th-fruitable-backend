package apptive.fruitable.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Generator {

    public String result;

    public MD5Generator(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        MessageDigest mdMD5 = MessageDigest.getInstance("MD5");
        mdMD5.update(input.getBytes("UTF-8"));
    }
}
