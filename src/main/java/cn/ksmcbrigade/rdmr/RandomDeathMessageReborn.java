package cn.ksmcbrigade.rdmr;

import net.minecraftforge.fml.common.Mod;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Mod("rdmr")
public class RandomDeathMessageReborn {
    public static final URL REMOTE_URL;

    static {
        try {
            REMOTE_URL = new URL("https://github.com/yiyuyan/RandomDeathMessageReborn/raw/master/src/main/resources/messages.txt");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static final List<String> MESSAGES = initRDMR();

    public static List<String> initRDMR() {

        try {
            List<String> returnList = new ArrayList<>();
            Scanner s = new Scanner(REMOTE_URL.openStream());
            while (s.hasNextLine()) returnList.add(s.nextLine());
            return returnList;
        } catch (IOException e) {
            System.out.println("Remote messages failure. Use fallback.");

            try {
                return Arrays.stream(IOUtils.toString(Objects.requireNonNull(RandomDeathMessageReborn.class.getResourceAsStream("/messages.txt"))).split("\\n")).toList();
            } catch (Exception ex) {
                System.out.println("RDM Total Failiure.");
                throw new RuntimeException(ex);
            }
        }
    }
}
