package cn.edu.scau.cmi.chenmengfu.redpacket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.cmi.chenmengfu.redpacket.dao.RedPacketDao;
import cn.edu.scau.cmi.chenmengfu.redpacket.pojo.RedPacket;
import cn.edu.scau.cmi.chenmengfu.redpacket.service.RedPacketService;
@Service
public class RedPacketServiceImpl implements RedPacketService {

	@Autowired
	private RedPacketDao redPacketDao = null;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	public RedPacket getRedPacket(Long id) {
		return redPacketDao.getRedPacket(id);
//		return redPacketDao.getRedPacketForUpdate(id);
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	public int decreaseRedPacket(Long id) {
		return redPacketDao.decreaseRedPacket(id);
	}

}
