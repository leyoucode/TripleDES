import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class TripleDES {
	
	public static final String KEY = "0123456789qwertyuiopasdf";

	//3DESECB加密,key必须是长度大于等于 3*8 = 24 位哈
	public String encryptThreeDESECB(String src,String key) throws Exception
	{
	  DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
	  SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
	  SecretKey securekey = keyFactory.generateSecret(dks);
	  
	  Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
	  cipher.init(Cipher.ENCRYPT_MODE, securekey);
	  byte[] b=cipher.doFinal(src.getBytes());
	  
	  //BASE64Encoder encoder = new BASE64Encoder();
	  return Base64.encode(b).replaceAll("\r", "").replaceAll("\n", "");
	  //return encoder.encode(b).replaceAll("\r", "").replaceAll("\n", "");
	}

	//3DESECB解密,key必须是长度大于等于 3*8 = 24 位哈
	public String decryptThreeDESECB(String src,String key) throws Exception
	{
	  //--通过base64,将字符串转成byte数组
	  //BASE64Decoder decoder = new BASE64Decoder();
	  byte[] bytesrc = Base64.decode(src);//decoder.decodeBuffer(src);
	  //--解密的key
	  DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
	  SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
	  SecretKey securekey = keyFactory.generateSecret(dks);
	  
	  //--Chipher对象解密
	  Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");  
	  cipher.init(Cipher.DECRYPT_MODE, securekey);  
	  byte[] retByte = cipher.doFinal(bytesrc);  
	        
	  return new String(retByte);
	}

}
