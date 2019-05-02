package com.channelsoft.script;

import javax.script.*;

public class App {
    public static void main(String[] args) throws ScriptException {
        //获取脚本引擎
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Compilable compilable = (Compilable) engine;
        //绑定数据,注意这里是a and b 绑定的是调用时参数, 而不是p1 and p2
        Bindings bindings = engine.createBindings();
        bindings.put("a", 1);
        bindings.put("b", 2);
        //编译成java代码
        CompiledScript function = compilable.compile("function route(p1,p2){return p1+p2} route(a,b)");
        //执行代码
        Object res = function.eval(bindings);
        System.out.println(res);
    }
}