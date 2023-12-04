package less05;
/*
 **2. Доработайте класс Tree и метод print который мы разработали на семинаре.
 * Ваш метод должен распечатать полноценное дерево директорий и файлов
 * относительно корневой директории.
*/

import java.io.File;

/**
 * TODO: Доработать метод print, необходимо распечатывать директории и файлы
 */
public class Tree {

    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
        }
        // символы обозначения для папок и файлов (для визульного восприятия дерева)
        if (file.isFile()){
            System.out.println("\uD83D\uDCC4" + file.getName());
        }else System.out.println("\uD83D\uDCC2" + file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++){
            //if (files[i].isDirectory())
                subDirTotal++;
        }

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++){
            //if (files[i].isDirectory()){
                print(files[i], indent, subDirTotal == ++subDirCounter);
            //}
        }
    }

}
