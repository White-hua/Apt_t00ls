package Exp.OA.weaveroa;

import Utilss.HttpTools;
import Utilss.Response;
import Utilss.shell;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;
import java.util.HashMap;

public class weaveroa_page_uploadOperation implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = this.att(url, shell.Testpath, textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return false;
    }

    private Boolean att(String url,String path,TextArea textArea){
        Response response = HttpTools.get(url + "/page/exportImport/uploadOperation.jsp", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && !response.getText().contains("error")){
            textArea.appendText("\n 漏洞疑似存在！！请联系作者补充exp！！ weaveroa_page_uploadOperation");
            return true;
        }else {
            textArea.appendText("\n weaveroa_page_uploadOperation - 漏洞不存在 (出现误报请联系作者)");
            return false;
        }

    }
}
