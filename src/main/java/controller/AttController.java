package controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import core.Exploitlnterface;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import utils.Kinds_Exp;
import utils.shell;

public class AttController {

  private final Kinds_Exp exp = new Kinds_Exp();//初始化EXP相关数据

  private final ExecutorService service = Executors.newCachedThreadPool();
  private final CompletionService<HashMap<String, Object>> completionService = new ExecutorCompletionService<>(
      service);
  private boolean initialized = false;//是否初始化

  @FXML
  private RadioButton radioButton_getshell;

  @FXML
  private Button Button_Att;

  @FXML
  private TextArea textArea_attInfo; //ATT 结果文本域

  @FXML
  private TextField textField_url;

  @FXML
  private TextArea textArea_info; //中间说明文本域

  @FXML
  private ChoiceBox<String> choiceBox_exp;

  @FXML
  private ListView<String> listview_kinds;

  @FXML
  private ChoiceBox<String> choiceBox_kinds;

  @FXML
  private Button button_jsp;

  @FXML
  private Button button_jspx;

  @FXML
  private Button button_asp;

  @FXML
  private Button button_aspx;

  @FXML
  private Button button_dnslog_token;

  @FXML
  private Button button_dnslog;

  @FXML
  private Button button_php;

  @FXML
  void clicked_jsp(MouseEvent event) {
    Runtime run = Runtime.getRuntime();
    //path:文件路径
    try {
      run.exec(shell.open + shell.Jsppath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void clicked_jspx(MouseEvent event) {
    Runtime run = Runtime.getRuntime();
    //path:文件路径
    try {
      run.exec(shell.open + shell.Jspxpath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void clicked_asp(MouseEvent event) {
    Runtime run = Runtime.getRuntime();
    //path:文件路径
    try {
      run.exec(shell.open + shell.Asppath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void clicked_aspx(MouseEvent event) {
    Runtime run = Runtime.getRuntime();
    //path:文件路径
    try {
      run.exec(shell.open + shell.Aspxpath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void clicked_php(MouseEvent event) {
    Runtime run = Runtime.getRuntime();
    //path:文件路径
    try {
      run.exec(shell.open + shell.Phppath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void clicked_dnslog(MouseEvent event) {
    Runtime run = Runtime.getRuntime();
    //path:文件路径
    try {
      run.exec(shell.open + shell.dnspath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void clicked_dnslog_token(MouseEvent event) {
    Runtime run = Runtime.getRuntime();
    //path:文件路径
    try {
      run.exec(shell.open + shell.dnscofpath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void Att_clicked(MouseEvent event) {         //ATT按钮
    //初始清空
    Platform.runLater(() -> {
      textArea_attInfo.clear();
    });
    //获取url地址
    String url;
    if (StrUtil.isBlank(textField_url.getText())) {
      Platform.runLater(() -> {
        textArea_attInfo.appendText("\n");
        textArea_attInfo.appendText("请填写URL：");
      });
      return;
    }
    if (textField_url.getText().trim().endsWith("/")) {
      url = textField_url.getText().trim()
          .substring(0, textField_url.getText().trim().length() - 1);
    } else {
      url = textField_url.getText().trim();
    }

    //获取需要利用的exp
    String vulname = choiceBox_exp.getValue();
    //获取get shell按钮是否被选中
    Boolean getshell = radioButton_getshell.selectedProperty().get();

    //如果是all
    if (vulname != null && vulname.equals("All")) {
      ObservableList<String> items = choiceBox_exp.getItems();
      CountDownLatch countDownLatch = new CountDownLatch(items.size() - 1);

      long start = System.currentTimeMillis();
      for (int i = 1; i < items.size(); i++) {
        String val = items.get(i);
        service.submit(() -> {
          try {
            String x = "线程：" + Thread.currentThread().getName() + "开始";
            System.out.println(x);
            Exploitlnterface exploit = Kinds_Exp.getExploit(val);
            if (exploit == null) {
              Platform.runLater(() -> {
                textArea_attInfo.appendText("\n");
                textArea_attInfo.appendText("未找到EXP：" + val);
              });
              throw new RuntimeException("未找到EXP");
            }
            if (Objects.isNull(textArea_attInfo)) {
              System.out.println("NPE debugger");
            }

            Boolean aBoolean = exploit.checkVul(url, textArea_attInfo);
            if(aBoolean){
              Platform.runLater(() -> {
                textArea_attInfo.appendText("\n");
                textArea_attInfo.appendText("-------------------" + val + "漏洞存在----------");
                textArea_attInfo.appendText("\n");
              });
            }

          } catch (Exception e) {
            if (e instanceof IndexOutOfBoundsException) {
              System.out.println("数组下标越界异常");
            }
            if (e instanceof NullPointerException) {
              System.out.println("NPE异常");
            }
            e.printStackTrace();
          } finally {
            String threadName = "线程：" + Thread.currentThread().getName();
            // String x = threadName + "结束";
            System.out.println(threadName+" spend:" + (System.currentTimeMillis() - start) + "ms");

            countDownLatch.countDown();
          }
        });
      }

      try {
        countDownLatch.await();
        System.out.println("total spend:" + (System.currentTimeMillis() - start) + "ms");
        Platform.runLater(() -> {
          textArea_attInfo.appendText(
              "\n\n如需获取shell请勾选 getshell并选择具体漏洞 " + DateUtil.now());
        });
      } catch (Exception e) {
        e.printStackTrace();
      }


    } else if (vulname != null) {

      //生成exp对应类对象
      Exploitlnterface exploit = Kinds_Exp.getExploit(vulname);
      //检查是否存在漏洞
      Boolean checkVul = exploit.checkVul(url, textArea_attInfo);
      if (checkVul) {
        if (!getshell) {
          textArea_attInfo.appendText("\n可以进行GetShell, 请选中getshell 单击ATT");
        }
        if (getshell) {
          Boolean shell_success = exploit.getshell(url, textArea_attInfo);
          if (shell_success) {
            textArea_attInfo.appendText("\n--Getshell 成功 若无特别说明则默认使用冰蝎4.0.3 aes--");
          }


        }
      }

    }
  }

  @FXML
  public void initialize() {


    textArea_info.appendText(
        "\n---------------------------(禁止未授权恶意攻击)-------------------------");

    textArea_info.appendText(
            "\n\n 本工具仅供学习研究及合法授权下渗透测试！！！！！\n");

    textArea_info.appendText(
            "\n 本工具webshell写入判断依据为md5 在修改shll内容时请勿删除md5");
    textArea_info.appendText(
            "\n config目录中shell开头文件均为 冰蝎4.0.3 aes生成webshell");
    textArea_info.appendText(
            "\n gsl.jsp为哥斯拉4.01 jsp aes 默认密码密钥 ");
    textArea_info.appendText(
            "\n chajet目录下为畅捷通编译好shell文件");
    textArea_info.appendText(
            "\n dnslog文件夹下为部分漏洞所需dnslog回显测试所用，请自行修改dnslog文件");

    textArea_info.appendText(
            "\n\n---------------------------(禁止未授权恶意攻击)-------------------------");

    //设置自动换行
    textArea_info.setWrapText(true);
    textArea_attInfo.setWrapText(true);

    //适配屏幕
    System.setProperty("prism.allowhidpi", "true");

    //渲染Tab1 左上 下拉选
    //设置初始化数据
    choiceBox_kinds.setItems(exp.getFXKindList());
    //设置默认选项
    choiceBox_kinds.setValue(exp.getKindList().get(0));
    //选项绑定监听器，当左上 下拉选 数据发生改变，更新列表数据，同时更新exp下拉选数据
    choiceBox_kinds.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> buildChoiceBoxListener(newValue));

    // 第一次渲染该页面时渲染数据
    if (!initialized) {
      //更新列表数据
      buildChoiceBoxListener(exp.getKindList().get(0));
      initialized = true;
    }

  }

  private void buildChoiceBoxListener(String newValue) {
    switch (newValue) {
      case "OA":
        listview_kinds.setItems(Kinds_Exp.oa());
        break;
      case "中间件":
        listview_kinds.setItems(Kinds_Exp.middleware());
        break;
      case "安全设备":
        listview_kinds.setItems(Kinds_Exp.equipment());
        break;
      case "CMS":
        listview_kinds.setItems(Kinds_Exp.cms());
        break;
    }
    updateListView(listview_kinds.getItems().get(0));
  }

  /**
   * 列表监听事件，监听所选的类型更新exp下拉选数据.
   */
  @FXML
  void listview_clicked() {
    String selectedItem = listview_kinds.getSelectionModel().getSelectedItem();
    updateListView(selectedItem);
  }

  /**
   * 根据列表所选类型给中间EXP下拉选赋值
   *
   * @param selectedItem 左边列表选的类型
   */
  private void updateListView(String selectedItem) {
    switch (selectedItem) {
      case "泛微-OA":
        choiceBox_exp.setItems(exp.buildWeaverOAList());
        break;
      case "蓝凌-OA":
        choiceBox_exp.setItems(exp.landrayoa());
        break;
      case "用友-OA":
        choiceBox_exp.setItems(exp.yongyouoa());
        break;
      case "万户-OA":
        choiceBox_exp.setItems(exp.wanhuoa());
        break;
      case "致远-OA":
        choiceBox_exp.setItems(exp.zhiyuanoa());
        break;
      case "通达-OA":
        choiceBox_exp.setItems(exp.tongdaoa());
        break;
      case "帆软-OA":
        choiceBox_exp.setItems(exp.fanruan());
        break;

      case "IIS":
        choiceBox_exp.setItems(exp.iis());
        break;



      case "海康":
        choiceBox_exp.setItems(exp.hik());
        break;
      case "H3C":
        choiceBox_exp.setItems(exp.h3c());
        break;
      case "奇安信":
        choiceBox_exp.setItems(exp.qianxin());
        break;
      case "网御星云":
        choiceBox_exp.setItems(exp.wangyu());
        break;



      case "Alibaba":
        choiceBox_exp.setItems(exp.Alibaba());
        break;



      default:
        System.out.println(selectedItem);
        // 当所选项还没有exp给默认选项
        choiceBox_exp.setItems(exp.defaultList());
    }
    // 设置下拉选默认exp
    choiceBox_exp.setValue(exp.getExpList().get(1));
  }
}
