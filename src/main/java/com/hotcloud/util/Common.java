package com.hotcloud.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.StringCharacterIterator;
import java.util.Collection;
import java.util.Map;

public class Common {

    /**
     * 按照规定的逻辑，对常见类型判断是否为空
     */
    public static boolean empty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String && (obj.equals("") || obj.equals("0"))) {
            return true;
        } else if (obj instanceof Number && ((Number) obj).doubleValue() == 0) {
            return true;
        } else if (obj instanceof Boolean && !((Boolean) obj)) {
            return true;
        } else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 返回字符串的长度，单位字节。该计算得出的字节数，和数据库是相同的。是占用数据库存储的 真实长度
     */
    public static int strlen(String text) {
        return strlen(text, ConfigUtil.HOME_CHARSET);
    }

    public static int strlen(String text, String charsetName) {
        if (text == null || text.length() == 0) {
            return 0;
        }
        int length = 0;
        try {
            length = text.getBytes(charsetName).length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 通过对Url解码，还原到成相应的编码形式的字符串
     */
    public static String urlDecode(String s) {
        return urlDecode(s, ConfigUtil.HOME_CHARSET);
    }

    public static String urlDecode(String s, String enc) {
        if (!empty(s)) {
            try {
                return URLDecoder.decode(s, enc);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    /**
     * 将字符串s按enc编码理解，并编码url传送的asc码
     */
    public static String urlEncode(String s) {
        return urlEncode(s, ConfigUtil.HOME_CHARSET);
    }

    public static String urlEncode(String s, String enc) {
        if (!empty(s)) {
            try {
                return URLEncoder.encode(s, enc);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    /**
     * addslashes() 函数在指定的预定义字符前添加反斜杠。 做转义处理后，写入数据库就不会因敏感字符（'之类）导致sql有问题。
     * 此处的转义处理内容是:在字符串中的单引号、双引号、反斜杠之前，都附加上反斜杠
     */
    public static String addSlashes(String text) {
        if (text == null || text.equals("")) {
            return "";
        }
        StringBuffer sb = new StringBuffer(text.length() * 2);
        StringCharacterIterator iterator = new StringCharacterIterator(text);
        char character = iterator.current();
        while (character != StringCharacterIterator.DONE) {
            // DONE,字符型，当迭代器已到达文本末尾或开始处时返回的常量。
            switch (character) {
                case '\'': /* 单引号 */
                case '"': /* 双引号 */
                case '\\': /* 单斜杠 */
                    sb.append("\\"); /* 单斜杠 */
                default:
                    sb.append(character);
                    break;
            }
            character = iterator.next();
        }
        return sb.toString();
    }

    public static int intval(String s) {
        return intval(s, 10);
    }

    /**
     * 将s字符串按radix进制进行解析，返回十进制的整形变量
     */
    public static int intval(String s, int radix) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (radix == 0) {
            radix = 10;
        } else if (radix < Character.MIN_RADIX) {
            return 0;
        } else if (radix > Character.MAX_RADIX) {
            return 0;
        }
        int result = 0;
        int i = 0, max = s.length();
        int limit;
        int multmin;
        int digit;
        boolean negative = false;
        if (s.charAt(0) == '-') {
            negative = true;
            limit = Integer.MIN_VALUE;
            i++;
        } else {
            limit = -Integer.MAX_VALUE;
        }
        if (i < max) {
            digit = Character.digit(s.charAt(i++), radix);
            if (digit < 0) {
                return 0;
            } else {
                result = -digit;
            }
        }
        multmin = limit / radix;
        while (i < max) {
            digit = Character.digit(s.charAt(i++), radix);
            if (digit < 0) {
                break;
            }
            if (result < multmin) {
                result = limit;
                break;
            }
            result *= radix;
            if (result < limit + digit) {
                result = limit;
                break;
            }
            result -= digit;
        }
        if (negative) {
            if (i > 1) {
                return result;
            } else {
                return 0;
            }
        } else {
            return -result;
        }
    }

}
