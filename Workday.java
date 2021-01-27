import java.util.*;
import java.sql.Time;
import java.text.*;
import java.time.LocalTime;

public class Workday {

	private static Date date;
	private LocalTime start;
	private LocalTime end;
	
	public Workday(Date date) {
		 
		this.date = date;
		start = LocalTime.of(9,0,0);
		end = LocalTime.of(17,0,0);
		
	}
	
	
	public  void set_data(Date d) {
		date = d;
		
	}
	public  void set_start( LocalTime start) {
		this.start = LocalTime.of(start.getHour(),start.getMinute(),start.getSecond()); 
		
	}
	
	public  void set_end( LocalTime end) {
		this.end = LocalTime.of(end.getHour(),end.getMinute(),end.getSecond()); 
		
	}
	
	public Date get_data() {
		return date;
	}
	
	public LocalTime get_start() {
		return start; 
	}
	
	public LocalTime get_end() {
		return end; 
	} 
	public double workTime() {
		double sum;
		double s_hour = start.getHour();
		double e_hour = end.getHour();
		double s_minute = start.getMinute();
		double e_minute = end.getMinute();
		if (e_minute >+s_minute ) {

			sum = e_hour - s_hour;
			sum = sum + (e_minute - s_minute)/60;

			return sum;

		
		}
		e_minute += 60;
		e_hour--;
		sum = e_hour - s_hour;
		sum = sum + (e_minute - s_minute)/60;
		return sum;
		
		
	}
	public Boolean isExtra() {
		if (this.workTime() > 8) {
			return true;
		}
		return false;
		
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	      // display time and date using toString()
		LocalTime end, new_end;
		new_end = LocalTime.of(18,45,0);
	      Date dNow = new Date( );
	      SimpleDateFormat ft =  new SimpleDateFormat (" dd.MM.yyyy");
		  Workday day = new Workday(dNow);
		  end = day.get_end();
		  System.out.println(day.isExtra());
		  day.set_end(new_end);
		  System.out.println(day.isExtra());

		  System.out.println(end.getHour() + ":" + end.getMinute()+":"+ end.getSecond());
		  System.out.println(day.workTime()+ "  ood");
	      System.out.println("Current Date: " + ft.format(dNow));           

	}

	

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
public class Company {
	private String name;
	private ArrayList<Worker> allWorkers;
	private ArrayList<Integer> sales;
	
	public Company(String name) {
		this.name = name;
		allWorkers = new ArrayList<Worker>();
		sales = new ArrayList<Integer>();
	}
	
	public double totalPayments() {
		double sum = 0.0;
		
		for (int i =0; i< allWorkers.size(); i++) {
			sum = sum + allWorkers.get(i).Salary();
			
		}
		
		return sum;
	}
    public int totalEarnings() {
	 int x = 0; 
	 for(int i =0; i< sales.size(); i++) {
		 x = x +sales.get(i);
	 }
	 return x;
 }
    
    public Worker workedMost() {
    	Worker work_most =  allWorkers.get(0);
    	double hours = 0.0, temp =0.0;
    	for(int i =0; i< work_most.get_ArrayList().size();i++) {
    		
    		hours = hours+ work_most.get_ArrayList().get(i).workTime();
    	}
    	for (int i =1 ; i <allWorkers.size(); i++) {
    		
    		for(int e =0; e< allWorkers.get(i).get_ArrayList().size();e++) {
        		
        		temp = temp+ allWorkers.get(i).get_ArrayList().get(e).workTime();
        	}
    		if (temp > hours) {
    			hours = temp;
    			work_most = allWorkers.get(i);
    		}
    		temp = 0.0;

    		
    	}
    	return work_most;
    	
    }
    public int index_of_workedMost() {
    	Worker work_most =  allWorkers.get(0);
    	double hours = 0.0, temp =0.0;
    	int index = 0;
    	for(int i =0; i< work_most.workingDays();i++) {
    		
    		hours = hours+ work_most.get_ArrayList().get(i).workTime();
    	}
    	for (int i = 1 ; i <allWorkers.size(); i++) {
    		
    		for(int e = 0; e< allWorkers.get(i).get_ArrayList().size();e++) {
        		
        		temp = temp+ allWorkers.get(i).get_ArrayList().get(e).workTime();
        	}
    		if (temp > hours) {
    			hours = temp;
    			index = i;
    		}
    		temp = 0.0;
    		
    	}
    	return index;
    	
    }
    
