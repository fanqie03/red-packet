package cn.edu.scau.cmi.chenmengfu.redpacket.dao;

import org.springframework.stereotype.Repository;

import cn.edu.scau.cmi.chenmengfu.redpacket.pojo.UserRedPacket;

@Repository
public interface UserRedPacketDao {
	public int grapRedPacket(UserRedPacket userRedPacket);
}
