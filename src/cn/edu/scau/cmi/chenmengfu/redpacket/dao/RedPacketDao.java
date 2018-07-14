package cn.edu.scau.cmi.chenmengfu.redpacket.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.edu.scau.cmi.chenmengfu.redpacket.pojo.RedPacket;

@Repository
public interface RedPacketDao {
	public RedPacket getRedPacket(Long id);

	public RedPacket getRedPacketForUpdate(Long id);

	public int decreaseRedPacket(Long id);


	public int decreaseRedPacketForVersion(@Param("id")Long id, @Param("version")int version);
}
