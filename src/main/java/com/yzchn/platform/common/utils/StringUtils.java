package com.yzchn.platform.common.utils;

import org.apache.poi.ss.formula.functions.T;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Base64.Decoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static String DES = "DES";
    // DES加密生成随机串使用
    private static byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};
    private static String[] chars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    /**
     * 匹配正整数
     */
    private static Pattern patternInt = Pattern.compile("^[1-9][0-9]*");

    /**
     * 匹配纯数字
     */
    private static Pattern patternNumber = Pattern.compile("^[0-9][0-9]*");

    /**
     * 匹配数字、英文不区分大小任意组合
     */
    private static Pattern patternNumberOrEn = Pattern.compile("[0-9A-Za-z]*");
    /**
     * 匹配数字和英文不区分大小任意组合
     */
    private static Pattern patternNumberAndEn = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]*$");
    /**
     * 匹配大于0的正数(会包含0.0、0.00)
     */
    private static Pattern patternPositive = Pattern.compile("([1-9][0-9]*(\\.\\d{1,2})?)|(0\\.\\d{1,2})");
    /**
     * 以中文开头，中文或中文+数字
     */
    private static Pattern patternName = Pattern.compile("^[\\u4e00-\\u9fa5]+(\\d*)$");
    /**
     * 校验中文
     */
    private static Pattern patternChineseName = Pattern.compile("^[\\u4e00-\\u9fa5]{1,10}$");


    /**
     * object为null, "null", "undefined" 时 返回 "" 字符串
     *
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        if (obj instanceof String) {
            return isNull(obj.toString());
        }
        return StringUtils.isObjectEmpty(obj) ? "" : isNull(obj.toString());
    }

    /**
     * 字符串为 null, "null", "undefined" 时 返回 "" 字符串
     *
     * @param s
     * @return
     */
    public static String isNull(String s) {
        if (null == s || "".equals(s.trim()) || "null".equals(s.trim()) || "undefined".equals(s.trim())) {
            s = "";
        }
        return s;
    }

    /**
     * 注意，如果order是null，也返回true。
     *
     * @param order
     * @return
     */
    public static boolean isModelAllFieldNull(Object order, Class pclass) {
        if (order == null) {
            return true;
        }
        Class<T> cls = pclass;
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (isObjectNotEmpty(field.get(order))) {
                    return false;
                }
            } catch (IllegalAccessException e) {
            }
        }
        return true;
    }

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inStr.getBytes("utf-8"));
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    public static String encrypt(String str) {
        String s = convertMD5(string2MD5(str));
        return s;
    }

    public static String getOrderString(HttpServletRequest request) {
        return getOrderString(request, null);
    }

    public static String getOrderString(HttpServletRequest request, String prefix) {
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        String orderby = null;
        if (!"".equals(isNull(sort)) && !"".equals(isNull(order)) && !"id".equals(sort) && !"oper".equals(sort)) {
            orderby = "order by " + ("".equals(isNull(prefix)) ? "" : prefix + ".") + sort + " " + order;
        }

        return orderby;
    }

    /**
     * 随机生成字符串 length用户要求产生字符串的长度
     */
    public static String getRandomString(int length, String str) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    // 数组转字符串
    public static String ArrayToString(String[] arr) {
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            bf.append(arr[i]);
        }
        return bf.toString();
    }

    // sha1加密
    public static String SHA1Encode(String sourceString) {
        String resultString = null;
        try {
            resultString = new String(sourceString);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }

    public static final String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString().toUpperCase();
    }

    /**
     * 判断对象是否为null或""(条件成立则返回ture,否则返回false)
     *
     * @param objects
     * @return
     */
    public static boolean isObjectEmpty(Object objects) {
        if (objects == null || "".equals(objects) || "null".equalsIgnoreCase(objects + "") || "undefined".equalsIgnoreCase(objects + "")) {
            return true;
        }
        return false;
    }

    /**
     * 判断对象是否不为null或""(条件成立则返回ture,否则返回false)
     *
     * @param objects
     * @return
     */
    public static boolean isObjectNotEmpty(Object objects) {
        return !isObjectEmpty(objects);
    }

    public static boolean isObjectsNotEmpty(Object... objects) {
        boolean flag = false;
        if (null != objects && objects.length > 0) {
            for (Object obj : objects) {
                flag = !isObjectEmpty(obj);
                if (!flag) {
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 把带_的key 去掉_并把后面第一个字母大写
     *
     * @param obj
     */
    public static void upperCase(Object obj) {
        if (obj instanceof Map) {
            Map<String, Object> temp_map = new HashMap<String, Object>((Map<String, Object>) obj);
            for (String key : temp_map.keySet()) {
                Object value = ((Map<String, Object>) obj).get(key);
                if (key.indexOf("_") > -1) {
                    String newkey = "";
                    String[] temp = key.split("_");
                    if (null != temp && temp.length > 0) {
                        for (int x = 0; x < temp.length; x++) {
                            // 首字母大写
                            String t = temp[x];
                            if (x != 0) {
                                newkey += t.substring(0, 1).toUpperCase() + t.substring(1);
                            } else {
                                newkey += t;
                            }
                        }
                        ((Map<String, Object>) obj).remove(key); // 移除带_的key，放入新key
                        ((Map<String, Object>) obj).put(newkey, value);
                    }
                }
                if (value instanceof List) {
                    for (Object o : (List) value) {
                        if (o instanceof Map) {
                            upperCase(o);
                        }
                    }
                }
                if (value instanceof Map) {
                    upperCase(value);
                }
            }
        }
    }

    /**
     * 生成随机数字
     *
     * @param length 长度
     * @return
     */
    public static String randomNum(int length) {
        StringBuffer buf = new StringBuffer("1,2,3,5,6,8,9,0");
        String[] arr = buf.toString().split(",");
        StringBuffer b = new StringBuffer();
        Random r;
        int k;
        for (int i = 0; i < length; i++) {
            r = new Random();
            k = r.nextInt();
            b.append(String.valueOf(arr[Math.abs(k % arr.length)]));
        }

        return b.toString();
    }

    /**
     * 生成订单号
     *
     * @return
     */
    public static String orderNumber() {
        String dateStr = "";
        Date date = new Date();
        DateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmssSS");
        try {
            dateStr = sdf2.format(date) + (1000 + (int) (Math.random() * 9000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptBASE64(String key) throws Exception {
        return new String(Base64.getDecoder().decode(key));
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String key) throws Exception {
        return Base64.getEncoder().encodeToString(key.getBytes());
    }

    public static Map<String, String> xmlToMap(String xml) throws Exception {
        Map<String, String> ret = new HashMap<String, String>();
        Document doc = DocumentHelper.parseText(xml);
        Element root = doc.getRootElement();
        if (null != root) {
            List<Element> eles = root.elements();
            if (null != eles && eles.size() > 0) {
                for (Element el : eles) {
                    if (null != el) {
                        ret.put(el.getName(), el.getTextTrim());
                    }
                }
            }
        }
        return ret;
    }

    public static Map<String, Object> xmlToMaps(String xml) throws Exception {
        Map<String, Object> ret = new HashMap<String, Object>();
        Document doc = DocumentHelper.parseText(xml);
        Element root = doc.getRootElement();
        if (null != root) {
            List<Element> eles = root.elements();
            if (null != eles && eles.size() > 0) {
                ret = elToMap(eles);
            }
        }
        return ret;
    }

    private static Map<String, Object> elToMap(List<Element> eles) {
        Map<String, Object> ret = new HashMap<String, Object>();
        if (null != eles && eles.size() > 0) {
            for (Element el : eles) {
                if (null != el && el.isTextOnly()) {
                    ret.put(el.getName(), el.getText());
                }
                if (null != el && !el.isTextOnly()) {
                    Object obj = ret.get(el.getName());
                    if (null != obj && !(obj instanceof List)) {
                        List<Object> objs = new ArrayList<Object>();
                        objs.add(obj);
                        objs.add(elToMap(el.elements()));
                        ret.put(el.getName(), objs);
                    } else if (null != obj) {
                        ((List<Object>) obj).add(elToMap(el.elements()));
                    } else {
                        ret.put(el.getName(), elToMap(el.elements()));
                    }
                }
            }
        }
        return ret;
    }

    /**
     * 微信支付拼接xml
     *
     * @param params
     * @return
     */
    public static String toXml(Map<String, String> params) {
        String xml = "<xml>";
        for (String key : params.keySet()) {
            if ("body".equals(key) || "attach".equals(key) || "sign".equals(key)) {
                xml += "<" + key + "><![CDATA[" + params.get(key) + "]]></" + key + ">";
            } else {
                xml += "<" + key + ">" + params.get(key) + "</" + key + ">";
            }
        }
        xml += "</xml>";
        return xml;
    }

    public static String toXmlT(Map<String, String> params) {
        String xml = "<xml>";
        for (String key : params.keySet()) {
            if ("detail".equals(key)) {
                xml += "<" + key + "><![CDATA[" + params.get(key) + "]]></" + key + ">";
            } else {
                xml += "<" + key + ">" + params.get(key) + "</" + key + ">";
            }
        }
        xml += "</xml>";
        return xml;
    }

    public static String getSign(Map<String, Object> params, String appkey) {
        String qr = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String[] p = new String[params.size()];
        int x = 0;
        for (String key : params.keySet()) {
            if (params.get(key) instanceof Timestamp) {
                p[x] = key + "=" + sdf.format(params.get(key));
            } else if (params.get(key) instanceof Date) {
                p[x] = key + "=" + sdf1.format(params.get(key));
            } else {
                p[x] = key + "=" + params.get(key);
            }
            x++;
        }
        // 参数排序
        Arrays.sort(p);
        // 拼出查询参数
        for (String param : p) {
            String[] value = param.split("=");
            qr += value[0] + "=" + params.get(value[0]) + "&";
        }
        return StringUtils.string2MD5(qr + "key=" + appkey).toUpperCase();
    }

    /***
     * 获取客户端IP地址;这里通过了Nginx获取;X-Real-IP,
     *
     * @param request
     * @return
     */
    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-real-ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        try {
            String[] ips = ip.split(",");
            if (StringUtils.isObjectNotEmpty(ips) && ips.length > 0) {
                ip = ips[0];
            }
        } catch (Exception e) {
        }
        return ip;
    }

    /**
     * 判断多个字符串是否都相等
     *
     * @param strs
     * @return
     */
    public static boolean stringEqual(String... strs) {
        boolean flag = true;
        if (null != strs && strs.length > 0) {
            String temp = null;
            for (int i = 0; i < strs.length; i++) {
                String str = strs[i];
                if (i == 0) {
                    temp = str;
                } else {
                    if (!((temp == null && str == null) || (null != temp && str != null && temp.equals(str)))) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = Base64.getEncoder().encodeToString(bt);
        return strs;
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        IvParameterSpec zeroIv = new IvParameterSpec(iv);

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, zeroIv);

        return cipher.doFinal(data);
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws IOException, Exception {
        if (data == null)
            return null;
        Decoder decoder = Base64.getDecoder();
        byte[] buf = decoder.decode(data);
        byte[] bt = decrypt(buf, key.getBytes());
        return new String(bt, "utf-8");
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        IvParameterSpec zeroIv = new IvParameterSpec(iv);

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, zeroIv);

        return cipher.doFinal(data);
    }

    /**
     * 生成指定位数的数字邀请码
     *
     * @param num 生成的位数
     * @throws Exception
     */
    public static String generateShortUuid(int num) throws Exception {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < num; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % chars.length]);
        }
        return shortBuffer.toString();
    }

    /**
     * 当前时间精确到毫秒，用于生成实体卡号:20190218102758163
     */
    public static String currentTimeStr() {
        Calendar Cld = Calendar.getInstance();
        int YY = Cld.get(Calendar.YEAR);
        int MM = Cld.get(Calendar.MONTH) + 1;
        int DD = Cld.get(Calendar.DATE);
        int HH = Cld.get(Calendar.HOUR_OF_DAY);
        int mm = Cld.get(Calendar.MINUTE);
        int SS = Cld.get(Calendar.SECOND);
        int MI = Cld.get(Calendar.MILLISECOND);
        StringBuilder str = new StringBuilder("" + YY + (MM > 9 ? MM : "0") + MM + DD + HH + mm + SS + MI);
        while (str.length() < 16) {
            str.append("1");
        }
        return str.toString();
    }


    /**
     * 获取指定字符串出现的次数
     *
     * @param srcText  源字符串
     * @param findText 要查找的字符串
     * @return
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }


    /**
     * 随机生成 ***位 大小写字母 数字 编码
     *
     * @param state  n(数字) c(字母(不区分大小写)) c-65(大写) c-97(小写) n-c(数字字母混合(字母不区分大小写)) n-c-65(数字字母混合(字母大写)) n-c-97(数字字母混合(字母小写))
     * @param length 位数
     * @return
     */
    public static String getOrangeKey(int length, String state) {
        String OrangeKey = "";
        Random random = new Random();
        // 参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if (state.equals("n")) {
                charOrNum = "num";
            } else if (state.equals("c") || state.equals("c-65") || state.equals("c-97")) {
                charOrNum = "char";
            }
            // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                if (state.equals("c-65") || state.equals("n-c-65")) {
                    temp = 65;
                } else if (state.equals("c-97") || state.equals("n-c-97")) {
                    temp = 97;
                }
                OrangeKey += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                OrangeKey += String.valueOf(random.nextInt(10));
            }
        }
        return OrangeKey;
    }


    /**
     * 判断字符串数组是否为正整数
     *
     * @param strs 传入的字符串数组
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String[] strs) {
        for (String str : strs) {
            if (!isInteger(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为纯数字
     *
     * @param str 传入的字符串
     * @return 符合返回true, 否则返回false
     */
    public static boolean isNumber(String str) {
        return patternNumber.matcher(str).matches();
    }

    /**
     * 判断是否为数字或英文不区分大小写混合
     *
     * @param str 传入的字符串
     * @return 符合返回true, 否则返回false
     */
    public static boolean isNumberOrEn(String str) {
        return patternNumberOrEn.matcher(str).matches();
    }

    /**
     * 判断是否为数字和英文不区分大小写混合(位置不区分)
     *
     * @param str 传入的字符串
     * @return 符合返回true, 否则返回false
     */
    public static boolean isNumberAndEn(String str) {
        return patternNumberAndEn.matcher(str).matches();
    }

    /**
     * 判断是否为正整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        return patternInt.matcher(str).matches();
    }

    /**
     * 判断字符串数组是否为正数
     *
     * @param strs 传入的字符串数组
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isPositive(String[] strs) {
        for (String str : strs) {
            if (!isPositive(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为大于0的正数
     *
     * @param str 传入的字符串
     * @return 是正返回true, 否则返回false
     */
    public static boolean isPositive(String str) {
        boolean flag = false;
        if (patternPositive.matcher(str).matches()) {
            if (0f != Float.valueOf(str)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 以中文开头，中文或中文+数字组合
     *
     * @param str
     * @return
     */
    public static boolean checkName(String str) {

        return patternName.matcher(str).matches();
    }

    /**
     * 校验中文
     *
     * @param str
     * @return
     */
    public static boolean checkChineseName(String str) {

        return patternChineseName.matcher(str).matches();
    }

    /**
     * 向上进位取整
     *
     * @param obj 数字类型 float 、double
     * @return
     */
    public static String formateUpInt(Object obj) {
        if (obj instanceof Float || obj instanceof Double) {
            //超过小数点后2位了
            DecimalFormat decimalFormat = new DecimalFormat("0");
            decimalFormat.setGroupingSize(0);
            decimalFormat.setRoundingMode(RoundingMode.UP);
            return new BigDecimal(decimalFormat.format(obj)).stripTrailingZeros().toPlainString();
        }
        return obj + "";
    }

    /**
     * 四舍五入取整
     *
     * @param obj 数字类型 float 、double
     * @return
     */
    public static String formateInt(Object obj) {
        if (obj instanceof Float || obj instanceof Double) {
            //超过小数点后2位了
            DecimalFormat decimalFormat = new DecimalFormat("0");
            decimalFormat.setGroupingSize(0);
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            return new BigDecimal(decimalFormat.format(obj)).stripTrailingZeros().toPlainString();
        }
        return obj + "";
    }

    /**
     * 保留2位小数，超过向上进位
     *
     * @param obj 数字类型 float 、double
     * @return
     */
    public static String formateTwo(Object obj) {
        if (obj instanceof Float || obj instanceof Double) {
            String val = new BigDecimal(obj + "").stripTrailingZeros().toPlainString();
            if (val.contains(".")) {
                val = val.substring(val.indexOf("."));
                if (3 < val.length()) {
                    String valNew = new BigDecimal(obj + "").stripTrailingZeros().toPlainString();
                    float objTwo = Float.parseFloat(valNew);
                    if (4 < val.length()) {
                        //大于3位的部分省略
                        objTwo = Float.parseFloat(valNew.substring(0, valNew.lastIndexOf(".") + 4));
                    }
                    val = (objTwo + "").substring((objTwo + "").indexOf("."));
                    //再次判断一下，防止第3位小数是0
                    if (3 < val.length()) {
                        //超过小数点后2位了
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        decimalFormat.setMaximumFractionDigits(2);
                        decimalFormat.setGroupingSize(0);
                        decimalFormat.setRoundingMode(RoundingMode.UP);
                        return new BigDecimal(decimalFormat.format(objTwo)).stripTrailingZeros().toPlainString();
                    } else {
                        return objTwo + "";
                    }
                }
            }
        }
        return obj + "";
    }

    /**
     * 保留2位小数，超过部分省略
     *
     * @param obj 数字类型 float 、double
     * @return
     */
    public static String decimalFormateTwo(Object obj) {
        if (obj instanceof Float || obj instanceof Double) {
            String val = new BigDecimal(obj + "").stripTrailingZeros().toPlainString();
            if (val.contains(".")) {
                val = val.substring(val.indexOf("."));
                if (3 < val.length()) {
                    //超过小数点后2位了
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    decimalFormat.setMaximumFractionDigits(2);
                    decimalFormat.setGroupingSize(0);
                    decimalFormat.setRoundingMode(RoundingMode.FLOOR);
                    return new BigDecimal(decimalFormat.format(obj)).stripTrailingZeros().toPlainString();
                }
            }
        }
        return obj + "";
    }

    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return true--有，false--没有
     */
    public static boolean containsEmoji(String source) {
        if (isObjectEmpty(source)) {
            return false;
        }
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
            return true;
        }
        return false;
    }
}
