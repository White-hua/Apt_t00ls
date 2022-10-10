import Utilss.Kinds_Exp;
import Utilss.shell;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import core.Exploitlnterface;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
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
}