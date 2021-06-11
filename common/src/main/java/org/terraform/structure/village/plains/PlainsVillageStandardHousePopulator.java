package org.terraform.structure.village.plains;

import org.terraform.coregen.PopulatorDataAbstract;
import org.terraform.data.SimpleBlock;
import org.terraform.data.Wall;
import org.terraform.structure.room.CubeRoom;
import org.terraform.structure.room.RoomPopulatorAbstract;
import org.terraform.structure.village.plains.house.PlainsVillageHouseJigsawBuilder;
import org.terraform.utils.BlockUtils;
import org.terraform.utils.GenUtils;
import org.terraform.utils.version.OneOneSevenBlockHandler;

import java.util.Random;

public class PlainsVillageStandardHousePopulator extends RoomPopulatorAbstract {
	
	private PlainsVillagePopulator plainsVillagePopulator;
    public PlainsVillageStandardHousePopulator(PlainsVillagePopulator plainsVillagePopulator, Random rand, boolean forceSpawn, boolean unique) {
        super(rand, forceSpawn, unique);
        this.plainsVillagePopulator = plainsVillagePopulator;
    }

    @Override
    public void populate(PopulatorDataAbstract data, CubeRoom room) {

        int height = GenUtils.getHighestGroundOrSeaLevel(data, room.getX(), room.getZ());

        //Debug squares
//    	int[] lowerCorner = room.getLowerCorner();
//    	int[] upperCorner = room.getUpperCorner();
//    	for(int x = lowerCorner[0]; x <= upperCorner[0]; x++)
//    		for(int z = lowerCorner[1]; z <= upperCorner[1]; z++) {
//    			data.setType(x, height, z, Material.RED_WOOL);
//    		}

        PlainsVillageHouseJigsawBuilder builder = new PlainsVillageHouseJigsawBuilder(
        		plainsVillagePopulator,
                room.getWidthX() - 3, room.getWidthZ() - 3, data, room.getX(), height, room.getZ()
        );
        if (room instanceof DirectionalCubeRoom)
            builder.forceEntranceDirection(((DirectionalCubeRoom) room).getDirection());

        builder.generate(this.rand);
        builder.build(this.rand);

        Wall entrance = builder.getEntranceBlock().getRear().getGround();
        int maxDepth = 6;

        boolean placedLamp = false;
        //Connect front to the nearest path.
        while (entrance.getType() != OneOneSevenBlockHandler.DIRT_PATH() && maxDepth > 0) {
            if (BlockUtils.isDirtLike(entrance.getType()))
                entrance.setType(OneOneSevenBlockHandler.DIRT_PATH());

            if (!placedLamp && GenUtils.chance(this.rand, 3, 5)) {
                SimpleBlock target;
                if (this.rand.nextBoolean())
                    target = entrance.getLeft(2).getGround().getRelative(0, 1, 0).get();
                else
                    target = entrance.getRight(2).getGround().getRelative(0, 1, 0).get();
                if (PlainsVillagePathPopulator.canPlaceLamp(target)) {
                    placedLamp = true;
                    PlainsVillagePathPopulator.placeLamp(rand, target);
                }

            }

            entrance = entrance.getFront().getGround();
            maxDepth--;
        }
    }


    @Override
    public boolean canPopulate(CubeRoom room) {
        return room.getWidthX() >= 15;
    }
}
