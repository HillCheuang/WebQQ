package com.yc.biz;

import java.util.List;

import com.yc.bean.MessageGroupVo;
import com.yc.dao.MessageGroupVoDao;

public class MessageGroupVoBiz {
	MessageGroupVoDao dao = new MessageGroupVoDao();
	
	
	
	public List<MessageGroupVo> findMessageByGroup(String groupqq) throws Exception{
		MessageGroupVo vo = new MessageGroupVo();
		vo.setGroupqq(groupqq);
		return dao.findByTrem(vo);
	}
}
