package com.abroad.uc.tools;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.abroad.uc.vo.UserVO;

/**
* @ClassName: PasswordHelper
* @Description: TODO(密码加密工具类)
* @author: mengxr
* @date 2017年3月31日 上午10:22:00
*/
public class PasswordHelper {
	
	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static String algorithmName = "md5";
	private static final int hashIterations = 2;
	/**
	* @Title: encryptPassword
	* @Description: TODO(加密密码和设置Salt)
	* @param @param user    设定文件
	* @return void    返回类型
	* @author: mengxr
	* @date 2017年3月31日 上午10:38:08
	* @throws
	*/
	public static void encryptPassword(UserVO user){
		String salt = randomNumberGenerator.nextBytes().toHex();
		user.setSalt(salt);
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(salt), hashIterations).toHex();
		user.setPassword(newPassword);
	}
}
