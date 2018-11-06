package com.vandung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.model.Schedule;
import com.vandung.repository.ScheduleRepository;

@Service("ScheduleService")
@Transactional
public class ScheduleServiceImp implements ScheduleService{
	
	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public Schedule findById(Long id_schedule) {
		return scheduleRepository.getOne(id_schedule);
	}

	@Override
	public List<Schedule> findAll() {
		return scheduleRepository.findAll();
	}

	@Override
	public void update(Schedule schedule, Long id_schedule) {
		Schedule scheduleDB = scheduleRepository.getOne(id_schedule);
		scheduleDB.setTeam_customer(schedule.getTeam_customer());
		scheduleDB.setTeam_home(schedule.getTeam_home());
		scheduleDB.setTime(schedule.getTime());
		scheduleRepository.save(scheduleDB);
	}

	@Override
	public void deleteById(Long id_schedule) {
		scheduleRepository.deleteById(id_schedule);
	}

	@Override
	public void save(Schedule schedule) {
		scheduleRepository.save(schedule);
	}

}
