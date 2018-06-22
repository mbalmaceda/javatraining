package P26_Reflexion;

public class E02_Class {

	public static void main(String[] args) {
		
		Class<?> printerClass;
		Printable printer;
		
		try {
			
			printerClass = Class.forName("P26_Reflexion.Hello");
			printer = (Printable) printerClass.newInstance();
			printer.print();
			System.out.println(printerClass.getName());		
			
			System.out.println();
			
			printerClass = Class.forName("P26_Reflexion.E02_Class$Bye");
			printer = (Printable) printerClass.newInstance();
			printer.print();
			System.out.println(printerClass.getName());	
			
			System.out.println();
			
			System.out.println(Class.forName("java.util.ArrayList").getName());
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	public static class Bye implements Printable {

		@Override
		public void print() {
			System.out.println("Bye");
		}
		
	}

}
