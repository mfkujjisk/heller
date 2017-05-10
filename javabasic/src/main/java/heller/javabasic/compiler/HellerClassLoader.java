package heller.javabasic.compiler;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author <a href="mailto:zhanghe.zzh@alibaba-inc.com">zhanghe.zzh</a>
 * @since 2017/4/17.
 */
public class HellerClassLoader extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        return defineClass(null, classData, 0, classData.length);
    }

    private byte[] loadClassData(String classPath){
        try {
            InputStream ins = new FileInputStream(classPath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();;
        }
        return null;
    }
}
