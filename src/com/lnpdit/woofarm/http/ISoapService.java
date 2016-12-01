package com.lnpdit.woofarm.http;

/**
 * webService请求接口
 * 
 * @author huanyu 类名称：ISoapService 创建时间:2014-11-4 下午7:08:50
 */
public interface ISoapService extends IASoapService {

	/**
	 * 用户登录--手机号 phone|密码 password
	 * 
	 * @param property_va
	 */
	void userLogin(Object[] property_va);

    /**
     * 发送手机号得到短信验证码--手机号 phone
     * 
     * @param property_va
     */
    void getCodeByPhone(Object[] property_va);

    /**
     * 用户注册--手机号 phone|验证码 code|昵称 nickname|密码 password
     * 
     * @param property_va
     */
    void memberReg(Object[] property_va);

    /**
     * 首页
     * 
     * @param property_va
     */
    void getIndex(Object[] property_va);

    /**
     * 首页-分类列表获取
     * 
     * @param property_va
     */
    void getProductClass(Object[] property_va);

    /**
     * 首页-分类内容列表获取--分类id classid|页大小 pagesize|第几页 pageindex
     * 
     * @param property_va
     */
    void getProductByClass(Object[] property_va, boolean isPage);

    /**
     * 首页-新品更多获取、预订更多获取--销售类型(presell:预售,sell:现有商品) selltype|页大小 pagesize|第几页 pageindex
     * 
     * @param property_va
     */
    void getProductBySellType(Object[] property_va, boolean isPage);

    /**
     * 预订月份查询--年 year|月 month
     * 
     * @param property_va
     */
    void getPreProductByMonth(Object[] property_va);
    
    /**
     * 预订月份查询--商品id productid
     * 
     * @param property_va
     */
    void getProduct(Object[] property_va);

    /**
     * 加入购物车--商品id productid|数量 quantity|会员id memberid
     * 
     * @param property_va
     */
    void addcart(Object[] property_va);
    
    /**
     * 生态列表获取
     * 
     * @param property_va
     */
    void getShopList(Object[] property_va);

    /**
     * 购物车列表获取--会员id memberid|页大小 pagesize|第几页 pageindex
     * 
     * @param property_va
     */
    void getCartList(Object[] property_va, boolean isPage);

    /**
     * 我的订单列表获取--会员id memberid|页大小 pagesize|第几页 pageindex
     * 
     * @param property_va
     */
    void getOrderListByMember(Object[] property_va, boolean isPage);

    /**
     * 我的地址列表获取--会员id memberid
     * 
     * @param property_va
     */
    void getReceaddressByMember(Object[] property_va);

    /**
     * 删除订单方法--订单id id
     * 
     * @param property_va
     */
    void deleteOrderById(Object[] property_va);
    
    /**
     * 删除地址方法--地址id id
     * 
     * @param property_va
     */
    void deleteReceaddressById(Object[] property_va);
    
    /**
     * 购物车删除方法--会员id memberid|商品id productid
     * 
     * @param property_va
     */
    void deleteCart(Object[] property_va);
    
    
    /**
     * 密码验证--会员id memberid|密码 password
     * 
     * @param property_va
     */
    void validationPasswordByMember(Object[] property_va);
    
    
    /**
     * 重置密码--会员id memberid|原密码 password_old|新密码 password_new
     * 
     * @param property_va
     */
    void setPassword(Object[] property_va);
    
    /**
     *修改地址--地址id id|收货人 username|电话 mobile|地址address|省 province|市 city
     * 
     * @param property_va
     */
    void updateReceaddress(Object[] property_va);
    
    /**
     *添加地址--会员id memberid|收货人 username|电话 mobile|地址address|省 province|市 city
     * 
     * @param property_va
     */
    void addReceaddress(Object[] property_va);
    
    /**
     *默认地址--地址 id
     * 
     * @param property_va
     */
    void setReceaddressStatById(Object[] property_va);
    
//    /**
//     *修改头像--Stream ImageContext|会员id memberid
//     * 
//     * @param property_va
//     */
//    void upLoadFile(Object[] property_va);

    /**
     *商品搜索--  商品名 name 页大小pagesize 第几页pageindex
     * 
     * @param property_va
     */
    void getProductByName(Object[] property_va, boolean isPage);
}
