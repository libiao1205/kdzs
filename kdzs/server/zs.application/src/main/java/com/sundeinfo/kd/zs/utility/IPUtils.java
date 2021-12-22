package com.sundeinfo.kd.zs.utility;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * IP地址工具类
 *
 * @author lgs
 * @date 2019-08-27 16:21:32
 */
public class IPUtils {

    /**
     * 获取本机的内网ip地址
     *
     * @return
     * @throws SocketException
     */
    public static String getInternalIP() throws SocketException {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        Enumeration<NetworkInterface> netInterfaces;
        netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress()
                        && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress()
                        && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }
        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }

    /**
     * 获取本机的外网ip地址
     *
     * @return
     */
    public static String getIP(final HttpServletRequest request) {
        String ipString = "";
        if (request != null) {
            ipString = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
                ipString = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
                ipString = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ipString) || "unknown".equalsIgnoreCase(ipString)) {
                ipString = request.getRemoteAddr();
            }

            // 多个路由时，取第一个非unknown的ip
            final String[] arr = ipString.split(",");
            for (final String str : arr) {
                if (!"unknown".equalsIgnoreCase(str)) {
                    ipString = str;
                    break;
                }
            }
        }

        return ipString;
    }

    public static String getInternetIP() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return getIP(request);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("内网IP:" + getInternalIP());
        System.out.println("外网IP:" + getInternetIP());
    }
}
