import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class DungChung {
	public static Connection cn;
	public void KetNoi() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://DESKTOP-5K2RFED\\SQLEXPRESS:1433;databaseName=LNT;user=sa;password=09112000";
		cn = DriverManager.getConnection(url);
	}
	public ResultSet getBang(String tb) throws Exception {
		String sql = "select * from "+tb;
		PreparedStatement cmd = cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		return rs;
	}
	public DefaultTableModel napbang(String tb) throws Exception {
		ResultSet rs = getBang(tb);
		ResultSetMetaData mt = rs.getMetaData();
		int socot = mt.getColumnCount();
		DefaultTableModel mh = new DefaultTableModel();
		for (int i=1; i<=socot; i++) {
			mh.addColumn(mt.getColumnName(i));
		}
		while (rs.next()) {
			Object[] t = new Object[socot];
			for (int i=1; i<=socot; i++) {
				t[i-1] = rs.getString(i);
			}
			mh.addRow(t);
		}
		return mh;
	}
	public int check (ArrayList<String[]> t, String key) {
		for (int i=0; i<t.size(); i++)
			if (key.equals(t.get(i)[0]))
				return 0;
		return 1;
	}
	public ArrayList<String[]> naptuSQL() throws Exception{
		ArrayList<String[]> ds = new ArrayList<String[]>();
		String sql = "select * from NHANVIEN";
		PreparedStatement cmd = DungChung.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		ResultSetMetaData mt = rs.getMetaData();
		int socot = mt.getColumnCount();
		while (rs.next()) {
			String[] t = new String[socot];
			for (int i=1; i<=socot; i++) {
				t[i-1] = rs.getString(i);
			}
			ds.add(t);
		}
		return ds;
	}
	public int Them(String manv, String tennv, String loaihopdong, float hsl) throws Exception {
		//Tạo câu lệnh sql để lấy về tất cả đơn vị
		String sql = "insert into NHANVIEN (MaNV, TenNV, LoaiHopDong, HeSoLuong) values (?,?,?,?)\r\n";
		//Tạo ra câu lệnh PrepareStatement
		PreparedStatement cmd = DungChung.cn.prepareStatement(sql);
		//Truyền tham số vào câu lệnh
		cmd.setString(1, manv);
		cmd.setString(2, tennv);
		cmd.setString(3, loaihopdong);
		cmd.setFloat(4, hsl);
		//Cho thực hiện câu lệnh
		return cmd.executeUpdate();
	}
	public DefaultTableModel hienthi() throws Exception{
		String sql = "select * from NHANVIEN where LoaiHopDong = \'chinhthuc\' ";
		//b2: tạo câu lệnh
		PreparedStatement cmd = cn.prepareStatement(sql);
		//b3: thực hiện câu lệnh
		ResultSet rs = cmd.executeQuery();
		ResultSetMetaData mt = rs.getMetaData();
		
		int socot = mt.getColumnCount();
		DefaultTableModel mh = new DefaultTableModel();
		//lấy ra tên cột
		for (int i=1; i<=socot; i++) {
			mh.addColumn(mt.getColumnName(i));
		}
		while (rs.next()) {
			Object[] t = new Object[socot];
			for (int i=1; i<=socot; i++) {
				t[i-1] = rs.getString(i);
			}
			mh.addRow(t);
		}
		return mh;
	}
}
