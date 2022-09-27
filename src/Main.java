import javax.swing.plaf.metal.MetalIconFactory;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int ch = -1;
        int n = 3;
        boolean flag = true;
        String str;
        Scanner scan = new Scanner(System.in);
        Func func = new Func();
        String dir = "F://Test//";

        while (flag) {
            System.out.println("Содержимое каталога: "+ dir);
            func.readDirectory(dir);
         /*  while (n!=1) {*/
                dir = "D://Test//";
                System.out.print("Введите путь к файлу: " + dir);
                String path = scan.next();
                dir = dir.concat(path);
             /*if*//*   if (func.isPathValid(dir)){
                    n=1;
                }
            }*/
            while (ch != 11) {
                System.out.println("Текущий путь к файлу: "+dir);
                System.out.println("1: Прочитать содержимое файла\n" +
                        "2:Запись в файл\n" +
                        "3:Копировать файл\n" +
                        "4:Удалить файл\n" +
                        "5:Посмотреть содержимое каталога\n" +
                        "6.Создать каталог\n" +
                        "7.Удалить каталог\n" +
                        "8.Изменить текущий путь к файлу\n" +
                        "9.Создать файл");
                ch = scan.nextInt();
                switch (ch) {
                    case 1: {
                        func.readFile(dir);
                        break;
                    }
                    case 2: {
                        func.editFile(dir);
                        break;
                    }
                    case 3: {
                        func.copyFile(dir);
                        break;
                    }
                    case 4: {
                        func.deleteFile(dir);
                        break;
                    }
                    case 5: {
                        func.readDirectoryMenu(dir);
                        break;
                    }
                    case 6: {
                        func.createDirectory(dir);
                        break;
                    }
                    case 7:{
                        func.deleteDirectory(dir);
                        break;
                    }
                    case 8:{
                     String newDir = func.changeDirectory(dir);
                     dir = newDir;
                        break;
                    }
                    case 9:{
                        func.createFileMenu(dir);
                        break;
                    }
                    default: {
                        System.out.println("Введено неверное значение");
                        break;

                    }
                }


            }
        }
    }
}