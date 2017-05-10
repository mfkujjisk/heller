import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by heller on 2017/3/31.
 */
public class Test2 {
    public static void main(String[] args) throws IOException {
        /*String str = "insert into cndcp_waybill_route(postal_code, route_desc) values('chile','chile desc')";

        StringBuilder sql = new StringBuilder("insert into cndcp_waybill_route\n" +
                "(gmt_create, gmt_modified, delivery_type, postal_code,country_code,route_id,route_code, route_desc, agency, is_deleted)\n" +
                "values");



        BufferedReader br = new BufferedReader(new InputStreamReader(Test2.class.getClassLoader().getResourceAsStream("1.txt"), "UTF-8"));
        String line = br.readLine();
        line = br.readLine();
        String result = "";
        int count = 0;
        BufferedWriter bw = new BufferedWriter(new FileWriter("./2.txt"));
        while (line != null) {
            String[] arry = line.split(";");
            line = br.readLine();

            if(count > 30000 && count <40000)
                sql.append("(now(),now(),'H','chile"+arry[0]+"','A','A','AAAAAAAAAA','chile desc','A','-1')").append(",");
            count ++;
        }
        System.out.println(count);




        bw.write(sql.toString());
        bw.close();*/

        splitDataToSaveFile(50000,"/Users/heller/Desktop/waybillchileconfig.txt", "/Users/heller/Desktop/chileconfig");

    }


    /**
     * 按行分割文件
     * @param rows 为多少行一个文件
     * @param sourceFilePath 为源文件路径
     * @param targetDirectoryPath 文件分割后存放的目标目录
     */
    public static void splitDataToSaveFile(int rows, String sourceFilePath,
                                    String targetDirectoryPath) {
        long start1 = System.currentTimeMillis();

        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetDirectoryPath);
        if (!sourceFile.exists() || rows <= 0 || sourceFile.isDirectory()) {
            return;
        }
        if (targetFile.exists()) {
            if (!targetFile.isDirectory()) {
                return;
            }
        } else {
            targetFile.mkdirs();
        }
        try {

            InputStreamReader in = new InputStreamReader(new FileInputStream(sourceFilePath),"UTF-8");
            BufferedReader br=new BufferedReader(in);

            BufferedWriter bw = null;
            String str = "";
            String tempData = br.readLine();
            int i = 1, s = 0;
            long start2 = System.currentTimeMillis();
            while (tempData != null) {
                str += tempData + "\r\n";
                if (i % rows == 0) {
                    bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                            targetFile.getAbsolutePath() + "/" +  sourceFile.getName() +"_" + (s+1) +".txt"), "UTF-8"),1024);

                    bw.write(str);
                    bw.close();

                    str = "";
                    start2 = System.currentTimeMillis();
                    s += 1;
                }
                i++;
                tempData = br.readLine();
            }
            if ((i - 1) % rows != 0) {

                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                        targetFile.getAbsolutePath() + "/" +  sourceFile.getName() +"_" + (s+1) +".txt"), "UTF-8"),1024);
                bw.write(str);
                bw.close();
                br.close();

                s += 1;
            }
            in.close();

        } catch (Exception e) {
        }
    }
}
