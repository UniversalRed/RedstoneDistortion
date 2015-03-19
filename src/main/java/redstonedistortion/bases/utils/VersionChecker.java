package redstonedistortion.bases.utils;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.nbt.NBTTagCompound;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by UniversalRed on 15-03-14.
 */
public class VersionChecker {

    public static final String currentVersion = "@VERSION@";
    public static boolean newerVersionAvailable = false;
    public static String newerVersionNumber = "";
    public static String[] changelog;

    public static void start() {
        new VersionCheckThread().start();
    }

    private static class VersionCheckThread extends Thread {

        @Override
        public void run() {
            ModLogger.info("Redstone Distortion version check initiated, current version: @VERSION@");
            try {

                URL version = new URL("https://raw.githubusercontent.com/UniversalRed/RedstoneDistortion/1.7.10/src/main/resources/versions/version");
                BufferedReader reader = new BufferedReader(new InputStreamReader(version.openStream()));
                newerVersionNumber = reader.readLine();
                if (!currentVersion.equals(newerVersionNumber)) {
                    newerVersionAvailable = true;
                    ModLogger.info("There is a newer version of Redstone Distortion available (" + newerVersionNumber + ") please consider updating");
                    URL changelogURL = new URL("https://github.com/UniversalRed/RedstoneDistortion/tree/1.7.10/src/main/resources/changelogs" + newerVersionNumber);
                    BufferedReader changelogReader = new BufferedReader((new InputStreamReader(changelogURL.openStream())));
                    String line;
                    ArrayList<String> changelogList = new ArrayList<String>();
                    while ((line = changelogReader.readLine()) != null)
                        changelogList.add(line);
                    changelog = new String[changelogList.size()];
                    changelogList.toArray(changelog);
                    pingVersionChecker();
                }

            } catch (Throwable e) {
                ModLogger.error("Redstone Distortion version check failed!");
                e.printStackTrace();
            }
        }

        public void pingVersionChecker() {
            if (Loader.isModLoaded("VersionChecker")) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setString("modDisplayName", "Redstone Distortion");
                tag.setString("oldVersion", currentVersion);
                tag.setString("newVersion", newerVersionNumber);
                tag.setString("updateUrl", "http://minecraft.curseforge.com/mc-mods/228224-redstone-distortion");
                tag.setBoolean("isDirectLink", false);
                StringBuilder builder = new StringBuilder();
                for (String s : changelog)
                    builder.append(s).append("/n");
                tag.setString("changeLog", builder.toString());
                FMLInterModComms.sendRuntimeMessage("reddistortion", "VersionChecker", "addUpdate", tag);
            }
        }
    }
}
