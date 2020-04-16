package mimicweb.manager.mapper.service;

public interface Gfs {
    /**
     * gfs 恢复成功通知接口
     * @param elementIP 设备ip
     * @return
     */
    boolean gfsRestore(String elementIP);

    /**
     * gfs 镜像节点硬盘损坏
     * @param elementIp
     * @return
     */
    boolean gfsImgDiskDestroy(String elementIp);

    /**
     * gfs 日志节点硬盘损坏
     * @param elementIp
     * @return
     */
    boolean gfsLogDiskDestroy(String elementIp);
}
