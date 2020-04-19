package mimicweb.manager.timer;





public interface CleanStrategy {
    String spaceStrategy();
    float getSpace();
    void cleanStrategy();
    String timeStrategy();
    String cleanAll(String logPath);
}
