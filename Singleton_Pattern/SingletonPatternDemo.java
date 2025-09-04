package Singleton_Pattern;

public class SingletonPatternDemo {
public static void main(String[] args) {
// Get the single object instance
SingleObject object = SingleObject.getInstance();
// Show message
object.showMessage(); } }