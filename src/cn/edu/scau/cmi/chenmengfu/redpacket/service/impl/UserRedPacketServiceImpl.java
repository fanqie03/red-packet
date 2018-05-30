package cn.edu.scau.cmi.chenmengfu.redpacket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.cmi.chenmengfu.redpacket.dao.RedPacketDao;
import cn.edu.scau.cmi.chenmengfu.redpacket.dao.UserRedPacketDao;
import cn.edu.scau.cmi.chenmengfu.redpacket.pojo.RedPacket;
import cn.edu.scau.cmi.chenmengfu.redpacket.pojo.UserRedPacket;
import cn.edu.scau.cmi.chenmengfu.redpacket.service.UserRedPacketService;

@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {

	@Autowired
	private UserRedPacketDao userRedPacketDao=null;
	
	@Autowired
	private RedPacketDao redPacketDao=null;
	
	private static final int FAILED=0;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	public int grabRedPacket(Long redPacketId, Long userId) {
		//获取红包信息
		RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
		//当前小红包库存大于0
		if(redPacket.getStock()>0){
			redPacketDao.decreaseRedPacket(redPacketId);
			//生成抢红包信息
			UserRedPacket userRedPacket = new UserRedPacket();
			userRedPacket.setRedPacketId(redPacketId);
			userRedPacket.setUserId(userId);
			userRedPacket.setAmount(redPacket.getUnitAmount());
			userRedPacket.setNote("抢红包 "+redPacketId);
			//插入抢红包信息
			int result=userRedPacketDao.grapRedPacket(userRedPacket);
			return result;
		}
		return FAILED;
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	public int grabRedPacketForUpdate(Long redPacketId, Long userId) {
		//获取红包信息
		RedPacket redPacket = redPacketDao.getRedPacketForUpdate(redPacketId);
		//当前小红包库存大于0
		if(redPacket.getStock()>0){
			redPacketDao.decreaseRedPacket(redPacketId);
			//生成抢红包信息
			UserRedPacket userRedPacket = new UserRedPacket();
			userRedPacket.setRedPacketId(redPacketId);
			userRedPacket.setUserId(userId);
			userRedPacket.setAmount(redPacket.getUnitAmount());
			userRedPacket.setNote("抢红包 "+redPacketId);
			//插入抢红包信息
			int result=userRedPacketDao.grapRedPacket(userRedPacket);
			return result;
		}
		return FAILED;
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	public int grabRedPacketForVersion(Long redPacketId, Long userId) {
		//获取红包信息
		RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
		//当前小红包库存大于0
		if(redPacket.getStock()>0){
			int update =redPacketDao.decreaseRedPacket(redPacketId);
			if(update==0) {
				return FAILED;
			}
			//生成抢红包信息
			UserRedPacket userRedPacket = new UserRedPacket();
			userRedPacket.setRedPacketId(redPacketId);
			userRedPacket.setUserId(userId);
			userRedPacket.setAmount(redPacket.getUnitAmount());
			userRedPacket.setNote("抢红包 "+redPacketId);
			//插入抢红包信息
			int result=userRedPacketDao.grapRedPacket(userRedPacket);
			return result;
		}
		return FAILED;
	}
	

}
