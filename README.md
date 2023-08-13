# Apt_t00ls

高危漏洞利用工具

---  

## 贡献者名单

<div><table frame=void>
	<tr>
        <td align="center">
            <img src="./image/I0veD.jpg"
                  alt="Typora-Logo"
                  height="80"/>
            <br>
            <a href="https://github.com/cdxiaodong"><sub>I0veD</sub></a>
        </td>  
        <td align="center">
            <img src="./image/luckyh.jpg"
                  alt="Typora-Logo"
                  height="80"/>
            <br>
            <a href="https://github.com/stop-bullshit"><sub>luckyh</sub></a>
        </td>
    </tr>
</table></div>

---

泛微:  
e-cology workrelate_uploadOperation.jsp-RCE (默认写入冰蝎4.0.3aes)  
e-cology page_uploadOperation.jsp-RCE (暂未找到案例 仅供检测poc)  
e-cology BshServlet-RCE (可直接执行系统命令)  
e-cology KtreeUploadAction-RCE (默认写入冰蝎4.0.3aes)  
e-cology WorkflowServiceXml-RCE (默认写入内存马 冰蝎 3.0 beta11)  
e-office logo_UploadFile.php-RCE (默认写入冰蝎4.0.3aes)  
e-office10 OfficeServer.php-RCE (默认写入冰蝎4.0.3aes)  
e-office8 fileupload-RCE (默认写入冰蝎4.0.3aes)  
e-office doexecl.php-RCE (写入phpinfo,需要getshell请自行利用)  
e-mobile_6.0 sqlli-RCE (可直接执行系统命令)  
e-mobile_6.6 messageType.do-SQlli (sqlmap利用，暂无直接shell的exp)

蓝凌：  
landray_datajson-RCE (可直接执行系统命令)  
landray_treexmlTmpl-RCE (可直接执行系统命令)  
landray_sysSearchMain-RCE (多个payload，写入哥斯拉 3.03 密码 yes)  
landrayoa_fileupload_sysSearch-RCE (默认写入冰蝎4.0.3aes)

用友:  
yongyou_chajet_RCE (用友畅捷通T+ rce 默认写入哥斯拉 Cshap/Cshap_aes_base64)  
yongyou_chajet_反序列化RCE(可直接执行系统命令)  
yongyou_NC_FileReceiveServlet-RCE 反序列化rce (默认写入冰蝎4.0.3aes)  
yongyou_NC_bsh.servlet.BshServlet_RCE (可直接执行系统命令)  
yongyou_NC_jsInovke任意文件上传 (默认写入冰蝎4.0.3aes)  
yongyou_NC_NCFindWeb 目录遍历漏洞 (可查看是否存在历史遗留webshell)  
yongyou_GRP_UploadFileData-RCE(默认写入冰蝎4.0.3aes)  
yongyou_GRP_AppProxy-RCE(默认写入冰蝎4.0.3aes)  
yongyou_KSOA_imageUpload-RCE (默认写入冰蝎4.0.3aes)  
yongyou_KSOA_Attachmentupload-RCE (默认写入冰蝎4.0.3aes)

万户：  
wanhuoa_OfficeServer-RCE(默认写入冰蝎4.0.3aes)    
wanhuoa_OfficeServer-RCE(默认写入哥斯拉4.0.1 jsp aes 默认密码密钥)  
wanhuoa_DocumentEdit-SQlli(mssql数据库 可 os-shell)  
wanhuoa_OfficeServerservlet-RCE(默认写入冰蝎4.0.3aes)  
wanhuoa_fileUploadController-RCE(默认写入冰蝎4.0.3aes)

致远：  
seeyonoa_main_log4j2-RCE (仅支持检测，自行开启ladp服务利用)  
seeyonoa_wpsAssistServlet-RCE(默认写入冰蝎4.0.3aes)  
seeyonoa_htmlofficeservlet-RCE(默认写入冰蝎4.0.3aes)  
seeyonreport_svg_upload-RCE(默认写入冰蝎4.0.3aes)  
seeyonoa_ajaxBypass-RCE(写入天蝎 密码sky)  
seeyon_testsqli-RCE(仅检测是否存在漏洞页面)

