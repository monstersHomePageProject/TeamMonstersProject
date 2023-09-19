package monsters.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayerDAO {

	private String DBUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String DBId = "hr";
	private String DBPwd = "hr";
	private static final String jdbcclass = "oracle.jdbc.OracleDriver";
	private ConnectionPool pool;
	private int result;
	private PlayerDTO player; // 웹 브라우저로부터 받은 값을 가지고 만들어진 MemberDTO의 객체를 할당.

	public PlayerDAO() {
		super();

		try {
			Class.forName(jdbcclass);
		} catch (Exception e) {
			e.printStackTrace(); // 발생한 오류가 무엇인지 출력해줌.
		}

		try {
			pool = ConnectionPool.getInstance(DBUrl, DBId, DBPwd, 3, 5, true, 500);
		} catch (Exception e) {
			// throws를 하면 웹쪽에서 처리해야하기 때문에 데이터 모델 단인 Service에서 처리해야함.
			e.printStackTrace();
		}
	}

	public PlayerDTO getPlayer() {
		return player;
	}

	public void setPlayer(PlayerDTO player) {
		this.player = player;
	}

	// 포지션별 선수 리스트 (Select)
	public ArrayList<PlayerDTO> selectPosition() throws SQLException {

		Connection conn = pool.getConnection();
		String sql = "select * from TBL_Player where pl_position= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		// sql ? 값에 PlayerDTO 객체의 id 집어넣음.
		pstmt.setInt(1, player.getPl_position());

		// execute (sql)
		ResultSet result = pstmt.executeQuery();

		ArrayList<PlayerDTO> playerList = new ArrayList(); // DB에 저장된 레코드들을 담을 리스트 생성
		PlayerDTO player = null;
		// get data
		while (result.next()) {
			player = new PlayerDTO(result.getInt("pl_id"), result.getString("pl_name"), result.getInt("pl_position"),
					result.getDate("pl_birth"), result.getInt("pl_backNo"), result.getString("pl_physical"),
					result.getInt("Pl_PnH"), result.getString("pl_subject"), result.getString("pl_contents"),
					result.getDate("regdate"), result.getString("pl_imgname"), result.getInt("pl_like"));

			playerList.add(player);
		}

		result.close();
		pstmt.close();
		pool.releaseConnection(conn); // 커넥션을 반환

		return playerList;

	}

	// 선수 디테일 (Select)
	public PlayerDTO playerDetail() throws SQLException {
		Connection conn = pool.getConnection();
		// sql문 작성
		String sql = "SELECT * FROM TBL_PLAYER WHERE pl_id = ?";
		// Statement 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// sql ? 값에 PlayerDTO 객체의 필드 값을 집어넣음.
		pstmt.setInt(1, player.getPl_id());

		// result에 쿼리 실행 값을 할당
		ResultSet result = pstmt.executeQuery();

		PlayerDTO player = null;

		while (result.next()) {
			player = new PlayerDTO(result.getInt("pl_id"), result.getString("pl_name"), result.getInt("pl_position"),
					result.getDate("pl_birth"), result.getInt("pl_backNo"), result.getString("pl_physical"),
					result.getInt("Pl_PnH"), result.getString("pl_subject"), result.getString("pl_contents"),
					result.getDate("regdate"), result.getString("pl_imgname"), result.getInt("pl_like"));
		}
		// 선수 정보 출력(Console)
		System.out.println(player);

		pstmt.close(); // Statement close
		pool.releaseConnection(conn); // 커넥션 반납

		return player;
	}

	// 선수 등록 (Insert)
	public int playerInsert() throws SQLException {
		Connection conn = pool.getConnection();
		// sql문 작성
		String sql = "INSERT INTO TBL_Player\r\n"
				+ "    (pl_id, pl_name, pl_position, pl_birth, pl_backNo, pl_physical, Pl_PnH, pl_subject, pl_contents, regdate, pl_imgname, pl_like)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		// Statement 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// sql ? 값에 MemberDTO 객체의 필드 값을 집어넣음.
		pstmt.setInt(1, player.getPl_id());
		pstmt.setString(2, player.getPl_name());
		pstmt.setInt(3, player.getPl_position());
		pstmt.setDate(4, (Date) player.getPl_birth());
		pstmt.setInt(5, player.getPl_backNo());
		pstmt.setString(6, player.getPl_physical());
		pstmt.setInt(7, player.getPl_PnH());
		pstmt.setString(8, player.getPl_subject());
		pstmt.setString(9, player.getPl_contents());
		pstmt.setDate(10, (Date) player.getRegdate());
		pstmt.setString(11, player.getPl_imgName());
		pstmt.setInt(12, player.getPl_like());

		// result에 쿼리 실행 값을 할당
		result = pstmt.executeUpdate();

		pstmt.close(); // Statement close
		pool.releaseConnection(conn); // 커넥션 반납
		return result;
	}

	// 선수정보변경 (Update)
	public int playerUpdate() throws SQLException {

		Connection conn = pool.getConnection();
		// sql문 작성
		String sql = "UPDATE TBL_Player SET\r\n" + "    pl_name = ?, \r\n" + "    pl_position = ?, \r\n"
				+ "    pl_birth = ?, \r\n" + "    pl_backNo = ?, \r\n" + "    pl_physical = ?, \r\n"
				+ "    Pl_PnH = ?, \r\n" + "    pl_subject = ?, \r\n" + "    pl_contents = ?, \r\n"
				+ "    pl_imgname = ?, \r\n" + "    pl_like = ?\r\n" + "WHERE pl_id = ?";
		// Statement 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// sql ? 값에 PlayerDTO 객체의 필드 값을 집어넣음.
		pstmt.setString(1, player.getPl_name());
		pstmt.setInt(2, player.getPl_position());
		pstmt.setDate(3, (java.sql.Date) player.getPl_birth());
		pstmt.setInt(4, player.getPl_backNo());
		pstmt.setString(5, player.getPl_physical());
		pstmt.setInt(6, player.getPl_PnH());
		pstmt.setString(7, player.getPl_subject());
		pstmt.setString(8, player.getPl_contents());
		pstmt.setDate(9, (java.sql.Date) player.getRegdate());
		pstmt.setString(10, player.getPl_imgName());
		pstmt.setInt(11, player.getPl_like());
		pstmt.setInt(12, player.getPl_id());

		// result에 쿼리 실행 값을 할당
		result = pstmt.executeUpdate();

		pstmt.close(); // Statement close
		pool.releaseConnection(conn); // 커넥션 반납
		return result;

	}

	// 선수 삭제 (Delete)
	public int playerDelete() throws SQLException {
		Connection conn = pool.getConnection();

		String sql = "DELETE * FROM TBL_PLAYER WHERE pl_id = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, player.getPl_id());
		// result에 쿼리 실행 값을 할당

		result = pstmt.executeUpdate();

		pstmt.close(); // Statement close
		pool.releaseConnection(conn); // 커넥션 반납
		return result;
	}
}
