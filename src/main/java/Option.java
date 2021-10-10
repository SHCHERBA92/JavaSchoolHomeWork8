import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Option {
    boolean memory() default false;   // false - память или файл - true
    int contArg() default 2;
    int maxElement() default 1;
    String nameKeys_File() default "";
    boolean zip() default false;

}
