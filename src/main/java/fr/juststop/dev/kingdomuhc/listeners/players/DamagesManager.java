package fr.juststop.dev.kingdomuhc.listeners.players;

import fr.juststop.dev.kingdomuhc.KingdomUHC;
import fr.juststop.dev.kingdomuhc.managers.UhcPlayer;
import fr.juststop.dev.kingdomuhc.managers.areas.Area;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamagesManager implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(KingdomUHC.getInstance().getGameManager().getConfig().GAME_TIMER < 300) { /*event.setCancelled(true); */ }
        else if((event.getCause() == EntityDamageEvent.DamageCause.LAVA
                || event.getCause() == EntityDamageEvent.DamageCause.FIRE
                || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)
                && event.getEntity().getType() == EntityType.PLAYER
                && event.getEntity().getLocation().getY() <= 30
        ) event.setCancelled(true);
        else if(event.getEntity().getType() == EntityType.PLAYER) {

            UhcPlayer player = KingdomUHC.getInstance().getGameManager().getPlayers().get(event.getEntity());

            if(player.getAreas().size() == 0) return;

            for(Area area : player.getAreas()) {
                if(player.getPlayer().getLocation().distance(area.getLocation()) <= area.getOptions().radius
                        && event.getCause() == EntityDamageEvent.DamageCause.FALL
                        && !area.getOptions().fallDamage
                ) event.setCancelled(true);
            }

        }
    }

    @EventHandler
    public void onDamageOnPlayer(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            UhcPlayer attacker = KingdomUHC.getInstance().getGameManager().getPlayers().get((Player) event.getDamager());
            UhcPlayer victim = KingdomUHC.getInstance().getGameManager().getPlayers().get((Player) event.getEntity());

            if(victim.getRole() != null && attacker.getRole() != null) {

                double originalDamages = event.getDamage() - 1; // -1 cause spigot adds 1 damage to each weapon.
                double finalDamages = 0;

                /*for(PotionEffect effect : attacker.getPlayer().getActivePotionEffects()) {
                    if(effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
                        Bukkit.broadcastMessage(effect.getType().getName());
                        finalDamages = originalDamages * (1 + (0.2 * effect.getAmplifier()));
                        Bukkit.broadcastMessage("Après force: "  + finalDamages);
                    }
                }*/

                finalDamages = finalDamages + originalDamages * (1 + attacker.getStrengthPercentage());

                /*for(PotionEffect effect : victim.getPlayer().getActivePotionEffects()) {
                    if(effect.getType().equals(PotionEffectType.DAMAGE_RESISTANCE)) {
                        finalDamages = finalDamages - originalDamages * (0.2 * effect.getAmplifier());
                        Bukkit.broadcastMessage("Après résistance: "  + finalDamages);
                    }
                }*/

                finalDamages = finalDamages - originalDamages * victim.getResistancePercentage();

                for(Area area : victim.getAreas()) {
                    if(victim.getPlayer().getLocation().distance(area.getLocation()) <= area.getOptions().radius) {
                        if(area.getOptions().damageResistanceBackward > 0) {
                            finalDamages = finalDamages - finalDamages * ( area.getOptions().damageResistanceBackward / 100 );
                        }
                    }
                }

                event.setDamage(finalDamages < 0 ? 0 : finalDamages);

            }
        }
    }

}
