package ltd.thzs.bili.sprider.utils;

import com.alibaba.fastjson.JSONObject;

public class JsonFind {
	JSONObject jso;
	public JsonFind(String jsonstr) {
		this.jso=JSONObject.parseObject(jsonstr);
	}
	public JSONObject get(String xpath) {
		String[] m=xpath.split("/");
		JSONObject temp=jso.getJSONObject(m[0]);
		for(int i=1;i<m.length;i++) {
			temp=temp.getJSONObject(m[i]);
		}
		return temp;
	}
}
