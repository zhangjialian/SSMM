package com.cherrycc.template.test;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author BG349176
 * @date 2019/1/8 11:13
 */
public class ProxyTest {

    @Test
    public void test1(){
        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Subject subject = new PrintProxy(new RealSubject()).getProxy();
        subject.doSomething();
        subject.saySomething();
    }

    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Subject subject = new PrintProxy(new RealSubject()).getProxy();
        subject.saySomething();
    }

}

interface Subject{
    void doSomething();

    void saySomething();
}

class RealSubject implements Subject{

    @Override
    public void doSomething() {
        System.out.println("===doSomething, " + this.getClass().getName());
    }

    @Override
    public void saySomething() {
        System.out.println("===saySomething, " + this.getClass().getName());
        this.extend();
    }

    private void extend(){
        System.out.println("===extend, " + this.getClass().getName());
    }
}

@Getter
@Setter
class PrintProxy implements InvocationHandler {

    private Object object;

    public PrintProxy(Object object) {
        this.object = object;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("===PrintProxy, before");
        Object result = method.invoke(object, args);
        System.out.println("===PrintProxy, after");

        return result;
    }
}
