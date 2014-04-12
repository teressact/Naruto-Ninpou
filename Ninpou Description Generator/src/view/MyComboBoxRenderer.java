package view;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyComboBoxRenderer extends JComboBox<Object> implements TableCellRenderer {
	
	private static final long serialVersionUID = 1L;
	public MyComboBoxRenderer(Object[] values) {
		super(values);
	}       
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {       
		//table.setRowHeight(20);
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}                
		setSelectedItem(value);
		return this;
	}

}