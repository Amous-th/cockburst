package com.alibaba.profiler.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author wxy on 16/6/4.
 */
public class IpUtil {

    private static String cacheLocalIp;

    public static String getLocalIp() {

        if (cacheLocalIp != null) {
            return cacheLocalIp;
        }
        // ����IP�����û����������IP�򷵻���
        String localIp = null;
        // ����IP
        String netip = null;
        try {

            Enumeration<NetworkInterface> netInterfaces =
                    NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            // �Ƿ��ҵ�����IP
            boolean finded = false;

            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    if (!ip.isSiteLocalAddress()
                            && !ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1) {// ����IP
                        netip = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (ip.isSiteLocalAddress()

                            && !ip.isLoopbackAddress()

                            && ip.getHostAddress().indexOf(":") == -1) {// ����IP
                        localIp = ip.getHostAddress();

                    }
                }
            }

        } catch (Exception e) {
            // Do Nothing
        }
//        if (netip != null && !"".equals(netip)) {
//            return netip;
//        } else {
        cacheLocalIp = localIp;
        return localIp;
//        }
    }
}
