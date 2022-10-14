import cn.hutool.core.util.RandomUtil;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

            char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
            String b = "gx74KW1roM9qwzPFVOBLSlYaeyncdNbI=JfUCQRHtj2+Z05vshXi3GAEuT/m8Dpk6";
            char[] str = "qfTdqfTdqfTdVaxJeAJQBRl3dExQyYOdNAlfeaxsdGhiyYlTcATdbHthwalGcRu5nHzs".toCharArray();
            String out = null;
            for (int i = 0; i < str.length; i++) {
                out += a[b.indexOf(str[i])];
            }
            System.out.println(decode(out));

        ExecutorTest();
    }

    private static void ExecutorTest() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        Future<Integer> f1 = completionService.submit(() -> {
            System.out.println("执行任务一");
            Thread.sleep(5000);
            return 1;
        });

        Future<Integer> f2 = completionService.submit(() -> {
            System.out.println("执行任务二");
            return 2;
        });

        Future<Integer> f3 = completionService.submit(() -> {
            System.out.println("执行任务三");
            Thread.sleep(3000);
            return 3;
        });

        for (int i = 0; i < 3; i++) {
            Future take = completionService.take();
            Integer integer = (Integer) take.get();
            executorService.execute(() -> {
                System.out.println("执行入库==" + integer);
            });
        }
        executorService.shutdown();
    }

    public static String decode(String base64Str) {
        // 解码后的字符串
        String str = "";
        // 解码
        byte[] base64Data = DatatypeConverter.parseBase64Binary(base64Str);
        // byte[]-->String
        str = new String(base64Data, StandardCharsets.UTF_8);
        return str;
    }


    @Test
    public void testRandom(){
        System.out.println(RandomUtil.randomString(6));

    }
}