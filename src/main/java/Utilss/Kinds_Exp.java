package Utilss;

import Exp.OA.weaveroa.weaveroa_office_UploadFile;
import Exp.OA.weaveroa.weaveroa_page_uploadOperation;
import Exp.OA.weaveroa.weaveroa_workrelate_uploadOperation;
import Exp.OA.yongyou.yongyou_chajet_upload;
import Exp.middleware.IIS.iis_put_rce;
import core.Exploitlnterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Kinds_Exp {

    public static ObservableList<String> kinds(){
        ArrayList<String> kinds = new ArrayList<>();
        kinds.add("OA");
        kinds.add("安全设备");
        kinds.add("中间件");
        ObservableList<String> observableList = FXCollections.observableArrayList(kinds);
        return observableList;
    }


    public static ObservableList<String> oa(){
        ArrayList<String> oa = new ArrayList<>();
        oa.add("泛微-OA");
        oa.add("蓝凌-OA");
        oa.add("用友-OA");
        ObservableList<String> observableList = FXCollections.observableArrayList(oa);
        return observableList;
    }

    public static ObservableList<String> middleware(){
        ArrayList<String> middleware = new ArrayList<>();
        middleware.add("IIS");
        middleware.add("Tomcat");
        middleware.add("Weblogic");
        ObservableList<String> observableList = FXCollections.observableArrayList(middleware);
        return observableList;
    }

    /*---------------------OA系列-------------------------*/

    //泛微oa
    public static ObservableList<String> weaveroa(){
        ArrayList<String> weaveroa = new ArrayList<>();
        weaveroa.add("All");
        weaveroa.add("e-cology workrelate_uploadOperation.jsp-RCE");
        weaveroa.add("e-cology page_uploadOperation.jsp-RCE");
        weaveroa.add("e-office logo_UploadFile.php-RCE");
        ObservableList<String> observableList = FXCollections.observableArrayList(weaveroa);
        return observableList;
    }

    //蓝凌oa
    public static ObservableList<String> landrayoa(){
        ArrayList<String> landrayoa = new ArrayList<>();
        landrayoa.add("All");
        ObservableList<String> observableList = FXCollections.observableArrayList(landrayoa);
        return observableList;
    }

    //用友oa
    public static ObservableList<String> yongyouoa(){
        ArrayList<String> yongyouoa = new ArrayList<>();
        yongyouoa.add("All");
        yongyouoa.add("chajet_upload");
        ObservableList<String> observableList = FXCollections.observableArrayList(yongyouoa);
        return observableList;
    }

    /*---------------------中间件系列-------------------------*/

    //IIS
    public static ObservableList<String> iis(){
        ArrayList<String> iis = new ArrayList<>();
        iis.add("All");
        iis.add("iis_put_rce");
        ObservableList<String> observableList = FXCollections.observableArrayList(iis);
        return observableList;
    }


    //根据选择的Exp返回对应的对象
    public static Exploitlnterface getExploit(String vulName){
        Exploitlnterface ei = null;
        if(vulName.contains("workrelate_uploadOperation")){
            ei = new weaveroa_workrelate_uploadOperation();
        }else if(vulName.contains("page_uploadOperation")){
            ei = new weaveroa_page_uploadOperation();
        }else if(vulName.contains("logo_UploadFile.php")){
            ei = new weaveroa_office_UploadFile();
        }

        else if(vulName.contains("chajet_upload")){
            ei = new yongyou_chajet_upload();
        }


        /*-----中间件-----*/
        else if(vulName.contains("iis_put_rce")){
            ei = new iis_put_rce();
        }

        return ei;
    }
}
