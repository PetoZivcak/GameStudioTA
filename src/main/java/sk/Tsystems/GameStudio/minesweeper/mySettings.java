package sk.Tsystems.GameStudio.minesweeper;//package minesweeper;
//
//import java.io.*;
//
//public class Settings implements Serializable {
//    private final int rowCount;
//    private final int columnCount;
//    private final int mineCount;
//
//    public static  Settings BEGINNER = new Settings(9, 9, 10);
//    public static  Settings EXPERT = new Settings(16, 16, 40);
//    public static  Settings INTERMEDIATE = new Settings(16, 30, 99);
//    private static  String SETTING_FILE = System.getProperty("user.home") + System.getProperty("file.separator") + "minesweeper.settings";
//
//
//    public Settings(int rowCount, int columnCount, int mineCount) {
//        this.rowCount = rowCount;
//        this.columnCount = columnCount;
//        this.mineCount = mineCount;
//
//    }
//
//    public int getRowCount() {
//        return rowCount;
//    }
//
//    public int getColumnCount() {
//        return columnCount;
//    }
//
//    public int getMineCount() {
//        return mineCount;
//    }
//
//    public void save() {
//        ObjectOutputStream oos = null;
//        try {
//            FileOutputStream s = new FileOutputStream(SETTING_FILE);
//            oos = new ObjectOutputStream(s);
//            oos.writeObject(this);
//
//
//        } catch (IOException e) {
//            System.out.println("Settings neboli zapisane do objektu");
//        } finally {
//            if (oos != null) {
//                try {
//                    oos.close();
//                } catch (IOException e) {
//                    //empty
//                }
//            }
//        }
//    }
//
//    public static Settings load() {
//        ObjectInputStream oir = null;
//        try {
//            FileInputStream r = new FileInputStream(SETTING_FILE);
//            oir = new ObjectInputStream(r);
//            Settings s = (Settings) oir.readObject();
//        } catch (IOException e) {
//            System.out.println("Nebolo mozne precitat subor s nastaveniami");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Nebolo mozne precitat subor s nastaveniami");
//        } finally {
//            if (oir != null) {
//                try {
//                    oir.close();
//                } catch (IOException e) {
//                    //empty
//                }
//
//            }
//        }
//        return BEGINNER;
//    }
//
//
//
//
//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof Settings)) {
//            return false;
//        }
//        Settings s = (Settings) obj;
//        return s.rowCount == rowCount
//                && s.columnCount == columnCount
//                && s.mineCount == mineCount;
//    }
//
//    @Override
//    public int hashCode() {
//        return rowCount * columnCount * mineCount;
//    }
//
//    public static void main(String[] args) {
//
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
