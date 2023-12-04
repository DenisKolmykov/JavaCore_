package less05;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
Задние:
1. Написать функцию, создающую резервную копию всех файлов в директории во вновь созданную папку ./backup
 */
public class Program {
    private static final String BACKUP_DIR = "backup";


    public static void main(String[] args) {
        //backup текущей папки
        try {
            File buckUpDir = new File("./" + BACKUP_DIR);
            if (Files.notExists(Paths.get(buckUpDir.getPath()))){
                Files.createDirectory(Paths.get(buckUpDir.getPath()));
            }
            backUp (new File("."));
            System.out.printf("резервная копия успешно создана в директории '%s'\n", BACKUP_DIR);
        } catch (IOException e){
            System.out.println("резервная копия не создана");
        }


    }

    /**
     * побайтовое копирование файла
     * @param fileIn - копируемый файл
     * @param fileOut - новый файл
     * @throws IOException
     */
    static void copyFile(String fileIn, String fileOut) throws IOException{
        // На запись
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileOut)){
            int c;
            // На чтение
            try (FileInputStream fileInputStream = new FileInputStream(fileIn)){
                while ((c = fileInputStream.read()) != -1){
                    fileOutputStream.write(c);
                }
            }
        }
    }

    static void backUp(File file) throws IOException {
        File[] files = file.listFiles();
        String newPath; // новый путь в папку BACKUP_DIR
        for (int i = 0; i < files.length; i++) {
            //указываем новый путь для файлов в корневой папке
            if (files[i].getParent().equals(".")){
                newPath = files[i].getParent().replace(".", "./backup");
            } else {
                // указываем новый путь для файлов во вложенных папках
                newPath = files[i].getParent().replace("./", "./backup/");
            }
            //проверка - если файл - то копируем файл (метод copyFile)
            if (files[i].isFile()){
                copyFile(files[i].getPath(),newPath + "/" + files[i].getName());
            }

            // проверка - если деректория - то создаем в папке BACKUP_DIR директорию с такимже путем и именем
            if (files[i].isDirectory() && !files[i].getName().equals(BACKUP_DIR)) {
                if (Files.notExists(Paths.get(newPath, files[i].getName()))) {
//                  Files.deleteIfExists(Paths.get(newP, files[i].getName())); // не разобрался про удаление НЕПУСТЫХ директорий
                    /**
                     * создание директории
                     * @newPath - новый путь для создания директории в папке BACKUP_DIR
                     * @files[i].getName() - название создаваемой директории
                     */
                    Files.createDirectory(Paths.get(newPath, files[i].getName()));
                }
                // рекурсивный вызов метода для погружения в поддиректории
                backUp(files[i]);
            }
        }
    }
}