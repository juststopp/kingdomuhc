package fr.juststop.dev.kingdomuhc.managers.world;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.game.GameConfig;
import fr.juststop.dev.kingdomuhc.utils.Utils;
import net.minecraft.server.v1_8_R3.BiomeBase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;

public class WorldManager {

    public void deleteWorld(World world) {
        File folder = world.getWorldFolder();

        Bukkit.unloadWorld(world, false);
        Utils.deleteFolder(folder);
    }

    public void replaceBiomes() {
        BiomeBase[] biomes = BiomeBase.getBiomes();
        BiomeBase[] copy = Arrays.copyOf(biomes, biomes.length);

        for(int i = 0; i < biomes.length; i++) {
            if(i != BiomeBase.ROOFED_FOREST.id && biomes[i] != null) {
                biomes[i] = copy[BiomeBase.ROOFED_FOREST.id];
            }
        }
    }

}
