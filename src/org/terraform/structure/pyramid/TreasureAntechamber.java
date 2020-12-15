package org.terraform.structure.pyramid;

import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.type.Chest;
import org.terraform.coregen.PopulatorDataAbstract;
import org.terraform.coregen.TerraLootTable;
import org.terraform.data.Wall;
import org.terraform.structure.room.CubeRoom;
import org.terraform.utils.GenUtils;

public class TreasureAntechamber extends Antechamber {

    public TreasureAntechamber(Random rand, boolean forceSpawn, boolean unique) {
		super(rand, forceSpawn, unique);
		// TODO Auto-generated constructor stub
	}

	/***
     * Contains a variety of Pyramid item loot
     */
    @Override
    public void populate(PopulatorDataAbstract data, CubeRoom room) {
    	super.populate(data, room);
    	
    	//Place chests against the walls
    	for(Entry<Wall,Integer> entry:room.getFourWalls(data, 1).entrySet()) {
    		Wall w = entry.getKey();
    		for(int i = 0; i < entry.getValue(); i++) {
    			
    			if(i != 0 && i != entry.getValue()-1)
    			if(w.getRear().getType().isSolid() && !w.getType().isSolid() 
    					&& GenUtils.chance(rand, 1, 4)) {
    				Chest chest = (Chest) Bukkit.createBlockData(Material.CHEST);
    				chest.setFacing(w.getDirection());
    				w.setBlockData(chest);
    				data.lootTableChest(w.getX(), w.getY(), w.getZ(), TerraLootTable.DESERT_PYRAMID);
    			}
    			
    			w = w.getLeft();
    		}
    		
    	}
    }
	
}
