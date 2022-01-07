package me.sores.Orion.commands;

import me.sores.Orion.Orion;
import me.sores.Orion.util.MessageUtil;
import me.sores.Orion.util.cmdfrmwrk.BaseCommand;
import me.sores.Orion.util.cmdfrmwrk.CommandUsageBy;
import org.bukkit.command.CommandSender;

/**
 * Created by sores on 1/6/2022.
 */
public class OrionCommand extends BaseCommand {

    public OrionCommand() {
        super("Orion", "Orion.Orion", CommandUsageBy.ALL);
        setUsage("/<command>");
        setMinArgs(0);
        setMaxArgs(0);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission("Orion.Orion")){
            MessageUtil.message(sender, "&a&lOrion &7is currently running version: &a" + Orion.getInstance().getDescription().getVersion());
        }
    }
    
}
