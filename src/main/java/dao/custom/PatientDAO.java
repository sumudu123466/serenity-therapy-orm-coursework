package dao.custom;

import dao.crud.CrudDAO;
import entity.Patient;

import java.util.List;

public interface PatientDAO
        extends CrudDAO<Patient,String> {

    List<Patient> getPatientsWithPrograms();

    List<Patient> getPatientsEnrolledInAllPrograms();
}