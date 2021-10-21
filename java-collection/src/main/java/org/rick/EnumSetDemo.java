package org.rick;

import java.util.*;

public class EnumSetDemo {

	public static void main(String[] args) {
		Set<Day> weekend = EnumSet.noneOf(Day.class);
		weekend.add(Day.SATURDAY);
		weekend.add(Day.SUNDAY);
		System.out.println(weekend);
		
		Worker[] workers = new Worker[]{
		        new Worker("张三", EnumSet.of(
		                Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.FRIDAY)),
		        new Worker("李四", EnumSet.of(
		                Day.TUESDAY, Day.THURSDAY, Day.SATURDAY)),
		        new Worker("王五", EnumSet.of(
		                Day.TUESDAY, Day.THURSDAY)),
		};
		//哪些天一个人都不会来
		Set<Day> days = EnumSet.allOf(Day.class);
		for(Worker w : workers){
		    days.removeAll(w.getAvailableDays());
		}
		System.out.println(days);
		//哪些天至少一个人会来
		Set<Day> day1 = EnumSet.noneOf(Day.class);
		for(Worker w : workers){
		    day1.addAll(w.getAvailableDays());
		}
		System.out.println(day1);
		//哪些天所有人会来
		Set<Day> dayAll = EnumSet.allOf(Day.class);
		for(Worker w : workers){
		    dayAll.retainAll(w.getAvailableDays());
		}
		System.out.println(dayAll);
		//哪些人周一二来
		Set<Worker> availableWorkers = new HashSet<Worker>();
		for(Worker w : workers){
		    if(w.getAvailableDays().containsAll(
		            EnumSet.of(Day.MONDAY,Day.TUESDAY))){
		        availableWorkers.add(w);
		    }
		}
		for(Worker w : availableWorkers){
		    System.out.println(w.getName());
		}
		//哪些天至少两个人来
		Map<Day, Integer> countMap = new EnumMap<>(Day.class);
		for(Worker w : workers){
		    for(Day d : w.getAvailableDays()){
		        Integer count = countMap.get(d);
		        countMap.put(d, count==null?1:count+1);
		    }
		}
		Set<Day> day2 = EnumSet.noneOf(Day.class);
		for(Map.Entry<Day, Integer> entry : countMap.entrySet()){
		    if(entry.getValue()>=2){
		        day2.add(entry.getKey());
		    }
		}
		System.out.println(day2);

	}

}

enum Day {
    MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

class Worker {
    String name;
    Set<Day> availableDays;
    
    public Worker(String name, Set<Day> availableDays) {
        this.name = name;
        this.availableDays = availableDays;
    }
    
    public String getName() {
        return name;
    }
    
    public Set<Day> getAvailableDays() {
        return availableDays;
    }
}