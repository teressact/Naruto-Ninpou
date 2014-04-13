package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SpinnerListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.BonusType;
import model.Item;
import model.ItemType;
import model.SkillType;
import controller.ItemControl;

public class Items extends JPanel {

	private static final long serialVersionUID = 1L;

	private Item actualItem;
	private JTextField name;
	private JTextArea description;
	private JSpinner hotkey;
	private JComboBox<ItemType> type;
	private JTable bonusTable;
	private JTable skillTable;
	private JButton bonusAdd, bonusRemove, skillAdd, skillRemove, copyDescription, copyBuy, save, newItem, removeItem;
	private JList<Item>[] listItems;
	private JTabbedPane tabbedList;
	
	@SuppressWarnings("unchecked")
	public void initComponents() { 
		name = new JTextField(15);
		description = new JTextArea(5, 15);
		description.setLineWrap(true);
		SpinnerListModel hotkeyModel = new SpinnerListModel(new Object[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"});
		hotkey = new JSpinner(hotkeyModel);
		type = new JComboBox<>(ItemType.values());
		DefaultTableModel bonusModel = new DefaultTableModel(null, new String[]{"Type", "Value", "Is percentage?"}) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] types = new Class[] { JComboBox.class, Integer.class, Boolean.class };
			@SuppressWarnings("rawtypes")
			public Class getColumnClass(int columnIndex) {
		        return types [columnIndex];
		    }
		};
		bonusTable = new JTable(bonusModel);
		bonusTable.setRowHeight(20);
		TableColumn bonusBoxColumn = bonusTable.getColumnModel().getColumn(0);
		bonusBoxColumn.setCellEditor(new DefaultCellEditor(new JComboBox<BonusType>(BonusType.values())));
		bonusBoxColumn.setCellRenderer(new MyComboBoxRenderer(BonusType.values()));
		DefaultTableModel skillModel = new DefaultTableModel(null, new String[]{"Name", "Description", "Type"}) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] types = new Class[] { String.class, String.class, JComboBox.class };
			@SuppressWarnings("rawtypes")
			public Class getColumnClass(int columnIndex) {
		        return types [columnIndex];
		    }
		};
		skillTable = new JTable(skillModel);
		skillTable.setRowHeight(20);
		TableColumn skillBoxColumn = skillTable.getColumnModel().getColumn(2);
		skillBoxColumn.setCellEditor(new DefaultCellEditor(new JComboBox<SkillType>(SkillType.values())));
		skillBoxColumn.setCellRenderer(new MyComboBoxRenderer(SkillType.values()));
		bonusAdd = new StandardButton("Add");
		bonusRemove = new StandardButton("Remove");
		skillAdd = new StandardButton("Add");
		skillRemove = new StandardButton("Remove");
		listItems = new JList[ItemType.values().length];
		for (int i = 0; i < ItemType.values().length; i++) {
			listItems[i] = new JList<>();
		}
		updateList();
		copyDescription = new StandardButton("Copy description");
		copyBuy = new StandardButton("Copy buy description");
		save = new StandardButton("Save");
		newItem = new StandardButton("New item");
		removeItem = new StandardButton("Remove item");
	}
	
	private void setLayout() { 
		JPanel pageStart = new JPanel(new GridBagLayout());
		pageStart.setOpaque(true);
		JPanel pageCenter = new JPanel(new GridBagLayout());
		pageCenter.setOpaque(true);
		JPanel pageEnd = new JPanel(new GridBagLayout());
		pageEnd.setOpaque(true);
		JPanel lineEnd = new JPanel();
		lineEnd.setOpaque(true);
		//---------------------------------------------------
		GridBagConstraints c;
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		pageStart.add(new JLabel("Name:"), c);
		c.gridy++;
		pageStart.add(name, c);
		c.gridy++;
		pageStart.add(new JLabel("Description:"), c);
		c.gridy++;
		pageStart.add(new JScrollPane(description), c);
		c.gridy++;
		c.gridwidth = 1;
		pageStart.add(new JLabel("Hotkey:  "), c);
		c.gridx++;
		pageStart.add(hotkey, c);
		c.gridy++;
		c.gridx = 0;
		pageStart.add(new JLabel("Type:  "), c);
		c.gridx++;
		pageStart.add(type, c);
		//------------------------------------
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		pageCenter.add(new JLabel("Bonus:"), c);
		c.gridy++;
		JScrollPane bonusScroll = new JScrollPane(bonusTable);
		bonusScroll.setPreferredSize(new Dimension(300, 100));
		pageCenter.add(bonusScroll, c);
		JPanel bonusButtons = new JPanel();
		bonusButtons.setOpaque(true);
		bonusButtons.add(bonusAdd);
		bonusButtons.add(bonusRemove);
		c.gridy++;
		pageCenter.add(bonusButtons, c);
		c.gridy++;
		pageCenter.add(new JLabel("Skills:"), c);
		JScrollPane skillsScroll = new JScrollPane(skillTable);
		skillsScroll.setPreferredSize(new Dimension(300, 100));
		c.gridy++;
		pageCenter.add(skillsScroll, c);
		JPanel skillsButtons = new JPanel();
		skillsButtons.setOpaque(true);
		skillsButtons.add(skillAdd);
		skillsButtons.add(skillRemove);
		c.gridy++;
		pageCenter.add(skillsButtons, c);
		c.gridx = 0;
		c.gridy = 0;
		pageEnd.add(new JLabel("Items:"), c);
		c.gridy++;
		tabbedList = new JTabbedPane();
		tabbedList.setUI(new PPTTabbedPaneUI());
		tabbedList.setOpaque(false);
		for (ItemType type : ItemType.values()) {
			tabbedList.addTab(type.toString(), listItems[type.ordinal()]);
		}
		JScrollPane tabbedScroll = new JScrollPane(tabbedList);
		tabbedScroll.setPreferredSize(new Dimension(300, 200));
		pageEnd.add(tabbedScroll, c);
		c.gridy++;
		JPanel buttonsList = new JPanel();
		buttonsList.setOpaque(true);
		buttonsList.add(newItem);
		buttonsList.add(removeItem);
		pageEnd.add(buttonsList, c);
		//---------------------------------
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.NORTH;
		add(pageStart, c);
		c.gridx++;
		add(pageCenter, c);
		c.gridx++;
		add(pageEnd, c);
		lineEnd.add(copyDescription);
		lineEnd.add(copyBuy);
		lineEnd.add(save);
		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.BOTH;
		add(lineEnd, c);
		setOpaque(true);
	}
	
	private void setListeners() { 
		bonusAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((DefaultTableModel)bonusTable.getModel()).addRow(new Object[]{null, 0, false});
			}
		});
		bonusRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				while (bonusTable.getSelectedRow() != -1)
					((DefaultTableModel)bonusTable.getModel()).removeRow(bonusTable.getSelectedRow());
			}
		});
		skillAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((DefaultTableModel)skillTable.getModel()).addRow(new Object[]{"", "", null});
			}
		});
		skillRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				while (skillTable.getSelectedRow() != -1)
					((DefaultTableModel)skillTable.getModel()).removeRow(skillTable.getSelectedRow());
			}
		});
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveItem();
			}
		});
		newItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newItem();
			}
		});
		removeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeItem();
				newItem();
			}
		});
		for (final JList<Item> list : listItems) {
			list.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					int index = list.getSelectedIndex();
					if (index != -1) {
						Item item = list.getModel().getElementAt(index);
						loadItem(item);
					}
				}
			});
			list.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void focusGained(FocusEvent arg0) {
					int index = list.getSelectedIndex();
					if (index != -1) {
						Item item = list.getModel().getElementAt(index);
						loadItem(item);
					}
				}
			});
		}
		copyDescription.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveItem();
				if (actualItem != null) {
					StringSelection stringSelection = new StringSelection(actualItem.generateDescription());
					Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
					clpbrd.setContents(stringSelection, null);
				}
			}
		});
		copyBuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveItem();
				if (actualItem != null) {
					StringSelection stringSelection = new StringSelection(actualItem.generateBuyDescription());
					Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
					clpbrd.setContents(stringSelection, null);
				}
			}
		});
		type.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tabbedList.setSelectedIndex(type.getSelectedIndex());
			}
		});
	}
	
	public Items() { 
		super(new GridBagLayout());
		initComponents();
		setLayout();
		setListeners();
	}
	
	private void clearTable(JTable table) { 
		for (int i = table.getRowCount() - 1; i >= 0; i--) {
			((DefaultTableModel)table.getModel()).removeRow(i);
		}
	}
	
	private void newItem() { 
		if (isModified()) {
			int saveItem = JOptionPane.showConfirmDialog(this, "Modifications will be lost. Do you want to save it?");
			if (saveItem == 0) {
				saveItem();
			}
		}
		this.actualItem = null;
		name.setText("");
		description.setText("");
		hotkey.setValue("A");
		type.setSelectedIndex(0);
		clearTable(bonusTable);
		clearTable(skillTable);
		tabbedList.setSelectedIndex(0);
	}
	
	private void loadItem(Item item) {
		if (isModified()) {
			int saveItem = JOptionPane.showConfirmDialog(this, "Modifications will be lost. Do you want to save it?");
			if (saveItem == 0) {
				saveItem();
			}
		}
		this.actualItem = item;
		name.setText(item.getName());
		description.setText(item.getDescription());
		hotkey.setValue(item.getHotkey());
		type.setSelectedItem(item.getType());
		clearTable(bonusTable);
		for (Object[] value : item.getBonusTable().getAllValues()) {
			BonusType type = (BonusType)value[0];
			int itemValue = (int)value[1];
			boolean isPercentage = (boolean)value[2];
			((DefaultTableModel)bonusTable.getModel()).addRow(new Object[]{type, itemValue, isPercentage});
		}
		clearTable(skillTable);
		for (Object[] value : item.getSkillTable().getAllValues()) {
			String name = (String)value[0];
			String description = (String)value[1];
			SkillType type = (SkillType)value[2];
			((DefaultTableModel)skillTable.getModel()).addRow(new Object[]{name, description, type});
		}
	}
	
	private void saveItem() {
		if (name.getText().trim().equals("")) return;
		if (actualItem != null) {
			ItemControl.remove(actualItem);
		}
		String itemName = name.getText();
		String itemDescription = description.getText();
		String itemHotkey = (String)hotkey.getValue();
		ItemType itemType = (ItemType)type.getSelectedItem();
		Item item = new Item(itemName, itemDescription, itemHotkey, itemType);
		for (int i = 0; i < bonusTable.getRowCount(); i++) {
			BonusType bonusType = (BonusType)bonusTable.getValueAt(i, 0);
			int bonusValue = (int)bonusTable.getValueAt(i, 1);
			boolean bonusPercentage = (boolean)bonusTable.getValueAt(i, 2);
			item.getBonusTable().add(bonusType, bonusValue, bonusPercentage);
		}
		for (int i = 0; i < skillTable.getRowCount(); i++) {
			String skillName = (String)skillTable.getValueAt(i, 0);
			String skillDescription = (String)skillTable.getValueAt(i, 1);
			SkillType skillType = (SkillType)skillTable.getValueAt(i, 2);
			item.getSkillTable().add(skillName, skillDescription, skillType);
		}
		ItemControl.add(item);
		actualItem = item;
		updateList();
	}
	
	private boolean isModified() { 
		if (actualItem == null) return false;
		return (actualItem.getName() != null && !actualItem.getName().equals(name.getText())) || (actualItem.getDescription() != null && !actualItem.getDescription().equals(description.getText()))
				|| (actualItem.getHotkey() != null && !actualItem.getHotkey().equals(hotkey.getValue())) || (actualItem.getType() != null && actualItem.getType() != type.getSelectedItem());
	}
	
	private void updateList() {
		for (final ItemType type : ItemType.values()) {
			JList<Item> myList = listItems[type.ordinal()];
			ListModel<Item> model = new ListModel<Item>() {
				private List<Item> list = ItemControl.getList(type);
				@Override
				public void addListDataListener(ListDataListener l) { }

				@Override
				public Item getElementAt(int index) {
					return list.get(index);
				}

				@Override
				public int getSize() {
					return list.size();
				}

				@Override
				public void removeListDataListener(ListDataListener l) { }
			};
			myList.setModel(model);
			if (actualItem != null)
				myList.setSelectedValue(actualItem, true);
		}
		
	}
	
	private void removeItem() { 
		//int index = listOfItems.getSelectedIndex();
		if (actualItem != null) {
			ItemControl.remove(actualItem);
			updateList();
		}
	}
	
}
