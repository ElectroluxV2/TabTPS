package xyz.jpenilla.tabtps.nms.v1_8_R3;

import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import xyz.jpenilla.tabtps.nms.api.NMS;

public class NMSHandler implements NMS {

    @Override
    public double[] getTps() {
        return MinecraftServer.getServer().recentTps;
    }

    @Override
    public double getMspt() {
        return MathHelper.a(MinecraftServer.getServer().h) * 1.0E-6D;
    }

    @Override
    public int getPing(Player player) {
        return ((CraftPlayer) player).getHandle().ping;
    }
}
