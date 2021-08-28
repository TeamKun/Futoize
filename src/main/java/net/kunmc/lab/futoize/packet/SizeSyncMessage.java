package net.kunmc.lab.futoize.packet;

import net.minecraft.network.PacketBuffer;

public class SizeSyncMessage {
    public float size;
    public boolean enable;
    public boolean random;

    public SizeSyncMessage(float size, boolean enable, boolean random) {
        this.size = size;
        this.enable = enable;
        this.random = random;
    }

    public static SizeSyncMessage decodeMessege(PacketBuffer buffer) {
        return new SizeSyncMessage(buffer.readFloat(), buffer.readBoolean(), buffer.readBoolean());
    }

    public static void encodeMessege(SizeSyncMessage messegeIn, PacketBuffer buffer) {
        buffer.writeFloat(messegeIn.size);
        buffer.writeBoolean(messegeIn.enable);
        buffer.writeBoolean(messegeIn.random);
    }

}
