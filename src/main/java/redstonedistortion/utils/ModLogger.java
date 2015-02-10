package redstonedistortion.utils;

import org.apache.logging.log4j.*;

public class ModLogger
{
    public static final Logger logger = LogManager.getLogger("Redstone Distortion");

    public static void initialize() {
        logger.info("Redstone Distortion Logger Started");
    }

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void error(String msg) {
        logger.error(msg);
    }
}
