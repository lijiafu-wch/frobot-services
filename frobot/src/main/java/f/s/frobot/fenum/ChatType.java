package f.s.frobot.fenum;

 /**
  * 机器人聊天类型
  * @author lijiafu
  * @date 2019/12/31 16:28
  */
public enum ChatType {

	TEXT("TEXT","Text"),//文本类型
	RECOMMED("RECOMMED","Recommend"),//推荐问题
	KNOWLEDGE("KNOWLEDGE","Knowledge");//匹配到知识库
	
	private final String code;

	private final String value;

	private ChatType(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public String getCode() {
		return code;
	}

	public static void main(String[] args) {
		System.out.println(ChatType.TEXT.value);
	}

}
