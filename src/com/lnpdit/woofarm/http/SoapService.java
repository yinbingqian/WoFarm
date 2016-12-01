package com.lnpdit.woofarm.http;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lnpdit.woofarm.entity.Address;
import com.lnpdit.woofarm.entity.Order;
import com.lnpdit.woofarm.entity.ProductByClass;
import com.lnpdit.woofarm.entity.ProductByMonth;
import com.lnpdit.woofarm.entity.ProductClass;
import com.lnpdit.woofarm.http.AsyncPostTaskBase.HttpPostObjectResult;
import com.lnpdit.woofarm.utils.EventCache;
import com.lnpdit.woofarm.utils.SOAP_UTILS;

import android.widget.Toast;

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

  //幻灯片数组
    @Override
    public void getIndex(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETINDEX);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub

                    soapRes.setObj(obj);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETINDEX);
                    EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETINDEX);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }


  //分类列表获取
    @Override
    public void getProductClass(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETPRODUCTCLASS);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                   if(result.equals("true")){
                       
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<ProductClass> message_list = new ArrayList<ProductClass>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        ProductClass hpn = new ProductClass();
                        hpn.setId(json_news.get("id").toString());
                        hpn.setName(json_news.get("name").toString());

                        message_list.add(hpn);
                    }
                   }else{
//                       Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                   }
                    soapRes.setObj(obj);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTCLASS);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTCLASS);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTCLASS);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

  //分类内容列表获取
    @Override
    public void getProductByClass(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        String[] property_nm = {"classid","pagesize","pageindex"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETPRODUCTBYCLASS);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    if(result.equals("true")){
                        
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<ProductByClass> message_list = new ArrayList<ProductByClass>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        ProductByClass hpn = new ProductByClass();
                        hpn.setImage(json_news.get("image").toString());
                        hpn.setId(json_news.get("id").toString());
                        hpn.setName(json_news.get("name").toString());
                        hpn.setPrice(json_news.get("price").toString());

                        message_list.add(hpn);
                    }
                    
                    soapRes.setObj(message_list);
                    }else{

                        soapRes.setObj(result);
                    }
                    soapRes.setPage(isPage);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYCLASS);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYCLASS);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYCLASS);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

  //新品更多获取、预订更多获取
    @Override
    public void getProductBySellType(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        //(presell:预售,sell:现有商品) selltype
        String[] property_nm = {"selltype","pagesize","pageindex"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETPRODUCTBYSELLTYPE);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    if(result.equals("true")){
                  
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<ProductByClass> message_list = new ArrayList<ProductByClass>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        ProductByClass hpn = new ProductByClass();
                        hpn.setImage(json_news.get("image").toString());
                        hpn.setId(json_news.get("id").toString());
                        hpn.setName(json_news.get("name").toString());
                        hpn.setPrice(json_news.get("price").toString());

                        message_list.add(hpn);
                    }
                    soapRes.setObj(message_list);
                    }else{
                        soapRes.setObj(result);
                    }
                    soapRes.setPage(isPage);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYSELLTYPE);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYSELLTYPE);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYSELLTYPE);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }

  //预订月份查询
    @Override
    public void getPreProductByMonth(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"year","month"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETPREPRODUCTBYMONTH);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<ProductByMonth> message_list = new ArrayList<ProductByMonth>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        ProductByMonth hpn = new ProductByMonth();
                        hpn.setDate(json_news.get("date").toString());
                        hpn.setName(json_news.get("name").toString());

                        message_list.add(hpn);
                    }
                    
                    soapRes.setObj(message_list);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPREPRODUCTBYMONTH);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPREPRODUCTBYMONTH);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPREPRODUCTBYMONTH);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //商品详情获取
    @Override
    public void getProduct(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"productid"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETPRODUCT);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                    
                    soapRes.setObj(obj);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCT);
                    EventCache.commandActivity.post(soapRes);
             
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCT);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //加入购物车
    @Override
    public void addcart(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"productid","quantity","memberid"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.ADDCART);
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
                soapRes.setCode(SOAP_UTILS.METHOD.ADDCART);
                EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.ADDCART);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //生态列表获取
    @Override
    public void getShopList(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETSHOPLIST);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                    
                    soapRes.setObj(obj);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETSHOPLIST);
                    EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETSHOPLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //购物车列表获取
    @Override
    public void getCartList(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        String[] property_nm = {"memberid","pagesize","pageindex"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETCARTLIST);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                    
                    soapRes.setObj(obj);
                    soapRes.setPage(isPage);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETCARTLIST);
                    EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETCARTLIST);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //我的订单列表获取
    @Override
    public void getOrderListByMember(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        String[] property_nm = {"memberid","pagesize","pageindex"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETORDERLISTBYMEMBER);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    if(result.equals("true")){
                  
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<Order> message_list = new ArrayList<Order>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        Order hpn = new Order();
                        hpn.setId(json_news.get("id").toString());
                        hpn.setOrderid(json_news.get("orderid").toString());
                        hpn.setConsigneerealname(json_news.get("consigneerealname").toString());
                        hpn.setConsigneeaddress(json_news.get("consigneeaddress").toString());
                        hpn.setConsigneephone(json_news.get("consigneephone").toString());
                        hpn.setPaymenttype(json_news.get("paymenttype").toString());
                        hpn.setOrderdate(json_news.get("orderdate").toString());
                        hpn.setOrderstate(json_news.get("orderstate").toString()); 
                        
                        JSONArray product_array = json_news.getJSONArray("orderproduct");
                        for(int j = 0; j < product_array.length(); j++){
                            JSONObject json_product = (JSONObject)product_array.get(j);
                            hpn.setProductimg(json_product.get("productimg").toString());
                            hpn.setProductname(json_product.get("productname").toString());
                            hpn.setProductprice(json_product.get("productprice").toString());
                            hpn.setProductnum(json_product.get("productnum").toString());
                        }

                        message_list.add(hpn);
                    }
                    soapRes.setObj(message_list);
                    }else{
                        soapRes.setObj(result);
                    }
                    soapRes.setPage(isPage);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETORDERLISTBYMEMBER);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETORDERLISTBYMEMBER);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETORDERLISTBYMEMBER);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //我的地址列表获取
    @Override
    public void getReceaddressByMember(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"memberid"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETRECEADDRESSBYMEMBER);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                try {
                    JSONObject json_obj = new JSONObject(obj.toString());
                    String result = json_obj.get("status").toString();
                    if(result.equals("true")){
                  
                    JSONArray message_array = json_obj.getJSONArray("msg");
                    
                    List<Address> message_list = new ArrayList<Address>();

                    for (int i = 0; i < message_array.length(); i++) {
                        JSONObject json_news = (JSONObject) message_array.get(i);
                        Address hpn = new Address();
                        hpn.setAddress(json_news.get("address").toString());
                        hpn.setCity(json_news.get("city").toString());
                        hpn.setId(json_news.get("id").toString());
                        hpn.setMobile(json_news.get("mobile").toString());
                        hpn.setProvince(json_news.get("province").toString());
                        hpn.setStat(json_news.get("stat").toString());
                        hpn.setUsername(json_news.get("username").toString());
                     
                        message_list.add(hpn);
                    }
                    soapRes.setObj(message_list);
                    }else{
                        soapRes.setObj(result);
                    }
                    soapRes.setCode(SOAP_UTILS.METHOD.GETRECEADDRESSBYMEMBER);
                    EventCache.commandActivity.post(soapRes);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    soapRes.setObj(null);
                    soapRes.setCode(SOAP_UTILS.METHOD.GETRECEADDRESSBYMEMBER);
                    EventCache.commandActivity.post(soapRes);
                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETRECEADDRESSBYMEMBER);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    
    //删除订单方法
    @Override
    public void deleteOrderById(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"id"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.DELETEORDERBYID);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                    
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.DELETEORDERBYID);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.DELETEORDERBYID);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //删除地址方法
    @Override
    public void deleteReceaddressById(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"id"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.DELETERECEADDRESSBYID);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                
                    
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.DELETERECEADDRESSBYID);
                EventCache.commandActivity.post(soapRes);
             
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.DELETERECEADDRESSBYID);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //购物车删除方法
    @Override
    public void deleteCart(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"memberid","productid"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.DELETECART);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
