package com.minemee.MMItemAction;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MMItemActionBlockListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL) 
	public void interactFood(PlayerInteractEvent event){

		final Player player = event.getPlayer();

		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{

			// Bandage

			if(player.getItemInHand().getType().equals(Material.PAPER))
			{ 

				ItemStack item = player.getItemInHand();

				if(item.getAmount() == 1){
					player.setItemInHand(new ItemStack(Material.AIR));
				} else {
					item.setAmount(item.getAmount() - 1);
				}

				if (player.getHealth() < 19) {
					player.sendMessage("This bandage is working well!");

					if((player.getHealth() + 5.0d) > 20.0d){
						player.setHealth(20.0d);
					}
					else{
						player.setHealth(player.getHealth() + 5.0d);
					}

				}
				else{
					player.sendMessage("You wasted one bandage. You were already fine.");
				}

			}


			if(player.getItemInHand().getType().equals(Material.EYE_OF_ENDER))
			{ 
				event.setCancelled(true);
				final double health = player.getHealth();

				final Location loc = player.getLocation();
				final int x = loc.getBlockX();
//				final int y = loc.getBlockY();
//				final int z = loc.getBlockZ();
				
				player.sendMessage("Trying to redirect you to a safe haven!");

				new Timer().schedule(new TimerTask() {          
					@Override
					public void run() {
						
						final Location new_loc = player.getLocation();
						
						 if(health > player.getHealth()){
							 player.sendMessage("Sorry, you can't escape battles.");
						 }
						 else if(x != new_loc.getBlockX()){
							 player.sendMessage("Sorry, you have to stay still.");
						 }
						 else{
							 player.sendMessage("Redirecting you now!");
							 World w = Bukkit.getServer().getWorld("world");
							 player.teleport(w.getSpawnLocation());
						 }
						 
					}
				}, 10000);

			}

		}	

	}

}