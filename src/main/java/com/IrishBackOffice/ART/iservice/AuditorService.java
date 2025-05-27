package com.IrishBackOffice.ART.iservice;

import com.IrishBackOffice.ART.entities.Auditor;
import com.IrishBackOffice.ART.exceptions.MyException;

import java.util.List;
import java.util.UUID;

public interface AuditorService {
    Auditor save(Auditor auditor) throws MyException;
    List<Auditor> listarAuditores();
    Auditor findById(UUID id) throws MyException;
    Auditor editarAuditor(Auditor auditor) throws MyException;
    void eliminarAuditor(UUID id) throws MyException;
}
