import java.io.*;
import java.util.Scanner;

public class Func {
    int ch;
    int n = -1;
    boolean flag = true;
    String str;
    Scanner scan = new Scanner(System.in);
    private String dir;
    public void readFile(String dir) {
        try (BufferedReader fr = new BufferedReader(new FileReader(dir))) {
            while ((str = fr.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
    public void editFile(String dir) {
        while (n != 3) {
            System.out.println("1:Записать в конец файла\n2:Перезаписать\n3:Выйти");
            n = scan.nextInt();
            switch (n) {
                case 1: {
                    System.out.print("Введите строку которую хотите записать: ");
                    str = scan.next();
                    try (FileWriter fw = new FileWriter(dir, true)) {
                        fw.write(str + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 2: {
                    System.out.print("Введите строку которую хотите записать: ");
                    str = scan.next();
                    try (FileWriter fw = new FileWriter(dir, false)) {
                        fw.write(str + "\n");
                    } catch (IOException exx) {
                        exx.printStackTrace();
                    }
                    break;
                }
                case 3: {
                    break;
                }
                default: {
                    System.out.println("Введено неверное значение");
                    break;
                }
            }
        }
    }
    public void deleteFile(String dir) {
        File file = new File(dir);
        if (file.delete()){
            System.out.println("Файл успешно удален");
        }
        else {
            System.out.println("Ошибка удаления файла");
        }
    }
    public void copyFile(String dir) {
        while (ch != 3) {
            String copyDir;
            System.out.println("Выберите:\n1.Коприровать в эту дерикторию\n2.Выбрать куда копировать файл\n3.Выйти");
            ch = scan.nextInt();
            switch (ch) {
                case 1: {
                    System.out.println("Сохранить название файла?\n1.Да\n2.Нет");
                    n = scan.nextInt();
                    copyDir = getPath(dir);
                    if (n == 1) {
                        String[] decodeFileName = dir.split("//");
                        int fileArrayLength = decodeFileName.length;
                        String fileNameUncut = decodeFileName[decodeFileName.length - 1];
                        String[] fileName = fileNameUncut.split(".txt");
                        copyDir = copyDir.concat(fileName[0] + "_copy.txt");
                    } else if (n == 2) {
                        System.out.println("Введите новое название файла:");
                        str = scan.next();
                        copyDir = copyDir.concat(str);
                        n = 1;
                    }
                    try (BufferedReader fr = new BufferedReader(new FileReader(dir));
                         FileWriter fw = new FileWriter(copyDir, false)) {
                        while ((str = fr.readLine()) != null) {
                            fw.write(str + "\n");
                        }
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                    break;
                }
                case 2: {
                    System.out.println("Введите директорию куда хотите копировать файл");
                    copyDir = scan.next();
                    if (copyDir.endsWith(".txt") != true) {
                        while (n != 1) {
                            System.out.println("Сохранить название файла?\n1.Да\n2.Нет");
                            n = scan.nextInt();
                            if (n == 1) {
                                String[] decodeFileName = dir.split("//");
                                int fileArrayLength = decodeFileName.length;
                                String fileNameUncut = decodeFileName[fileArrayLength - 1];
                                String[] fileName = fileNameUncut.split(".txt");
                                copyDir = copyDir.concat(fileName[0] + "_copy.txt");
                            } else if (n == 2) {
                                System.out.println("Введите новое название файла:");
                                str = scan.next();
                                copyDir = copyDir.concat(str);
                                n = 1;
                            }
                        }
                    }
                    try (BufferedReader fr = new BufferedReader(new FileReader(dir));
                         FileWriter fw = new FileWriter(copyDir, true)) {
                        while ((str = fr.readLine()) != null) {
                            fw.write(str + "\n");
                        }
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                    break;
                }

            }
        }
    }
    public void createFile(String dir) throws IOException {
        dir = getPath(dir);
        System.out.println("Введите название файла который хотите создать:");
        str = scan.next();
       dir = dir.concat(str);
        File file = new File(dir);
       boolean d = file.createNewFile();
       if(d){
           System.out.println("Файл успешно создан");
       }
       else {
           System.out.println("Такой файл уже существует");
       }
    }
    public  void createFileMenu(String dir) throws IOException {

        System.out.println("Создать файл по текущему пути?\n1.Да\n2.Нет");
        while (n!=1){
            n=scan.nextInt();
            switch (n){
                case 1:{
                    createFile(dir);
                    break;
                }
                case 2:{
                    System.out.println("Введите путь к месту создания файла");
                    dir = scan.next();
                    createFile(dir);
                    break;
                }
                default:{
                    System.out.println("Error");
                    break;
                }
            }
        }
    }
    public void createDirectory(String dir) {
        String path;
        dir = getPath(dir);
        while (n!=3) {
            System.out.println("Создать каталог в этой директории?\n1.Да\nНет");
            n = scan.nextInt();
            switch (n) {
                case 1:{
                System.out.println("Введите каталог/каталоги которые хотите создать");
                path = scan.next();
                dir = dir.concat(path);
                File D = new File(dir);
                boolean D1 = D.mkdirs();
                if (D1) {
                    System.out.println("Каталог создан успешно");
                } else {
                    System.out.println("Такой каталог уже существует");
                }
                }
            case 2: {
                System.out.println("Введите путь к каталогу/каталогам которые хотите создать");
                path = scan.next();
                File D = new File(path);
                boolean D1 = D.mkdirs();
                if (D1) {
                    System.out.println("Каталог создан успешно");
                } else {
                    System.out.println("Такой каталог уже существует");
                }
            }
        }
        }
    }
    public void readDirectory(String dir){
        File file = new File(dir);
        if (file.isDirectory()){
            for(File files: file.listFiles()){
                if(files.isFile()){
                    System.out.println(files.getName()+" файл");
                }
                else{
                    System.out.println(files.getName()+ " каталог");
                    readDirectory(files.getPath());
                }
            }
        }
    }
    public void readDirectoryMenu(String dir){
        System.out.println("Вывести на экран содержимое текущего каталога?\n1.Да\n2.Нет");
        while (n!=3){
            n=scan.nextInt();
            switch (n){
                case 1:{
                    dir = getPath(dir);
                    readDirectory(dir);
                    n= 3;
                    break;
                }
                case 2:{
                    System.out.println("Введите путь к каталогу содержание которого хотите вывести на экран");
                    dir = scan.next();
                    readDirectory(dir);
                    n=3;
                    break;
                }
                default:{
                    System.out.println("Некоректный ввод");
                }
            }
        }
    }
    public void deleteDirectory(String dir){
       while (n!=1) {
           System.out.println("Удалить текущий каталог?\n1.Да\n2.Нет");
           n = scan.nextInt();
           switch (n) {
               case 1: {
                   dir = getPath(dir);
                   File file = new File(dir);
                   file.delete();
               }
               case 2: {
                   System.out.println("Введите путь к каталогу который хотите удалить");
                   dir = scan.next();
                   File file = new File(dir);
                   file.delete();
                   n=1;
               }
           }
       }
    }
    public String changeDirectory(String dir){
        System.out.println("Введите новый путь:");
        dir = scan.next();
        return dir;
    }
    public String getPath(String dir){
        if(dir.endsWith(".txt")){
        String[] decodePathName = dir.split("//");
        String pathFileName = decodePathName[decodePathName.length - 1];
        String[] decodePath = dir.split(pathFileName);
        dir = decodePath[0];
        }
        return dir;
    }
    public String getFileName(String dir){
        String[] decodePathName = dir.split("//");
        String pathFileName = decodePathName[decodePathName.length - 1];
        return pathFileName;
    }
    public  String  getValidationPath(String dir){
        String[] decodePathName = dir.split("//");
        String pathFileName = decodePathName[decodePathName.length - 1];
        String[] decodePath = dir.split(pathFileName);
        dir = decodePath[0];
        return dir;
    }
    public  boolean  validationCheck(File file, String fileName){
        String name ="";
        if (file.isDirectory()){
            for(File files: file.listFiles()){
              name =  getValidationPath(files.getPath());
                if(files.isFile()){
                    if (files.getName()== (name = name.concat(fileName))){
                        return true;
                    }
                }
                else{
                    validationCheck(files, fileName);
                }
            }
        }
        return false;
    }
    public boolean isPathValid(String dir) {
        String fileName = getFileName(dir);
        dir = getValidationPath(dir);
        File file = new File(dir);
        if (validationCheck(file, fileName) == true) {
return true;
        }
        else return false;
    }
}



