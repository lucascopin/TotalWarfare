import com.fr.minecraft.totalwarfare.network.ModPackets;
import com.fr.minecraft.totalwarfare.network.FireGunPacket;
import com.fr.minecraft.totalwarfare.client.ModKeyMapping;
import com.fr.minecraft.totalwarfare.TotalWarfare;
import com.fr.minecraft.totalwarfare.item.GunItem;
import com.fr.minecraft.totalwarfare.item.M9GunItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;


// Utiliser l\'annotation pour enregistrer automatiquement la classe côté client uniquement
//Cette classe permet un affichage plus adapté à nos besoins comme l'affichage des mains uniquement en première personne ou la gestion du tir côté client. Mais on peut visiblement l'utiliser pour tirer (par exemple) en capturant le KeyBind (côté client) et en l'envoyant côté serveur.
@EventBusSubscriber(modid = TotalWarfare.MODID, value = Dist.CLIENT)
public class GunEventHandler {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (ModKeyMapping.SHOOT.consumeClick()){
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || mc.screen != null) return;

            Player player = mc.player;
            ItemStack mainHand = player.getMainHandItem();
            if (mainHand.getItem() instanceof M9GunItem gun) {
                mc.player.sendSystemMessage(Component.literal("Normalement ça tire"));
                ModPackets.INSTANCE.sendToServer(new FireGunPacket());
            }
        }
    }

    @EventBusSubscriber(modid = TotalWarfare.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public class ClientModBusEvents{
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(ModKeyMapping.SHOOT);
        }
    }
}

