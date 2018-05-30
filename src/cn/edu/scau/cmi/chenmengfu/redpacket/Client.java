package cn.edu.scau.cmi.chenmengfu.redpacket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Client client = (Client) context.getBean("client");
		System.out.println(client);
//		context.
	}
}
