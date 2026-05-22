package bo.custom.impl;

import bo.custom.TherapistBO;
import dao.DAOFactory;
import dao.custom.TherapistDAO;
import dto.TherapistDTO;
import entity.Therapist;

import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl
        implements TherapistBO {

    TherapistDAO therapistDAO =
            (TherapistDAO) DAOFactory
                    .getInstance()
                    .getDAO(DAOFactory.DAOTypes.THERAPIST);

    @Override
    public void saveTherapist(TherapistDTO dto) {

        therapistDAO.save(

                new Therapist(
                        dto.getTherapistId(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getPhone(),
                        dto.getSpecialization(),
                        dto.getAvailability()
                )
        );
    }

    @Override
    public void updateTherapist(TherapistDTO dto) {

        therapistDAO.update(

                new Therapist(
                        dto.getTherapistId(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getPhone(),
                        dto.getSpecialization(),
                        dto.getAvailability()
                )
        );
    }

    @Override
    public void deleteTherapist(String id) {

        therapistDAO.delete(id);
    }

    @Override
    public TherapistDTO searchTherapist(String id) {

        Therapist therapist =
                therapistDAO.search(id);

        return new TherapistDTO(
                therapist.getTherapistId(),
                therapist.getName(),
                therapist.getEmail(),
                therapist.getPhone(),
                therapist.getSpecialization(),
                therapist.getAvailability()
        );
    }

    @Override
    public List<TherapistDTO> getAllTherapists() {

        List<Therapist> therapists =
                therapistDAO.getAll();

        List<TherapistDTO> dtoList =
                new ArrayList<>();

        for (Therapist therapist : therapists){

            dtoList.add(

                    new TherapistDTO(
                            therapist.getTherapistId(),
                            therapist.getName(),
                            therapist.getEmail(),
                            therapist.getPhone(),
                            therapist.getSpecialization(),
                            therapist.getAvailability()
                    )
            );
        }

        return dtoList;
    }
}