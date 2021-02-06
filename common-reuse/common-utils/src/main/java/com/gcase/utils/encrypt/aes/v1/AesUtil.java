package com.gcase.utils.encrypt.aes.v1;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * <p>
 *
 * <p>
 *
 * @creator bicheng.deng
 * @createTime 2021/2/1
 */

public class AesUtil {


    static final String ALGORITHM = "AES";
    public static Charset CHARSET = Charset.forName("UTF-8");
    public static String AES_LOGIN_KEY = "myKey";


    public static SecretKey generateKey(String aesKey) { // 生成密钥
        KeyGenerator kgen = null;
        SecureRandom random=null;
        SecretKeySpec sks = null;
        try {
            kgen = KeyGenerator.getInstance(ALGORITHM);
            random = SecureRandom.getInstance("SHA1PRNG","SUN");
            random.setSeed(aesKey.getBytes());
            kgen.init(128,random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            sks = new SecretKeySpec(enCodeFormat, ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        return sks;
    }


    public static byte[] encrypt(String content, String aesKey) { // 加密
        return aes(content.getBytes(CHARSET), Cipher.ENCRYPT_MODE, generateKey(aesKey));
    }

    public static String decrypt(byte[] contentArray, String aesKey) { // 解密
        byte[] result =  aes(contentArray,Cipher.DECRYPT_MODE,generateKey(aesKey));
        return new String(result,CHARSET);
    }

    private static byte[] aes(byte[] contentArray, int mode, SecretKey secretKey){
        Cipher cipher = null;
        byte[] result = null;
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(mode, secretKey);
            result = cipher.doFinal(contentArray);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args) {
        String content = "111111";
        String aesKey = "myKey";
        long timeStart = System.currentTimeMillis();
        byte[] encryptResult = encrypt(content, aesKey);
        long timeEnd = System.currentTimeMillis();
        System.out.println("加密后的结果为：" + new String(encryptResult,CHARSET));
        String decryptResult = decrypt(encryptResult,aesKey);
        System.out.println("解密后的结果为：" + decryptResult);
        System.out.println("加密用时：" + (timeEnd - timeStart));

    }
}
