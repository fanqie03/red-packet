package cn.edu.scau.cmi.chenmengfu.redpacket;

interface TStudent{
	void test() throws Exception;
	void test1();
}
class Main implements TStudent{
	public static void main(String[] args) {
		TStudent main = new Main();
		main.test1();
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void test1() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
}