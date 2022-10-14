import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

public class Test {

    public static void main(String[] args) {

            char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
            String b = "gx74KW1roM9qwzPFVOBLSlYaeyncdNbI=JfUCQRHtj2+Z05vshXi3GAEuT/m8Dpk6";
            char[] str = "qfTdqfTdqfTdVaxJeAJQBRl3dExQyYOdNAlfeaxsdGhiyYlTcATdbHthwalGcRu5nHzs".toCharArray();
            String out = null;
            for (int i = 0; i < str.length; i++) {
                out += a[b.indexOf(str[i])];
            }
            System.out.println(decode(out));
    }

    public static String decode(String base64Str) {
        // 解码后的字符串
        String str = "";
        // 解码
        byte[] base64Data = DatatypeConverter.parseBase64Binary(base64Str);
        try {
            // byte[]-->String
            str = new String(base64Data, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}