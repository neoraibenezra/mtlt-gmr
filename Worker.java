import java.text.SimpleDateFormat;
import java.util.*;

public class Worker {
	
	private String name;
	private ArrayList<Workday> workdays;
	private int pay;
	
	public Worker(String name, int pay) {
		this.name = name;
		this.pay = pay;
		workdays = new ArrayList<Workday>();
	}
	
	public void set_name(String name) {
		this.name = name;
	}
	
	
	public void pay(int pay) {
		this.pay = pay;
	}
	
	public String get_name() {
		return name;
				
	}
	
	public int get_pay() {
		return pay;
	}
	
	public void set_workdays(ArrayList<Workday> new_work_days) {
		 workdays.clear();
		 workdays.addAll(new_work_days);
		
	}
	
	public  ArrayList<Workday> get_ArrayList(){
		return workdays ;
	}
	
	public int workingDays() {
		
		return workdays.size();
	}
	public double Salary() {
		double slary = 0.0;
		for (int i = 0; i < workdays.size(); i++)
		{
			slary = slary+  workdays.get(i).workTime()*pay;
		}
		return slary;
	}
	public Worker diligent(Worker other) {
		double w_1 =0.0 ,w_2 = 0.0 ;
		for (int i = 0; i < workdays.size(); i++) {
			w_1 = w_1 + workdays.get(i).workTime();
		}
		ArrayList<Workday> arr_other = other.get_ArrayList();
		for (int i = 0; i < arr_other.size(); i++) {
			w_2 = w_2 + arr_other.get(i).workTime();
		}
		if (w_1 >= w_2) {
			return this;
		}
		return other;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  Date dNow = new Date( );
	      SimpleDateFormat ft =  new SimpleDateFormat (" dd.MM.yyyy");
	      Workday day1 = new Workday(dNow);
		  Workday day2 = new Workday(dNow);
		  Workday day3 = new Workday(dNow);
		  Workday day4 = new Workday(dNow);
		  Workday day5 = new Workday(dNow);


		  ArrayList<Workday> w = new ArrayList<Workday>();
		  Worker Worker_1 = new Worker ("david" ,20);
		  Worker Worker_2 = new Worker ("arie" ,1000);
		  Worker Worker_3 = new Worker ("giladi" ,300);


		  w.add(day1);
		  w.add(day2);

		  w.add(day3);
		  Worker_3.set_workdays(w);
		  w.add(day4);
		  Worker_2.set_workdays(w);
		  w.add(day5);
		  Worker_1.set_workdays(w);


		  
		  System.out.println("name 1 = "+ Worker_1.get_name() + " size 0f days of work = " +Worker_1.workingDays());
		  System.out.println("name 2 = "+ Worker_2.get_name() + " size 0f days of work = " +Worker_2.workingDays());
		  System.out.println("name 3 = "+ Worker_3.get_name() + " size 0f days of work = " +Worker_3.workingDays());

		  System.out.println(Worker_1.Salary());
		  System.out.println("size = "+ Worker_1.workingDays());
		  System.out.println(Worker_1.Salary());


	}

}
