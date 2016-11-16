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
}
