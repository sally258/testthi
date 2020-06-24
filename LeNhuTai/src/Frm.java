import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Frm extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm frame = new Frm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ArrayList<String[]> ds = new ArrayList<String[]>();
	public ArrayList<String[]> d = new ArrayList<String[]>();
	public Frm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(48, 136, 333, 197);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane, null);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnReadFile = new JButton("Read File");
		btnReadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel mh = new DefaultTableModel();
					mh.addColumn("Ma NV");
					mh.addColumn("Ten NV");
					mh.addColumn("Loai hop dong");
					mh.addColumn("He so luong");
					FileReader f = new FileReader("ds.txt");
					BufferedReader r = new BufferedReader(f);
					while (true) {
						String st = r.readLine();
						if (st == null || st == "")
							break;
						String[] t = st.split("[;]");
						if (t.length==4 && ("chinhthuc".equals(t[2])==true || "hopdong".equals(t[2])==true))
						{
							mh.addRow(t);
						}
						table.setModel(mh);
					}
					r.close();
				} catch (Exception e2) {
					e2.printStackTrace();
					// TODO: handle exception
				}
			}
		});
		try {
			FileReader f = new FileReader("ds.txt");
			BufferedReader r = new BufferedReader(f);
			while (true) {
				String st = r.readLine();
				if (st == null || st == "")
					break;
				String[] t = st.split("[;]");
				if (t.length==4 && ("chinhthuc".equals(t[2])==true || "hopdong".equals(t[2])==true))
				{
					ds.add(t);
				}
			}
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		btnReadFile.setBounds(128, 4, 126, 23);
		contentPane.add(btnReadFile);
		
		JButton btnReadArraylist = new JButton("Read ArrayList");
		btnReadArraylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel mh = new DefaultTableModel();
				mh.addColumn("Ma NV");
				mh.addColumn("Ten NV");
				mh.addColumn("Loai hop dong");
				mh.addColumn("He so luong");
				for (String[] i: ds) {
					mh.addRow(i);
				}
				table.setModel(mh);
			}
		});
		btnReadArraylist.setBounds(128, 38, 126, 23);
		contentPane.add(btnReadArraylist);
		
		JButton btnNewButton = new JButton("Add DataBase");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DungChung dc = new DungChung();
					dc.KetNoi();
					d = dc.naptuSQL();
					for (int i=0; i<ds.size(); i++)
						if (dc.check(d, ds.get(i)[0])==1)
							dc.Them(ds.get(i)[0], ds.get(i)[1], ds.get(i)[2], Float.parseFloat(ds.get(i)[3]));
				} catch (Exception e2) {
					e2.printStackTrace();
					// TODO: handle exception
				}
			}
		});
		btnNewButton.setBounds(128, 72, 126, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Read SQL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DungChung dc = new DungChung();
					dc.KetNoi();
					table.setModel(dc.hienthi());
				} catch (Exception e2) {
					e2.printStackTrace();
					// TODO: handle exception
				}
			}
		});
		btnNewButton_1.setBounds(128, 106, 126, 23);
		contentPane.add(btnNewButton_1);
	}
}
