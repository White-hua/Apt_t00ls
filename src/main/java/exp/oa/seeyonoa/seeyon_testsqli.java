package exp.oa.seeyonoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class seeyon_testsqli implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url,textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞暂不支持getshell 请手动利用");
        });
        return false;
    }

    private boolean att(String url , TextArea textArea){
        Response response = HttpTools.get(url + "/yyoa/common/js/menu/test.jsp", new HashMap<String, String>(), "utf-8");
        if (response.getCode() == 200) {
            Platform.runLater(() -> {
                textArea.appendText("\n 漏洞页面存在 请自行查看是否存在注入");
            });
            return true;
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n seeyon_testsqli-RCE-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }



}

