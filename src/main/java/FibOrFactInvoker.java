import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FibOrFactInvoker implements InvocationHandler {

//    FibOrFact fibOrFact;
        JustClass justClass;

//    public FibOrFactInvoker(FibOrFact fibOrFact) {
//        this.fibOrFact = fibOrFact;
//    }


    public FibOrFactInvoker(JustClass justClass) {
        this.justClass = justClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("bbbbb");

        int resultOfMethod;

        Class classJustClass = justClass.getClass();
        Method methodJustClass = classJustClass.getMethod("myMethod", int.class);

        Integer number = (Integer) args[0];
        String keyFromAnnotation = methodJustClass.getAnnotation(Option.class).nameKeys_File();
        String key = keyFromAnnotation+number;

        System.out.println(key);
        if (methodJustClass.getAnnotation(Option.class).memory())
        {
            String fileName = "./" + methodJustClass.getAnnotation(Option.class).nameKeys_File()+".txt";
            System.out.println(fileName);
            File file = new File(fileName);

            FileReader fileReader = null;

            StringBuilder stringBuilder = new StringBuilder("");

            try {
                fileReader = new FileReader(file);
                Scanner scanner = new Scanner(fileReader);
                while (scanner.hasNextLine())
                {
                    stringBuilder.append(scanner.nextLine()).append("\n");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not Found");
            }finally {
                try {
                    fileReader.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            String stroka = stringBuilder.toString();
            if (stroka.contains(key))
            {
                String strstr = stroka.substring(stroka.lastIndexOf(key)).
                        substring(0,stroka.indexOf("\n")).
                        substring(stroka.indexOf(":")+1).trim();

                resultOfMethod = Integer.parseInt(strstr);

                System.out.println("ДАнные взяты из файла");
                return resultOfMethod;
            }else {
                System.out.println("Данных в файле нет");
            }

        }
        else {
            HashMap hashMap = justClass.getHashMapCash();
            if (hashMap.containsKey(key))
            {
                resultOfMethod = (int) hashMap.get(key);
                System.out.println("Данные взяты из Cash");
                return resultOfMethod;
            }
            else
            {
                System.out.println("Данных в Cash нет");
            }

        }


        System.out.println("=================");

        return method.invoke(justClass,args);
    }
}
