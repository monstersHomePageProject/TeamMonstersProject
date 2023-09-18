package monsters.model;

public class MemberDTO {
	private String mem_id;
	private String mem_role;
	private String mem_pwd;
	private String mem_name;
	private String mem_email;
	private String mem_phone;
	
	public MemberDTO() {
		super();
	}

	/**
	 * @param mem_id
	 * @param mem_role
	 * @param mem_pwd
	 * @param mem_name
	 * @param mem_email
	 * @param mem_phone
	 */
	public MemberDTO(String mem_id, String mem_role, String mem_pwd, String mem_name, String mem_email,
			String mem_phone) {
		super();
		this.mem_id = mem_id;
		this.mem_role = mem_role;
		this.mem_pwd = mem_pwd;
		this.mem_name = mem_name;
		this.mem_email = mem_email;
		this.mem_phone = mem_phone;
	}
	
	public String getMem_id() {
		return mem_id;
	}
	public String getMem_role() {
		return mem_role;
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public String getMem_name() {
		return mem_name;
	}
	public String getMem_email() {
		return mem_email;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public void setMem_role(String mem_role) {
		this.mem_role = mem_role;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	@Override
	public String toString() {
		return "MemberDTO [mem_id=" + mem_id + ", mem_role=" + mem_role + ", mem_pwd=" + mem_pwd + ", mem_name="
				+ mem_name + ", mem_email=" + mem_email + ", mem_phone=" + mem_phone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mem_id == null) ? 0 : mem_id.hashCode());
		result = prime * result + ((mem_pwd == null) ? 0 : mem_pwd.hashCode());
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
		MemberDTO other = (MemberDTO) obj;
		if (mem_id == null) {
			if (other.mem_id != null)
				return false;
		} else if (!mem_id.equals(other.mem_id))
			return false;
		if (mem_pwd == null) {
			if (other.mem_pwd != null)
				return false;
		} else if (!mem_pwd.equals(other.mem_pwd))
			return false;
		return true;
	}
	
}
