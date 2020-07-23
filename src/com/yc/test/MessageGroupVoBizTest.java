package com.yc.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yc.biz.GroupUserBiz;
import com.yc.biz.MessageBiz;
import com.yc.biz.MessageGroupVoBiz;

public class MessageGroupVoBizTest {
	MessageGroupVoBiz biz = new MessageGroupVoBiz();
	GroupUserBiz bbiz = new GroupUserBiz();
	@Test
	public void test() {
		try {
			System.out.println(biz.findMessageByGroup("00000000"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test01() {
		try {
			System.out.println(bbiz.findByGroupno("00000000"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public  void test02(){
		System.out.println("".equals(""));
	}
}
