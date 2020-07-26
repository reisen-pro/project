package com.project.myutil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 */
public class AesUtil {

    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */

    private static final String SKY = "abcdef0123456789";
    private static final String IVPARAMETER = "0123456789abcdef";

    /**
     * 加密
     *
     * @param str 入参字符串
     * @return 加密后的值
     */
    public static String encrypt(String str) {
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = SKY.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(IVPARAMETER.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] bytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            result = new BASE64Encoder().encode(bytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | InvalidKeyException | BadPaddingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解密
     *
     * @param str 加密后的字符串
     * @return 解密后的密码
     */
    public static String decrypt(String str) {
        String originalString = null;
        try {
            byte[] raw = SKY.getBytes(StandardCharsets.US_ASCII);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IVPARAMETER.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            // 先用base64解密
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(str);
            byte[] original = cipher.doFinal(encrypted1);
            originalString = new String(original, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return originalString;
    }


    public static void main(String[] args) {
        System.out.println(AesUtil.encrypt("1234567891123456"));
        System.out.println(AesUtil.decrypt("NwruFWQ7zBgQrjFz9c0sHFW+tveQy3afYV1g2Tr2Y70="));

    }
}