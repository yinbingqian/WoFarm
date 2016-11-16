package com.lnpdit.woofarm.utils;

public class SOAP_UTILS {
    public class METHOD {
        public static final String LOGIN = "MemberLogin";
        public static final String GETCODEBYPHONE = "GetCodeByPhone";
        public static final String MEMBERREG = "MemberReg";
    }

    public class ERROR {
        public static final String ERR0000 = "ERR 000";
        public static final String ERR0001 = "ERR 001";
        public static final String ERR0002 = "ERR 002";
        public static final String ERR0003 = "ERR 003";
        public static final String ERR0004 = "ERR 004";
        public static final String ERR0005 = "ERR 005 XML解析错误";
        public static final String ERR0006 = "ERR 006 本地错误";

    }
    public static final String NAMESPACE = "WoFarm";
    public static final String IP_SIMPLE = "123.56.88.189";
    public static final String IP = "http://woshi.tsti.cn/WSIWcfService/Member.svc/";
//    public static final String IP = "http://192.168.1.201:3236/myService.svc/";
    public static final String FOLDER_PATH = "https://visiblefarm.com:4431/";
    public static final String URL = IP;
    public static final String URL_WITHOUT_WSDL = IP;
    public static final String PIC_FILE = FOLDER_PATH + "image/";
    // login type
    public static final int POLICE = 0;// 警察
    public static final int CITIZEN = 1;// 市民
    
}
