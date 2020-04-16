package mimicweb.manager.util;

import java.util.Properties;

public class SystemExcute{
    public static boolean schedule(String shell) {
        try {
            Properties props = System.getProperties();
            String [] cmds=new String[3];
            String osname = props.getProperty("os.name");
            if (osname.equals("Linux")) {
                cmds[0]="/bin/sh";
                cmds[1]="-c";
                cmds[2]=shell;
            } else {
                cmds[0]="cmd.exe";
                cmds[1]="/C";
                cmds[2]=shell;
            }
            Process pro = Runtime.getRuntime().exec(cmds);
            int a=pro.waitFor();
            if(a==0){
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
