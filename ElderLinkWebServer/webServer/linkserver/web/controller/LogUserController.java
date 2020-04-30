package linkserver.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import linkserver.base.pojo.LogUser;
import linkserver.data.handler.LogUserJDBC;
import linkserver.tools.LogUserAttribute;

@RestController
@RequestMapping(value="/user",produces="application/json; charset=utf-8")
public class LogUserController {
	private LogUserJDBC template;
	@Autowired
	public void setTemplate(LogUserJDBC userJDBCTemplate) {
		this.template=userJDBCTemplate;
	}
	//����ָ���û������û���Ϣ
	@RequestMapping(value="/find/{name}",method=RequestMethod.GET)
	public Object queryLogUser(@PathVariable("name") String userName) {
		LogUser user=template.getUser(userName);
		if(user==null) {return LogUserAttribute.FINDUSER_NULL;}  //���δ�ҵ���Ӧ�û����򷵻�null
		return user;
	}
	
	//�о����г�Ա
	@RequestMapping(value="/listAll",method=RequestMethod.GET)
	public List<LogUser> lisLogUser(){
		List<LogUser> users=template.listUser();
		return users;
	}
	
	//�½������û���Ϣ
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String createNewUser(@RequestBody LogUser cuser) {
		if(template.getUser(cuser.getUserName())!=null) return LogUserAttribute.CREATE__REPEATNAME;
		if(template.createUser(cuser)) return LogUserAttribute.CREATE_SUCCESS;
		return LogUserAttribute.CREATE_FAILD;
	}
	
	//�޸��û���Ϣ����ԭ����ֻ���޸�����������
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateUser(@RequestBody LogUser uuser) {
		if(template.getUser(uuser.getUserName())==null) return LogUserAttribute.UPDATE_NULL;
		if(template.updateUser(uuser)) return LogUserAttribute.UPDATE_SUCCESS;
		return LogUserAttribute.UPDATE_FAILD;
	}
	
	//�޸��û������롪���������template.updateUser(pauser)����ʵ��
	@RequestMapping(value="/password",method=RequestMethod.POST)
	public String amendPassword(@RequestBody LogUser pauser) {
		if(template.getUser(pauser.getUserName())==null) return LogUserAttribute.PASSWORD_NULL;
		if(template.updateUser(pauser)) return LogUserAttribute.PASSWORD_SUCCESS;
		return LogUserAttribute.PASSWORD_FAILD;
	}
	//�÷���������Ա��ݵ��û����á���������������������������������Ҫ��ӹ���������
	@RequestMapping(value="/delete/{deleteName}",method=RequestMethod.GET)
	public String deleteUser(@PathVariable("deleteName") String name) {
		if(template.DeleteUser(name)) return LogUserAttribute.DELETE_SUCCESS;
		return LogUserAttribute.DELETE_FAILD;
	}
	
}
