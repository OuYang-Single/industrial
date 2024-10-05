package com.ijcsj.common_library.util;

import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class StringGenerator {
 
    public static String generateRandomString(int minLength, int maxLength) {
        Random random = new Random();
        int length = random.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder sb = new StringBuilder(length);
 
        for (int i = 0; i < length; i++) {
            // 可以根据需要扩展字符集
            int randomChar = random.nextInt(26) + 'a';
            sb.append((char) randomChar);
        }
 
        return sb.toString();
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String encrypt(String data, String secretKey)  {

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
            byte[] keyBytes = new byte[16];
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(secretKey.getBytes("UTF-8"));
            System.arraycopy(md.digest(), 0, keyBytes, 0, Math.min(keyBytes.length, md.digest().length));

            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            // 加密数据
            byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));

            // 将加密后的数据转换为Base64字符串
            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException |
                 IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            return null;
        }
    }


}