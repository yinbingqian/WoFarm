package com.lnpdit.woofarm.db;

import java.util.ArrayList;
import java.util.List;

import com.lnpdit.woofarm.entity.ADIndex;
import com.lnpdit.woofarm.entity.ADInfo;
import com.lnpdit.woofarm.entity.Address;
import com.lnpdit.woofarm.entity.Area;
import com.lnpdit.woofarm.entity.Camera;
import com.lnpdit.woofarm.entity.Cart;
import com.lnpdit.woofarm.entity.ChatMessage;
import com.lnpdit.woofarm.entity.Classify;
import com.lnpdit.woofarm.entity.LoginUser;
import com.lnpdit.woofarm.entity.Menu;
import com.lnpdit.woofarm.entity.Order;
import com.lnpdit.woofarm.entity.PreProduct;
import com.lnpdit.woofarm.entity.Product;
import com.lnpdit.woofarm.entity.ProductByClass;
import com.lnpdit.woofarm.entity.ProductInfo;
import com.lnpdit.woofarm.entity.UserInfo;
import com.lnpdit.woofarm.utils.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "";
    private static final int VERSION = 1;
    private static final CursorFactory FACTORY = null;
    private static String DB_NAME = "wofarm.db";
    private Context context;
    private static String DB_PATH = "/data/data/com.lnpdit.woofarm/databases/";
    private static final int ASSETS_SUFFIX_BEGIN = 101;
    private static final int ASSETS_SUFFIX_END = 103;
    private SQLiteDatabase myDataBase = null;

    private String CHAT_DB = "";
    private String FRIE_DB = "";

    /**
     * _id integer primary key autoincrement not null "byte_content blob "
     * user_guid text "insert_date text "direction integer "type integer "
     * user_clid bigint "target_type integer "child_clid text
     * 
     */
    private static final String CREATE_TABLE_USERINFO = "CREATE TABLE USERINFO (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,USERID TEXT, USERNAME TEXT,  HEADPIC TEXT)";

    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE PRODUCT (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,PROID TEXT, NAME TEXT,  PRICE TEXT,UPTIME TEXT, PLACE TEXT,NUMBER TEXT,THUMB TEXT,PIC TEXT,IP TEXT,PORT TEXT,USERNAME TEXT,PASSWORD TEXT, CHNO TEXT)";

    private static final String CREATE_TABLE_PRODUCTBYCLASS = "CREATE TABLE PRODUCTBYCLASS (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,IMAGE TEXT,PROID TEXT, NAME TEXT,  PRICE TEXT)";

    private static final String CREATE_TABLE_PREPRODUCT = "CREATE TABLE PREPRODUCT (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,IMAGE TEXT,PROID TEXT, NAME TEXT,  PRICE TEXT)";

    private static final String CREATE_TABLE_MENU = "CREATE TABLE MENU (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,PROID TEXT, NAME TEXT)";

    private static final String CREATE_TABLE_ADINDEX = "CREATE TABLE ADINDEX (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,IMAGE TEXT, LINKADDRESS TEXT)";
     
    private static final String CREATE_TABLE_PRODUCTINFO = "CREATE TABLE PRODUCTINFO (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,STATUS TEXT, MSG TEXT,IMAGE TEXT, NAME TEXT, PRICE TEXT,SHELVESTIME TEXT, AREA TEXT,CHANNELID TEXT, IP TEXT,PORT TEXT, ACCOUNT TEXT,PASSWORD TEXT, TYPE TEXT,ID TEXT, YIELD TEXT,IMGS TEXT)";
   
    private static final String CREATE_TABLE_MYORDER = "CREATE TABLE MYORDER (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ID TEXT, ORDERID TEXT, CONSIGNEEREALNAME TEXT,  CONSIGNEEADDRESS TEXT,CONSIGNEEPHONE TEXT, PAYMENTTYLE TEXT,ORDERDATE TEXT,ORDERSTATE TEXT,PRODUCTIMG TEXT,PRODUCTNAME TEXT,PRODUCTPRICE TEXT,PRODUCTNUM TEXT)";

    private static final String CREATE_TABLE_CART = "CREATE TABLE CART (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,IMAGE TEXT, ID TEXT, NAME TEXT, PRICE TEXT,QUANTITY TEXT, TOTALPRICE TEXT, SHOPID TEXT)";
  
    private static final String CREATE_TABLE_CLASSIFY = "CREATE TABLE CLASSIFY (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,CLAID TEXT, NAME TEXT,  TYPE TEXT)";

    private static final String CREATE_TABLE_ADDRESS = "CREATE TABLE ADDRESS (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,ADDRESS TEXT,CITY TEXT,ID TEXT,  MOBILE TEXT,PROVINCE TEXT,  STAT TEXT, USERNAME TEXT)";

    private static final String CREATE_TABLE_AREA = "CREATE TABLE AREA (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, AREAID TEXT,  ADDRESS TEXT, IMG TEXT)";

    private static final String CREATE_TABLE_CAMERA = "CREATE TABLE CAMERA (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, AREAID TEXT,  CAMERAID TEXT, IMG TEXT,   NAME TEXT)";

    public DBHelper(Context context, String name, CursorFactory factory,
            int version) {
        super(context, name, null, version);
        this.context = context;
    }

    public DBHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    public DBHelper(Context context, String name) {
        this(context, name, VERSION);
    }

    public DBHelper(Context context) {
        this(context, DB_PATH + DB_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("wofarm.db ========== onCreate");
        db.execSQL(CREATE_TABLE_USERINFO);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_PRODUCTBYCLASS);
        db.execSQL(CREATE_TABLE_PREPRODUCT);
        db.execSQL(CREATE_TABLE_ADINDEX);
        db.execSQL(CREATE_TABLE_MENU);
        db.execSQL(CREATE_TABLE_PRODUCTINFO);
        db.execSQL(CREATE_TABLE_MYORDER);
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_CLASSIFY);
        db.execSQL(CREATE_TABLE_ADDRESS);
        db.execSQL(CREATE_TABLE_AREA);
        db.execSQL(CREATE_TABLE_CAMERA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public void insUserInfo(UserInfo data) {
        System.out.println("#SU DB# insUserInfo");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("USERID", data.getUserid());
        values.put("USERNAME", data.getUsername());
        values.put("HEADPIC", data.getHeadpic());
        db.insert("USERINFO", "", values);
        close();
    }

    public void updateUser(String loginUserId, String name, String data) {
        ContentValues cv = new ContentValues();
        cv.put(name, data);
        SQLiteDatabase db = getWritableDatabase();
        db.update("USERINFO", cv, "USERID=?", new String[] { loginUserId });
        close();
    }

    public List<UserInfo> queryUserInfo() {
        System.out.println("#SU DB# queryAdviserInfo");
        List<UserInfo> list = new ArrayList<UserInfo>();
        UserInfo user = new UserInfo();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM USERINFO", null);
        while (c.moveToNext()) {
            user = new UserInfo();
            user.setUserid(c.getString(c.getColumnIndex("USERID")));
            user.setUsername(c.getString(c.getColumnIndex("USERNAME")));
            user.setHeadpic(c.getString(c.getColumnIndex("HEADPIC")));
            list.add(user);
        }
        close();
        return list;
    }

    public UserInfo queryUserInfoById(String userId) {
        System.out.println("#SU DB# queryUserInfoById");
        UserInfo userinfo = new UserInfo();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = { userId };
        Cursor c = db.rawQuery("SELECT * FROM USERINFO WHERE USERID = ?", param);
        while (c.moveToNext()) {
            userinfo = new UserInfo();
            userinfo.setUserid(c.getString(c.getColumnIndex("USERID")));
            userinfo.setUsername(c.getString(c.getColumnIndex("USERNAME")));
            userinfo.setHeadpic(c.getString(c.getColumnIndex("HEADPIC")));
        }
        c.close();
        close();
        return userinfo;
    }

    
    public void clearNewsData() {
        System.out.println("#SU DB# cheanLoginUserData");
        SQLiteDatabase db = getWritableDatabase();
        db.delete("T_SU_NEWS", null, null);
    }

    public void clearAdtData() {
        System.out.println("#SU DB# cheanLoginUserData");
        SQLiteDatabase db = getWritableDatabase();
        db.delete("T_SU_ADT", null, null);
    }

    public void clearStockNewsData() {
        System.out.println("#SU DB# cheanLoginUserData");
        SQLiteDatabase db = getWritableDatabase();
        db.delete("T_SU_STOCKNEWS", null, null);
    }

    public void clearStandPointData() {
        System.out.println("#SU DB# cheanLoginUserData");
        SQLiteDatabase db = getWritableDatabase();
        db.delete("T_SU_STANDPOINT", null, null);
    }

    public void clearUserInfoData() {
        System.out.println("#SU DB# cheanLoginUserData");
        SQLiteDatabase db = getWritableDatabase();
        db.delete("USERINFO", null, null);
    }

    /**
     * 登陆者
     * 
     * @param loginUser
     */

    public void insLoginUserInfo(LoginUser loginUser) {
        System.out.println("#SU DB# insLoginUserInfo");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("AUTOLOGIN", loginUser.getAutologin());
        values.put("CRTIME", loginUser.getCrtime());
        values.put("CITY", loginUser.getCity());
        values.put("EMAIL", loginUser.getEmail());
        values.put("GROUP_ID", loginUser.getGroupid());
        values.put("HEADPIC", loginUser.getHeadpic());
        values.put("IMEI", loginUser.getImei());
        values.put("ISLOCK", loginUser.getIslock());
        values.put("LEVEL", loginUser.getLevel());
        values.put("LOGIN_TYPE", loginUser.getLoginType());
        values.put("MARK", loginUser.getMark());
        values.put("MOBILEPHONE", loginUser.getMobilephone());
        values.put("NAME", loginUser.getName());
        values.put("ORGID", loginUser.getOrgid());
        values.put("ORGNAME", loginUser.getOrgname());
        values.put("PAIDMARK", loginUser.getPaidmark());
        values.put("PTITLE", loginUser.getPtitle());
        values.put("PASSWORD", loginUser.getPassword());
        values.put("PROVINCE", loginUser.getProvince());
        values.put("REALNAME", loginUser.getRealname());
        values.put("REMARK", loginUser.getRemark());
        values.put("RESUME", loginUser.getResume());
        values.put("REWARDMARK", loginUser.getRewardmark());
        values.put("SEX", loginUser.getSex());
        values.put("SIM", loginUser.getSim());
        values.put("SPECIALTY", loginUser.getSpecialty());
        values.put("STATUS", loginUser.getStatus());
        values.put("STOCK_AGE", loginUser.getStock_age());
        values.put("STOCK_STYLE", loginUser.getStock_style());
        values.put("TYPE", loginUser.getType());
        values.put("USER_ID", loginUser.getUserid());
        db.insert("T_SU_LUI", "", values);
        close();
    }

    public void cheanLoginUserData() {
        System.out.println("#SU DB# cheanLoginUserData");
        SQLiteDatabase db = getWritableDatabase();
        db.delete("T_SU_LUI", null, null);
    }

    public LoginUser queryLoginUserInfo() {
        System.out.println("#SU DB# queryLoginUserInfo");
        LoginUser loginUser = new LoginUser();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT MAX(_ID) , L.* FROM T_SU_LUI L", null);
        while (c.moveToNext()) {
            loginUser = new LoginUser();
            loginUser.setUserid(c.getString(c.getColumnIndex("USER_ID")));
            loginUser.setAutologin(c.getString(c.getColumnIndex("AUTOLOGIN")));
            loginUser.setCity(c.getString(c.getColumnIndex("CITY")));
            loginUser.setCrtime(c.getString(c.getColumnIndex("CRTIME")));
            loginUser.setEmail(c.getString(c.getColumnIndex("EMAIL")));
            loginUser.setGroupid(c.getString(c.getColumnIndex("GROUP_ID")));
            loginUser.setHeadpic(c.getString(c.getColumnIndex("HEADPIC")));
            loginUser.setImei(c.getString(c.getColumnIndex("IMEI")));
            loginUser.setIslock(c.getString(c.getColumnIndex("ISLOCK")));
            loginUser.setLevel(c.getString(c.getColumnIndex("LEVEL")));
            loginUser.setLoginType(c.getInt(c.getColumnIndex("LOGIN_TYPE")));
            loginUser.setMark(c.getString(c.getColumnIndex("MARK")));
            loginUser.setMobilephone(
                    c.getString(c.getColumnIndex("MOBILEPHONE")));
            loginUser.setName(c.getString(c.getColumnIndex("NAME")));
            loginUser.setOrgid(c.getString(c.getColumnIndex("ORGID")));
            loginUser.setOrgname(c.getString(c.getColumnIndex("ORGNAME")));
            loginUser.setPaidmark(c.getString(c.getColumnIndex("PAIDMARK")));
            loginUser.setPassword(c.getString(c.getColumnIndex("PASSWORD")));
            loginUser.setProvince(c.getString(c.getColumnIndex("PROVINCE")));
            loginUser.setPtitle(c.getString(c.getColumnIndex("PTITLE")));
            loginUser.setRealname(c.getString(c.getColumnIndex("REALNAME")));
            loginUser.setRemark(c.getString(c.getColumnIndex("REMARK")));
            loginUser.setResume(c.getString(c.getColumnIndex("RESUME")));
            loginUser
                    .setRewardmark(c.getString(c.getColumnIndex("REWARDMARK")));
            loginUser.setSex(c.getString(c.getColumnIndex("SEX")));
            loginUser.setSim(c.getString(c.getColumnIndex("SIM")));
            loginUser.setSpecialty(c.getString(c.getColumnIndex("SPECIALTY")));
            loginUser.setStatus(c.getString(c.getColumnIndex("STATUS")));
            loginUser.setStock_age(c.getString(c.getColumnIndex("STOCK_AGE")));
            loginUser.setStock_style(
                    c.getString(c.getColumnIndex("STOCK_STYLE")));
            loginUser.setType(c.getString(c.getColumnIndex("TYPE")));
        }
        System.out.println("###   ###  " + loginUser.getUserid() + ":"
                + loginUser.getName() + ":" + loginUser.getPassword() + ":"
                + loginUser.getRealname() + ":" + loginUser.getLoginType());
        close();
        return loginUser;
    }

    public int queryGzAdminMsg() {

        return 0;

    }

    public int queryGzAdminMsg(String loginid) {
        System.out.println("#SU DB# queryMsgData");
        List<ChatMessage> list = new ArrayList<ChatMessage>();
        String[] param = { loginid };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT * FROM T_SU_MSG WHERE LOGIN_ID  = ? AND SENDER_ID = 1",
                param);
        System.out.println(">>>>>" + c.getCount());
        return c.getCount();
    }

    public void initSysMsgData(String loginid, String[] wStr) {
        System.out.println(">>>　初始化消息数据...");
        System.out.println("#SU DB# initAMsgData" + "id : " + loginid);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put("STATUS", 0);
        values1.put("CONTENT1", wStr[0]);
        values1.put("CONTENT2", "");
        values1.put("CONTENT3_TITLE", "");
        values1.put("CONTENT3_CONTENT", "");
        values1.put("CONTENT3_URL", "");
        values1.put("CONTENT3_IMG", "");
        values1.put("MSG_DIRECTION", 0);
        values1.put("MSG_TYPE", 0);
        values1.put("MSG_DATE", Utils.getSystemDate());
        values1.put("LOGIN_ID", loginid);
        values1.put("SENDER_ID", 1);
        db.insert("T_SU_MSG", "", values1);
        ContentValues values2 = new ContentValues();
        values2.put("STATUS", 0);
        values2.put("CONTENT1", wStr[1]);
        values2.put("CONTENT2", "");
        values2.put("CONTENT3_TITLE", "");
        values2.put("CONTENT3_CONTENT", "");
        values2.put("CONTENT3_URL", "");
        values2.put("CONTENT3_IMG", "");
        values2.put("MSG_DIRECTION", 0);
        values2.put("MSG_TYPE", 0);
        values2.put("MSG_DATE", Utils.getSystemDate());
        values2.put("LOGIN_ID", loginid);
        values2.put("SENDER_ID", -1);
        db.insert("T_SU_MSG", "", values2);
        close();
    }

    /**
     * 商品
     * 
     * @param product
     */

    public void insertProduct(Product product) {
        System.out.println("#SU DB# insertProduct");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PROID", product.getProid());
        values.put("NAME", product.getName());
        values.put("PRICE", product.getPrice());
        values.put("UPTIME", product.getUptime());
        values.put("PLACE", product.getPlace());
        values.put("NUMBER", product.getNumber());
        values.put("THUMB", product.getThumb());
        values.put("PIC", product.getPic());
        values.put("IP", product.getIp());
        values.put("PORT", product.getPort());
        values.put("USERNAME", product.getUsername());
        values.put("PASSWORD", product.getPassword());
        values.put("CHNO", product.getChno());
        db.insert("PRODUCT", "", values);
        close();
    }


    public void insProductList(List<Product> data) {
        System.out.println("#SU DB# insProductList");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("PROID", data.get(i).getProid());
            values.put("NAME", data.get(i).getName());
            values.put("PRICE", data.get(i).getPrice());
            values.put("UPTIME", data.get(i).getUptime());
            values.put("PLACE", data.get(i).getPlace());
            values.put("NUMBER", data.get(i).getNumber());
            values.put("THUMB", data.get(i).getThumb());
            values.put("PIC", data.get(i).getPic());
            values.put("IP", data.get(i).getIp());
            values.put("PORT", data.get(i).getPort());
            values.put("USERNAME", data.get(i).getUsername());
            values.put("PASSWORD", data.get(i).getPassword());
            values.put("CHNO", data.get(i).getChno());
            db.insert("PRODUCT", "", values);

        }
        close();
    }

    
    public Product queryProductById(String productId) {
        System.out.println("#SU DB# queryProductById");
        Product product = new Product();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = { productId };
        Cursor c = db.rawQuery("SELECT * FROM PRODUCT WHERE ID = ?", param);
        while (c.moveToNext()) {
            product = new Product();
            product.setProid(c.getString(c.getColumnIndex("PROID")));
            product.setName(c.getString(c.getColumnIndex("NAME")));
            product.setPrice(c.getString(c.getColumnIndex("PRICE")));
            product.setUptime(c.getString(c.getColumnIndex("UPTIME")));
            product.setPlace(c.getString(c.getColumnIndex("PLACE")));
            product.setNumber(c.getString(c.getColumnIndex("NUMBER")));
            product.setThumb(c.getString(c.getColumnIndex("THUMB")));
            product.setPic(c.getString(c.getColumnIndex("PIC")));
            product.setIp(c.getString(c.getColumnIndex("IP")));
            product.setPort(c.getString(c.getColumnIndex("PORT")));
            product.setUsername(c.getString(c.getColumnIndex("USERNAME")));
            product.setPassword(c.getString(c.getColumnIndex("PASSWORD")));
            product.setChno(c.getString(c.getColumnIndex("CHNO")));
        }
        c.close();
        close();
        return product;
    }

    public List<Product> queryProduct() {
        System.out.println("#SU DB# queryProduct");
        List<Product> list = new ArrayList<Product>();
        Product product = new Product();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM PRODUCT", null);
        while (c.moveToNext()) {
            product = new Product();
            product.setProid(c.getString(c.getColumnIndex("PROID")));
            product.setName(c.getString(c.getColumnIndex("NAME")));
            product.setPrice(c.getString(c.getColumnIndex("PRICE")));
            product.setUptime(c.getString(c.getColumnIndex("UPTIME")));
            product.setPlace(c.getString(c.getColumnIndex("PLACE")));
            product.setNumber(c.getString(c.getColumnIndex("NUMBER")));
            product.setThumb(c.getString(c.getColumnIndex("THUMB")));
            product.setPic(c.getString(c.getColumnIndex("PIC")));
            product.setIp(c.getString(c.getColumnIndex("IP")));
            product.setPort(c.getString(c.getColumnIndex("PORT")));
            product.setUsername(c.getString(c.getColumnIndex("USERNAME")));
            product.setPassword(c.getString(c.getColumnIndex("PASSWORD")));
            product.setChno(c.getString(c.getColumnIndex("CHNO")));
            list.add(product);
        }
        c.close();
        close();
        return list;
    }
    public int deleteProductById(String productId) {
        System.out.println("#SU DB# deleteProductById");
        String[] param = { productId };
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("PRODUCT", "ID=? ", param);
    }

    public int clearAllProduct() {
        System.out.println("#SU DB# clearAllProduct");
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("PRODUCT", null, null);
    }


    /**
     * 产品列表
     * 
     * @param order
     */

    public void insProductByClass(List<ProductByClass> data) {
        System.out.println("#SU DB# insProductByClass");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("IMAGE", data.get(i).getImage());
            values.put("PROID", data.get(i).getId());
            values.put("NAME", data.get(i).getName());
            values.put("PRICE", data.get(i).getPrice());
            db.insert("PRODUCTBYCLASS", "", values);
        }
        close();
    }
    
    public void insProductByClassList(List<ProductByClass> data) {
        System.out.println("#SU DB# insProductByClassList");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("IMAGE", data.get(i).getImage());
            values.put("PROID", data.get(i).getId());
            values.put("NAME", data.get(i).getName());
            values.put("PRICE", data.get(i).getPrice());
            db.insert("PRODUCTBYCLASS", "", values);

        }
        close();
    }

    
    public ArrayList<ProductByClass> queryProductByClass() {
        System.out.println("#SU DB# queryProductByClass");
        ArrayList<ProductByClass> list = new ArrayList<ProductByClass>();
        ProductByClass adi = new ProductByClass();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM PRODUCTBYCLASS", null);
        while (c.moveToNext()) {
            adi = new ProductByClass();
            adi.setImage(c.getString(c.getColumnIndex("IMAGE")));
            adi.setId(c.getString(c.getColumnIndex("PROID")));
            adi.setName(c.getString(c.getColumnIndex("NAME")));
            adi.setPrice(c.getString(c.getColumnIndex("PRICE")));
            list.add(adi);
            
        }

        close();
        return list;
    }
    
    
    public void clearProductByClass() {
        System.out.println("#SU DB# cheanProductByClass");
        SQLiteDatabase db = getWritableDatabase();
        db.delete("PRODUCTBYCLASS", null, null);
    }
    
    /**
     * 产品列表-预订商品
     * 
     * @param order
     */

    public void insPreProduct(List<PreProduct> data) {
        System.out.println("#SU DB# insPreProduct");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("IMAGE", data.get(i).getImage());
            values.put("PROID", data.get(i).getId());
            values.put("NAME", data.get(i).getName());
            values.put("PRICE", data.get(i).getPrice());
            db.insert("PREPRODUCT", "", values);
        }
        close();
    }
    
    public void insPreProductList(List<PreProduct> data) {
        System.out.println("#SU DB# insPreProductList");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("IMAGE", data.get(i).getImage());
            values.put("PROID", data.get(i).getId());
            values.put("NAME", data.get(i).getName());
            values.put("PRICE", data.get(i).getPrice());
            db.insert("PREPRODUCT", "", values);

        }
        close();
    }

    
    public ArrayList<PreProduct> queryPreProduct() {
        System.out.println("#SU DB# queryPreProduct");
        ArrayList<PreProduct> list = new ArrayList<PreProduct>();
        PreProduct adi = new PreProduct();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM PREPRODUCT", null);
        while (c.moveToNext()) {
            adi = new PreProduct();
            adi.setImage(c.getString(c.getColumnIndex("IMAGE")));
            adi.setId(c.getString(c.getColumnIndex("PROID")));
            adi.setName(c.getString(c.getColumnIndex("NAME")));
            adi.setPrice(c.getString(c.getColumnIndex("PRICE")));
            list.add(adi);
            
        }

        close();
        return list;
    }
    
    
    public void clearPreProduct() {
        System.out.println("#SU DB# cheanPreProduct");
        SQLiteDatabase db = getWritableDatabase();
        db.delete("PREPRODUCT", null, null);
    }
    
    
    /**
     * 首页-左侧弹出产品分类列表
     * 
     * @param order
     */

    public void insADIndex(List<ADInfo> data) {
        System.out.println("#SU DB# insADIndex");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("IMAGE", data.get(i).getImage());
            values.put("LINKADDRESS", data.get(i).getLinkaddress());
            db.insert("ADINDEX", "", values);
        }
        close();
    }
    
    public void insADIndexList(List<ADInfo> data) {
        System.out.println("#SU DB# insADIndexList");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("IMAGE", data.get(i).getImage());
            values.put("LINKADDRESS", data.get(i).getLinkaddress());
            db.insert("ADINDEX", "", values);

        }
        close();
    }

    
    public ArrayList<ADInfo> queryADIndex() {
        System.out.println("#SU DB# queryADIndex");
        ArrayList<ADInfo> list = new ArrayList<ADInfo>();
        ADInfo adi = new ADInfo();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ADINDEX", null);
        while (c.moveToNext()) {
            adi = new ADInfo();
            adi.setImage(c.getString(c.getColumnIndex("IMAGE")));
            adi.setLinkaddress(c.getString(c.getColumnIndex("LINKADDRESS")));
            list.add(adi);
            
        }

        close();
        return list;
    }
    
    
    public void clearADIndex() {
        System.out.println("#SU DB# cheanADIndex");
        SQLiteDatabase db = getWritableDatabase();
        db.delete("ADINDEX", null, null);
    }
   
    /**
     * 首页-左侧弹出产品分类列表
     * 
     * @param order
     */

    public void insMenu(List<Menu> data) {
        System.out.println("#SU DB# insMenu");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("PROID", data.get(i).getId());
            values.put("NAME", data.get(i).getName());
            db.insert("MENU", "", values);
        }
        close();
    }
    
    public void insMenuList(List<Menu> data) {
        System.out.println("#SU DB# insMenuList");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("PROID", data.get(i).getId());
            values.put("NAME", data.get(i).getName());
            db.insert("MENU", "", values);

        }
        close();
    }

    
    public ArrayList<Menu> queryMenu() {
        System.out.println("#SU DB# queryMenu");
        ArrayList<Menu> list = new ArrayList<Menu>();
        Menu adi = new Menu();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM MENU", null);
        while (c.moveToNext()) {
            adi = new Menu();
            adi.setId(c.getString(c.getColumnIndex("PROID")));
            adi.setName(c.getString(c.getColumnIndex("NAME")));
            list.add(adi);
            
        }

        close();
        return list;
    }
    
    
    public void clearMenu() {
        System.out.println("#SU DB# cheanMenu");
        SQLiteDatabase db = getWritableDatabase();
        db.delete("MENU", null, null);
    }
     

    /**
     * 首页-左侧弹出产品分类列表
     * 
     * @param order
     */

    public void insProductInfo(List<ProductInfo> data) {
        System.out.println("#SU DB# insProductInfo");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("STATUS", data.get(i).getStatus());
            values.put("MSG", data.get(i).getMsg());
            values.put("IMAGE", data.get(i).getImage());
            values.put("NAME", data.get(i).getName());
            values.put("PRICE", data.get(i).getPrice());
            values.put("SHELVESTIME", data.get(i).getShelvestime());
            values.put("AREA", data.get(i).getArea());
            values.put("CHANNELID", data.get(i).getChannelid());
            values.put("IP", data.get(i).getIp());
            values.put("PORT", data.get(i).getPort());
            values.put("ACCOUNT", data.get(i).getAccount());
            values.put("PASSWORD", data.get(i).getPassword());
            values.put("TYPE", data.get(i).getType());
            values.put("ID", data.get(i).getId());
            values.put("YIELD", data.get(i).getYield());
            values.put("IMGS", data.get(i).getImgs());
            db.insert("PRODUCTINFO", "", values);
        }
        close();
    }
    
    public void insProductInfoList(List<ProductInfo> data) {
        System.out.println("#SU DB# insProductInfoList");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("STATUS", data.get(i).getStatus());
            values.put("MSG", data.get(i).getMsg());
            values.put("IMAGE", data.get(i).getImage());
            values.put("NAME", data.get(i).getName());
            values.put("PRICE", data.get(i).getPrice());
            values.put("SHELVESTIME", data.get(i).getShelvestime());
            values.put("AREA", data.get(i).getArea());
            values.put("CHANNELID", data.get(i).getChannelid());
            values.put("IP", data.get(i).getIp());
            values.put("PORT", data.get(i).getPort());
            values.put("ACCOUNT", data.get(i).getAccount());
            values.put("PASSWORD", data.get(i).getPassword());
            values.put("TYPE", data.get(i).getType());
            values.put("ID", data.get(i).getId());
            values.put("YIELD", data.get(i).getYield());
            values.put("IMGS", data.get(i).getImgs());
            db.insert("PRODUCTINFO", "", values);

        }
        close();
    }

    
    public ArrayList<ProductInfo> queryProductInfo() {
        System.out.println("#SU DB# queryProductInfo");
        ArrayList<ProductInfo> list = new ArrayList<ProductInfo>();
        ProductInfo adi = new ProductInfo();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM PRODUCTINFO", null);
        while (c.moveToNext()) {
            adi = new ProductInfo();
            adi.setStatus(c.getString(c.getColumnIndex("STATUS")));
            adi.setMsg(c.getString(c.getColumnIndex("MSG")));
            adi.setImage(c.getString(c.getColumnIndex("IMAGE")));
            adi.setName(c.getString(c.getColumnIndex("NAME")));
            adi.setPrice(c.getString(c.getColumnIndex("PRICE")));
            adi.setShelvestime(c.getString(c.getColumnIndex("SHELVESTIME")));
            adi.setArea(c.getString(c.getColumnIndex("AREA")));
            adi.setChannelid(c.getString(c.getColumnIndex("CHANNELID")));
            adi.setIp(c.getString(c.getColumnIndex("IP")));
            adi.setPort(c.getString(c.getColumnIndex("PORT")));
            adi.setAccount(c.getString(c.getColumnIndex("ACCOUNT")));
            adi.setPassword(c.getString(c.getColumnIndex("PASSWORD")));
            adi.setType(c.getString(c.getColumnIndex("TYPE")));
            adi.setId(c.getString(c.getColumnIndex("ID")));
            adi.setYield(c.getString(c.getColumnIndex("YIELD")));
            adi.setImgs(c.getString(c.getColumnIndex("IMGS")));
            list.add(adi);
            
        }

        close();
        return list;
    }
    
    
    public void clearProductInfo() {
        System.out.println("#SU DB# cheanProductInfo");
        SQLiteDatabase db = getWritableDatabase();
        db.delete("PRODUCTINFO", null, null);
    }
     
    
    /**
     * 订单
     * 
     * @param order
     */

    public void insertOrder(Order order) {
        System.out.println("#SU DB# insertOrder");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", order.getId());
        values.put("ORDERID", order.getOrderid());
        values.put("CONSIGNEEREALNAME", order.getConsigneerealname());
        values.put("CONSIGNEEADDRESS", order.getConsigneeaddress());
        values.put("CONSIGNEEPHONE", order.getConsigneephone());
        values.put("PAYMENTTYLE", order.getPaymenttype());
        values.put("ORDERDATE", order.getOrderdate());
        values.put("ORDERSTATE", order.getOrderstate());
        values.put("PRODUCTIMG", order.getProductimg());
        values.put("PRODUCTNAME", order.getProductname());
        values.put("PRODUCTPRICE", order.getProductprice());
        values.put("PRODUCTNUM", order.getProductnum());
        db.insert("MYORDER", "", values);
        close();
    }
    
    public void insOrderList(List<Order> data) {
        System.out.println("#SU DB# insOrderList");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("ID", data.get(i).getId());
            values.put("ORDERID", data.get(i).getOrderid());
            values.put("CONSIGNEEREALNAME", data.get(i).getConsigneerealname());
            values.put("CONSIGNEEADDRESS", data.get(i).getConsigneeaddress());
            values.put("CONSIGNEEPHONE", data.get(i).getConsigneephone());
            values.put("PAYMENTTYLE", data.get(i).getPaymenttype());
            values.put("ORDERDATE", data.get(i).getOrderdate());
            values.put("ORDERSTATE", data.get(i).getOrderstate());
            values.put("PRODUCTIMG", data.get(i).getProductimg());
            values.put("PRODUCTNAME", data.get(i).getProductname());
            values.put("PRODUCTPRICE", data.get(i).getProductprice());
            values.put("PRODUCTNUM", data.get(i).getProductnum());
            db.insert("MYORDER", "", values);

        }
        close();
    }

    
    public Order queryOrderById(String orderId) {
        System.out.println("#SU DB# queryOrderById");
        Order order = new Order();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = { orderId };
        Cursor c = db.rawQuery("SELECT * FROM MYORDER WHERE ID = ?", param);
        while (c.moveToNext()) {
            order = new Order();
            order.setId(c.getString(c.getColumnIndex("ID")));
            order.setOrderid(c.getString(c.getColumnIndex("ORDERID")));
            order.setConsigneerealname(c.getString(c.getColumnIndex("CONSIGNEEREALNAME")));
            order.setConsigneeaddress(c.getString(c.getColumnIndex("CONSIGNEEADDRESS")));
            order.setConsigneephone(c.getString(c.getColumnIndex("CONSIGNEEPHONE")));
            order.setPaymenttype(c.getString(c.getColumnIndex("PAYMENTTYLE")));
            order.setOrderdate(c.getString(c.getColumnIndex("ORDERDATE")));
            order.setOrderstate(c.getString(c.getColumnIndex("ORDERSTATE")));
            order.setProductimg(c.getString(c.getColumnIndex("PRODUCTIMG")));
            order.setProductname(c.getString(c.getColumnIndex("PRODUCTNAME")));
            order.setProductprice(c.getString(c.getColumnIndex("PRODUCTPRICE")));
            order.setProductnum(c.getString(c.getColumnIndex("PRODUCTNUM")));
        }
        c.close();
        close();
        return order;
    }

    public List<Order> queryOrder() {
        System.out.println("#SU DB# queryOrder");
        List<Order> list = new ArrayList<Order>();
        Order order = new Order();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM MYORDER", null);
        while (c.moveToNext()) {
            order = new Order();
            order.setId(c.getString(c.getColumnIndex("ID")));
            order.setOrderid(c.getString(c.getColumnIndex("ORDERID")));
            order.setConsigneerealname(c.getString(c.getColumnIndex("CONSIGNEEREALNAME")));
            order.setConsigneeaddress(c.getString(c.getColumnIndex("CONSIGNEEADDRESS")));
            order.setConsigneephone(c.getString(c.getColumnIndex("CONSIGNEEPHONE")));
            order.setPaymenttype(c.getString(c.getColumnIndex("PAYMENTTYLE")));
            order.setOrderdate(c.getString(c.getColumnIndex("ORDERDATE")));
            order.setOrderstate(c.getString(c.getColumnIndex("ORDERSTATE")));
            order.setProductimg(c.getString(c.getColumnIndex("PRODUCTIMG")));
            order.setProductname(c.getString(c.getColumnIndex("PRODUCTNAME")));
            order.setProductprice(c.getString(c.getColumnIndex("PRODUCTPRICE")));
            order.setProductnum(c.getString(c.getColumnIndex("PRODUCTNUM")));
            list.add(order);
        }
        c.close();
        close();
        return list;
    }

