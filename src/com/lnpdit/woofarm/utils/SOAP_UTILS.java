package com.lnpdit.woofarm.utils;

public class SOAP_UTILS {
    public class METHOD {
        public static final String LOGIN = "Member.svc/MemberLogin";//登录
        public static final String GETCODEBYPHONE = "Member.svc/GetCodeByPhone";//获取验证码
        public static final String MEMBERREG = "Member.svc/MemberReg";//注册
        public static final String GETINDEX = "Advertise.svc/GetIndex";//获取首页数据
        public static final String GETPRODUCTCLASS = "Product.svc/GetProductClass";//分类列表获取
        public static final String GETPRODUCTBYCLASS = "Product.svc/GetProductByClass";//分类内容列表获取
        public static final String GETPRODUCTBYSELLTYPE = "Product.svc/GetProductBySellType";//新品更多获取、预订更多获取
        public static final String GETPREPRODUCTBYMONTH = "Product.svc/GetPreProductByMonth";//预订月份查询
        public static final String GETPRODUCT = "Product.svc/GetProduct";//预订月份查询
        public static final String ADDCART = "Product.svc/Addcart";//加入购物车
        public static final String GETSHOPLIST = "Member.svc/GetShopList";//生态列表获取
        public static final String GETCARTLIST = "Product.svc/GetCartList";//购物车列表获取
        public static final String GETORDERLISTBYMEMBER = "Product.svc/GetOrderListByMember";//我的订单列表获取
        public static final String GETRECEADDRESSBYMEMBER = "Member.svc/GetReceaddressByMember";//我的地址列表获取
        public static final String DELETEORDERBYID = "Product.svc/DeleteOrderById";//删除订单列表
        public static final String DELETERECEADDRESSBYID = "Member.svc/DeleteReceaddressById";//删除地址列表
        public static final String DELETECART = "Product.svc/DeleteCart";//删除购物车列表获取
        public static final String VALIDATIONPASSWORD = "Member.svc/ValidationPasswordByMember";//密码验证
        public static final String SETPASSWORD = "Member.svc/SetPassword";//重置密码
        public static final String UPDATERECEADDRESS = "Member.svc/UpdateReceaddress";//修改地址
        public static final String ADDRECEADDRESS = "Member.svc/AddReceaddress";//添加地址
        public static final String SETRECEADDRESSSTATBYID = "Member.svc/SetReceaddressStatById";//设置默认地址
        public static final String UPLOADFILE = "DataRcvService.svc/UpLoadFile";//修改头像
        public static final String GETPRODUCTBYNAME = "Product.svc/GetProductByName";//商品搜索
        
        
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
    public static final String IP = "http://woshi.tsti.cn/WSIWcfService/";
//    public static final String IP = "http://192.168.1.201:3236/myService.svc/";
    public static final String FOLDER_PATH = "https://visiblefarm.com:4431/";
    public static final String URL = IP;
    public static final String URL_WITHOUT_WSDL = IP;
    public static final String PIC_FILE = FOLDER_PATH + "image/";
    // login type
    public static final int POLICE = 0;// 警察
    public static final int CITIZEN = 1;// 市民
    
}
