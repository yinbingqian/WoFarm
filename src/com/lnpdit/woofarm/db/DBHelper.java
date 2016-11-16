package com.lnpdit.woofarm.db;

import java.util.ArrayList;
import java.util.List;

import com.lnpdit.woofarm.entity.Address;
import com.lnpdit.woofarm.entity.Area;
import com.lnpdit.woofarm.entity.Camera;
import com.lnpdit.woofarm.entity.Cart;
import com.lnpdit.woofarm.entity.ChatMessage;
import com.lnpdit.woofarm.entity.LoginUser;
import com.lnpdit.woofarm.entity.Order;
import com.lnpdit.woofarm.entity.Product;
import com.lnpdit.woofarm.entity.Classify;
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
    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE PRODUCT (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,PROID TEXT, NAME TEXT,  PRICE TEXT,UPTIME TEXT, PLACE TEXT,NUMBER TEXT,THUMB TEXT,PIC TEXT,IP TEXT,PORT TEXT,USERNAME TEXT,PASSWORD TEXT, CHNO TEXT)";

    private static final String CREATE_TABLE_MYORDER = "CREATE TABLE MYORDER (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, USERID TEXT, ORDERID TEXT, PROID TEXT,  TYPE TEXT,NAME TEXT, PRICE TEXT,THUMB TEXT,HJ TEXT,RESULT TEXT)";

    private static final String CREATE_TABLE_CART = "CREATE TABLE CART (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, USERID TEXT, CARTID TEXT, PROID TEXT,  NAME TEXT, PRICE TEXT,COUNT TEXT, THUMB TEXT,HJ TEXT)";

    private static final String CREATE_TABLE_CLASSIFY = "CREATE TABLE CLASSIFY (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,CLAID TEXT, NAME TEXT,  TYPE TEXT)";

    private static final String CREATE_TABLE_ADDRESS = "CREATE TABLE ADDRESS (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, USERID TEXT, ADDID TEXT, IFDEFAULT TEXT,  NAME TEXT, PHONE TEXT,ADDINFO TEXT)";

    private static final String CREATE_TABLE_AREA = "CREATE TABLE AREA (_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, AREAID TEXT, IMG TEXT,  NAME TEXT)";

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
        db.execSQL(CREATE_TABLE_PRODUCT);
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
        values.put("CHECKNUM", data.getchecknum());
        values.put("CHECKSTA", data.getchecksta());
        values.put("HEADPIC", data.getheadpic());
        values.put("ID", data.getid());
        values.put("LEVEL", data.getlevel());
        values.put("MARK", data.getmark());
        values.put("NAME", data.getname());
        values.put("PRESTIGE", data.getprestige());
        values.put("RANK", data.getrank());
        values.put("REALNAME", data.getrealname());
        values.put("BIRTH", data.getbirth());
        values.put("SEX", data.getsex());
        db.insert("T_SU_USER", "", values);
        close();
    }

    public void updateUser(String loginUserId, String name, String data) {
        ContentValues cv = new ContentValues();
        cv.put(name, data);
        SQLiteDatabase db = getWritableDatabase();
        db.update("T_SU_USER", cv, "ID=?", new String[] { loginUserId });
        close();
    }

    public List<UserInfo> queryUserInfo() {
        System.out.println("#SU DB# queryAdviserInfo");
        List<UserInfo> list = new ArrayList<UserInfo>();
        UserInfo user = new UserInfo();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM T_SU_USER", null);
        while (c.moveToNext()) {
            user = new UserInfo();
            user.setchecknum(c.getString(c.getColumnIndex("CHECKNUM")));
            user.setchecksta(c.getString(c.getColumnIndex("CHECKSTA")));
            user.setheadpic(c.getString(c.getColumnIndex("HEADPIC")));
            user.setid(c.getString(c.getColumnIndex("ID")));
            user.setlevel(c.getString(c.getColumnIndex("LEVEL")));
            user.setmark(c.getString(c.getColumnIndex("MARK")));
            user.setname(c.getString(c.getColumnIndex("NAME")));
            user.setprestige(c.getString(c.getColumnIndex("PRESTIGE")));
            user.setrank(c.getString(c.getColumnIndex("RANK")));
            user.setrealname(c.getString(c.getColumnIndex("REALNAME")));
            user.setsex(c.getString(c.getColumnIndex("SEX")));
            user.setbirth(c.getString(c.getColumnIndex("BIRTH")));
            list.add(user);
        }
        close();
        return list;
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
        db.delete("T_SU_USER", null, null);
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
    //
    // public void updateProduct(String ProductName, String StartDate) {
    // ContentValues cv = new ContentValues();
    // cv.put("STATUS", "1");
    // SQLiteDatabase db = getWritableDatabase();
    // db.update("PRODUCT", cv, "ProductName=? AND StartDate=?", new String[] {
    // ProductName, StartDate });
    // close();
    // }

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
     * 订单
     * 
     * @param order
     */

    public void insertOrder(Order order) {
        System.out.println("#SU DB# insertOrder");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("USERID", order.getUserid());
        values.put("ORDERID", order.getOrderid());
        values.put("PROID", order.getProid());
        values.put("TYPE", order.getType());
        values.put("NAME", order.getName());
        values.put("PRICE", order.getPrice());
        values.put("THUMB", order.getThumb());
        values.put("HJ", order.getHj());
        values.put("RESULT", order.getResult());
        db.insert("MYORDER", "", values);
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
            order.setUserid(c.getString(c.getColumnIndex("USERID")));
            order.setOrderid(c.getString(c.getColumnIndex("ORDERID")));
            order.setProid(c.getString(c.getColumnIndex("PROID")));
            order.setType(c.getString(c.getColumnIndex("TYPE")));
            order.setName(c.getString(c.getColumnIndex("NAME")));
            order.setPrice(c.getString(c.getColumnIndex("PRICE")));
            order.setThumb(c.getString(c.getColumnIndex("THUMB")));
            order.setHj(c.getString(c.getColumnIndex("HJ")));
            order.setResult(c.getString(c.getColumnIndex("RESULT")));
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
            order.setUserid(c.getString(c.getColumnIndex("USERID")));
            order.setOrderid(c.getString(c.getColumnIndex("ORDERID")));
            order.setProid(c.getString(c.getColumnIndex("PROID")));
            order.setType(c.getString(c.getColumnIndex("TYPE")));
            order.setName(c.getString(c.getColumnIndex("NAME")));
            order.setPrice(c.getString(c.getColumnIndex("PRICE")));
            order.setThumb(c.getString(c.getColumnIndex("THUMB")));
            order.setHj(c.getString(c.getColumnIndex("HJ")));
            order.setResult(c.getString(c.getColumnIndex("RESULT")));
            list.add(order);
        }
        c.close();
        close();
        return list;
    }

    public List<Order> queryOrderByUserid(String userId) {
        System.out.println("#SU DB# queryOrderByUserid");
        List<Order> list = new ArrayList<Order>();
        Order order = new Order();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = { userId };
        Cursor c = db.rawQuery("SELECT * FROM MYORDER WHERE USERID = ?", param);
        while (c.moveToNext()) {
            order = new Order();
            order.setUserid(c.getString(c.getColumnIndex("USERID")));
            order.setOrderid(c.getString(c.getColumnIndex("ORDERID")));
            order.setProid(c.getString(c.getColumnIndex("PROID")));
            order.setType(c.getString(c.getColumnIndex("TYPE")));
            order.setName(c.getString(c.getColumnIndex("NAME")));
            order.setPrice(c.getString(c.getColumnIndex("PRICE")));
            order.setThumb(c.getString(c.getColumnIndex("THUMB")));
            order.setHj(c.getString(c.getColumnIndex("HJ")));
            order.setResult(c.getString(c.getColumnIndex("RESULT")));
            list.add(order);
        }
        c.close();
        close();
        return list;
    }
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
        values.put("USERID", cart.getUserid());
        values.put("CARTID", cart.getCartid());
        values.put("PROID", cart.getProid());
        values.put("NAME", cart.getName());
        values.put("PRICE", cart.getPrice());
        values.put("THUMB", cart.getThumb());
        values.put("HJ", cart.getHj());
        values.put("COUNT", cart.getCount());
        db.insert("CART", "", values);
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
            cart.setUserid(c.getString(c.getColumnIndex("USERID")));
            cart.setCartid(c.getString(c.getColumnIndex("CARTID")));
            cart.setProid(c.getString(c.getColumnIndex("PROID")));
            cart.setCount(c.getString(c.getColumnIndex("COUNT")));
            cart.setName(c.getString(c.getColumnIndex("NAME")));
            cart.setPrice(c.getString(c.getColumnIndex("PRICE")));
            cart.setThumb(c.getString(c.getColumnIndex("THUMB")));
            cart.setHj(c.getString(c.getColumnIndex("HJ")));
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
            cart.setUserid(c.getString(c.getColumnIndex("USERID")));
            cart.setCartid(c.getString(c.getColumnIndex("CARTID")));
            cart.setProid(c.getString(c.getColumnIndex("PROID")));
            cart.setCount(c.getString(c.getColumnIndex("COUNT")));
            cart.setName(c.getString(c.getColumnIndex("NAME")));
            cart.setPrice(c.getString(c.getColumnIndex("PRICE")));
            cart.setThumb(c.getString(c.getColumnIndex("THUMB")));
            cart.setHj(c.getString(c.getColumnIndex("HJ")));
            list.add(cart);
        }
        c.close();
        close();
        return list;
    }

    public List<Cart> queryCartByUserid(String userId) {
        System.out.println("#SU DB# queryCartByUserid");
        List<Cart> list = new ArrayList<Cart>();
        Cart cart = new Cart();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = { userId };
        Cursor c = db.rawQuery("SELECT * FROM CART WHERE USERID = ?", param);
        while (c.moveToNext()) {
            cart = new Cart();
            cart.setUserid(c.getString(c.getColumnIndex("USERID")));
            cart.setCartid(c.getString(c.getColumnIndex("CARTID")));
            cart.setProid(c.getString(c.getColumnIndex("PROID")));
            cart.setCount(c.getString(c.getColumnIndex("COUNT")));
            cart.setName(c.getString(c.getColumnIndex("NAME")));
            cart.setPrice(c.getString(c.getColumnIndex("PRICE")));
            cart.setThumb(c.getString(c.getColumnIndex("THUMB")));
            cart.setHj(c.getString(c.getColumnIndex("HJ")));
            list.add(cart);
        }
        c.close();
        close();
        return list;
    }
    //
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
        values.put("USERID", address.getUserid());
        values.put("ADDID", address.getAddid());
        values.put("IFDEFAULT", address.getIfdefault());
        values.put("NAME", address.getName());
        values.put("PHONE", address.getPhone());
        values.put("ADDINFO", address.getAddinfo());
        db.insert("ADDRESS", "", values);
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
            address.setUserid(c.getString(c.getColumnIndex("USERID")));
            address.setAddid(c.getString(c.getColumnIndex("ADDID")));
            address.setIfdefault(c.getString(c.getColumnIndex("IFDEFAULT")));
            address.setName(c.getString(c.getColumnIndex("NAME")));
            address.setPhone(c.getString(c.getColumnIndex("PHONE")));
            address.setAddinfo(c.getString(c.getColumnIndex("ADDINFO")));
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
            address.setUserid(c.getString(c.getColumnIndex("USERID")));
            address.setAddid(c.getString(c.getColumnIndex("ADDID")));
            address.setIfdefault(c.getString(c.getColumnIndex("IFDEFAULT")));
            address.setName(c.getString(c.getColumnIndex("NAME")));
            address.setPhone(c.getString(c.getColumnIndex("PHONE")));
            address.setAddinfo(c.getString(c.getColumnIndex("ADDINFO")));
            list.add(address);
        }
        c.close();
        close();
        return list;
    }

    public List<Address> queryAddressByUserid(String userId) {
        System.out.println("#SU DB# queryAddressByUserid");
        List<Address> list = new ArrayList<Address>();
        Address address = new Address();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] param = { userId };
        Cursor c = db.rawQuery("SELECT * FROM ADDRESS WHERE USERID = ?", param);
        while (c.moveToNext()) {
            address = new Address();
            address.setUserid(c.getString(c.getColumnIndex("USERID")));
            address.setAddid(c.getString(c.getColumnIndex("ADDID")));
            address.setIfdefault(c.getString(c.getColumnIndex("IFDEFAULT")));
            address.setName(c.getString(c.getColumnIndex("NAME")));
            address.setPhone(c.getString(c.getColumnIndex("PHONE")));
            address.setAddinfo(c.getString(c.getColumnIndex("ADDINFO")));
            list.add(address);
        }
        c.close();
        close();
        return list;
    }
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
        values.put("IMG", area.getImg());
        values.put("NAME", area.getName());
        db.insert("AREA", "", values);
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
            area.setImg(c.getString(c.getColumnIndex("IMG")));
            area.setName(c.getString(c.getColumnIndex("NAME")));
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
            area.setImg(c.getString(c.getColumnIndex("IMG")));
            area.setName(c.getString(c.getColumnIndex("NAME")));
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