//    public List<Order> queryOrderByUserid(String userId) {
//        System.out.println("#SU DB# queryOrderByUserid");
//        List<Order> list = new ArrayList<Order>();
//        Order order = new Order();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] param = { userId };
//        Cursor c = db.rawQuery("SELECT * FROM MYORDER WHERE USERID = ?", param);
//        while (c.moveToNext()) {
//            order = new Order();
//          order.setId(c.getString(c.getColumnIndex("ID")));
//          order.setOrderid(c.getString(c.getColumnIndex("ORDERID")));
//          order.setConsigneerealname(c.getString(c.getColumnIndex("CONSIGNEEREALNAME")));
//          order.setConsigneeaddress(c.getString(c.getColumnIndex("CONSIGNEEADDRESS")));
//          order.setConsigneephone(c.getString(c.getColumnIndex("CONSIGNEEPHONE")));
//          order.setPaymenttype(c.getString(c.getColumnIndex("PAYMENTTYLE")));
//          order.setOrderdate(c.getString(c.getColumnIndex("ORDERDATE")));
//          order.setOrderstate(c.getString(c.getColumnIndex("ORDERSTATE")));
//          order.setProductimg(c.getString(c.getColumnIndex("PRODUCTIMG")));
//          order.setProductname(c.getString(c.getColumnIndex("PRODUCTNAME")));
//          order.setProductprice(c.getString(c.getColumnIndex("PRODUCTPRICE")));
//          order.setProductnum(c.getString(c.getColumnIndex("PRODUCTNUM")));
//            list.add(order);
//        }
//        c.close();
//        close();
//        return list;
//    }
    
    
    //
    // public void updateOrder(String OrderName, String StartDate) {
    // ContentValues cv = new ContentValues();
    // cv.put("STATUS", "1");
    // SQLiteDatabase db = getWritableDatabase();
    // db.update("ORDER", cv, "OrderName=? AND StartDate=?", new String[] {
    // OrderName, StartDate });
    // close();
    // }

    public int deleteOrderById(String orderId) {
        System.out.println("#SU DB# deleteOrderById");
        String[] param = { orderId };
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("MYORDER", "ID=? ", param);
    }

    public int clearAllOrder() {
        System.out.println("#SU DB# clearAllOrder");
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("MYORDER", null, null);
    }

    /**
     * 购物车
     * 
     * @param cart
     */

    public void insertCart(Cart cart) {
        System.out.println("#SU DB# insertCart");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("IMAGE", cart.getImage());
        values.put("ID", cart.getId());
        values.put("NAME", cart.getName());
        values.put("PRICE", cart.getPrice());
        values.put("QUANTITY", cart.getQuantity());
        values.put("TOTALPRICE", cart.getTotalprice());
        values.put("SHOPID", cart.getShopid());
        db.insert("CART", "", values);
        close();
    }
 

    public void insCartList(List<Cart> data) {
        System.out.println("#SU DB# insCartList");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("IMAGE", data.get(i).getImage());
            values.put("ID", data.get(i).getId());
            values.put("NAME", data.get(i).getName());
            values.put("PRICE", data.get(i).getPrice());
            values.put("QUANTITY", data.get(i).getQuantity());
            values.put("TOTALPRICE", data.get(i).getTotalprice());
            values.put("SHOPID", data.get(i).getShopid());
            db.insert("CART", "", values);

        }
        close();
    }

    
    public Cart queryCartById(String cartId) {
        System.out.println("#SU DB# queryCartById");
        Cart cart = new Cart();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = { cartId };
        Cursor c = db.rawQuery("SELECT * FROM CART WHERE ID = ?", param);
        while (c.moveToNext()) {
            cart = new Cart();
            cart.setImage(c.getString(c.getColumnIndex("IMAGE")));
            cart.setId(c.getString(c.getColumnIndex("ID")));
            cart.setName(c.getString(c.getColumnIndex("NAME")));
            cart.setPrice(c.getString(c.getColumnIndex("PRICE")));
            cart.setQuantity(c.getString(c.getColumnIndex("QUANTITY")));
            cart.setTotalprice(c.getString(c.getColumnIndex("TOTALPRICE")));
            cart.setShopid(c.getString(c.getColumnIndex("SHOPID")));
        }
        c.close();
        close();
        return cart;
    }

    public List<Cart> queryCart() {
        System.out.println("#SU DB# queryCart");
        List<Cart> list = new ArrayList<Cart>();
        Cart cart = new Cart();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM CART", null);
        while (c.moveToNext()) {
            cart = new Cart();
            cart.setImage(c.getString(c.getColumnIndex("IMAGE")));
            cart.setId(c.getString(c.getColumnIndex("ID")));
            cart.setName(c.getString(c.getColumnIndex("NAME")));
            cart.setPrice(c.getString(c.getColumnIndex("PRICE")));
            cart.setQuantity(c.getString(c.getColumnIndex("QUANTITY")));
            cart.setTotalprice(c.getString(c.getColumnIndex("TOTALPRICE")));
            cart.setShopid(c.getString(c.getColumnIndex("SHOPID")));
            list.add(cart);
        }
        c.close();
        close();
        return list;
    }