通达:  
tongdaoa_getdata-RCE (直接执行系统命令)  
tongdaoa_apiali-RCE (默认写入冰蝎4.0.3aes)

帆软：
fanruan_save_svg-RCE (默认写入冰蝎4.0.3aes)

中间件:  
IIS_PUT_RCE (emm暂时没办法getshell  仅支持检测 java没有MOVE方法)

安全设备:  
综合安防_applyCT_fastjson-RCE(仅支持检测,自行使用ladp服务利用)  
综合安防_api_file任意文件上传 (默认写入冰蝎4.0.3aes)  
综合安防_external_report任意文件上传 (默认写入冰蝎4.0.3aes)  
网康下一代防火墙_ngfw_waf_route-RCE(写入菜刀shell 密码:nishizhu)  
H3C cas_cvm_upload-RCE  (默认写入冰蝎4.0.3aes)  
大华智慧园区任意文件上传  (默认写入冰蝎4.0.3aes)  
深信服应用交付管理系统命令执行  
网御星云账号密码泄露
阿里nacos未授权任意用户添加

使用截图：  
![QQ截图20221014202028](https://user-images.githubusercontent.com/100954709/195846430-84bfff61-2c7b-4027-abcc-76d5910b76e4.png)  
![QQ截图20221014202151](https://user-images.githubusercontent.com/100954709/195846449-cbf2d0c2-e0f6-4567-b0d4-d9ead527d459.png)  
![3](https://user-images.githubusercontent.com/100954709/193958439-cdaf1a64-55f4-4afb-9a44-cfec5e237208.png)
  
---
## 工具模块:

文件上传指令生成  
![upload](https://user-images.githubusercontent.com/100954709/195846198-3133fd70-3849-4dfe-862c-c42dd865b214.png)


Tasklist敏感进程检测  
![tasklist](https://user-images.githubusercontent.com/100954709/195846255-b06e35e9-718b-4b69-a203-cadb88338858.png)

反弹shell命令生成  
![shell](https://user-images.githubusercontent.com/100954709/195846331-474bdd57-ef97-45a5-b872-5b39de592c70.png)


---
## 配置相关

部分漏洞使用dnslog检测  请自行修改 Apt_config/dnslog下内容  
本工具使用CEYE.IO   只需修改为自己的地址及tokent即可

---
## 问题反馈
可直接提Issu  
或加我wx进群交流，微信请备注apt

![my](https://user-images.githubusercontent.com/100954709/193801691-df73fec6-284a-450a-943a-09fe023bcde0.png)


---


## 开心指数

[![Stargazers over time](https://starchart.cc/White-hua/Apt_t00ls.svg)](https://starchart.cc/White-hua/Apt_t00ls)
---
## 免责声明
本工具仅面向合法授权的企业安全建设行为，如您需要测试本工具的可用性，请自行搭建靶机环境。

为避免被恶意使用，本项目所有收录的poc均为漏洞的理论判断，不存在漏洞利用过程，不会对目标发起真实攻击和漏洞利用。

在使用本工具进行检测时，您应确保该行为符合当地的法律法规，并且已经取得了足够的授权。请勿对非授权目标进行扫描。

如您在使用本工具的过程中存在任何非法行为，您需自行承担相应后果，我们将不承担任何法律及连带责任。

在安装并使用本工具前，请您务必审慎阅读、充分理解各条款内容，限制、免责条款或者其他涉及您重大权益的条款可能会以加粗、加下划线等形式提示您重点注意。 除非您已充分阅读、完全理解并接受本协议所有条款，否则，请您不要安装并使用本工具。您的使用行为或者您以其他任何明示或者默示方式表示接受本协议的，即视为您已阅读并同意本协议的约束

