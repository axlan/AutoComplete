package autocomplete;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class EntryModel extends AbstractTableModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5827501151290917240L;
	private String[] columnNames = {"Alias",
                                    "Snippet"};
	
   private ArrayList<String> aliases=new ArrayList<String>();
   private ArrayList<String> snippets=new ArrayList<String>();
   
   
   EntryModel()
   {
	   aliases.add("");
	   snippets.add("");
   }
   

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return aliases.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    

    @Override
	public void setValueAt(Object arg0, int row, int col) {

    	switch (col) {
		case 0:
			 aliases.set(row, arg0.toString());
			break;
		case 1:
			snippets.set(row, arg0.toString());
			break;

		default:
			break;
		}
	}


	public Object getValueAt(int row, int col) {
    	String val=null;
    	
    	switch (col) {
		case 0:
			val= aliases.get(row);
			break;
		case 1:
			val= snippets.get(row);
			break;

		default:
			break;
		}
    	return val;
    }

   

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
            return true;
    }

    

}
