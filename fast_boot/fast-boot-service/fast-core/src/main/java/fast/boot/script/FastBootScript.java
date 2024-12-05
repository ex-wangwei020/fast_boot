package fast.boot.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 利用javax.script在java中执行script脚本
 */
public class FastBootScript {

    private ScriptEngine engine;


    public void init() throws ScriptException {
        engine = new ScriptEngineManager().getEngineByName("js");
        engine.eval("function say(x, y) {return (x + y);}");
    }

    public Object executeScript(int x, int y) throws ScriptException {
        return  engine.eval("say("+ x +", "+ y +");");
    }

    public static void main(String[] args) throws ScriptException {
        FastBootScript f = new FastBootScript();
        f.init();
        Object v = f.executeScript(1 ,2);
        System.out.println(v);
        //转化为long
        System.out.println(Math.round((double)v));
    }
}
