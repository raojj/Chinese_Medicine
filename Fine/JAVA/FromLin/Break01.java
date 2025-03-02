
public class Break01 { 


	public static void main(String[] args) {
		for( int i = 0; i < 10; i++) {
			if( i == 3) {
				break ;//只是提前结束for循环
			}
			System.out.println("i=" + i);
		}

		System.out.println("退出for循环，继续执行");

	
	}
}