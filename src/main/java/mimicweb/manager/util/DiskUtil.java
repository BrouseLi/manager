package mimicweb.manager.util;


import java.io.File;

public class DiskUtil {
    public static long free;
    public static long total;
    public static void initDiskFree(){
        File[] roots = File.listRoots();//获取磁盘分区列表
        for (File file : roots) {
            free+=file.getFreeSpace();
            total+=file.getTotalSpace();
        }
    }
}
