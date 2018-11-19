package com.resthome.interceptor;



import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class StrutsInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 8430609013830925045L;

	@Override
	public void destroy() {
		super.destroy();
	}


	@Override
	public void init() {
		super.init();
	}


	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("********************进入拦截器*****************");
		System.out.println("调用 的action:"+invocation.getProxy().getAction().getClass().getName());
		System.out.println("调用的方法："+invocation.getProxy().getMethod());
		System.out.println("********************走出拦截器*****************");
		return invocation.invoke();
	}

}
