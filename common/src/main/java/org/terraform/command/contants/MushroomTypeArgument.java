package org.terraform.command.contants;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.terraform.tree.FractalTypes;
import org.terraform.tree.FractalTypes.Mushroom;
import org.terraform.tree.FractalTypes.Tree;

public class MushroomTypeArgument extends TerraCommandArgument<FractalTypes.Mushroom> {

    public MushroomTypeArgument(String name, boolean isOptional) {
        super(name, isOptional);
        // TODO Auto-generated constructor stub
    }

    @Override
    public FractalTypes.Mushroom parse(CommandSender sender, String value) {

        return FractalTypes.Mushroom.valueOf(value.toUpperCase());
    }

    @Override
    public String validate(CommandSender sender, String value) {
        try {
            parse(sender, value);
        } catch (IllegalArgumentException e) {
            return "Mushroom type does not exist!";
        }
        return "";
    }
    

    @Override
    public ArrayList<String> getTabOptions(String[] args) {
        if (args.length != 2) return new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

//        for (StructurePopulator spop : StructureRegistry.getAllPopulators()) {
//        	if(spop.getClass().getSimpleName().toUpperCase().startsWith(args[1].toUpperCase()))
//        		values.add(spop.getClass().getSimpleName());
//        }
        
        for(Mushroom type: FractalTypes.Mushroom.values()) {
        	if(type.toString().startsWith(args[1].toUpperCase()))
        		values.add(type.toString());
        }
        
        return values;
    }

}
