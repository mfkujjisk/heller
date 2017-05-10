package heller.http;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
/**
 * @author <a href="mailto:zhanghe.zzh@alibaba-inc.com">zhanghe.zzh</a>
 * @since 2017/5/10.
 */
public class HttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            //用户code，使用时候替换一下
            String myCookie = "";//"UM_distinctid=15bd37d1c09915-027e6342162edf-143d655c-1fa400-15bd37d1c0a38d; JSESSIONID=FF6YN3RSQ2-NLIKP26LRNFHS0GB26ZQ2-Y25VAI2J-73; cndcpconsole_USER_COOKIE=32EFACF36192FD4DD618520EE95D4485E324F6D900370B9C506A77631890A32380ED3DB1FE8FE773D6A94B724AB2FCF37D94E2A1C3E1466CEA3D8D65FFE20ED77239A1ED2903FD996983BC4832D565EC2AE1DD9E7E4E9C05255EA95A19B9A3822127D78E0073926460A8DB7FF6610F1A00C6524F980B66BF61366D2BD6371F9603C7AAB680A6DD966466EB3A8D0FECC2ADC2D8AEB6B808643D950B58D0ED3186A1530867AC9DC310A8F198B34E72E128CA4EB258EAB4673D51F54F15012E66A898306790105602775B03B88D827BDD7A; cainiaouser_USER_COOKIE=4C16118E91500F9FFFC7B82FB749BC6552F8BF07ABC3BDD00320F87C836455AB8EB7115373D321306367DADB188B341DE65607854C2AC5D806757581DF3B6CA2A10F37CD81B800460D5716631550150DA0DCC531FA48AE3E1490352BF053A4B032D62A7CA890F0E1CAE94248471244429CDF01327C68DD9FD22A7EDFB29CDCDE6D7AB2B46D938845131E3987DBDFB18E928ACDB8B38828339AC0D34C464C962364EEB2E56B0303B37CBC53184CFF19596E78C1837584AB0104F12C09826ABE8EEF719C554D0D579444C17343FE52CADBD61A4D4351BC9C8D732DFE683043A24C063F839DAB32BE0F14B3233222704146690AC20CFD2C43E39CDC6B2ADA19A125499EBFD0376A938A7BA95E9B8A35D7435372CB389667ABAD930093B2745CB4ED550554FBBCEFEAA069D6C484E61AF80506AC4E26824E69F47C4C8061DE3A378B67FED5FB7CB7C5450A88971F68FAD9C64C1B9EBF8D9A07ADE8276035CA547746ED2FE5BC015AD2178F98F204874D4AC9; cainiaouser_SSO_TOKEN=3E81B0C63FAF6B4A2C9B220BAA637D9E1A701D1406F314515EC1859A08BAEAF108E7882A9C6546992CD3707DCC649BB4; cainiaouser_LAST_HEART_BEAT_TIME=BF9980473BBBC520E6DFAA31DA370200; _temp0=Rhy3LBe9ogQDFblC73j1FrU%2B4yaTPYvmDJ6zsATF%2F%2BFzCssuzBoGcpun6Eo2Nwo%2BYTqCEc70VrCNo9QK2IaM%2FWVu2PhAcRJVjLFpLC%2FXJmEm9zw%2FAcHdFdCm2sHY05RCqET6VTg%2BtBVNTI0PvSh%2BwH8N4HkM6KjHogLqcyhR4kPaQ5%2BGqriCm%2FnpooECm3%2BR; _login_user0=Pw%2FJJa0KVAjBUmrD3NY72Dal21u61gunTboEGEGrWULyCCsyCFfHOaSThGPH6yHYF4yb%2FxZwEWwnY4dFS8b%2FrgyZshunEBmmhJ%2FWEPKNLC%2Bn0QRzvedPvpxppa3uj%2FbSTOZvGY0OsqUvIU8ytaSeZWuu%2F4C6juFEhojS6DQ%2FYwLPOpM8nvFaUvRw63S6hvtkNbi7BcDof8N84sBI3Jx2Lg%3D%3D; cna=I71HEZf30ygCASp4SsgQI1/1; cndcpconsole_SSO_TOKEN=D2259D7B63E221E7AC64C37F1DC65E911782D319E3BBDF4DC86924F7D127BCBC9B98CEB5FEDB148B07C4CB1DD842E86A; cndcpconsole_LAST_HEART_BEAT_TIME=EF34598B620DAB95A304415184AA8EC3; l=Ag4agukWoC65/UuyecVzkypq3u7ET9JO; isg=AvXh9vsnwKY1tyXK9YYu0r7WBHcU4aTYDsmc2ncOcWx8TiJAesONVHAI7qUC";
            conn.setRequestProperty("Cookie", myCookie);
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //发送 POST 请求
        String sr=HttpRequest.sendPost("http://dcpconsole.cainiao-inc.com/gsdp/reActivityRpc/doResend.json", "lgOrders=LP00073761466811,LP000737614668113\n" +
                "&activities=PICKUP_ORDER_INFO_NOTIFY\n" +
                "&select_type=1\n" +
                "&needUpdateState=false\n");
        System.out.println(sr);
        JSONObject jsonObject = JSON.parseObject(sr);
        String success = jsonObject.getString("success");
        String failedLp = jsonObject.getJSONObject("paramMap").getString("failedLp");
        System.out.println("sucess="+success);
        System.out.println("failedLp="+ failedLp);
    }
}
