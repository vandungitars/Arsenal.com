package com.vandung.service;

import java.util.List;

import com.vandung.model.Schedule;

public interface ScheduleService {

	Schedule findById(Long id_schedule);
	List<Schedule> findAll();
	void save(Schedule schedule);
	void update(Schedule schedule, Long id_schedule);
	void deleteById(Long id_schedule);
}
