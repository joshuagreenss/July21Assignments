package com.smoothstack.javabasics.day5;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeAPI {
	public static void q1() {
		ZonedDateTime q1 = ZonedDateTime.of(1996, 10, 2, 5, 6, 32, 876, ZoneId.of("America/Los_Angeles"));
	}
	
	public static void q2() {
		LocalDate q2 = LocalDate.of(2000, 9, 13);
		do {
			q2 = q2.minusDays(1);
		} while(!(q2.getDayOfWeek() == DayOfWeek.THURSDAY));
		System.out.println(q2);
	}
	
	public static void q4() {
		Instant q4 = Instant.now();
		ZonedDateTime fromInstant = ZonedDateTime.ofInstant(q4, ZoneId.of("America/Los_Angeles"));
		System.out.println(fromInstant);
	}
	
	public static void q5(int year) {
		LocalDate dayCount = LocalDate.of(year, 1, 1);
		int count = 0;
		for(int i = 0; i < 12; i++) {
			Month currMonth = dayCount.getMonth();
			System.out.println(currMonth);
			count = 0;
			while(dayCount.getMonth() == currMonth) {
				count++;
				dayCount = dayCount.plusDays(1);
			}
			System.out.println(count);
		}
	}
	
	public static void q6(Month m) {
		LocalDate val = LocalDate.of(LocalDateTime.now().getYear(), m, 1);
		int count = 0;
		while(val.getMonth() == m) {
			if(val.getDayOfWeek() == DayOfWeek.MONDAY) {
				count++;
			}
			val = val.plusDays(1);
		}
		System.out.println(count + " Mondays in " + m);
	}
	
	public static void q7(Month m, int d, int y) {
		LocalDate val = LocalDate.of(y, m, d);
		if(val.getDayOfWeek() == DayOfWeek.FRIDAY && val.getDayOfMonth() == 13) {
			System.out.println(m.getValue() + "/" + d + "/" + y + " is on Friday the 13th");
		}
		else {
			System.out.println(m.getValue() + "/" + d + "/" + y + " is not on Friday the 13th");
		}
	}

	public static void main(String[] args) {
		//Question 1 example
		q2();
		q4();
		q5(2019);
		q6(Month.JULY);
		q7(Month.NOVEMBER, 13, 2020);
	}
}
