package Organization.Ecommerce;

public class Test {

	public static void main(String[] args) {
		String name = "Pankaj Singh";
		int len = name.length();
		String s = Integer.toString(len);
		System.out.println(s);
		for (int i = len-1; i >= 0; i--) {
			System.out.print(name.charAt(i));
		}
	}

}
