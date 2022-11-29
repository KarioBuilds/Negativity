package com.elikill58.negativity.api.packets.packet.playout;

import com.elikill58.negativity.api.block.data.LightData;
import com.elikill58.negativity.api.packets.PacketType;
import com.elikill58.negativity.api.packets.PacketType.Server;
import com.elikill58.negativity.api.packets.nms.PacketSerializer;
import com.elikill58.negativity.api.packets.packet.NPacketPlayOut;
import com.elikill58.negativity.universal.Version;

public class NPacketPlayOutUpdateLight implements NPacketPlayOut {

	public int x, z;
	public LightData light;
	
	@Override
	public void read(PacketSerializer serializer, Version version) {
		this.x = serializer.readVarInt();
		this.z = serializer.readVarInt();
		this.light = new LightData(serializer, version);
	}
	
	@Override
	public PacketType getPacketType() {
		return Server.LIGHT_UPDATE;
	}
}