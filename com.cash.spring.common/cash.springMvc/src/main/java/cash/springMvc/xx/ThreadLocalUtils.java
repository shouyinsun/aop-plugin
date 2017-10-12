package cash.springMvc.xx;

public class ThreadLocalUtils {
	private static ThreadLocal<Something> local=new ThreadLocal<Something>();
	
	public static void set(Something s){
		local.set(s);
	}
	
	public static String getTraceId(){
		return local.get().getTraceId();
	}
	
	public void clear(){
		local.set(null);
	}
}
