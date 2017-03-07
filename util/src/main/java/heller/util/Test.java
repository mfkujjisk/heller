package heller.util;

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.*;
import java.util.*;

/**
 * Created by zhouzhanghe on 2017/1/18.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        List<String> files = new ArrayList<String>();
        files.add("d:/tmp/近1月下单用户1.csv");
        files.add("d:/tmp/近1月下单用户2.csv");
        files.add("d:/tmp/近1月下单用户3.csv");
        files.add("d:/tmp/近1月下单用户4.csv");

        File output = new File("d:/tmp/output.txt");
        BufferedWriter bw = new BufferedWriter((new FileWriter(output)));
        int count=0;
        int total = 0;
        for(String file : files){
            BufferedReader br = new BufferedReader(new FileReader(new File(file)));
            String line = br.readLine();
            while(line != null){
                String[] arry = line.split(",");
                if(arry.length == 3) {
                    String phone = arry[2];
                    bw.append(phone).append(",");
                    count++;
                    total++;
                }
                line = br.readLine();
                if(count == 20000){
                    bw.newLine();
                    count = 0;
                }
            }
        }
        bw.close();
        System.out.println(total);

    }
}
