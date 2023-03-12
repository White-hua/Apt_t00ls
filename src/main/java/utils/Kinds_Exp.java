package utils;

import core.Exploitlnterface;
import exp.cms.nacos_Creatuser;
import exp.equipment.h3c.cas_cvm_upload;
import exp.equipment.hikvision.hik_applyCT_fastjson;
import exp.equipment.qianxin.ngfw_waf_router;
import exp.equipment.wangyu.Leadsec_ACM_account;
import exp.middleware.iis.iis_put_rce;
import exp.oa.fanruan.fanruan_save_svg;
import exp.oa.landrayoa.landray_datajson;
import exp.oa.landrayoa.landray_fileupload_sysSearch;
import exp.oa.landrayoa.landray_sysSearchMain;
import exp.oa.landrayoa.landray_treexmlTmpl;
import exp.oa.seeyonoa.*;
import exp.oa.tongdaoa.tongdaoa_apiali;
import exp.oa.tongdaoa.tongdaoa_getdata;
import exp.oa.wanhuoa.wanhu_DocumentEdit;
import exp.oa.wanhuoa.wanhuoa_OfficeServer;
import exp.oa.wanhuoa.wanhuoa_Officeserverservlet;
import exp.oa.wanhuoa.wanhuoa_fileUploadController;
import exp.oa.wanhuoa.wanhuoa_smartUpload;
import exp.oa.weaveroa.*;
import exp.oa.yongyou.*;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Kinds_Exp {


  private ArrayList<String> expList;
  private ArrayList<String> kindList;

  /**
   * 构造器初始化数据
   */
  public Kinds_Exp() {
    this.kinds();
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
    kindList.add("CMS");
    return kindList;
  }


  public static ObservableList<String> oa() {
    ArrayList<String> oa = new ArrayList<>();
    oa.add("泛微-OA");
    oa.add("蓝凌-OA");
    oa.add("用友-OA");
    oa.add("万户-OA");
    oa.add("致远-OA");
    oa.add("通达-OA");
    oa.add("帆软-OA");
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
    equipment.add("H3C");
    equipment.add("深信服");
    equipment.add("网御星云");
    equipment.add("奇安信");
    return FXCollections.observableArrayList(equipment);
  }

  public static ObservableList<String> cms() {
    ArrayList<String> equipment = new ArrayList<>();
    equipment.add("Alibaba");
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
    expList.add("e-cology KreeUploadAction-RCE");
    expList.add("e-office logo_UploadFile.php-RCE");
    expList.add("e-office8 upload.php-RCE");
    expList.add("e-office10 OfficeServer.php-RCE");
    expList.add("e-office doexcel.php-RCE");
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
    expList.add("landray_fileupload_sysSearch-RCE");
    return FXCollections.observableArrayList(expList);
  }

  public ObservableList<String> fanruan(){
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("fanruan-design_save_svg-RCE");
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
    expList.add("NC_UploadServlet-RCE");
    expList.add("GRP_U8_UploadFileData-RCE");
    expList.add("GRP_U8_AppProxy-RCE");
    expList.add("KSOA_ImageUpload-RCE");
    expList.add("KSOA_Attachmentupload-RCE");
    return FXCollections.observableArrayList(expList);
  }

  //万户oa
  public ObservableList<String> wanhuoa() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("wanhu_OfficeServer-RCE");
    expList.add("wanhu_smartUpload-RCE");
    expList.add("wanhu_fileUploadController-RCE");
    expList.add("wanhu_DocumentEdit-SQLli");
    expList.add("wanhu_Officeserverservlet-RCE");
    return FXCollections.observableArrayList(expList);
  }

  //致远oa
  public ObservableList<String> zhiyuanoa() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("seeyonoa_main_log4j2-RCE");
    expList.add("seeyonoa_seeyonreport_upload-RCE");
    expList.add("seeyonoa_wpsAssisServlet-RCE");
    expList.add("seeyonoa_htmlofficeservlet-RCE");
    expList.add("seeyonoa_ajaxBypass-RCE");
    expList.add("seeyon_testsqli-RCE");
    return FXCollections.observableArrayList(expList);
  }

  //通达oa
  public ObservableList<String> tongdaoa() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("tongdaoa_getdata-RCE");
    expList.add("tongdaoa_apiali-RCE");
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

  public ObservableList<String> h3c() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("cas_cvm云计算管理平台-RCE");
    return FXCollections.observableArrayList(expList);
  }

  //奇安信
  public ObservableList<String> qianxin() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("网康防火墙_NGFW_waf_router-RCE");
    return FXCollections.observableArrayList(expList);
  }

  public ObservableList<String> wangyu() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("上网行为管理账号密码泄露_Leadsec_ACM");
    return FXCollections.observableArrayList(expList);
  }

  /*---------------------CMS-------------------------*/

  public ObservableList<String> Alibaba() {
    expList = new ArrayList<>();
    expList.add("All");
    expList.add("nacos任意用户添加");
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
    }else if(vulName.contains("e-cology KreeUploadAction-RCE")){
      ei = new weaveroa_KtreeUploadAction();
    }else if(vulName.contains("e-office doexcel.php-RCE")){
      ei = new weaveroa_doExecl();
    }
    else if(vulName.contains("e-office8 upload.php-RCE")){
      ei = new weaveroa_eoffice8_upload();
    }


    else if (vulName.contains("fanruan-design_save_svg-RCE")) {
      //帆软
      ei = new fanruan_save_svg();
    }

    else if (vulName.contains("chajet_upload-RCE")) {
      //用友
      ei = new yongyou_chajet_upload();
    } else if (vulName.contains("NC_bsh.servlet.BshServlet-RCE")) {
      ei = new yongyou_nc_BshServlet();
    } else if (vulName.contains("NC_NCFindWeb")) {
      ei = new yongyou_nc_NCFindWeb();
    } else if (vulName.contains("NC_FileReceiveServlet-RCE")) {
      ei = new yongyou_nc_FileReceiveServlet();
    }else if(vulName.contains("GRP_U8_UploadFileData-RCE")){
      ei = new yongyou_grp_UploadFileData();
    }else if(vulName.contains("KSOA_ImageUpload-RCE")){
      ei = new yongyou_KSOA_imageupload();
    }else if(vulName.contains("NC_UploadServlet-RCE")){
      ei = new yongyou_nc_uploadServlet();
    } else if (vulName.contains("GRP_U8_AppProxy-RCE")) {
      ei = new yongyou_U8_AppProxy();
    } else if (vulName.contains("KSOA_Attachmentupload-RCE")) {
      ei = new yongyou_KSOA_Attachmentupload();

    } else if (vulName.contains("landray_sysSearchMain.do-RCE")) {
      //蓝凌
      ei = new landray_sysSearchMain();
    } else if (vulName.contains("landray_treexmlTmpl-RCE")) {
      ei = new landray_treexmlTmpl();
    } else if (vulName.contains("landray_datajson-RCE")) {
      ei = new landray_datajson();
    } else if (vulName.contains("landray_fileupload_sysSearch-RCE")) {
      ei = new landray_fileupload_sysSearch();
    }

    else if(vulName.contains("wanhu_OfficeServer-RCE")){
      //万户
      ei = new wanhuoa_OfficeServer();
    }else if(vulName.contains("wanhu_smartUpload-RCE")){
      ei = new wanhuoa_smartUpload();
    }else if(vulName.contains("wanhu_fileUploadController-RCE")){
      ei = new wanhuoa_fileUploadController();
    }else if(vulName.contains("wanhu_DocumentEdit-SQLli")){
      ei = new wanhu_DocumentEdit();
    }else if(vulName.contains("wanhu_Officeserverservlet-RCE")){
      ei = new wanhuoa_Officeserverservlet();
    }

    else if(vulName.contains("seeyonoa_main_log4j2-RCE")){
      //致远oa
      ei = new seeyonoa_main_log4j2();
    }else if(vulName.contains("seeyonoa_wpsAssisServlet-RCE")){
      ei = new seeyonoa_wpsAssistServlet();
    }else if(vulName.contains("seeyonoa_htmlofficeservlet-RCE")){
      ei = new seeyonoa_htmlofficeservlet();
    }else if(vulName.contains("seeyonoa_ajaxBypass-RCE")){
      ei = new seeyonoa_ajaxBypass();
    }else if(vulName.contains("seeyonoa_seeyonreport_upload-RCE")){
      ei = new seeyonreport_svg_upload();
    }else if (vulName.contains("seeyon_testsqli-RCE")) {
      ei = new seeyon_testsqli();
    }

    else if(vulName.contains("tongdaoa_getdata-RCE")){
      //通达oa
      ei = new tongdaoa_getdata();
    }else if(vulName.contains("tongdaoa_apiali-RCE")){
      ei = new tongdaoa_apiali();
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
    else if(vulName.contains("上网行为管理账号密码泄露_Leadsec_ACM")){
      //网御星云
      ei = new Leadsec_ACM_account();
    } else if (vulName.contains("cas_cvm云计算管理平台-RCE")) {
      //h3c
      ei = new cas_cvm_upload();
    }

    /*-----CMS-----*/
    else if (vulName.contains("nacos任意用户添加")) {
      ei = new nacos_Creatuser();
    }

    return ei;
  }
}
