package Utilss;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;

public class shell {
//     我本地测试用例！！！
    public static String Jsppath = "D:\\Apt_config\\shell.jsp";
    public static String Jspxpath = "D:\\Apt_config\\shell.jspx";
    public static String Asppath = "D:\\Apt_config\\shell.asp";
    public static String Phppath = "D:\\Apt_config\\shell.php";
    public static String Aspxpath = "D:\\Apt_config\\shell.aspx";
    public static String Testpath = "D:\\Apt_config\\shell.txt";
    public static String Gsljsppath = "D:\\Apt_config\\gsl.jsp";
    public static String chajetDllpath = "D:\\Apt_config\\chajet\\App_Web_nishizhu.aspx.cdcab7d2.dll";
    public static String chajetCompiledpath = "D:\\Apt_config\\chajet\\nishizhu.aspx.cdcab7d2.compiled";
    public static String tasklistpath = "D:\\Apt_config\\config\\tasklist.txt";

    public static String dnscofpath = "D:\\Apt_config\\dnslog\\dnslogconfig.txt";
    public static String dnspath = "D:\\Apt_config\\dnslog\\dnslog.txt";

//    生产用例
//    public static String Jsppath = "./Apt_config/shell.jsp";
//    public static String Jspxpath = "./Apt_config/shell.jspx";
//    public static String Asppath = "./Apt_config/shell.asp";
//    public static String Phppath = "./Apt_config/shell.php";
//    public static String Aspxpath = "./Apt_config/shell.aspx";
//    public static String Testpath = "./Apt_config/shell.txt";
//    public static String Gsljsppath = "./Apt_config/gsl.jsp";
//    public static String chajetDllpath = "./Apt_config/chajet/App_Web_nishizhu.aspx.cdcab7d2.dll";
//    public static String chajetCompiledpath = "./Apt_config/chajet/nishizhu.aspx.cdcab7d2.compiled";
//    public static String tasklistpath = "./Apt_config/config/tasklist.txt";
//
//    public static String dnscofpath = "./Apt_config/dnslog/dnslogconfig.txt";
//    public static String dnspath = "./Apt_config/dnslog/dnslog.txt";


    //标记内容
    public static String test_payload = "9df37afc77bdd582d90aefaf4e35c63e";

    public static String readFile(String path){

        try {
            FileInputStream in = new FileInputStream(path);
            int size=in.available();
            byte[] buffer=new byte[size];
            in.read(buffer);
            in.close();
            String s = new String(buffer, StandardCharsets.UTF_8);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

/*----------------------------------------------------TASKLIST相关-----------------------------------------------------*/
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String[] readFileByLines(String fileName) {
        String[] resultlist = new String[1000];
        File file = new File(fileName);
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        int i = 0;
        try {
            reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String tempString = "";
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null)
            {
                if (tempString.contains("."))
                {
                    resultlist[i] = tempString;
                    i++;
                }else{
                    try {
                        resultlist[i] = tempString;
                    }catch (ArrayIndexOutOfBoundsException ignored){}
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return resultlist;
    }

    public static String[] taskexechange(String[] resultlist) {
        String[] resultlist2 = new String[1000];
        BufferedReader reader = null;
        int i = 0;
        try {
            for (String tempString : resultlist){
                // 一次读入一行，直到读入null为文件结束
                if ((tempString != null))
                    if (tempString.contains(".")) {
                        resultlist2[i] = tempString;
                        i++;

                    }else{
                        try {
                            resultlist2[i] = tempString;
                            i++;

                        }catch (ArrayIndexOutOfBoundsException ignored){;}
                    }
            }
        }catch (Exception e)
        {}
        return resultlist2;
    }

    public static String ifexe(String[] resultlist2 , Map<String,String> exelist){// resultlist2为输入的
        String total = "",str2 = "";
        for (String str : resultlist2) {
            if (str != null){
                for (Map.Entry<String, String> entry : exelist.entrySet()) {
                    if (str.split("   ")[0].trim().equals(entry.getKey().replace("\"", ""))) {
                        total = total + entry.getKey().replace("\"", "") + "->" + entry.getValue().replace("\"", "").replace(",", "") + '\n';
                    }
                }
                // 查找出远程桌面服务的pid
                if (str.contains("TermService")) {
                    for(int i=0;i<str.length();i++){
                        if(str.charAt(i)>=48 && str.charAt(i)<=57) {
                            str2+=str.charAt(i);
                        }
                    }
                }
            }
        }

        total = total + "\n远程桌面pid为" + str2 + '\n' + "netstat -ano|findstr \"" + str2 +"\"\n查找远程端口";
        return total;
    }

    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /*--生成随机字符串-*/
    public static String getRandomString(){
        int length = 6;
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
