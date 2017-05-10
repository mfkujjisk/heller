package heller.javabasic;

/**
 * @author <a href="mailto:zhanghe.zzh@alibaba-inc.com">zhanghe.zzh</a>
 * @since 2017/4/17.
 */
public class Test {
    public static void main(String[] args) {
        String serviceTags = "5000000011398-502$%";
        String serviceItemId = serviceTags.substring(0, serviceTags.indexOf("-"));
        System.out.println(serviceItemId);
    }
}
