package monsters.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import monsters.model.ConnectionPool;
import monsters.model.MemberDTO;

public class MemberDAO {

	private String DBUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String DBId = "hr";
	private String DBPwd = "hr";
	private static final String jdbcclass = "oracle.jdbc.OracleDriver";   
	private ConnectionPool pool;
	private int result;
	private MemberDTO user; // 웹 브라우저로부터 받은 값을 가지고 만들어진 MemberDTO의 객체를 할당.
	
	//생성자
	public MemberDAO() {
		super();
		
		try {
			Class.forName(jdbcclass); 
		} catch (Exception e) {
			e.printStackTrace(); // 발생한 오류가 무엇인지 출력해줌.
		}
		
		try {
			pool = ConnectionPool.getInstance(DBUrl,DBId,DBPwd,3,5,true,500);
		} catch (Exception e) {
			// throws를 하면 웹쪽에서 처리해야하기 때문에 데이터 모델 단인 Service에서 처리해야함.
			e.printStackTrace();
		}
	}
	
	public MemberDTO getUser() {
		return user;
	}
	//MemberDTO 객체를 생성하는 set메서드
	public void setUser(MemberDTO user) {
		this.user = user;
	}
	
	//로그인 기능(select)
	public int login() throws SQLException {
		// 커넥션 생성 (pool로부터 connection을 가져옴.
		Connection conn = pool.getConnection();
		// sql문 작성
		String sql = "SELECT * FROM TBL_MEMBER WHERE mem_id = ?";
		//Statement 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//sql ? 값에 MemberDTO 객체의 id 집어넣음.
		pstmt.setString(1, user.getMem_id());
		
		//ResultSet에 쿼리 실행 값을 할당
		ResultSet rs = pstmt.executeQuery();
		
		//데이터 베이스 오류 시, -2 반환
		int result = -2; 
		//rs.next()를 통해 sql을 통한 값이 저장되었는지 확인
		if(rs.next()) {
			//아이디 비밀번호가 일치하면 1 반환
			if(rs.getString("mem_pwd").equals(user.getMem_pwd())) {
				//select문으로 가져온 레코드로 MemberDTO 객체 생성
				user = new MemberDTO(rs.getString("mem_id"), rs.getString("mem_role"), rs.getString("mem_pwd"), rs.getString("mem_name"),
									rs.getString("mem_email"), rs.getString("mem_phone"));  
				result = 1;
			}else { // 비밀번호가 다르면 0 반환
				result = 0;
			}
		}else { //아이디가 존재하지 않으면 -1 반환
			result = -1;
		}
		
		rs.close(); // ResultSet close
		pstmt.close(); // Statement close
		pool.releaseConnection(conn); // 커넥션 반납
		return result;
		
	}
	
	//회원가입 기능(insert)
	public int register() throws SQLException {
		// 커넥션 생성 (pool로부터 connection을 가져옴.
		Connection conn = pool.getConnection();
		// sql문 작성
		String sql = "INSERT INTO TBL_MEMBER\r\n"
				+ "    (mem_id, mem_role, mem_pwd, mem_name, mem_email, mem_phone)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		//Statement 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//sql ? 값에 MemberDTO 객체의 필드 값을 집어넣음.
		pstmt.setString(1, user.getMem_id());
		pstmt.setString(2, user.getMem_role());
		pstmt.setString(3, user.getMem_pwd());
		pstmt.setString(4, user.getMem_name());
		pstmt.setString(5, user.getMem_email());
		pstmt.setString(6, user.getMem_phone());
		
		//result에 쿼리 실행 값을 할당
		result = pstmt.executeUpdate();
		
		
		pstmt.close(); // Statement close
		pool.releaseConnection(conn); // 커넥션 반납
		return result;
	}
	
	//id 중복체크 기능
	public int regDupCheck(String id) throws SQLException {
		//result가 -1이면 에러 발생
		result = -1;
		// 커넥션 생성 (pool로부터 connection을 가져옴.
		Connection conn = pool.getConnection();
		// sql문 작성
		String sql = "SELECT * FROM TBL_MEMBER WHERE mem_id = ?";
		//Statement 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//sql ? 값에 MemberDTO 객체의 id 집어넣음.
		pstmt.setString(1, id);
			
		//ResultSet에 쿼리 실행 값을 할당
		ResultSet rs = pstmt.executeQuery();
			
		//rs.next()를 통해 sql을 통한 값이 저장되었는지 확인
		if(rs.next()) {
			//result가 0이면 사용 불가능한 ID
			result = 0;
		}else { //result가 1이면 사용 가능한 ID
			result = 1;
		}
		System.out.println("아이디 중복체크결과 : "+result);
			
		rs.close(); // ResultSet close
		pstmt.close(); // Statement close
		pool.releaseConnection(conn); // 커넥션 반납
			
		return result;
			
	}
	
	
	
	
}
