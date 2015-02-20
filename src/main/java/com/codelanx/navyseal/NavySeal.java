/*
 * Copyright (C) 2015 Your mom
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.codelanx.navyseal;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Class description for {@link NavySeal}
 *
 * @since 1.0.0
 * @author 1Rogue
 * @version 1.0.0
 */
public class NavySeal extends JavaPlugin implements Listener {

    private MessengerThread thread;

    @Override
    public void onEnable() {
        this.thread = new MessengerThread();
        this.thread.start();
    }

    @Override
    public void onDisable() {
        this.thread.running = false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player) && args.length < 3) {
            return true;
        }
        //ned b daown hur cuz bed
        if (args.length < 1) {
            this.thread.queue(new PlayerWordPair((Player) sender, "mazen"));
            return true;
        }
        if (args.length < 2) {
            this.thread.queue(new PlayerWordPair((Player) sender, args[0]));
            return true;
        }
        int wpm = PlayerWordPair.DEFAULT_WPM;
        try {
            wpm = Integer.parseInt(args[1]);
            if (wpm < 45) {
                //screw slow people
                wpm = 69;
            }
        } catch(NumberFormatException ex) {}
        if (args.length < 3) {
            this.thread.queue(new PlayerWordPair((Player) sender, args[0], wpm));
            return true;
        }
        Player other = Bukkit.getPlayer(args[2]);
        if (other == null) {
            sender.sendMessage("[NavySeal] Player not online");
            return true;
        }
        this.thread.queue(new PlayerWordPair(other, args[0], wpm));
        return true;
    }


}
