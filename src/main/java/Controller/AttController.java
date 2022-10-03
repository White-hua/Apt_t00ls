package Controller;

import Utilss.Kinds_Exp;
import core.Exploitlnterface;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class AttController {
    @FXML
    private RadioButton radioButton_getshell;

    @FXML
    private Button Button_Att;

    @FXML
    private TextArea textArea_attInfo;

    @FXML
    private TextField textField_url;

    @FXML
    private TextArea textArea_info;

    @FXML
    private ChoiceBox<String> choiceBox_exp;

    @FXML
    private ListView<String> listview_kinds;

    @FXML
    private ChoiceBox<String> choiceBox_kinds;

    @FXML
    void Att_clicked(MouseEvent event){         //ATT按钮
        //初始清空
        textArea_attInfo.setText("");
        //获取url地址
        String url;
        if(textField_url.getText().trim().endsWith("/")){
            url = textField_url.getText().trim().substring(0,textField_url.getText().trim().length() - 1);
        }else {
            url =textField_url.getText().trim();
        }
        //获取需要利用的exp
        String vulname = choiceBox_exp.getValue();
        //获取getshell按钮是否被选中
        Boolean getshell = radioButton_getshell.selectedProperty().get();

        //如果是all
        if(vulname != null && vulname.equals("All")){

            for(String val : choiceBox_exp.getItems()){
                if (!val.equals("All")) {
                    Exploitlnterface exploit = Kinds_Exp.getExploit(val);
                    Boolean aBoolean = exploit.checkVul(url, textArea_attInfo);
                    if(aBoolean){
                        textArea_attInfo.appendText("\n----" + val +"漏洞存在----\n");
                    }
                }
            }
            textArea_attInfo.appendText("\n 获取shell请单选 不支持批量获取shell");

        }else if(vulname != null){

            //生成exp对应类对象
            Exploitlnterface exploit = Kinds_Exp.getExploit(vulname);
            //检查是否存在漏洞
            Boolean checkVul = exploit.checkVul(url,textArea_attInfo);
            if(checkVul){
                if(!getshell) {
                    textArea_attInfo.appendText("\n可以进行GetShell, 请选中getshell 单击ATT");
                }
                if(getshell){
                    Boolean shell_success = exploit.getshell(url, textArea_attInfo);
                    if(shell_success) {
                        textArea_attInfo.appendText("\n--Getshell 成功 若无特别说明则默认使用冰蝎4.0.3 aes--");
                    }


                }
            }

        }
    }

    @FXML
    public void initialize(){
        textArea_info.setText("------------目前EXP如下-------------");
        textArea_info.appendText("\n\nOA类------------>>>>>");
        textArea_info.appendText("\ne-cology workrelate_uploadOperation.jsp-RCE (默认写入冰蝎4.0.3aes)");
        textArea_info.appendText("\ne-cology page_uploadOperation.jsp-RCE (暂未找到案例 仅供检测poc)");
        textArea_info.appendText("\ne-cology WorkflowServiceXml-RCE (shell详情见回显)");
        textArea_info.appendText("\ne-cology BshServlet-RCE (可直接执行系统命令)");
        textArea_info.appendText("\ne-office logo_UploadFile.php-RCE (默认写入冰蝎4.0.3aes)");

        textArea_info.appendText("\n\nyongyou_chajet_rce (用友畅捷通T+ rce 默认写入哥斯拉 Cshap/Cshap_aes_base64)");

        textArea_info.appendText("\n\n中间件------------>>>>>");
        textArea_info.appendText("\nIIS_PUT_RCE (emm暂时没办法getshell  仅支持检测 java没有MOVE方法)");

        textArea_info.appendText("\n\n安全设备------------>>>>>");
        textArea_info.appendText("\n综合安防_applyCT_fastjson-RCE(仅支持检测,自行使用ladp服务利用)");

        textArea_info.appendText("\n\n------------------(禁止未授权恶意攻击)---------------");

        textArea_info.appendText("\n\n---------小提醒，工具所用shell为冰蝎默认aes加密生成shell" +
                "\n 若工具提示shell写入成功 但访问不存在或连接不上 请考虑免杀，修改shell位置在工具目录下Apt_config" +
                "\n 工具判断shell是否写入依据md5 可自行打开查看 修改shell请保留md5 否则会影响漏洞判断");

        //设置自动换行
        textArea_info.setWrapText(true);
        textArea_attInfo.setWrapText(true);

        //返回kinds 下拉框内容
        choiceBox_kinds.setItems(Kinds_Exp.kinds());

        //监听下拉框返回对应内容至listview
        choiceBox_kinds.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue.equals("OA")) {
                        listview_kinds.setItems(Kinds_Exp.oa());

                    }else if(newValue.equals("中间件")){
                        listview_kinds.setItems(Kinds_Exp.middleware());

                    }else if(newValue.equals("安全设备")){
                        listview_kinds.setItems(Kinds_Exp.equipment());
                    }
        });
    }

    @FXML
    void listview_clicked(MouseEvent mouseEvent){
        if(listview_kinds.getSelectionModel().getSelectedItem().equals("泛微-OA")) {
            choiceBox_exp.setItems(Kinds_Exp.weaveroa());

        }else if(listview_kinds.getSelectionModel().getSelectedItem().equals("蓝凌-OA")){
            choiceBox_exp.setItems(Kinds_Exp.landrayoa());

        }else if(listview_kinds.getSelectionModel().getSelectedItem().equals("用友-OA")){
            choiceBox_exp.setItems(Kinds_Exp.yongyouoa());
        }

        else if(listview_kinds.getSelectionModel().getSelectedItem().equals("IIS")){
            choiceBox_exp.setItems(Kinds_Exp.iis());
        }

        else if(listview_kinds.getSelectionModel().getSelectedItem().equals("海康")){
            choiceBox_exp.setItems(Kinds_Exp.hik());
        }
    }
}
