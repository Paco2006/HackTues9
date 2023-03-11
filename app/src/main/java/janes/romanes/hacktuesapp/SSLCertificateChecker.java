package janes.romanes.hacktuesapp;

import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLCertificateChecker {

    public static boolean isCertificateTrustable(String url) {
        AtomicBoolean isSecure = new AtomicBoolean(false); // initialize to false
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    try {
                        for (X509Certificate cert : certs) {
                            cert.checkValidity();
                            verifyHostName(url, cert);
                        }
                        isSecure.set(true); // modify the value here
                    } catch (Exception e) {
                        isSecure.set(false);
                    }
                }
            }}, null);

            URLConnection urlConnection = new URL(url).openConnection();
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlConnection;
            httpsURLConnection.setSSLSocketFactory(sslContext.getSocketFactory());
            httpsURLConnection.connect();

            Certificate[] serverCerts = httpsURLConnection.getServerCertificates();
            for (Certificate cert : serverCerts) {
                if (cert instanceof X509Certificate) {
                    ((X509Certificate) cert).checkValidity();
                    verifyHostName(url, (X509Certificate) cert);
                    isSecure.set(true);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSecure.get();
    }

    private static void verifyHostName(String url, X509Certificate cert) throws SSLPeerUnverifiedException {
        if (!url.equalsIgnoreCase(cert.getSubjectDN().getName())) {
            throw new SSLPeerUnverifiedException("Certificate for " + cert.getSubjectDN().getName() +
                    " does not match " + url);
        }
    }
}
