package com.service.demo;

/**
 * Created by yezhangyuan on 2017-12-13.
 *
 * @author yezhangyuan
 */
public class B {

//	@Autowired
	private A a;

	public void setA(A a) {
		this.a = a;
	}

	public void run(){
		a.runA();
	}


}
