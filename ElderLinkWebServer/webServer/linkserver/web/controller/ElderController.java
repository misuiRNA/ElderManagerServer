package linkserver.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import linkserver.base.pojo.Elder;
import linkserver.data.handler.ElderJDBC;
import linkserver.tools.ElderAttribute;

@RestController
@RequestMapping(value = "/elder", produces = "application/json; charset=utf-8")
public class ElderController {
	private ElderJDBC template;
	
	@Autowired
	public void setTemplate(ElderJDBC elderJDBCTemplate) {
		this.template=elderJDBCTemplate;
	}
	/**
	 * ����id��ȡָ�����˵���Ϣ
	 * @param elderID
	 * @return
	 */
	@RequestMapping(value="/find/{id}",method=RequestMethod.GET)
	public Object getElderByID(@PathVariable("id") String elderID) {
		Elder elder=template.getElder(elderID);
		if(elder==null) return ElderAttribute.FINDELDER_NULL;
		return elder;
	}
	/**
	 * �о�����������Ϣ
	 * @return
	 */
	@RequestMapping(value="/listAll",method=RequestMethod.GET)
	public Object listAllElder(){
		List<Elder> elders=template.listElder();
		if(elders.size()==0) return ElderAttribute.LISTELDER_NULL;
		return elders;
	}
	/**
	 * ����һ���µ�id���������
	 * @return
	 */
	@RequestMapping(value="/newID",method=RequestMethod.GET)
	public String getNewElderID() {
		String newID=template.getNewID();
		return newID;
	}
	//�½�������Ϣ
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String createElder(@RequestBody Elder celder) {
		if(template.createElder(celder)) return ElderAttribute.CREATE_SUCCESS;
		return ElderAttribute.CREATE_FAILD;
	}
	//�޸�������Ϣ�������������������������������������÷���ֻ���Ա�����Ա���ã�������������
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String amendElder(@RequestBody Elder aelder) {
		if(template.getElder(aelder.getElderID())==null) return ElderAttribute.UPDATE_NULL;
		if(template.updateElder(aelder)) return ElderAttribute.UPDATE_SUCCESS;
		return ElderAttribute.UPDATE_FAILD;
	}
	//ɾ��������Ϣ�������������������������������������÷���ֻ���Ա�����Ա���ã�������������
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String deleteElder(@RequestBody Elder delder) {
		Elder helper=template.getElder(delder.getElderID());
		if(helper==null) return ElderAttribute.DELETE_NULL;
		if(!delder.equals(helper)) return ElderAttribute.DELETE_FAILD;  //�����Ϣ�����ݿⲻƥ�䣬��ֹɾ��
		if(template.deleteElder(delder.getElderID())) return ElderAttribute.DELETE_SUCCESS;
		return ElderAttribute.DELETE_FAILD;
	}
	
}
