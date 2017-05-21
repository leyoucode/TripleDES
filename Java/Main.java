public class Main 
{
	public static void main(String[] args) 
	{
		TripleDES des = new TripleDES();
		try {
			String s1 = des.encryptThreeDESECB("测试加密", TripleDES.KEY);
			System.out.println(s1);
			String s2 = des.decryptThreeDESECB(s1, TripleDES.KEY);
			System.out.println(s2);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}