//    public List<Cart> queryCartByUserid(String userId) {
//        System.out.println("#SU DB# queryCartByUserid");
//        List<Cart> list = new ArrayList<Cart>();
//        Cart cart = new Cart();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] param = { userId };
//        Cursor c = db.rawQuery("SELECT * FROM CART WHERE USERID = ?", param);
//        while (c.moveToNext()) {
//            cart = new Cart();
//            cart.setImage(c.getString(c.getColumnIndex("IMAGE")));
//            cart.setId(c.getString(c.getColumnIndex("ID")));
//            cart.setName(c.getString(c.getColumnIndex("NAME")));
//            cart.setPrice(c.getString(c.getColumnIndex("PRICE")));
//            cart.setQuantity(c.getString(c.getColumnIndex("QUANTITY")));
//            cart.setTotalprice(c.getString(c.getColumnIndex("TOTALPRICE")));
//            list.add(cart);
//        }
//        c.close();
//        close();
//        return list;
//    }
    
    
    // public void updateCart(String CartName, String StartDate) {
    // ContentValues cv = new ContentValues();
    // cv.put("STATUS", "1");
    // SQLiteDatabase db = getWritableDatabase();
    // db.update("CART", cv, "CartName=? AND StartDate=?", new String[] {
    // CartName, StartDate });
    // close();
    // }

    public int deleteCartById(String cartId) {
        System.out.println("#SU DB# deleteCartById");
        String[] param = { cartId };
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("CART", "ID=? ", param);
    }

    public int clearAllCart() {
        System.out.println("#SU DB# clearAllCart");
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("CART", null, null);
    }

    /**
     * 类别
     * 
     * @param classify
     */

    public void insertClassify(Classify classify) {
        System.out.println("#SU DB# insertClassify");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CLAID", classify.getClaid());
        values.put("NAME", classify.getName());
        values.put("TYPE", classify.getType());
        db.insert("CLASSIFY", "", values);
        close();
    }

    public Classify queryClassifyById(String classifyId) {
        System.out.println("#SU DB# queryClassifyById");
        Classify classify = new Classify();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = { classifyId };
        Cursor c = db.rawQuery("SELECT * FROM CLASSIFY WHERE ID = ?", param);
        while (c.moveToNext()) {
            classify = new Classify();
            classify.setClaid(c.getString(c.getColumnIndex("CLAID")));
            classify.setName(c.getString(c.getColumnIndex("NAME")));
            classify.setType(c.getString(c.getColumnIndex("TYPE")));
        }
        c.close();
        close();
        return classify;
    }

    public List<Classify> queryClassify() {
        System.out.println("#SU DB# queryClassify");
        List<Classify> list = new ArrayList<Classify>();
        Classify classify = new Classify();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM CLASSIFY", null);
        while (c.moveToNext()) {
            classify = new Classify();
            classify.setClaid(c.getString(c.getColumnIndex("CLAID")));
            classify.setName(c.getString(c.getColumnIndex("NAME")));
            classify.setType(c.getString(c.getColumnIndex("TYPE")));
            list.add(classify);
        }
        c.close();
        close();
        return list;
    }

    public int deleteClassifyById(String classifyId) {
        System.out.println("#SU DB# deleteClassifyById");
        String[] param = { classifyId };
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("CLASSIFY", "ID=? ", param);
    }

    public int clearAllClassify() {
        System.out.println("#SU DB# clearAllClassify");
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("CLASSIFY", null, null);
    }

    // 地址

    /**
     * 地址
     * 
     * @param address
     */

    public void insertAddress(Address address) {
        System.out.println("#SU DB# insertAddress");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ADDRESS", address.getAddress());
        values.put("CITY", address.getCity());
        values.put("ID", address.getId());
        values.put("MOBILE", address.getMobile());
        values.put("PROVINCE", address.getProvince());
        values.put("STAT", address.getStat());
        values.put("USERNAME", address.getUsername());
        db.insert("ADDRESS", "", values);
        close();
    }

    public void insAddressList(List<Address> data) {
        System.out.println("#SU DB# insAddressList");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("ADDRESS", data.get(i).getAddress());
            values.put("CITY", data.get(i).getCity());
            values.put("ID", data.get(i).getId());
            values.put("MOBILE", data.get(i).getMobile());
            values.put("PROVINCE", data.get(i).getProvince());
            values.put("STAT", data.get(i).getStat());
            values.put("USERNAME", data.get(i).getUsername());
            db.insert("ADDRESS", "", values);

        }
        close();
    }
    
    public Address queryAddressById(String addId) {
        System.out.println("#SU DB# queryAddressById");
        Address address = new Address();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = { addId };
        Cursor c = db.rawQuery("SELECT * FROM ADDRESS WHERE ID = ?", param);
        while (c.moveToNext()) {
            address = new Address();
            address.setAddress(c.getString(c.getColumnIndex("ADDRESS")));
            address.setCity(c.getString(c.getColumnIndex("CITY")));
            address.setId(c.getString(c.getColumnIndex("ID")));
            address.setMobile(c.getString(c.getColumnIndex("MOBILE")));
            address.setProvince(c.getString(c.getColumnIndex("PROVINCE")));
            address.setStat(c.getString(c.getColumnIndex("STAT")));
            address.setUsername(c.getString(c.getColumnIndex("USERNAME")));
        }
        c.close();
        close();
        return address;
    }

    public List<Address> queryAddress() {
        System.out.println("#SU DB# queryAddress");
        List<Address> list = new ArrayList<Address>();
        Address address = new Address();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ADDRESS", null);
        while (c.moveToNext()) {
            address = new Address();
            address.setAddress(c.getString(c.getColumnIndex("ADDRESS")));
            address.setCity(c.getString(c.getColumnIndex("CITY")));
            address.setId(c.getString(c.getColumnIndex("ID")));
            address.setMobile(c.getString(c.getColumnIndex("MOBILE")));
            address.setProvince(c.getString(c.getColumnIndex("PROVINCE")));
            address.setStat(c.getString(c.getColumnIndex("STAT")));
            address.setUsername(c.getString(c.getColumnIndex("USERNAME")));
            list.add(address);
        }
        c.close();
        close();
        return list;
    }

