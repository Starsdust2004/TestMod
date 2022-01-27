import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// 此处的值应与 META-INFmods.toml 文件中的条目匹配
//@Mod("examplemod")
public class ExampleMod
{
    // 直接引用一个 log4j 记录器
    private static final Logger LOGGER = LogManager.getLogger();

    public ExampleMod() {
        // 注册 modloading 的设置方法
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // 注册 enqueueIMC 方法进行 modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // 注册用于modloading的processIMC方法
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // 注册 doClientStuff 方法以进行 modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // 注册我们自己感兴趣的服务器和其他游戏活动
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // 做一些只能在客户端上做的事情
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // 一些示例代码将 IMC 发送到另一个 mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // 一些示例代码，用于接收和处理来自其他 mod 的 InterModComms
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // 您可以使用 SubscribeEvent 并让事件总线发现方法来调用
//    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // 服务器启动时做某事
        LOGGER.info("HELLO from server starting");
    }

    // 您可以使用 EventBusSubscriber 自动订阅包含的类上的事件（这是订阅 MOD 事件总线以接收注册表事件）
//    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
//        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // 在这里注册一个新方块
            LOGGER.info("来自方块注册器的Hello");
        }
    }
}
