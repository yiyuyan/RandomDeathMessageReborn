package cn.ksmcbrigade.rdmr;

import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                return Files.readAllLines(Path.of(RandomDeathMessageReborn.class.getResource("/messages.txt").getPath()));
            } catch (IOException ex) {
                System.out.println("RDM Total Failiure.");
                throw new RuntimeException(ex);
            }
        }
    }
}
