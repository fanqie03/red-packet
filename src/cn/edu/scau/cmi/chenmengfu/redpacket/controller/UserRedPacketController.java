package cn.edu.scau.cmi.chenmengfu.redpacket.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.scau.cmi.chenmengfu.redpacket.service.UserRedPacketService;

@Controller
@RequestMapping("/userRedPacket")
public class UserRedPacketController {
	@Autowired
	private UserRedPacketService userRedPacketService=null;
	
	@RequestMapping(value="/grapRedPacket")
	@ResponseBody
	public Map<String, Object> grapRedPacket(Long redPacketId,Long userId){
		//抢红包
		int result=userRedPacketService.grabRedPacket(redPacketId, userId);
		Map<String, Object> retMap=new HashMap<>();
		boolean flag = result>0;
		retMap.put("success", flag);
		retMap.put("message", flag?"抢红包成功":"抢红包失败");
		return retMap;
	}
	//悲观锁请求
	@RequestMapping(value="/grapRedPacketForUpdate")
	@ResponseBody
	public Map<String, Object> grapRedPacketForUpdate(Long redPacketId,Long userId){
		//抢红包
		int result=userRedPacketService.grabRedPacketForUpdate(redPacketId, userId);
		Map<String, Object> retMap=new HashMap<>();
		boolean flag = result>0;
		retMap.put("success", flag);
		retMap.put("message", flag?"抢红包成功":"抢红包失败");
		return retMap;
	}
	//乐观锁请求
	@RequestMapping(value="/grapRedPacketForVersion")
	@ResponseBody
	public Map<String, Object> grapRedPacketForVersion(Long redPacketId,Long userId){
		//抢红包
		int result=userRedPacketService.grabRedPacketForVersion(redPacketId, userId);
		Map<String, Object> retMap=new HashMap<>();
		boolean flag = result>0;
		retMap.put("success", flag);
		retMap.put("message", flag?"抢红包成功":"抢红包失败");
		return retMap;
	}
	@RequestMapping(value="/grapRedPacketByRedis")
	@ResponseBody
	public Map<String, Object>grapRedPacketByRedis(Long redPacketId,Long userId){
		Map<String, Object>resultMap = new HashMap<>();
		Long result=userRedPacketService.grapRedPacketByRedis(redPacketId, userId);
		boolean flag = result>0;
		resultMap.put("result", flag);
		resultMap.put("message", flag?"抢红包成功":"强红包失败");
		return resultMap;
	}
	@RequestMapping(value="/grapRedPacketForCount")
	@ResponseBody
	public Map<String, Object>grapRedPacketForCount(Long redPacketId,Long userId){
		Map<String, Object>resultMap = new HashMap<>();
		int result=userRedPacketService.grapRedPacketForCount(redPacketId, userId);
		boolean flag = result>0;
		resultMap.put("result", flag);
		resultMap.put("message", flag?"抢红包成功":"强红包失败");
		return resultMap;
	}
	@RequestMapping(value="/grapRedPacketForTimestamp")
	@ResponseBody
	public Map<String, Object>grapRedPacketForTimestamp(Long redPacketId,Long userId){
		Map<String, Object>resultMap = new HashMap<>();
		int result=userRedPacketService.grapRedPacketForTimestamp(redPacketId, userId);
		boolean flag = result>0;
		resultMap.put("result", flag);
		resultMap.put("message", flag?"抢红包成功":"强红包失败");
		return resultMap;
	}
}