//                try {
//                    JSONObject json_obj = new JSONObject(obj.toString());
//                    String result = json_obj.get("status").toString();
//                    String message = json_obj.get("msg").toString();
//                    if (result.equals("true")) {
//                        message = "true";
//                    }
                    
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.DELETECART);
                EventCache.commandActivity.post(soapRes);
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.DELETECART);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //密码验证方法
    @Override
    public void validationPasswordByMember(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"memberid","password"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.VALIDATIONPASSWORD);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                    
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.VALIDATIONPASSWORD);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.VALIDATIONPASSWORD);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    
    //重置密码方法
    @Override
    public void setPassword(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"memberid","password_old","password_new"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.SETPASSWORD);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
                    
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.SETPASSWORD);
                EventCache.commandActivity.post(soapRes);
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.SETPASSWORD);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }  
    

    //添加地址方法
    @Override
    public void addReceaddress(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"memberid","username","mobile","address","province","city"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.ADDRECEADDRESS);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
     
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.ADDRECEADDRESS);
                EventCache.commandActivity.post(soapRes);

            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.ADDRECEADDRESS);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    

    //编辑地址方法
    @Override
    public void updateReceaddress(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"id","username","mobile","address","province","city"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.UPDATERECEADDRESS);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
              
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.UPDATERECEADDRESS);
                EventCache.commandActivity.post(soapRes);
              
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.UPDATERECEADDRESS);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    

    //默认地址
    @Override
    public void setReceaddressStatById(Object[] property_va) {
        // TODO Auto-generated method stub
        String[] property_nm = {"id"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.SETRECEADDRESSSTATBYID);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
               
                soapRes.setObj(obj);
                soapRes.setCode(SOAP_UTILS.METHOD.SETRECEADDRESSSTATBYID);
                EventCache.commandActivity.post(soapRes);
               
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.SETRECEADDRESSSTATBYID);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }
    //商品搜索
    @Override
    public void getProductByName(Object[] property_va, final boolean isPage) {
        // TODO Auto-generated method stub
        String[] property_nm = {"name","pagesize","pageindex"};
        asynPostTaskBase.setMethod(SOAP_UTILS.METHOD.GETPRODUCTBYNAME);
        asynPostTaskBase.setProperty_nm(property_nm);
        asynPostTaskBase.setProperty_va(property_va);
        asynPostTaskBase.executeDo(new HttpPostObjectResult() {

            @Override
            public void soapResult(Object obj) {
                // TODO Auto-generated method stub
               
                soapRes.setObj(obj);
                soapRes.setPage(isPage);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYNAME);
                EventCache.commandActivity.post(soapRes);
               
            }

            @Override
            public void soapError() {
                soapRes.setObj(null);
                soapRes.setCode(SOAP_UTILS.METHOD.GETPRODUCTBYNAME);
                EventCache.commandActivity.post(soapRes);
            }
        });
    }  
    
}
