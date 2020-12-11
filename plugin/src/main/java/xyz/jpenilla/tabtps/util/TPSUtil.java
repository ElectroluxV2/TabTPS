package xyz.jpenilla.tabtps.util;

import org.bukkit.Bukkit;
import xyz.jpenilla.tabtps.TabTPS;

import java.text.DecimalFormat;

public class TPSUtil {
    private static final DecimalFormat FORMAT = new DecimalFormat("###.00");

    private final TabTPS tabTPS;

    public TPSUtil(TabTPS tabTPS) {
        this.tabTPS = tabTPS;
    }

    public double[] getTps() {
        if (tabTPS.getMajorMinecraftVersion() < 16 || !tabTPS.isPaperServer()) {
            return tabTPS.getNmsHandler().getTps();
        }
        return Bukkit.getServer().getTPS();
    }

    public double getMspt() {
        if (tabTPS.getMajorMinecraftVersion() < 16 || !tabTPS.isPaperServer()) {
            return tabTPS.getNmsHandler().getMspt();
        }
        return Bukkit.getServer().getAverageTickTime();
    }

    public static String round(double value) {
        final String formatted = FORMAT.format(value);
        if (formatted.startsWith(".")) {
            return String.format("0%s", formatted);
        }
        return formatted;
    }

    public static String getColoredTps(double tps) {
        final StringBuilder s = new StringBuilder();
        if (tps >= 18.5) {
            s.append("<gradient:green:dark_green>");
        } else if (tps > 15.0) {
            s.append("<gradient:gold:yellow>");
        } else {
            s.append("<gradient:red:gold>");
        }
        s.append(round(tps));
        s.append("</gradient>");
        return s.toString();
    }

    public static double toMilliseconds(long time) {
        return time * 1.0E-6D;
    }

    public static double toMilliseconds(double time) {
        return time * 1.0E-6D;
    }

    public static String getColoredMspt(double mspt) {
        final StringBuilder m = new StringBuilder();
        if (mspt <= 25.0) {
            m.append("<gradient:green:dark_green>");
        } else if (mspt <= 40) {
            m.append("<gradient:gold:yellow>");
        } else {
            m.append("<gradient:red:gold>");
        }
        m.append(round(mspt));
        m.append("</gradient>");
        return m.toString();
    }
}