//    public List<Address> queryAddressByUserid(String userId) {
//        System.out.println("#SU DB# queryAddressByUserid");
//        List<Address> list = new ArrayList<Address>();
//        Address address = new Address();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] param = { userId };
//        Cursor c = db.rawQuery("SELECT * FROM ADDRESS WHERE USERID = ?", param);
//        while (c.moveToNext()) {
//            address = new Address();
//            address.setUserid(c.getString(c.getColumnIndex("USERID")));
//            address.setAddid(c.getString(c.getColumnIndex("ADDID")));
//            address.setIfdefault(c.getString(c.getColumnIndex("IFDEFAULT")));
//            address.setName(c.getString(c.getColumnIndex("NAME")));
//            address.setPhone(c.getString(c.getColumnIndex("PHONE")));
//            address.setAddinfo(c.getString(c.getColumnIndex("ADDINFO")));
//            list.add(address);
//        }
//        c.close();
//        close();
//        return list;
//    }
    
    
    //
    // public void updateAddress(String AddressName, String StartDate) {
    // ContentValues cv = new ContentValues();
    // cv.put("STATUS", "1");
    // SQLiteDatabase db = getWritableDatabase();
    // db.update("ADDRESS", cv, "AddressName=? AND StartDate=?", new String[] {
    // AddressName, StartDate });
    // close();
    // }

    public int deleteAddressById(String addId) {
        System.out.println("#SU DB# deleteAddressById");
        String[] param = { addId };
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("ADDRESS", "ID=? ", param);
    }

    public int clearAllAddress() {
        System.out.println("#SU DB# clearAllAddress");
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("ADDRESS", null, null);
    }

    // 区域

    /**
     * 区域
     * 
     * @param area
     */

    public void insertArea(Area area) {
        System.out.println("#SU DB# insertArea");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("AREAID", area.getAreaid());
        values.put("ADDRESS", area.getAddress());
        values.put("IMG", area.getImg());
        db.insert("AREA", "", values);
        close();
    }

    
    public void insAreaList(List<Area> data) {
        System.out.println("#SU DB# insAreaList");
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < data.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("AREAID", data.get(i).getAreaid());
            values.put("ADDRESS", data.get(i).getAddress());
            values.put("IMG", data.get(i).getImg());
            db.insert("AREA", "", values);

        }
        close();
    }

    
    
    public Area queryAreaById(String areaId) {
        System.out.println("#SU DB# queryAreaById");
        Area area = new Area();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = { areaId };
        Cursor c = db.rawQuery("SELECT * FROM AREA WHERE ID = ?", param);
        while (c.moveToNext()) {
            area = new Area();
            area.setAreaid(c.getString(c.getColumnIndex("AREAID")));
            area.setAddress(c.getString(c.getColumnIndex("ADDRESS")));
            area.setImg(c.getString(c.getColumnIndex("IMG")));
        }
        c.close();
        close();
        return area;
    }

    public List<Area> queryArea() {
        System.out.println("#SU DB# queryArea");
        List<Area> list = new ArrayList<Area>();
        Area area = new Area();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM AREA", null);
        while (c.moveToNext()) {
            area = new Area();
            area.setAreaid(c.getString(c.getColumnIndex("AREAID")));
            area.setAddress(c.getString(c.getColumnIndex("ADDRESS")));
            area.setImg(c.getString(c.getColumnIndex("IMG")));
            list.add(area);
        }
        c.close();
        close();
        return list;
    }

    public int deleteAreaById(String areaId) {
        System.out.println("#SU DB# deleteAreaById");
        String[] param = { areaId };
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("AREA", "ID=? ", param);
    }

    public int clearAllArea() {
        System.out.println("#SU DB# clearAllArea");
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("AREA", null, null);
    }

    // 视频

    /**
     * 视频
     * 
     * @param camera
     */

    public void insertCamera(Camera camera) {
        System.out.println("#SU DB# insertCamera");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("AREAID", camera.getAreaid());
        values.put("CAMERAID", camera.getCameraid());
        values.put("IMG", camera.getImg());
        values.put("NAME", camera.getName());
        db.insert("CAMERA", "", values);
        close();
    }

    public Camera queryCameraById(String cameraId) {
        System.out.println("#SU DB# queryCameraById");
        Camera camera = new Camera();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = { cameraId };
        Cursor c = db.rawQuery("SELECT * FROM CAMERA WHERE ID = ?", param);
        while (c.moveToNext()) {
            camera = new Camera();
            camera.setAreaid(c.getString(c.getColumnIndex("AREAID")));
            camera.setCameraid(c.getString(c.getColumnIndex("CAMERAID")));
            camera.setImg(c.getString(c.getColumnIndex("IMG")));
            camera.setName(c.getString(c.getColumnIndex("NAME")));
        }
        c.close();
        close();
        return camera;
    }

    public List<Camera> queryCamera() {
        System.out.println("#SU DB# queryCamera");
        List<Camera> list = new ArrayList<Camera>();
        Camera camera = new Camera();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM CAMERA", null);
        while (c.moveToNext()) {
            camera = new Camera();
            camera.setAreaid(c.getString(c.getColumnIndex("AREAID")));
            camera.setCameraid(c.getString(c.getColumnIndex("CAMERAID")));
            camera.setImg(c.getString(c.getColumnIndex("IMG")));
            camera.setName(c.getString(c.getColumnIndex("NAME")));
            list.add(camera);
        }
        c.close();
        close();
        return list;
    }

    public List<Camera> queryCameraByAreaid(String areaId) {
        System.out.println("#SU DB# queryCameraByAreaid");
        List<Camera> list = new ArrayList<Camera>();
        Camera camera = new Camera();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = { areaId };
        Cursor c = db.rawQuery("SELECT * FROM ADDRESS WHERE USERID = ?", param);
        while (c.moveToNext()) {
            camera = new Camera();
            camera.setAreaid(c.getString(c.getColumnIndex("AREAID")));
            camera.setCameraid(c.getString(c.getColumnIndex("CAMERAID")));
            camera.setImg(c.getString(c.getColumnIndex("IMG")));
            camera.setName(c.getString(c.getColumnIndex("NAME")));
            list.add(camera);
        }
        c.close();
        close();
        return list;
    }

    public int deleteCameraById(String cameraId) {
        System.out.println("#SU DB# deleteCameraById");
        String[] param = { cameraId };
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("CAMERA", "ID=? ", param);
    }

    public int clearAllCamera() {
        System.out.println("#SU DB# clearAllCamera");
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("CAMERA", null, null);
    }

}
