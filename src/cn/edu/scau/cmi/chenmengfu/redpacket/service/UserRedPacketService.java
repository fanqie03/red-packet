package cn.edu.scau.cmi.chenmengfu.redpacket.service;

public interface UserRedPacketService {
	/**
	 * 未进行优化，可能会出现不可重读和幻读
	 * @param redPacketId
	 * @param userId
	 * @return
	 */
	public int grabRedPacket(Long redPacketId,Long userId);
	/**
	 * 通过sql的for update来实现悲观锁
	 * @param redPacketId
	 * @param userId
	 * @return
	 */
	public int grabRedPacketForUpdate(Long redPacketId,Long userId);
	/**
	 * 实现乐观锁，通过version避免ABA问题
	 * @param redPacketId
	 * @param userId
	 * @return
	 */
	public int grabRedPacketForVersion(Long redPacketId,Long userId);
	/**
	 * 0 - 没有库存，失败
	 * 1 - 成功，且不是最后一个红包
	 * 2 - 成功，且是最后一个红包
	 * @param redisPacketId
	 * @param userId
	 * @return
	 */
	public Long grapRedPacketByRedis(Long redPacketId,Long userId);
	/**
	 * 基于时间戳的重试
	 * @param redPacketId
	 * @param userId
	 * @return
	 */
	public int grapRedPacketForTimestamp(Long redPacketId,Long userId);
	/**
	 * 基于重试次数的重试
	 * @param redPacketId
	 * @param userId
	 * @return
	 */
	public int grapRedPacketForCount(Long redPacketId,Long userId);
}
