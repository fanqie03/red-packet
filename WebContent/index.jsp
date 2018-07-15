<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参数</title>

</head>
<body>


	<button onclick="tip()">tip</button><br/>
	Firefox<br/>
	抓取次数：<input type="text" id="count" value="3"><br/>
	红包编号：<input type="text" id="redPacketId" value="3"><br/>
	使用方法：
	<select id="method">
		<option value="grapRedPacket">普通方法</option>
		<option value="grapRedPacketForVersion">乐观锁</option>
		<option value="grapRedPacketForUpdate">悲观锁</option>
		<option value="grapRedPacketByRedis">Redis缓存</option>
		<option value="grapRedPacketForCount">次数</option>
		<option value="grapRedPacketForTimestamp">时间戳</option>
	</select><br/>
	<button onclick="grap()">test</button><br/>
	


	<!-- 加载Query文件-->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.0.js"></script>
	<script type="text/javascript">
		function tip() {
			alert("123");
			console.log("hello");
		}

		function grap() {
			var count=$("#count").val();
			var redPacketId=$("#redPacketId").val();
			var method=$("#method").val();
			console.log("count: "+count+"redPacketId: "+redPacketId+"method: "+method);
			console.log("./userRedPacket/" + method + ".do?redPacketId="+ redPacketId + "&userId=" + 1);
			for (var i = 1; i <= count; i++) {
				//jQuery的post请求，请注意这是异步请求
				$.post({
					//请求抢id为1的红包
					//根据自己请求修改对应的url和大红包编号
					//url: "./userRedPacket/grapRedPacket.do?redPacketId=1&userId=" + i,
					url : "./userRedPacket/" + method + ".do?redPacketId="
							+ redPacketId + "&userId=" + i,
					//成功后的方法
					success : function(result) {
						console.log("redPacketId="+ redPacketId + "&userId=" + i+"message:"+result.message);
					}
				});
			}
		}
	</script>
</body>
</html>