    public void addWorker(Worker w) {
    	allWorkers.add(w);
    	
    }
    
    
    public int index_the_most_slary() {
    	int index_max  = 0;
    	double slary =allWorkers.get(0).Salary();
    	
    	for (int i =1 ; i <allWorkers.size(); i++) {
    		if (slary < allWorkers.get(i).Salary() ) {
    			slary = allWorkers.get(i).Salary();
    			index_max = i;
    		}
    	
    }
    	return index_max;
    }
    
	public void fire() {
        int count_hour = 0 , count_salary= 0 ;
    	int [] size_of_most_hour  = new int [allWorkers.size()/4];
    	int [] size_of_most_salary = new int [allWorkers.size()/4];
    	int[] mergedArray = new int [2*( allWorkers.size()/4)];
    	  
        int i= 0, j = 0, k= 0;
         
        ArrayList <Worker> temp1 = new ArrayList <Worker>();
    	ArrayList<Worker> temp2 = new ArrayList<Worker>();

    	for(int e =0; e<allWorkers.size(); e++ ) {
    		
    	
		 temp1.add(allWorkers.get(e));
		 temp2.add(allWorkers.get(e));
    	}

	for (int e =0; e < allWorkers.size()/4; e++ )
	{
    		
		temp1.remove(this.index_of_workedMost());
		size_of_most_hour[count_hour] = this.index_of_workedMost();

		count_hour++;
    }
	for (int e =0; e <allWorkers.size()/4; e++ ) 
	{
		
		temp2.remove(this.index_the_most_slary());
		size_of_most_salary[count_salary] = this.index_the_most_slary();
		count_salary++;
	}
	 
     //Inserting all elements of arrayA into mergedArray
      
     while (i < allWorkers.size()/4)
     {
         mergedArray[k] = size_of_most_hour[i];
         k++;
         i++;
     }
      
     //Inserting all elements of arrayB into mergedArray
      
     while (j < allWorkers.size()/4)
     {
         mergedArray[k] = size_of_most_salary[j];
         k++;
         j++;
     }
     Set<Integer> setWithNoDuplicates = new HashSet<>();
     
     //Adding all elements of mergedArray into setWithNoDuplicates
      
     for (int m = 0; m < 2*( allWorkers.size()/4); m++) 
     {
         setWithNoDuplicates.add(mergedArray[m]);
     }
     Iterator<Integer> it = setWithNoDuplicates.iterator();
     
     int[] mergedArrayWithNoDuplicates = new int[setWithNoDuplicates.size()];
      
     int n = 0;
      
     //Adding all elements of setWithNoDuplicates into mergedArrayWithNoDuplicates
      
     while (it.hasNext()) 
     {
         mergedArrayWithNoDuplicates[n] = it.next();
         n++;
     }
     for (int m = 0; m< setWithNoDuplicates.size(); m++) {
    	 
    	 allWorkers.remove(mergedArrayWithNoDuplicates[m]);
     }
 
    
    	
    }
    
	
	
	public static void main(String[] args) {
		
		
		  Date dNow = new Date( );
	      SimpleDateFormat ft =  new SimpleDateFormat (" dd.MM.yyyy");
		  Workday day1 = new Workday(dNow);
		  Workday day2 = new Workday(dNow);
		  Workday day3 = new Workday(dNow);
		  Workday day4 = new Workday(dNow);
		  Workday day5 = new Workday(dNow);
		  Workday day6 = new Workday(dNow);


		  ArrayList<Workday> w = new ArrayList<Workday>();
		  Worker Worker_1 = new Worker ("david" ,20);
		  Worker Worker_2 = new Worker ("arie" ,100);
		  Worker Worker_3 = new Worker ("giladi" ,30000);
		  Worker Worker_4 = new Worker ("giladi" ,10);


		  w.add(day1);
		  w.add(day2);

		  w.add(day3);
		  Worker_3.set_workdays(w);
		  Worker_4.set_workdays(w);

		  w.add(day4);
		  Worker_2.set_workdays(w);
		  w.add(day5);
		  Worker_1.set_workdays(w);
		  w.add(day6);

		  System.out.println("size = "+ Worker_1.workingDays());
		  System.out.println(Worker_1.Salary());
		  System.out.println("size = "+ Worker_1.workingDays());
		  System.out.println(Worker_2.Salary());

		  System.out.println(Worker_3.Salary());


		   
		  Company tasla = new Company("tasla");
		  tasla.addWorker(Worker_1);
		  tasla.addWorker(Worker_2);
		  tasla.addWorker(Worker_3);
		  tasla.addWorker(Worker_4);

		  System.out.println(tasla.workedMost().get_name());
		  System.out.println(tasla.index_the_most_slary());
		  tasla.fire();
		  System.out.println(tasla.workedMost().get_name());
		  System.out.println(tasla.index_the_most_slary());
		
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
