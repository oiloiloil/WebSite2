package website.model;

public class ResponseMessage {
	private String message;
	private String status;
	
	public ResponseMessage(String message, String status) {
		this.message = message;
		this.status = status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return this.message;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}
}
