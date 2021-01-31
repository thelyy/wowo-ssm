package com.yc.wowo.dto;

public class ResultDTO {
	private int code; // 响应吗
	private String msg; //响应信息
	private Object obj; //响应数据
	
	public ResultDTO() {
		super();
	}
	
	public ResultDTO(int code) {
		this.code = code;
	}

	public ResultDTO(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public ResultDTO(int code, Object obj) {
		this.code = code;
		this.obj = obj;
	}

	public ResultDTO(int code, String msg, Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "ResultDTO [code=" + code + ", msg=" + msg + ", obj=" + obj + "]";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((obj == null) ? 0 : obj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultDTO other = (ResultDTO) obj;
		if (code != other.code)
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (this.obj == null) {
			if (other.obj != null)
				return false;
		} else if (!this.obj.equals(other.obj))
			return false;
		return true;
	}
	
	
}
