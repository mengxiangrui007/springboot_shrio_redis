package com.abroad.common.tools;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * juntai环境
 */
public class JuntaiEnvironment {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String CLIENT = "client";
    private final static String TRACE_ID = "trace_id";
    private final static String PHONE_TYPE = "phoneType";
    private final static String DEV_ID = "devId";
    private final static String IP_ADDRESS = "remoteAddr";
    public final static String PLATFORM_IOS = "1";
    public final static String PLATFORM_ANDROID = "2";
    public final static String PLATFORM_PCWEB = "3";
    public final static String PLATFORM_H5 = "4";

    public static void put(String key, String val) {
        MDC.put(key, val);
    }

    public static void putTraceId(String value) {
        MDC.put(TRACE_ID, value);
    }

    public static void putClient(String value) {
        MDC.put(CLIENT, value);
    }

    public static void putPhoneType(String value) {
        MDC.put(PHONE_TYPE, value);
    }

    public static void putDevId(String value) {
        MDC.put(DEV_ID, value);
    }

    public static void putRemoteAddr(String value) {
        MDC.put(IP_ADDRESS, value);
    }

    /**
     *
     * @return
     */
    public static String getRemoteAddr() {
        return MDC.get(IP_ADDRESS);
    }

    /**
     *
     * @return
     */
    public static String getTraceId() {
        return MDC.get(CLIENT);
    }

    /**
     *
     * @return
     */
    public static String getClient() {
        return MDC.get(CLIENT);
    }

    /**
     *
     * @return
     */
    public static String getPhoneType() {
        return MDC.get(CLIENT);
    }

    /**
     *
     * @return
     */
    public static String getDevId() {
        return MDC.get(CLIENT);
    }
}
