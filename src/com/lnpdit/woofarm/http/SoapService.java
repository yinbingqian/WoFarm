package com.lnpdit.woofarm.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.woofarm.entity.UserInfo;
import com.lnpdit.woofarm.http.AsyncPostTaskBase.HttpPostObjectResult;
import com.lnpdit.woofarm.http.AsyncTaskBase.HttpObjectResult;
import com.lnpdit.woofarm.utils.EventCache;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

public class SoapService implements ISoapService {
	private AsyncTaskBase asynTaskBase = new AsyncTaskBase();
    private AsyncPostTaskBase asynPostTaskBase = new AsyncPostTaskBase();
	private SoapRes soapRes = new SoapRes();

    @Override
    public void userLogin(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"phone","password"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.LOGIN);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.LOGIN);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.LOGIN);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

    @Override
    public void getCodeByPhone(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"phone"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETCODEBYPHONE);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    String message = json_obj.get("msg").toString();
                    if (result.equals("true")) {
                        message = "true";
                    }
                    
                soapRes.setObj(message);
                soapRes.setCode(SOAP_UTILS.METHOD.GETCODEBYPHONE);
                EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETCODEBYPHONE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    @Override
    public void memberReg(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"phone","code","nickname","password"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.MEMBERREG);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    String message = json_obj.get("msg").toString();
                    if (result.equals("true")) {
                        message = "true";
                    }
                soapRes.setObj(message);
                soapRes.setCode(SOAP_UTILS.METHOD.MEMBERREG);
                
                EventCache.commandActivity.post(soapRes);
                
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.MEMBERREG);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
}
