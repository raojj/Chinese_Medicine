package grammar.C_FaceTo_Object.B_Code;

public class C2_ArrayList_Object {
    public static void main(String[] args) {
        String book1 = "test1";
        String book2 = "test2";
        test(book1,book2);
        String[] forTest = new String[]{"test1", "test2", "test3"};
        for (String t:forTest){
            System.out.println(t);
        }
    }
    public static void test(String... books){
      for (String book : books){
          System.out.println(book.toUpperCase());
      }
    }
}