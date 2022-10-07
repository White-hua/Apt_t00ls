package Utilss;

import Exp.OA.landrayoa.landray_datajson;
import Exp.OA.landrayoa.landray_sysSearchMain;
import Exp.OA.landrayoa.landray_treexmlTmpl;
import Exp.OA.weaveroa.*;
import Exp.OA.yongyou.yongyou_chajet_upload;
import Exp.OA.yongyou.yongyou_nc_BshServlet;
import Exp.OA.yongyou.yongyou_nc_FileReceiveServlet;
import Exp.OA.yongyou.yongyou_nc_NCFindWeb;
import Exp.equipment.HIKVISION.hik_applyCT_fastjson;
import Exp.equipment.qianxin.ngfw_waf_router;
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

    public static ObservableList<String> equipment(){
        ArrayList<String> equipment = new ArrayList<>();
        equipment.add("海康");
        equipment.add("深信服");
        equipment.add("奇安信");
        ObservableList<String> observableList = FXCollections.observableArrayList(equipment);
        return observableList;
    }

    /*---------------------OA系列-------------------------*/

    //泛微oa
    public static ObservableList<String> weaveroa(){
        ArrayList<String> weaveroa = new ArrayList<>();
        weaveroa.add("All");
        weaveroa.add("e-cology workrelate_uploadOperation.jsp-RCE");
        weaveroa.add("e-cology page_uploadOperation.jsp-RCE");
        weaveroa.add("e-cology WorkflowServiceXml-RCE");
        weaveroa.add("e-cology BshServlet-RCE");
        weaveroa.add("e-office logo_UploadFile.php-RCE");
        weaveroa.add("e-office10 OfficeServer.php-RCE");
        weaveroa.add("e-mobile_6.6 messageType.do-SQlli");
        ObservableList<String> observableList = FXCollections.observableArrayList(weaveroa);
        return observableList;
    }

    //蓝凌oa
    public static ObservableList<String> landrayoa(){
        ArrayList<String> landrayoa = new ArrayList<>();
        landrayoa.add("All");
        landrayoa.add("landray_sysSearchMain.do-RCE");
        landrayoa.add("landray_treexmlTmpl-RCE");
        landrayoa.add("landray_datajson-RCE");
        ObservableList<String> observableList = FXCollections.observableArrayList(landrayoa);
        return observableList;
    }

    //用友oa
    public static ObservableList<String> yongyouoa(){
        ArrayList<String> yongyouoa = new ArrayList<>();
        yongyouoa.add("All");
        yongyouoa.add("chajet_upload-RCE");
        yongyouoa.add("NC_bsh.servlet.BshServlet-RCE");
        yongyouoa.add("NC_NCFindWeb-Directory");
        yongyouoa.add("NC_FileReceiveServlet-RCE");
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


    /*---------------------安全设备-------------------------*/

    //海康
    public static ObservableList<String> hik(){
        ArrayList<String> hik = new ArrayList<>();
        hik.add("All");
        hik.add("综合安防_applyCT_fastjson-RCE");
        ObservableList<String> observableList = FXCollections.observableArrayList(hik);
        return observableList;
    }

    public static ObservableList<String> qianxin(){
        ArrayList<String> qianxin = new ArrayList<>();
        qianxin.add("All");
        qianxin.add("网康防火墙_NGFW_waf_router-RCE");
        ObservableList<String> observableList = FXCollections.observableArrayList(qianxin);
        return observableList;
    }


    //根据选择的Exp返回对应的对象
    public static Exploitlnterface getExploit(String vulName){
        Exploitlnterface ei = null;
        /*-----OA-----*/
        if(vulName.contains("workrelate_uploadOperation")){
            //泛微
            ei = new weaveroa_workrelate_uploadOperation();
        }else if(vulName.contains("page_uploadOperation")){
            ei = new weaveroa_page_uploadOperation();
        }else if(vulName.contains("logo_UploadFile.php")){
            ei = new weaveroa_office_UploadFile();
        }else if(vulName.contains("e-cology BshServlet-RCE")){
            ei = new weaveroa_BshServlet();
        }else if(vulName.contains("e-cology WorkflowServiceXml-RCE")){
            ei = new weaveroa_WorkflowServiceXml();
        }else if(vulName.contains("e-mobile_6.6 messageType.do-SQlli")){
            ei = new weaveroa_mobile6_sqlli();
        }else if(vulName.contains("e-office10 OfficeServer.php-RCE")){
            ei = new weaveroa_eoffice10_OfficeServer();
        }

        else if(vulName.contains("chajet_upload-RCE")){
            //用友
            ei = new yongyou_chajet_upload();
        }else if(vulName.contains("NC_bsh.servlet.BshServlet-RCE")){
            ei = new yongyou_nc_BshServlet();
        }else if(vulName.contains("NC_NCFindWeb")){
            ei = new yongyou_nc_NCFindWeb();
        }else if(vulName.contains("NC_FileReceiveServlet-RCE")){
            ei = new yongyou_nc_FileReceiveServlet();
        }

        else if(vulName.contains("landray_sysSearchMain.do-RCE")){
            //蓝凌
            ei = new landray_sysSearchMain();
        }else if(vulName.contains("landray_treexmlTmpl-RCE")){
            ei = new landray_treexmlTmpl();
        }else if(vulName.contains("landray_datajson-RCE")){
            ei = new landray_datajson();
        }


        /*-----中间件-----*/
        else if(vulName.contains("iis_put_rce")){
            //IIS
            ei = new iis_put_rce();
        }

        /*-----安全设备-----*/
        else if(vulName.contains("applyCT_fastjson-RCE")){
            //海康
            ei = new hik_applyCT_fastjson();
        }

        else if(vulName.contains("NGFW_waf_router-RCE")){
            //奇安信网康
            ei = new ngfw_waf_router();
        }



        return ei;
    }
}
