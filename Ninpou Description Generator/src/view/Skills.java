package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.AreaOfEffectType;
import model.BonusType;
import model.DamageType;
import model.Skill;
import model.SkillHotkey;
import controller.SkillControl;

public class Skills extends JPanel {

	private static final long serialVersionUID = 1L;

	private Skill actualSkill;
	
	private JTextField hero, name, search;
	private JTextArea description;
	private JSpinner baseRange, rangeFactor, baseCooldown, cooldownFactor, areaOfEffect;
	private JComboBox<SkillHotkey> hotkey;
	private JComboBox<AreaOfEffectType> AoEType;
	private JButton generateName, generateLearn, generateDescription, save, newSkill, skillRemove;
	private JTable damageTable;
	private JList<Skill> skillList;
	
	private void initComponents() { 
		hero = new JTextField(15);
		name = new JTextField(15);
		description = new JTextArea(3, 15);
		description.setLineWrap(true);
		hotkey = new JComboBox<>(SkillHotkey.values());
		SpinnerNumberModel baseRangeModel = new SpinnerNumberModel(0, 0, 1200, 10);
		SpinnerNumberModel rangeFactorModel = new SpinnerNumberModel(0, -1200, 1200, 10);
		SpinnerNumberModel areaOfEffectModel = new SpinnerNumberModel(0, 0, 1200, 10);
		baseRange = new JSpinner(baseRangeModel);
		rangeFactor = new JSpinner(rangeFactorModel);
		areaOfEffect = new JSpinner(areaOfEffectModel);
		SpinnerNumberModel baseCooldownModel = new SpinnerNumberModel(0, 0, 300, 0.01);
		SpinnerNumberModel cooldownFactorModel = new SpinnerNumberModel(0, -300, 300, 0.01);
		baseCooldown = new JSpinner(baseCooldownModel);
		cooldownFactor = new JSpinner(cooldownFactorModel);
		AoEType = new JComboBox<>(AreaOfEffectType.values());
		DefaultTableModel damageModel = new DefaultTableModel(new Object[][]{{0f, 0f, DamageType.NORMAL, null}}, new String[]{"Base", "Factor", "Type", "Multipier"}) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] types = new Class[] { Float.class, Float.class, JComboBox.class, JComboBox.class };
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
		        return types [columnIndex];
		    }
		};
		damageTable = new JTable(damageModel);
		damageTable.setRowHeight(20);
		TableColumn damageBoxColumn = damageTable.getColumnModel().getColumn(2);
		damageBoxColumn.setCellEditor(new DefaultCellEditor(new JComboBox<DamageType>(DamageType.values())));
		damageBoxColumn.setCellRenderer(new MyComboBoxRenderer(DamageType.values()));
		damageBoxColumn = damageTable.getColumnModel().getColumn(3);
		damageBoxColumn.setCellEditor(new DefaultCellEditor(new JComboBox<BonusType>(BonusType.values())));
		damageBoxColumn.setCellRenderer(new MyComboBoxRenderer(BonusType.values()));
		skillList = new JList<>();
		updateList("");
		search = new JTextField(15);
		newSkill = new JButton("New skill");
		skillRemove = new JButton("Remove skill");
		generateLearn = new JButton("Generate learn");
		generateName = new JButton("Generate name");
		generateDescription = new JButton("Generate description");
		save = new JButton("Save");
	}
	
	private void setLayout() { 
		JPanel contentPane = new JPanel(new BorderLayout());
		JPanel pageStart = new JPanel(new GridBagLayout());
		JPanel pageEnd = new JPanel(new GridBagLayout());
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.BOTH;
		pageStart.add(new JLabel("Hero:"), c);
		c.gridy++;
		pageStart.add(hero, c);
		c.gridy++;
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
		pageStart.add(new JPanel(), c);
		c.gridy++;
		pageStart.add(new JLabel("Base range:"), c);
		c.gridx++;
		pageStart.add(baseRange, c);
		c.gridx++;
		pageStart.add(new JLabel("  Range factor:"), c);
		c.gridx++;
		pageStart.add(rangeFactor, c);
		c.gridx = 0;
		c.gridy++;
		pageStart.add(new JLabel("Base cooldown:"), c);
		c.gridx++;
		pageStart.add(baseCooldown, c);
		c.gridx++;
		pageStart.add(new JLabel("  Cooldown factor: "), c);
		c.gridx++;
		pageStart.add(cooldownFactor, c);
		c.gridx = 0;
		c.gridy++;
		pageStart.add(new JLabel("Area of Effect:"), c);
		c.gridx++;
		pageStart.add(areaOfEffect, c);
		c.gridx++;
		pageStart.add(new JLabel("  Type:"), c);
		c.gridx++;
		pageStart.add(AoEType, c);
		c.gridx = 0;
		c.gridy++;
		pageStart.add(new JPanel(), c);
		c.gridy++;
		c.gridwidth = 4;
		c.gridy++;
		JScrollPane damageScroll = new JScrollPane(damageTable);
		damageScroll.setPreferredSize(new Dimension(300, 45));
		pageStart.add(damageScroll, c);
		c.gridx = 0;
		c.gridy = 0;
		pageEnd.add(new JLabel("Skills:"), c);
		c.gridy++;
		pageEnd.add(search, c);
		c.gridy++;
		JScrollPane listScroll = new JScrollPane(skillList);
		listScroll.setPreferredSize(new Dimension(350, 200));
		pageEnd.add(listScroll, c);
		c.gridy++;
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(newSkill);
		buttonsPanel.add(skillRemove);
		pageEnd.add(buttonsPanel, c);
		bottomPanel.add(generateLearn);
		bottomPanel.add(generateName);
		bottomPanel.add(generateDescription);
		bottomPanel.add(save);
		contentPane.add(pageStart, BorderLayout.LINE_START);
		contentPane.add(pageEnd, BorderLayout.LINE_END);
		contentPane.add(bottomPanel, BorderLayout.PAGE_END);
		add(contentPane);
	}
	
	private void initListeners() { 
		newSkill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newSkill();
			}
		});
		skillList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = skillList.getSelectedIndex();
				if (index != -1) {
					Skill skill = skillList.getModel().getElementAt(index);
					loadSkill(skill);
				}
			}
		});
		skillList.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				int index = skillList.getSelectedIndex();
				if (index != -1) {
					Skill skill = skillList.getModel().getElementAt(index);
					loadSkill(skill);
				}
			}
		});
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveSkill();
			}
		});
		search.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent arg0) {
				updateList(search.getText().toLowerCase());
			}
		});
		skillRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (actualSkill != null) {
					SkillControl.remove(actualSkill);
				}
				updateList("");
				newSkill();
			}
		});
		generateDescription.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveSkill();
				if (actualSkill != null) {
					StringSelection stringSelection = new StringSelection(actualSkill.generateGenericDescription());
					Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
					clpbrd.setContents(stringSelection, null);
				}
			}
		});
		generateLearn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveSkill();
				if (actualSkill != null) {
					StringSelection stringSelection = new StringSelection(actualSkill.generateLearnName());
					Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
					clpbrd.setContents(stringSelection, null);
				}
			}
		});
		generateName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveSkill();
				if (actualSkill != null) {
					StringSelection stringSelection = new StringSelection(actualSkill.generateGenericName());
					Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
					clpbrd.setContents(stringSelection, null);
				}
			}
		});
	}
	
	public Skills() { 
		super();
		initComponents();
		setLayout();
		initListeners();
		/*Skill skill = new Skill();
		skill.setHero("Uzumaki Naruto");
		skill.setName("Rasengan");
		skill.setHotkey(SkillHotkey.Q);
		skill.setBaseCooldown(13);
		skill.setCooldownFactor(0.3f);
		skill.setAreaOfEffect(400);
		skill.setAoEType(AreaOfEffectType.LINE);
		skill.getDamageTable().setMultiplier(BonusType.TAIJUTSU);
		skill.getDamageTable().setDamageFactor(1);
		loadSkill(skill);
		newSkill();*/
	}
	
	private Skill getActualSkill() { 
		Skill skill = new Skill();
		skill.setHero(hero.getText());
		skill.setName(name.getText());
		skill.setDescription(description.getText());
		skill.setHotkey((SkillHotkey)hotkey.getSelectedItem());
		skill.setBaseRange((int)baseRange.getValue());
		skill.setRangeFactor((int)rangeFactor.getValue());
		skill.setBaseCooldown(Float.parseFloat(baseCooldown.getValue()+"f"));
		skill.setCooldownFactor(Float.parseFloat(cooldownFactor.getValue() +"f"));
		skill.setAreaOfEffect((int)areaOfEffect.getValue());
		skill.setAoEType((AreaOfEffectType)AoEType.getSelectedItem());
		skill.getDamageTable().setBaseDamage((float)damageTable.getValueAt(0, 0));
		skill.getDamageTable().setDamageFactor((float)damageTable.getValueAt(0, 1));
		skill.getDamageTable().setType((DamageType)damageTable.getValueAt(0, 2));
		if (damageTable.getValueAt(0, 3) != null)
			skill.getDamageTable().setMultiplier((BonusType)damageTable.getValueAt(0, 3));
		return skill; 
	}
	
	private void updateList(final String prefix) {
		ListModel<Skill> model = new ListModel<Skill>() {
			private List<Skill> list = SkillControl.getList(prefix);
			@Override
			public void addListDataListener(ListDataListener l) { }

			@Override
			public Skill getElementAt(int index) {
				return list.get(index);
			}

			@Override
			public int getSize() {
				return list.size();
			}

			@Override
			public void removeListDataListener(ListDataListener l) { }
		};
		skillList.setModel(model);
	}
	
	private void saveSkill() {
		if (name.getText().trim().equals("")) return;
		if (actualSkill != null) {
			SkillControl.remove(actualSkill);
		}
		Skill skill = getActualSkill();
		SkillControl.add(skill);
		updateList("");
		skillList.setSelectedValue(actualSkill, true);
		this.actualSkill = skill;
	}
	
	private void loadSkill(Skill skill) {
		if (isModified()) {
			int wantSave = JOptionPane.showConfirmDialog(this, "Modifications will be lost. Do you want to save it?");
			if (wantSave == 0) {
				saveSkill();
			}
		}
		hero.setText(skill.getHero());
		name.setText(skill.getName());
		description.setText(skill.getDescription());
		hotkey.setSelectedItem(skill.getHotkey());
		baseRange.setValue(skill.getBaseRange());
		rangeFactor.setValue(skill.getRangeFactor());
		baseCooldown.setValue(skill.getBaseCooldown());
		cooldownFactor.setValue(skill.getCooldownFactor());
		areaOfEffect.setValue(skill.getAreaOfEffect());
		AoEType.setSelectedItem(skill.getAoEType());
		damageTable.setValueAt(skill.getDamageTable().getBaseDamage(), 0, 0);
		damageTable.setValueAt(skill.getDamageTable().getDamageFactor(), 0, 1);
		damageTable.setValueAt(skill.getDamageTable().getType(), 0, 2);
		damageTable.setValueAt(skill.getDamageTable().getMultiplier(), 0, 3);
		actualSkill = skill;
	}
	
	private void newSkill() { 
		if (isModified()) {
			int wantSave = JOptionPane.showConfirmDialog(this, "Modifications will be lost. Do you want to save it?");
			if (wantSave == 0) {
				saveSkill();
			}
		}
		hero.setText("");
		name.setText("");
		description.setText("");
		hotkey.setSelectedItem(SkillHotkey.Q);
		baseRange.setValue(0);
		rangeFactor.setValue(0);
		baseCooldown.setValue(0f);
		cooldownFactor.setValue(0f);
		areaOfEffect.setValue(0);
		AoEType.setSelectedItem(AreaOfEffectType.CIRCULAR);
		damageTable.setValueAt(0f, 0, 0);
		damageTable.setValueAt(0f, 0, 1);
		damageTable.setValueAt(DamageType.NORMAL, 0, 2);
		damageTable.setValueAt(null, 0, 3);
		actualSkill = null;
	}
	
	private boolean isModified() { 
		if (actualSkill == null) return false;
		return !getActualSkill().equals(actualSkill);
	}
	
}
