import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JustClass implements FibOrFact{
    HashMap<String, Integer> hashMapCash = new HashMap<>();

    public HashMap<String, Integer> getHashMapCash() {
        return hashMapCash;
    }

    @Override
    @Option(nameKeys_File = "fib",memory = true)
    public int myMethod(int number) {
        String key = null;
        Method method = null;
        int resultMethod = 0;

        try {
            method = this.getClass().getMethod("myMethod", int.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        ///// Создание неповторимого ключа
        Option option = method.getAnnotation(Option.class);
        key = option.nameKeys_File() + String.valueOf(number);

        System.out.println(key);
        //////////////


        ///Проверка есть ли данные  в памяти
//        Через Динамический прокси сделаем

        ////Какой рассчёт производить - Фибоначчи или факториал
        if (method. getAnnotation(Option.class).nameKeys_File().toLowerCase().equals("fib"))
        {
            resultMethod = fibNumber(number);
            System.out.println("fib");
        }
        else if(method. getAnnotation(Option.class).nameKeys_File().toLowerCase().equals("factorial"))
        {
            resultMethod = factorial(number);
            System.out.println("factorial");
        }

        ///Запись в Файл - true или Запись в HashMap false
        try {

            if (JustClass.class.getMethod("myMethod", int.class).getAnnotation(Option.class).memory())
            {
                HashMap <String, Integer> hashMap = new HashMap<>();
                hashMap.put(key, resultMethod);
                String fileNameWrite = "./"
                        + JustClass.class.getMethod("myMethod", int.class).getAnnotation(Option.class).nameKeys_File()
                        +".txt";
//
                File fileWrite = new File(fileNameWrite);

                StringBuilder stringBuilder = new StringBuilder("");

                for (Map.Entry<String, Integer> entry:
                        hashMap.entrySet()) {
//            stringBuilder.append("Key:").append(entry.getKey() + ";").append("Val=").append(entry.getValue()).append("\n");
                    stringBuilder.append(entry.getKey() + ":").append(entry.getValue()).append("\n");
                }

                try {
                    FileWriter fileWriter = new FileWriter(fileWrite,true);
                    fileWriter.write(stringBuilder.toString());
                    fileWriter.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            else{
                System.out.println("false");
                hashMapCash.put(key,resultMethod);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return resultMethod;
    }

    ///////////////////
    ///////////////////
    /// Вспомогательные методы
    private int fibNumber(int n)
    {
        int [] mass = new int[n];
        mass[0] = 0;
        mass[1] = 1;
        for (int i = 2; i < n; i++) {
            mass[i]+=mass[i-1] + mass[i-2];
        }

        ArrayList arrayList = new ArrayList();
        arrayList = (ArrayList) Arrays.stream(mass).boxed().collect(Collectors.toList());
//        for (int i = 0; i < n; i++) {
//            System.out.print(mass[i] + "  ");
//        }
//        System.out.println(arrayList);
        int sum = arrayList.stream().mapToInt(value -> (int) value).sum();
//        System.out.println(sum);
        return sum;
    }

    private int factorial(int n)
    {
        if (n == 1)
        {
            return 1;
        }
        return n * factorial(n-1);
    }

    /*
    private boolean checkMemory(String key, boolean memory, String nameFile)
    {
        if (memory)
        {
            File file = new File(nameFile);
            file.setReadOnly();
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                while (fileInputStream.read()!=-1)
                {
                    fileInputStream.readAllBytes().toString();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        else {
            if (hashMapCash.get(key) != null)
            {
                return true;
            }
            else {return false;}

        }
    }
    */
}
