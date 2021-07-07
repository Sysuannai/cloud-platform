package com.yzchn.platform.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class SymmetricEncoder {
    final static Base64.Encoder encoder = Base64.getEncoder();
    final static Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 给字符串加密
     *
     * @param text
     * @return
     */
    public static String encode(String text) {
        byte[] textByte = new byte[0];
        try {
            textByte = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodedText = encoder.encodeToString(textByte);
        return encodedText;
    }

    /**
     * 将加密后的字符串进行解密
     *
     * @param encodedText
     * @return
     */
    public static String decode(String encodedText) {
        String text = null;
        try {
            String strB = encodedText.substring(0, 2);
            String strE = encodedText.substring(encodedText.length() - 2, encodedText.length());
            encodedText = strE + encodedText.substring(2, encodedText.length() - 2) + strB;
            text = new String(decoder.decode(encodedText), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 将加密后的字符串进行解密
     *
     * @param encodedText
     * @return
     */
    public static String encodes(String encodedText) {
        encodedText = encode(encodedText);
        String strB = encodedText.substring(0, 2);
        String strE = encodedText.substring(encodedText.length() - 2, encodedText.length());
        encodedText = strE + encodedText.substring(2, encodedText.length() - 2) + strB;
        return encodedText;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        /*String username = "Miracle Luna";
        String password = "p@sSW0rd";

        // 加密
        System.out.println("====  [加密后] 用户名/密码  =====");
        System.out.println(SymmetricEncoder.encode(username));
        System.out.println(SymmetricEncoder.encode(password));

        // 解密
        System.out.println("\n====  [解密后] 用户名/密码  =====");
        System.out.println(SymmetricEncoder.decode(SymmetricEncoder.encode(username)));
        System.out.println(SymmetricEncoder.decode(SymmetricEncoder.encode(password)));*/
//        String str = "MQ==";
//        String strB = str.substring(0, 2);
//        String strE = str.substring(str.length() - 2, str.length());
//        str = strE + str.substring(2, str.length() - 2) + strB;
//        System.out.println(str + "," + strB + "," + strE);

//        String str1 = "a123456";
//        // 给字符串加密
//        String encode = encodes(str1);
//        System.out.println(" 加密后的数据" + encode);
//        System.out.println(" 解密后的数据" + decode(encode));
//        LocalDate startDate =
//                LocalDate.parse("2021-05-27", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        if(startDate.isBefore(LocalDate.now())){
//            System.out.println("预计完成时间不能小于当前时间");
//        }
        String a = "a";
        String b = a;
        a = "c";
        System.out.println(b);
        System.out.println((1000 / 7));
    }

}