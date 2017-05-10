package heller.javabasic.compiler;

import javax.tools.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * @author <a href="mailto:zhanghe.zzh@alibaba-inc.com">zhanghe.zzh</a>
 * @since 2017/4/17.
 */
public class CompilterTest {
    public static void main(String[] args) throws Exception {
        String javaSource = " public class TestMain{public TestMain(){} public static void main(String[] args){System.out.println(\"hi\");} public void say(String words){System.out.println(words);}}";

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager javaFileManager = compiler.getStandardFileManager(null, null, null);
        StringSourceJavaObject sourceJavaObject = new StringSourceJavaObject("TestMain", javaSource);
        Iterable fileObjects = Arrays.asList(new Object[]{sourceJavaObject});
        Iterable options = Arrays.asList("-d", "/Users/heller");
        JavaCompiler.CompilationTask task = compiler.getTask(null, javaFileManager, null, options, null, fileObjects);
        Boolean result = task.call();
        if (result.booleanValue()) {
            System.out.println("编译成功！");
            HellerClassLoader hellerClassLoader = new HellerClassLoader();
            Class c = hellerClassLoader.findClass("/Users/heller/TestMain.class");
            Object o = c.newInstance();
            Method m = c.getMethod("main", new Class[]{String[].class});
            m.invoke(o, new Object[]{new String[]{}});//调用main函数

            m = c.getDeclaredMethod("say", new Class[]{String.class});
            Object param = "a";
            m.invoke(o, new Object[]{param});//调用自定义函数
        }
    }

    static class StringSourceJavaObject extends SimpleJavaFileObject {
        private String content = null;

        public StringSourceJavaObject(String name, String content) throws URISyntaxException {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return content;
        }
    }
}