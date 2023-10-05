package myma.listeners;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.projectile.EntityArrow;
import cn.nukkit.entity.projectile.EntityProjectile;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByChildEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityShootBowEvent;
import cn.nukkit.event.entity.ProjectileLaunchEvent;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.EntityEventPacket;
import cn.nukkit.utils.TextFormat;
import myma.Main;
import myma.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class EntityListeners implements Listener {

    @EventHandler
    public void onDamageByChildEntity(EntityDamageByChildEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();
        Entity child = event.getChild();
        if (entity instanceof Player playerEntity && damager instanceof Player playerDamager) {
            if (child instanceof EntityArrow) {
                Vector3 childMotion = child.getMotion();
                EntityEventPacket entityEventPacket = new EntityEventPacket();
                entityEventPacket.eid = playerEntity.getId();
                entityEventPacket.event = EntityEventPacket.HURT_ANIMATION;
                List<Player> viewers = new ArrayList<>(playerEntity.getViewers().values());
                viewers.add(playerDamager);
                Server.broadcastPacket(viewers, entityEventPacket);
                if (playerEntity.getName().equals(damager.getName())) {
                    double horizontalSpeed = Math.sqrt(Math.pow(childMotion.x, 2) + Math.pow(childMotion.z, 2));
                    if (horizontalSpeed > 0) {
                        double multiplier = 0.50 * 1.6 / horizontalSpeed;
                        playerEntity.setMotion(entity.getMotion().add(childMotion.x * multiplier, 0.22, childMotion.z * multiplier));
                    }
                } else {
                    playerEntity.setMotion(new Vector3(childMotion.x, 0.5, childMotion.z));
                    Utils.playSound(playerEntity, "note.bell");
                    if (event.isApplicable(EntityDamageEvent.DamageModifier.ABSORPTION)) {
                        event.setDamage(0.0F, EntityDamageEvent.DamageModifier.ABSORPTION);
                    }
                    float finalDamage = event.getFinalDamage();
                    if (finalDamage < playerEntity.getHealth()) {
                        playerEntity.setHealth(playerEntity.getHealth() - finalDamage);
                        double entityHealth = Math.round(playerEntity.getHealth() / 2.0 * 10.0) / 10.0;
                        Function<Double, String> colorPlayerHealth = (Double health) -> {
                            if (health <= 10 && health >= 8) {
                                return String.valueOf(TextFormat.GREEN);
                            } else if (health < 8 && health >= 6) {
                                return String.valueOf(TextFormat.YELLOW);
                            } else if (health < 6 && health >= 4) {
                                return String.valueOf(TextFormat.GOLD);
                            } else if (health < 4 && health >= 2) {
                                return String.valueOf(TextFormat.RED);
                            } else if (health < 2 && health >= 0) {
                                return String.valueOf(TextFormat.DARK_RED);
                            } else {
                                return String.valueOf(TextFormat.GRAY);
                            }
                        };
                        if (entityHealth > 0) {
                            playerDamager.sendMessage("§fLe joueur §a" + playerEntity.getName() + " §fest désormais à " + colorPlayerHealth.apply(entityHealth) + entityHealth + " HP §f!");
                        }
                    } else {
                        // TODO: FAKE DEATH
                        playerEntity.setHealth(playerEntity.getMaxHealth());
                        playerEntity.teleport(playerEntity.getLevel().getSpawnLocation());
                        Main.getInstance().getServer().broadcastMessage("§a" + playerEntity.getName() + " §7a été tué par §c" + playerDamager.getName() + " §7!");
                    }
                }
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onShootBow(EntityShootBowEvent event) {
        Entity entity = event.getEntity();
        EntityProjectile projectile = event.getProjectile();
        if (entity instanceof Player && projectile instanceof EntityArrow) {
            event.setForce(event.getForce() * 1.10);
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Entity shooter = event.getShooter();
        EntityProjectile entity = event.getEntity();
        if (shooter instanceof Player && entity instanceof EntityArrow arrow) {
            arrow.setPickupMode(EntityArrow.PICKUP_ANY);
        }
    }

}
