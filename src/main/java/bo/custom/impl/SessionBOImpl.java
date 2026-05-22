package bo.custom.impl;

import bo.custom.SessionBO;
import dao.DAOFactory;
import dao.custom.SessionDAO;
import dto.TherapySessionDTO;
import entity.Patient;
import entity.Therapist;
import entity.TherapyProgram;
import entity.TherapySession;
import exception.SchedulingConflictException;
import exception.ValidationException;
import util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class SessionBOImpl
		implements SessionBO {

	SessionDAO sessionDAO =
			(SessionDAO) DAOFactory
					.getInstance()
					.getDAO(DAOFactory.DAOTypes.SESSION);

	@Override
	public boolean saveSession(TherapySessionDTO dto) {
		validateSession(dto);
		assertNoScheduleConflict(dto, null);
		return sessionDAO.save(toEntity(dto));
	}

	@Override
	public boolean updateSession(TherapySessionDTO dto) {
		validateSession(dto);
		assertNoScheduleConflict(dto, dto.getSessionId());
		return sessionDAO.update(toEntity(dto));
	}

	@Override
	public boolean deleteSession(String id) {
		ValidationUtil.requireNonBlank(id, "Session ID");
		return sessionDAO.delete(id.trim());
	}

	@Override
	public TherapySessionDTO searchSession(String id) {
		ValidationUtil.requireNonBlank(id, "Session ID");
		TherapySession session = sessionDAO.search(id.trim());
		if (session == null) {
			return null;
		}

		return new TherapySessionDTO(
				session.getSessionId(),
				session.getSessionDate(),
				session.getSessionTime(),
				session.getPatient().getPatientId(),
				session.getTherapist().getTherapistId(),
				session.getTherapyProgram().getProgramId()
		);
	}

	@Override
	public List<TherapySessionDTO> getAllSessions() {
		List<TherapySessionDTO> dtoList = new ArrayList<>();
		List<TherapySession> list = sessionDAO.getAll();

		for (TherapySession session : list) {
			dtoList.add(new TherapySessionDTO(
					session.getSessionId(),
					session.getSessionDate(),
					session.getSessionTime(),
					session.getPatient().getPatientId(),
					session.getTherapist().getTherapistId(),
					session.getTherapyProgram().getProgramId()
			));
		}

		return dtoList;
	}

	private void validateSession(TherapySessionDTO dto) {
		if (dto == null) {
			throw new ValidationException("Session data is required");
		}

		ValidationUtil.requireNonBlank(dto.getSessionId(), "Session ID");
		if (dto.getSessionDate() == null) {
			throw new ValidationException("Session date is required");
		}
		if (dto.getSessionTime() == null) {
			throw new ValidationException("Session time is required");
		}
		ValidationUtil.requireNonBlank(dto.getPatientId(), "Patient ID");
		ValidationUtil.requireNonBlank(dto.getTherapistId(), "Therapist ID");
		ValidationUtil.requireNonBlank(dto.getProgramId(), "Program ID");
	}

	private void assertNoScheduleConflict(TherapySessionDTO dto, String ignoreSessionId) {
		boolean hasConflict = sessionDAO.hasTherapistConflict(
				dto.getTherapistId().trim(),
				dto.getSessionDate(),
				dto.getSessionTime(),
				ignoreSessionId
		);

		if (hasConflict) {
			throw new SchedulingConflictException("Therapist already has a session at this date/time");
		}
	}

	private TherapySession toEntity(TherapySessionDTO dto) {
		Patient patient = new Patient();
		patient.setPatientId(dto.getPatientId().trim());

		Therapist therapist = new Therapist();
		therapist.setTherapistId(dto.getTherapistId().trim());

		TherapyProgram program = new TherapyProgram();
		program.setProgramId(dto.getProgramId().trim());

		TherapySession session = new TherapySession();
		session.setSessionId(dto.getSessionId().trim());
		session.setSessionDate(dto.getSessionDate());
		session.setSessionTime(dto.getSessionTime());
		session.setPatient(patient);
		session.setTherapist(therapist);
		session.setTherapyProgram(program);

		return session;
	}
}
