package Controller;

import Utilss.shell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TsklistController {
    @FXML
    private TextArea textArea_res;

    @FXML
    private TextArea textArea_check;

    @FXML
    private Button button_check;

    @FXML
    void clicked_check(MouseEvent event) {
        String tasklist =textArea_check.getText();
        Map<String,String> exelist = new HashMap<String,String>();
        String[] exetestlist = shell.readFileByLines(shell.tasklistpath);
        for (String str : exetestlist)
            if (str != null) {
                exelist.put(str.split(": ")[0], str.split(":")[1]);
            }
        String[] resultlist2 = tasklist.split("\n"); // 将读取的进程通过换行分割成字符串组
        String[] resultlist22;
        resultlist22 = shell.taskexechange(resultlist2);
        String finallist = shell.ifexe(resultlist22, exelist);
        String res = null;
        try {
            res = new String(finallist.getBytes("gbk"));
            textArea_res.setText(res);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
