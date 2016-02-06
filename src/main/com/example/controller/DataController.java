package main.com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.com.example.beans.Data;
import main.com.example.dao.DataDao;

@RestController
public class DataController {

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public List<Data> data() {
		List<Data> dataList = new DataDao().getAllData();
		return dataList;
	}
}