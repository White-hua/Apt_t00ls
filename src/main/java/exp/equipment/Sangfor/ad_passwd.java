package exp.equipment.Sangfor;

import cn.hutool.http.HttpRequest;
import core.Exploitlnterface;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Response;

import java.util.HashMap;

public class ad_passwd implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea) {
        return null;
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        return null;
    }

    private Boolean att(String url, TextArea textArea){
        Response response = HttpTools.get(url, new HashMap<String, String>(), "utf-8");
        return false;
    }
}

