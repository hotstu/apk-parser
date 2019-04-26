package net.dongliu.apk.parser;

import net.dongliu.apk.parser.bean.ApkSigner;
import net.dongliu.apk.parser.bean.CertificateMeta;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Locale;

/**
 * Main method for parser apk
 *
 * @author Liu Dong {@literal <dongliu@live.cn>}
 */
public class Main {
    public static void main(String[] args) throws IOException, CertificateException {
        if (args.length == 0) {
            System.out.println("请输入参数 <option> apkPath");
            System.out.println("option = [meta, manifest, signer]");
            return;
        }
        String action;
        String apkPath;
        if (args.length == 1) {
            action = "meta";
            apkPath = args[0];
        } else {
            action = args[0];
            apkPath = args[1];
        }

        try (ApkFile apkFile = new ApkFile(apkPath)) {
            apkFile.setPreferredLocale(Locale.getDefault());
            switch (action) {
                case "meta":
                    System.out.println(apkFile.getApkMeta());
                    for (ApkSigner apkSinger : apkFile.getApkSingers()) {
                        System.out.println("cert: \t" + apkSinger.getPath());
                        for (CertificateMeta certificateMeta : apkSinger.getCertificateMetas()) {
                            System.out.println("md5: \t" + certificateMeta.getCertMd5());
                            System.out.println("sha1: \t" + certificateMeta.getCertsha1());
                            System.out.println("sha256: \t" + certificateMeta.getCertsha256());
                        }
                    }
                    break;
                case "manifest":
                    System.out.println(apkFile.getManifestXml());
                    break;
                case "signer":
                    System.out.println(apkFile.getApkSingers());
                    break;
                default:
            }

        }
    }
}
