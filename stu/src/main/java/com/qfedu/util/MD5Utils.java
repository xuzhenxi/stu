package com.qfedu.util;


import org.apache.shiro.crypto.hash.Md5Hash;

// md5 hash算法
// 通过hash算法可以生成一个固定长度的数据，(比如，md5可以生成一个32位的16进制的字符串)
// 不同的数据，经过hash后，得到相同值的概率非常小（碰撞）
public class MD5Utils {
	
  public static String getMd5(String password, int count) {
	  	Md5Hash md5 = new Md5Hash(password, null, 1);
		return md5.toString();
  }

}
