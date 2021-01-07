package org.terraform.coregen.bukkit;

import javafx.util.Pair;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.terraform.biome.BiomeBank;
import org.terraform.coregen.ChunkCache;
import org.terraform.biome.BiomeHandler;
import org.terraform.coregen.HeightMap;
import org.terraform.data.SimpleChunkLocation;
import org.terraform.data.TerraformWorld;
import org.terraform.main.TConfigOption;
import org.terraform.main.TerraformGeneratorPlugin;
import org.terraform.utils.GenUtils;

import java.util.*;

public class TerraformGenerator extends ChunkGenerator {
    public static final ArrayList<SimpleChunkLocation> preWorldInitGen = new ArrayList<>();
    public static int seaLevel = 62;
    public static int minMountainLevel = 85;

    public static HashMap<Integer, ChunkCache> chunkCaches = new HashMap<>();

    public static void updateSeaLevelFromConfig() {
        seaLevel = TConfigOption.HEIGHT_MAP_SEA_LEVEL.getInt();
    }

    public static void updateMinMountainLevelFromConfig() {
        minMountainLevel = TConfigOption.BIOME_MOUNTAIN_HEIGHT.getInt();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        TerraformWorld tw = TerraformWorld.get(world);
        chunkCaches.put(Objects.hash(tw, chunkX, chunkZ), new ChunkCache(tw, chunkX, chunkZ));

        //Bukkit.getLogger().info("Attempting gen: " + chunkX + "," + chunkZ);

        //Patch for WorldInitEvent issues.
        if (!TerraformGeneratorPlugin.INJECTED_WORLDS.contains(world.getName())) {
            preWorldInitGen.add(new SimpleChunkLocation(world.getName(), chunkX, chunkZ));
        }

        ArrayList<BiomeHandler> biomesToTransform = new ArrayList<>();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int rawX = chunkX * 16 + x;
                int rawZ = chunkZ * 16 + z;

                // This will also cache the height
                int height = HeightMap.getHeight(tw, rawX, rawZ);

                BiomeBank bank = tw.getBiomeBank(rawX, height, rawZ);
                Material[] crust = bank.getHandler().getSurfaceCrust(random);
                biome.setBiome(x, z, bank.getHandler().getBiome());
                int undergroundHeight = height;
                int index = 0;
                while (index < crust.length) {
                    chunk.setBlock(x, undergroundHeight, z, crust[index]);
                    index++;
                    undergroundHeight--;
                }

                for (int y = undergroundHeight; y > 0; y--) {
                    chunk.setBlock(x, y, z, Material.STONE);
                }

                //Any low elevation is sea
                for (int y = height + 1; y <= seaLevel; y++) {
                    chunk.setBlock(x, y, z, Material.WATER);
                }

                //Bedrock Base
                chunk.setBlock(x, 2, z, GenUtils.randMaterial(random, Material.STONE, Material.BEDROCK));
                chunk.setBlock(x, 1, z, GenUtils.randMaterial(random, Material.STONE, Material.BEDROCK));
                chunk.setBlock(x, 0, z, Material.BEDROCK);

                BiomeHandler transformHandler = bank.getHandler().getTransformHandler();
                if (transformHandler != null && !biomesToTransform.contains(transformHandler)) biomesToTransform.add(transformHandler);
            }
        }

        for (BiomeHandler handler : biomesToTransform) {
            handler.transformTerrain(tw, random, chunk, chunkX, chunkZ);
        }

        return chunk;
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0, HeightMap.getHeight(TerraformWorld.get(world), 0, 0), 0);
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        TerraformWorld tw = TerraformWorld.get(world);
        return Collections.singletonList(new TerraformBukkitBlockPopulator(tw));
    }

    public static ChunkCache getCache(TerraformWorld tw, int x, int z) {
        int hash = Objects.hash(tw, ChunkCache.getChunkCoordinate(x), ChunkCache.getChunkCoordinate(z));

        if (chunkCaches.get(hash) == null) {
            ChunkCache cache = new ChunkCache(tw, x, z);
            chunkCaches.put(hash, cache);
            return cache;
        } else {
            return TerraformGenerator.chunkCaches.get(hash);
        }
    }
}