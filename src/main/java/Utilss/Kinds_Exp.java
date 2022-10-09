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


  private ArrayList<String> expList;
  private ArrayList<String> kindList;

  /**
   * 构造器初始化数据
   */
  public Kinds_Exp() {
    this.kinds();
  }


  public ObservableList<String> getFXExpList() {
    return FXCollections.observableArrayList(expList);
  }

  public ArrayList<String> getKindList() {
    return kindList;
  }

  public ObservableList<String> getFXKindList() {
    return FXCollections.observableArrayList(kindList);
  }

  public ArrayList<String> getExpList() {
    return expList;
  }

  /**
   * 初始化默认数据.
   *
   * @return 选项集合.
   */
  public ArrayList<String> kinds() {
    kindList = new ArrayList<>();
    kindList.add("OA");
    kindList.add("安全设备");
    kindList.add("中间件");
    return kindList;
  }


  public static ObservableList<String> oa() {
    ArrayList<String> oa = new ArrayList<>();
    oa.add("泛微-OA");
    oa.add("蓝凌-OA");
    oa.add("用友-OA");
    return FXCollections.observableArrayList(oa);
  }

  public static ObservableList<String> middleware() {
    ArrayList<String> middleware = new ArrayList<>();
    middleware.add("IIS");
    middleware.add("Tomcat");
    middleware.add("Weblogic");
    return FXCollections.observableArrayList(middleware);
  }

  public static ObservableList<String> equipment() {
    ArrayList<String> equipment = new ArrayList<>();
    equipment.add("海康");
    equipment.add("深信服");
    equipment.add("奇安信");
    return FXCollections.observableArrayList(equipment);
  }

  /*---------------------OA系列-------------------------*/

  //泛微oa
  public ObservableList<String> buildWeaverOAList() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("e-cology workrelate_uploadOperation.jsp-RCE");
    expList.add("e-cology page_uploadOperation.jsp-RCE");
    expList.add("e-cology WorkflowServiceXml-RCE");
    expList.add("e-cology BshServlet-RCE");
    expList.add("e-office logo_UploadFile.php-RCE");
    expList.add("e-office10 OfficeServer.php-RCE");
    expList.add("e-mobile_6.6 messageType.do-SQlli");
    return FXCollections.observableArrayList(expList);
  }

  //蓝凌oa
  public ObservableList<String> landrayoa() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("landray_sysSearchMain.do-RCE");
    expList.add("landray_treexmlTmpl-RCE");
    expList.add("landray_datajson-RCE");
    return FXCollections.observableArrayList(expList);
  }

  //用友oa
  public ObservableList<String> yongyouoa() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("chajet_upload-RCE");
    expList.add("NC_bsh.servlet.BshServlet-RCE");
    expList.add("NC_NCFindWeb-Directory");
    expList.add("NC_FileReceiveServlet-RCE");
    return FXCollections.observableArrayList(expList);
  }

  /*---------------------中间件系列-------------------------*/

  //IIS
  public ObservableList<String> iis() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("iis_put_rce");
    return FXCollections.observableArrayList(expList);
  }


  /*---------------------安全设备-------------------------*/

  //海康
  public ObservableList<String> hik() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("综合安防_applyCT_fastjson-RCE");
    return FXCollections.observableArrayList(expList);
  }

  public ObservableList<String> qianxin() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("网康防火墙_NGFW_waf_router-RCE");
    return FXCollections.observableArrayList(expList);
  }

  public ObservableList<String> defaultList() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("待添加");
    return FXCollections.observableArrayList(expList);
  }

  //根据选择的Exp返回对应的对象
  public static Exploitlnterface getExploit(String vulName) {
    Exploitlnterface ei = null;
    /*-----OA-----*/
    if (vulName.contains("workrelate_uploadOperation")) {
      //泛微
      ei = new weaveroa_workrelate_uploadOperation();
    } else if (vulName.contains("page_uploadOperation")) {
      ei = new weaveroa_page_uploadOperation();
    } else if (vulName.contains("logo_UploadFile.php")) {
      ei = new weaveroa_office_UploadFile();
    } else if (vulName.contains("e-cology BshServlet-RCE")) {
      ei = new weaveroa_BshServlet();
    } else if (vulName.contains("e-cology WorkflowServiceXml-RCE")) {
      ei = new weaveroa_WorkflowServiceXml();
    } else if (vulName.contains("e-mobile_6.6 messageType.do-SQlli")) {
      ei = new weaveroa_mobile6_sqlli();
    } else if (vulName.contains("e-office10 OfficeServer.php-RCE")) {
      ei = new weaveroa_eoffice10_OfficeServer();
    } else if (vulName.contains("chajet_upload-RCE")) {
      //用友
      ei = new yongyou_chajet_upload();
    } else if (vulName.contains("NC_bsh.servlet.BshServlet-RCE")) {
      ei = new yongyou_nc_BshServlet();
    } else if (vulName.contains("NC_NCFindWeb")) {
      ei = new yongyou_nc_NCFindWeb();
    } else if (vulName.contains("NC_FileReceiveServlet-RCE")) {
      ei = new yongyou_nc_FileReceiveServlet();
    } else if (vulName.contains("landray_sysSearchMain.do-RCE")) {
      //蓝凌
      ei = new landray_sysSearchMain();
    } else if (vulName.contains("landray_treexmlTmpl-RCE")) {
      ei = new landray_treexmlTmpl();
    } else if (vulName.contains("landray_datajson-RCE")) {
      ei = new landray_datajson();
    }


    /*-----中间件-----*/
    else if (vulName.contains("iis_put_rce")) {
      //IIS
      ei = new iis_put_rce();
    }

    /*-----安全设备-----*/
    else if (vulName.contains("applyCT_fastjson-RCE")) {
      //海康
      ei = new hik_applyCT_fastjson();
    } else if (vulName.contains("NGFW_waf_router-RCE")) {
      //奇安信网康
      ei = new ngfw_waf_router();
    }

    return ei;
  }
}
