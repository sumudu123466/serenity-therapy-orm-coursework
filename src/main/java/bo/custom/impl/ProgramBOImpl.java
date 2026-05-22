package bo.custom.impl;

import bo.custom.ProgramBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dto.TherapyProgramDTO;
import entity.TherapyProgram;
import exception.RegistrationException;
import util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl
		implements ProgramBO {

	ProgramDAO programDAO =
			(ProgramDAO) DAOFactory
					.getInstance()
					.getDAO(DAOFactory.DAOTypes.PROGRAM);

	@Override
	public boolean saveProgram(TherapyProgramDTO dto) {
		validateProgram(dto);

		if (programDAO.search(dto.getProgramId().trim()) != null) {
			throw new RegistrationException("Program already exists: " + dto.getProgramId());
		}

		return programDAO.save(toEntity(dto));
	}

	@Override
	public boolean updateProgram(TherapyProgramDTO dto) {
		validateProgram(dto);
		return programDAO.update(toEntity(dto));
	}

	@Override
	public boolean deleteProgram(String id) {
		ValidationUtil.requireNonBlank(id, "Program ID");
		return programDAO.delete(id.trim());
	}

	@Override
	public TherapyProgramDTO searchProgram(String id) {
		ValidationUtil.requireNonBlank(id, "Program ID");
		TherapyProgram program = programDAO.search(id.trim());
		if (program == null) {
			return null;
		}

		return new TherapyProgramDTO(
				program.getProgramId(),
				program.getProgramName(),
				program.getDuration(),
				program.getFee(),
				program.getDescription()
		);
	}

	@Override
	public List<TherapyProgramDTO> getAllPrograms() {
		List<TherapyProgramDTO> dtoList = new ArrayList<>();
		List<TherapyProgram> list = programDAO.getAll();

		for (TherapyProgram program : list) {
			dtoList.add(new TherapyProgramDTO(
					program.getProgramId(),
					program.getProgramName(),
					program.getDuration(),
					program.getFee(),
					program.getDescription()
			));
		}

		return dtoList;
	}

	private void validateProgram(TherapyProgramDTO dto) {
		if (dto == null) {
			throw new RegistrationException("Program data is required");
		}

		ValidationUtil.requireNonBlank(dto.getProgramId(), "Program ID");
		ValidationUtil.requireNonBlank(dto.getProgramName(), "Program name");
		ValidationUtil.requireNonBlank(dto.getDuration(), "Program duration");

		if (dto.getFee() <= 0) {
			throw new RegistrationException("Program fee must be greater than zero");
		}
	}

	private TherapyProgram toEntity(TherapyProgramDTO dto) {
		TherapyProgram program = new TherapyProgram();
		program.setProgramId(dto.getProgramId().trim());
		program.setProgramName(dto.getProgramName().trim());
		program.setDuration(dto.getDuration().trim());
		program.setFee(dto.getFee());
		program.setDescription(dto.getDescription());
		return program;
	}
}
