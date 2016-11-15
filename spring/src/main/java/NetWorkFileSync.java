import heller.util.MD5Util;
import org.springframework.util.FileCopyUtils;
import sun.security.provider.MD5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网络文件同步工具
 * Created by zhouzhanghe on 2016/11/11.
 */
public class NetWorkFileSync {

    public static void main(String[] args) throws IOException {


        List<File> sourceFiles = getFiles(new File("d:/网盘同步"));
        copyFiles(sourceFiles,"z");
        //sycDelFile(destinationFiles,"d");
    }

    static void sycDelFile(List<File> sourceFiles, String destinationDiskName) {
        if(sourceFiles == null)
            return;
        for(File file : sourceFiles){
            String sourceRelativePath = file.getPath().substring(file.getPath().indexOf(":")+1);

            File destinationFile = new File(destinationDiskName+":"+sourceRelativePath);

            boolean del = false;
            if(!destinationFile.exists()) {
                del = true;
            }

            if(del){
                try {
                    file.delete();
                    System.out.println("del file " + file.getAbsolutePath());
                } catch (Exception e) {
                    System.out.println("fail to del file: " + file.getAbsolutePath());
                    e.printStackTrace();
                }
            }
        }
    }

    static void copyFiles(List<File> sourceFiles, String destinationDiskName) {
        if(sourceFiles == null)
            return;
        for(File file : sourceFiles){
            String sourceRelativePath = file.getPath().substring(file.getPath().indexOf(":")+1);

            File destinationFile = new File(destinationDiskName+":"+sourceRelativePath);

            boolean copy = false;
            if(destinationFile.exists()){
                if(destinationFile.lastModified() < file.lastModified() && destinationFile.length() != file.length())
                    copy = true;
            }else{
                destinationFile.getParentFile().mkdirs();
                copy = true;
            }

            if(copy){
                try {
                    FileCopyUtils.copy(file, destinationFile);
                    System.out.println("copy file["+file.getAbsolutePath()+"] to " + destinationFile.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println("fail to copy file to the path: " + destinationFile.getAbsolutePath());
                    e.printStackTrace();
                }
            }
        }
    }


    static void printFiles(List<File> files){
        for(File file : files){
            System.out.println(file.getAbsolutePath()+File.separator+file.getName());
        }
    }


    static List<File> getFiles(File file){
        if(file.exists()){
            List<File> arryFile = new ArrayList<File>();
            if(file.isDirectory()){
                for(File subFile : file.listFiles()) {
                    arryFile.addAll(getFiles(subFile));
                }
            }else{
                arryFile.add(file);
            }
            return arryFile;
        }else {
            return null;
        }
    }
}