package org.terraform.v1_18_R2;

import org.bukkit.entity.EntityType;
import org.terraform.main.TerraformGeneratorPlugin;

public class EntityTypeMapper {

	public static String getObfsNameFromBukkitEntityType(EntityType e){
		switch(e) {
		case AREA_EFFECT_CLOUD: 
		    return "b";
		case ARMOR_STAND: 
		    return "c";
		case ARROW: 
		    return "d";
		case AXOLOTL: 
		    return "e";
		case BAT: 
		    return "f";
		case BEE: 
		    return "g";
		case BLAZE: 
		    return "h";
		case BOAT: 
		    return "i";
		case CAT: 
		    return "j";
		case CAVE_SPIDER: 
		    return "k";
		case CHICKEN: 
		    return "l";
		case COD: 
		    return "m";
		case COW: 
		    return "n";
		case CREEPER: 
		    return "o";
		case DOLPHIN: 
		    return "p";
		case DONKEY: 
		    return "q";
		case DRAGON_FIREBALL: 
		    return "r";
		case DROWNED: 
		    return "s";
		case ELDER_GUARDIAN: 
		    return "t";
		case ENDER_CRYSTAL: 
		    return "u";
		case ENDER_DRAGON: 
		    return "v";
		case ENDERMAN: 
		    return "w";
		case ENDERMITE: 
		    return "x";
		case EVOKER: 
		    return "y";
		case EVOKER_FANGS: 
		    return "z";
		case EXPERIENCE_ORB: 
		    return "A";
//		case : 
//		    return "B";
		case FALLING_BLOCK: 
		    return "C";
		case FIREWORK: 
		    return "D";
		case FOX: 
		    return "E";
		case GHAST: 
		    return "F";
		case GIANT: 
		    return "G";
		case GLOW_ITEM_FRAME: 
		    return "H";
		case GLOW_SQUID: 
		    return "I";
		case GOAT: 
		    return "J";
		case GUARDIAN: 
		    return "K";
		case HOGLIN: 
		    return "L";
		case HORSE: 
		    return "M";
		case HUSK: 
		    return "N";
		case ILLUSIONER: 
		    return "O";
		case IRON_GOLEM: 
		    return "P";
		case DROPPED_ITEM: 
		    return "Q";
		case ITEM_FRAME: 
		    return "R";
		case FIREBALL: 
		    return "S";
		case LEASH_HITCH:
		    return "T";
		case LIGHTNING: 
		    return "U";
		case LLAMA: 
		    return "V";
		case LLAMA_SPIT: 
		    return "W";
		case MAGMA_CUBE: 
		    return "X";
		case MARKER: 
		    return "Y";
		case MINECART: 
		    return "Z";
		case MINECART_CHEST: 
		    return "aa";
		case MINECART_COMMAND: 
		    return "ab";
		case MINECART_FURNACE: 
		    return "ac";
		case MINECART_HOPPER: 
		    return "ad";
		case MINECART_MOB_SPAWNER: 
		    return "ae";
		case MINECART_TNT: 
		    return "af";
		case MULE: 
		    return "ag";
		case MUSHROOM_COW: 
		    return "ah";
		case OCELOT: 
		    return "ai";
		case PAINTING: 
		    return "aj";
		case PANDA: 
		    return "ak";
		case PARROT: 
		    return "al";
		case PHANTOM: 
		    return "am";
		case PIG: 
		    return "an";
		case PIGLIN: 
		    return "ao";
		case PIGLIN_BRUTE: 
		    return "ap";
		case PILLAGER: 
		    return "aq";
		case POLAR_BEAR: 
		    return "ar";
		case PRIMED_TNT:
		    return "as";
		case PUFFERFISH: 
		    return "at";
		case RABBIT: 
		    return "au";
		case RAVAGER: 
		    return "av";
		case SALMON: 
		    return "aw";
		case SHEEP: 
		    return "ax";
		case SHULKER: 
		    return "ay";
		case SHULKER_BULLET: 
		    return "az";
		case SILVERFISH: 
		    return "aA";
		case SKELETON: 
		    return "aB";
		case SKELETON_HORSE: 
		    return "aC";
		case SLIME: 
		    return "aD";
		case SMALL_FIREBALL: 
		    return "aE";
		case SNOWMAN: 
		    return "aF";
		case SNOWBALL: 
		    return "aG";
		case SPECTRAL_ARROW: 
		    return "aH";
		case SPIDER: 
		    return "aI";
		case SQUID: 
		    return "aJ";
		case STRAY: 
		    return "aK";
		case STRIDER: 
		    return "aL";
		case EGG: 
		    return "aM";
		case ENDER_PEARL: 
		    return "aN";
		case THROWN_EXP_BOTTLE: 
		    return "aO";
		case SPLASH_POTION: 
		    return "aP";
		case TRIDENT: 
		    return "aQ";
		case TRADER_LLAMA: 
		    return "aR";
		case TROPICAL_FISH: 
		    return "aS";
		case TURTLE: 
		    return "aT";
		case VEX: 
		    return "aU";
		case VILLAGER: 
		    return "aV";
		case VINDICATOR: 
		    return "aW";
		case WANDERING_TRADER: 
		    return "aX";
		case WITCH: 
		    return "aY";
		case WITHER: 
		    return "aZ";
		case WITHER_SKELETON: 
		    return "ba";
		case WITHER_SKULL: 
		    return "bb";
		case WOLF: 
		    return "bc";
		case ZOGLIN: 
		    return "bd";
		case ZOMBIE: 
		    return "be";
		case ZOMBIE_HORSE: 
		    return "bf";
		case ZOMBIE_VILLAGER: 
		    return "bg";
		case ZOMBIFIED_PIGLIN: 
		    return "bh";
		case PLAYER: 
		    return "bi";
		case FISHING_HOOK:
		    return "bj";
		case ENDER_SIGNAL:
		case UNKNOWN:
			break;
		}
		TerraformGeneratorPlugin.logger.error("INVALID ENTITY REQUESTED: " + e.toString());
		return "";
	}
}
