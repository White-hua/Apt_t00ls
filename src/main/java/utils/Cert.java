package utils;

/**
 * @author yhy
 * @date 2021/8/20 23:25
 * @github https://github.com/yhy0
 */

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class Cert implements X509TrustManager {
    public Cert() {
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
