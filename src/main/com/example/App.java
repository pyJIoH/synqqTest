package main.com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		
//		DataDao dataDao = new DataDao();
//		for (Data dataRow : dataDao.getAllData()) {
//			System.out.println(
//				"id: " + dataRow.getId() +
//				" startTime: " + dataRow.getStartTime() +
//				" attendees: " + dataRow.getAttendeeses()
//			);
//		}
		
	}
}
