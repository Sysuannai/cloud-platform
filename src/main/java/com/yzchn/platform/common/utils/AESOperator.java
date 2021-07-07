package com.yzchn.platform.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;

/**
 * 加解密工具
 *
 * @author 柏龙进
 */
public class AESOperator {

    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static String sKey = "ABCQWE1231231239";
    private static String ivParameter = "1231231239ABCQWE";
    private static AESOperator instance = null;

    public AESOperator() {

    }

    public static AESOperator getInstance() {
        if (instance == null) {
            instance = new AESOperator();
        }
        return instance;
    }

    /**
     * 加密
     *
     * @param sSrc
     * @return
     */
    public static String encrypt(String sSrc) {
        if (StringUtils.isBlank(sSrc)) {
            return sSrc;
        }
        String result = "";
        try {
            Cipher cipher;
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            result = new String(Base64.encodeBase64(encrypted));
            // result = new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 此处使用BASE64做转码。
        return result;

    }

    /**
     * 解密
     *
     * @param sSrc
     * @return
     */
    public static String decrypt(String sSrc) {
        if (StringUtils.isBlank(sSrc)) {
            return sSrc;
        }
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            // 先用base64解密
            byte[] encrypted1 = Base64.decodeBase64(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
//        // 需要加密的字串
//        String cSrc = "1234567";
//        System.out.println(cSrc + "  长度为" + cSrc.length());
//        // 加密
//        long lStart = System.currentTimeMillis();
//        String enString = AESOperator.getInstance().encrypt(cSrc);
//        System.out.println("加密后的字串是：" + enString + "长度为" + enString.length());
//
//        long lUseTime = System.currentTimeMillis() - lStart;
//        System.out.println("加密耗时：" + lUseTime + "毫秒");
//        // 解密
//        lStart = System.currentTimeMillis();
//        String DeString = AESOperator.getInstance().decrypt("nHBF/7kVbmnwTaJM1SCOaWkGeS8UWo3TeYmwyxhw4yg=");
//        System.out.println("解密后的字串是：" + DeString);
//        lUseTime = System.currentTimeMillis() - lStart;
//        System.out.println("解密耗时：" + lUseTime + "毫秒");


        // Parses the date
        LocalDateTime dt1 = LocalDateTime.parse("2020-08-30 00:00:00");

        // Prints the date
        System.out.println(dt1);

        // Parses the date
        LocalDateTime nowTime = LocalDateTime.now();

        // Prints the date
        System.out.println(nowTime);

        // Compares both dates
        System.out.println(nowTime.isAfter(dt1));

    }
}
