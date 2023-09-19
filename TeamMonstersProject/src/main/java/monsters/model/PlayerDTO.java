package monsters.model;

import java.util.Date;

public class PlayerDTO {
	 // num 
    private int pl_id;
    // name 
    private String pl_name;
    // position 
    private int pl_position;
    // birth 
    private Date pl_birth;
    // backNo 
    private int pl_backNo;
    // phys 
    private String pl_physical;
    // PnH 
    private int pl_PnH;
    // subject 
    private String pl_subject;
    // contents 
    private String pl_contents;
    // regdate 
    private Date regdate;
    // img 
    private String pl_imgName;
    // like 
    private int pl_like;
	
    //생성자
    public PlayerDTO() {
		super();
	}

    //getter
	public int getPl_id() {
		return pl_id;
	}
	public String getPl_name() {
		return pl_name;
	}
	public int getPl_position() {
		return pl_position;
	}
	public Date getPl_birth() {
		return pl_birth;
	}
	public int getPl_backNo() {
		return pl_backNo;
	}
	public String getPl_physical() {
		return pl_physical;
	}
	public int getPl_PnH() {
		return pl_PnH;
	}
	public String getPl_subject() {
		return pl_subject;
	}
	public String getPl_contents() {
		return pl_contents;
	}
	public Date getRegdate() {
		return regdate;
	}
	public String getPl_imgName() {
		return pl_imgName;
	}
	public int getPl_like() {
		return pl_like;
	}

	//setter
	public void setPl_id(int pl_id) {
		this.pl_id = pl_id;
	}
	public void setPl_name(String pl_name) {
		this.pl_name = pl_name;
	}
	public void setPl_position(int pl_position) {
		this.pl_position = pl_position;
	}
	public void setPl_birth(Date pl_birth) {
		this.pl_birth = pl_birth;
	}
	public void setPl_backNo(int pl_backNo) {
		this.pl_backNo = pl_backNo;
	}
	public void setPl_physical(String pl_physical) {
		this.pl_physical = pl_physical;
	}
	public void setPl_PnH(int pl_PnH) {
		this.pl_PnH = pl_PnH;
	}
	public void setPl_subject(String pl_subject) {
		this.pl_subject = pl_subject;
	}
	public void setPl_contents(String pl_contents) {
		this.pl_contents = pl_contents;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public void setPl_imgName(String pl_imgName) {
		this.pl_imgName = pl_imgName;
	}
	public void setPl_like(int pl_like) {
		this.pl_like = pl_like;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pl_id;
		result = prime * result + ((pl_name == null) ? 0 : pl_name.hashCode());
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
		PlayerDTO other = (PlayerDTO) obj;
		if (pl_id != other.pl_id)
			return false;
		if (pl_name == null) {
			if (other.pl_name != null)
				return false;
		} else if (!pl_name.equals(other.pl_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlayerDTO [pl_id=" + pl_id + ", pl_name=" + pl_name + ", pl_position=" + pl_position + ", pl_birth="
				+ pl_birth + ", pl_backNo=" + pl_backNo + ", pl_physical=" + pl_physical + ", pl_PnH=" + pl_PnH
				+ ", pl_subject=" + pl_subject + ", pl_contents=" + pl_contents + ", regdate=" + regdate
				+ ", pl_imgName=" + pl_imgName + ", pl_like=" + pl_like + "]";
	}

	
    
    
}
