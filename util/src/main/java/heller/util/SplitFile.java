package heller.util;

import sun.misc.CharacterEncoder;

import java.io.*;

/**
 * @author <a href="mailto:zhanghe.zzh@alibaba-inc.com">zhanghe.zzh</a>
 * @since 2017/4/6.
 */
public class SplitFile {
    /**
     * 按行分割文件
     * @param rows 为多少行一个文件
     * @param sourceFilePath 为源文件路径
     * @param targetDirectoryPath 文件分割后存放的目标目录
     * @param charsetName 文件编码
     */
    public static void splitDataToSaveFile(int rows, String sourceFilePath,
                                           String targetDirectoryPath, String charsetName) {
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

            InputStreamReader in = new InputStreamReader(new FileInputStream(sourceFilePath), charsetName);
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
                            targetFile.getAbsolutePath() + "/" +  sourceFile.getName() +"_" + (s+1) +".txt"), charsetName),1024);

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
                        targetFile.getAbsolutePath() + "/" +  sourceFile.getName() +"_" + (s+1) +".txt"), charsetName),1024);
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
