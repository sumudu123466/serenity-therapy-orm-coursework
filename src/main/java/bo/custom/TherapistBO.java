package bo.custom;

import bo.superbo.SuperBO;
import dto.TherapistDTO;

import java.util.List;

public interface TherapistBO
        extends SuperBO {

    void saveTherapist(TherapistDTO dto);

    void updateTherapist(TherapistDTO dto);

    void deleteTherapist(String id);

    TherapistDTO searchTherapist(String id);

    List<TherapistDTO> getAllTherapists();
}