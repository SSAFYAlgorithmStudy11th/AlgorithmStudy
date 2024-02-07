import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int p =0;
		int m = 0;
		boolean f = true;
		String str = br.readLine();
		int num = 0;
		
		for(int i=0;i<str.length();i++) {
			char s = str.charAt(i);
			
			if(s == '-') {
				
				if(f) {
					p+=num;
				}
				else {
					m+=num;
				}
				f = false;
				num=0;
			}
			else if(s== '+') {
				if(f) {
					p+=num;
				}
				else {
					m+=num;
				}
				num=0;
				
			}
			else {
				num*=10;
				num+=Integer.parseInt(String.valueOf(s));
			}
		}
		
		if(f) {
			p+=num;
		}
		else {
			m+=num;
		}
		
		System.out.println(p-m);
		

	}

}
