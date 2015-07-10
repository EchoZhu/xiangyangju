package com.zykj.landous;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

/**
 * @author 作者 zyk
 * @version 创建时间：2015年1月5日 上午10:05:22 类说明
 */
public class LandousAppConst {
	/**
	 * 软件版本名称
	 */
	public static final String app_version_name="1.0";
	/**
	 * 软件版本号
	 */
	public static final int app_version_code=1;
	
	
//	public static final String SERVER_URL = "http://112.53.78.18:8088/appif/";
//	public static final String SERVER_URL_Test="http://api.landous.com/";
//	public static final String IMG_URL = "http://112.53.78.18:8088/data/upload/";
	
	public static final String SERVER_URL = "http://app.xingyangju.com/appif";
//	public static final String SERVER_URL_Test="http://api.landous.com/";
	public static final String IMG_URL = "http://app.xiangyangju.com/data/upload/";
	/**
	 * 商品图片
	 */
//	public static final String HOME_IMG_URL = "http://img.landous.com/shop/store/goods/";
	public static final String HOME_IMG_URL = "http://192.168.1.145/data/upload/shop/store/goods/";
	/**
	 * 商铺店铺图片连接头
	 */
//	public static final String SHOP_IMG_URL = "http://img.landous.com/shop/store/";
	public static final String SHOP_IMG_URL = "http://121.199.27.159:80/shop/store/";
	/**
	 * 积分中心图片链接头
	 */
//	public static final String PointsGoods_IMG_URL = "http://112.53.78.18:8088/data/upload/shop/pointprod/";
	public static final String PointsGoods_IMG_URL = "http://121.199.27.159:80/data/upload/shop/pointprod/";
	/**
	 * 手机wap url头
	 */
//	public static final String url="http://wap.landous.com/tmpl/product_detail.html?goods_id=";
	public static final String url="http://wap.landous.com/tmpl/product_detail.html?goods_id=";
	/**
	 * 测试服务器头像链接头
	 */
//	public static final String avatar_url_head_test="http://112.53.78.18:8088/data/upload/shop/avatar/";
	public static final String avatar_url_head_test="http://121.199.27.159:80/data/upload/shop/avatar/";
	/**
	 * 正式服务器头像链接头
	 */
//	public static final String avatar_url_head="http://img.landous.com/shop/avatar/";
	public static final String avatar_url_head="http://121.199.27.159:80/shop/avatar/";
	/**
	 * 测试服务器专题图片链接头
	 */
//	public static final String special_img_head1="http://112.53.78.18:8088/data/upload/cms/special/";
	public static final String special_img_head1="http://192.168.1.145:80/data/upload/cms/special/";
	/**
	 * 正式服务器专题图片链接头
	 */
//	public static final String special_img_head="http://img.landous.com/cms/special/";
	public static final String special_img_head="http://192.168.1.145/data/upload/cms/special/";

	
	/**
	 * 首页轮播图
	 */
	public static List<String> imageUrls =new ArrayList<String>();
	public static JSONArray array= new JSONArray();
	/**
	 * 活动-图片
	 * 体验馆-图片
	 */
//	public static final String act_img_head="http://192.168.1.145/data/upload/shop/store/goods/1/";
	public static final String act_img_head="http://app.xiangyangju.com/data/upload/shop/store/goods/1/";
	

}
