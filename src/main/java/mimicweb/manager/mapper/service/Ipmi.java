package mimicweb.manager.mapper.service;

public interface Ipmi {
    /**
     *
     * @param elementIp 设备管理ip
     * @return 执行结果
     */
    boolean restartOs(String elementIp);

    /**
     *
     * @param oldBmcIp 设备管理ip
     * @param newBmcIp 要修改bmcIp
     * @param elementIp 设备ip
     * @return 更新结果
     */
    boolean updateBandBmcIp(String elementIp,String oldBmcIp,String newBmcIp);

    /**
     *
     * @param elementIp 设备管理ip
     * @param bmcIp 主板bmc ip
     * @return 执行结果
     */
    boolean startOs(String elementIp,String bmcIp);

    /**
     *
     * @param elementIp 设备管理ip
     * @param bmcIp 主板bmc ip
     * @return 执行结果
     */
    boolean shutdownOs(String elementIp,String bmcIp);
}
