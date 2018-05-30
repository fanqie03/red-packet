package cn.edu.scau.cmi.chenmengfu.redpacket.service;

import cn.edu.scau.cmi.chenmengfu.redpacket.pojo.RedPacket;

public interface RedPacketService {
	public RedPacket getRedPacket(Long id);
	public int decreaseRedPacket(Long id);
}
