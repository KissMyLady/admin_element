package top.mylady.api.util.linux;
import cn.hutool.core.io.IoUtil;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Vector;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/6/22 8:18
 * @Description: 执行shell命令
 */
public class ExecuteShellUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteShellUtil.class);

    private Vector<String> stdout;

    Session session;


    public ExecuteShellUtil(final String ipAddress, final String username, final String password,int port) {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, ipAddress, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(3000);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

    }

    public int execute(final String command) {
        int returnCode = 0;
        ChannelShell channel = null;
        PrintWriter printWriter = null;
        BufferedReader input = null;
        stdout = new Vector<String>();
        try {
            channel = (ChannelShell) session.openChannel("shell");
            channel.connect();
            input = new BufferedReader(new InputStreamReader(channel.getInputStream()));
            printWriter = new PrintWriter(channel.getOutputStream());
            printWriter.println(command);
            printWriter.println("exit");
            printWriter.flush();
            // logger.info("The remote command is: ");
            String line;
            while ((line = input.readLine()) != null) {
                stdout.add(line);
                logger.info("command命令执行: {}", line);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return -1;
        }finally {
            IoUtil.close(printWriter);
            IoUtil.close(input);
            if (channel != null) {
                channel.disconnect();
            }
        }
        return returnCode;
    }

    public void close(){
        if (session != null) {
            session.disconnect();
        }
    }

    public String executeForResult(String command) {
        execute(command);
        StringBuilder sb = new StringBuilder();
        for (String str : stdout) {
            sb.append(str);
        }
        return sb.toString();
    }


}
