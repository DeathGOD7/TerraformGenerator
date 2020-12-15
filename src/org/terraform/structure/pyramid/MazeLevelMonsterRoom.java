package org.terraform.structure.pyramid;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.terraform.coregen.PopulatorDataAbstract;
import org.terraform.structure.room.CubeRoom;
import org.terraform.structure.room.RoomPopulatorAbstract;
import org.terraform.utils.GenUtils;

public class MazeLevelMonsterRoom extends RoomPopulatorAbstract {

    public MazeLevelMonsterRoom(Random rand, boolean forceSpawn, boolean unique) {
        super(rand, forceSpawn, unique);
    }

    //Basically just spawns monsters tbh. A boring room otherwise.
    @Override
    public void populate(PopulatorDataAbstract data, CubeRoom room) {
    	//Feeble attempt to decorate the room
    	data.setType(room.getX(), room.getY(), room.getZ(), Material.RED_TERRACOTTA);
    	
    	int choice = rand.nextInt(3); //0,1,2
    	EntityType monster = new EntityType[] {
    			EntityType.HUSK,
    			EntityType.CAVE_SPIDER,
    			EntityType.SILVERFISH
    	}[choice];
    	for(int i = 0; i < GenUtils.randInt(1, 5); i++) {
    		data.addEntity(room.getX(), room.getY()+1, room.getZ(), monster);
    	}
    }


    @Override
    public boolean canPopulate(CubeRoom room) {
        return true;
    }


}
